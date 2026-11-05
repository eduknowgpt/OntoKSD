"""Offline query regression. Reads project files; never contacts GraphDB or loads imports."""
from pathlib import Path
import argparse,csv,hashlib,json,tempfile
import rdflib
from rdflib import Graph, Dataset, Namespace, URIRef, RDF
from rdflib.plugins.sparql.parser import parseQuery, parseUpdate

parser=argparse.ArgumentParser(description=__doc__)
parser.add_argument('--project-root',type=Path,default=Path(__file__).resolve().parents[3])
parser.add_argument('--output',type=Path,default=None)
parser.add_argument('--as-of',default='2026-11-01T12:00:00Z')
args=parser.parse_args()
cq=Path(__file__).resolve().parents[1]
project=args.project_root.resolve()
report=args.output or Path(tempfile.mkdtemp(prefix='ontoksd-cq-validation-'))
report.mkdir(parents=True,exist_ok=True)
ontology=project/'ontoKSDOntology'/'OntoKSD - SKOS - v7.0.ttl'
inputs=[ontology]+[project/'proficiency level'/name for name in ('01 - ProficiencyLevel.rq','02 - Proficiency_Instances.rq','03 - Gap_Analisys.rq')]
for p in inputs:
 if not p.is_file(): raise FileNotFoundError(f'Missing project input: {p}; set --project-root to the OntoKSD directory.')
o=Namespace('http://purl.org/ontoksd#')
def text(p):return p.read_text(encoding='utf-8-sig')
def find(id):return next(cq.glob(id+' - *.rq'))
def freeze(s):return s.replace('BIND(NOW() AS ?asOf)','VALUES ?asOf { "'+args.as_of+'"^^xsd:dateTime }')
files=sorted(cq.glob('CQ*.rq'))
assert len(files)==58,'Update the manifest and assertions if the query set changes.'
for p in files:
 (parseUpdate if p.name.startswith('CQ39b - ') else parseQuery)(text(p))
g=Graph().parse(ontology,format='turtle')
for p in inputs[1:]:g.update(text(p))
answers={};summary=[]
for p in files:
 id=p.name.split(' - ')[0]
 if id=='CQ39b':continue
 rows=list(g.query(freeze(text(p))));answers[id]=rows
 summary.append({'query':id,'operation':'SELECT','rows':len(rows),'dataset':'local ontology + proficiency examples 01-03; no OWL inference','as_of':args.as_of+' (where applicable)'})
assert len(answers)==57
assert answers['CQ18'] and answers['CQ20c'] and answers['CQ30']
assert answers['CQ26']==answers['CQ32a']
assert answers['CQ39a']==answers['CQ40b']==answers['CQ42b']
for id in ('CQ39a','CQ40b','CQ42b'):
 assert {r.competence for r in answers[id]}=={o.Competence_DFAConstruction}
 assert all(r.evidencedOrdinal.toPython()==3 and r.requiredOrdinal.toPython()==4 for r in answers[id])
for id in ('CQ27','CQ40a','CQ40d','CQ42c'):assert not answers[id],id
assert {r.constituent for r in answers['CQ40c']}=={o.KnowledgeSkill_DFAConstruction}
fixture=Path(__file__).resolve().with_name('activation-regression.ttl')
h=Graph().parse(ontology,format='turtle').parse(fixture,format='turtle')
for id in ('CQ14','CQ15','CQ16','CQ17'):assert list(h.query(text(find(id)))),id
unit=URIRef('http://purl.org/ontoksd/test/activation-regression#unlinkedUnit')
assert any(r.instructionalUnit==unit and r.competenceActivation is None for r in h.query(text(find('CQ17'))))
fixture_inputs=[fixture]
for family in ('CQ11','CQ27'):
 folder=cq/'tests'/family
 data=folder/'01-load-synthetic-data.rq'
 ds=Dataset(default_union=True);ds.update(text(data))
 assert len(list(ds.query(text(find(family)))))==3
 scoped=list(ds.query(text(folder/('02-run-'+family+'-on-synthetic-data.rq'))))
 assert len(scoped)==3
 checks=list(ds.query(text(folder/'03-check-expected-results.rq')))
 assert len(checks)==(6 if family=='CQ11' else 5) and all(r.passed.toPython() is True for r in checks)
 fixture_inputs.extend(folder.glob('*.rq'))
# Optional UPDATE is evaluated only on this disposable in-memory graph.
before=set(g.subjects(RDF.type,o.LearningNeed));g.update(freeze(text(find('CQ39b'))))
assert len(set(g.subjects(RDF.type,o.LearningNeed))-before)==1
size=len(g);g.update(freeze(text(find('CQ39b'))));assert len(g)==size
with (report/'local-results.csv').open('w',encoding='utf-8',newline='') as f:
 w=csv.DictWriter(f,fieldnames=summary[0].keys());w.writeheader();w.writerows(summary)
def digest(p):return hashlib.sha256(p.read_bytes()).hexdigest()
metadata={'revision_date':'2026-11-01','engine':'RDFLib '+rdflib.__version__,'parsed_files':58,'executed_select_files':57,'imports_fetched':False,'owl_inference':False,'graphdb_executed':False,'all_assertions_passed':True,'fixed_comparison_time':args.as_of,'input_sha256':{p.name:digest(p) for p in inputs},'fixture_sha256':{str(p.relative_to(cq)):digest(p) for p in fixture_inputs},'query_sha256':{p.name:digest(p) for p in files}}
(report/'local-validation.json').write_text(json.dumps(metadata,indent=2),encoding='utf-8')
print('PASS: 58 files parsed; 57 SELECTs executed; expected empty/positive cases, aliases, activation fixtures, CQ11/CQ27 and local-only materialization verified.')
print('Reports:',report.resolve())
