# Instantiation validation

- **1515 checks passed** for the generated instance dataset.
- All 38 insertion files executed as SPARQL INSERT DATA. Repeating each insertion introduced no additional graph triples; file-order independence was checked.
- 23 competence identities; 44 unique knowledge–level pairs; 54 competence–pair links; 53 distinct pair–verb links.
- 60 current source associations represented by 120 activations and 120 qualified constraints; 46 mandatory, eight optional and six conditional source associations.
- Seven fixed composition links, complete component unions and explicit distinctness of component individuals.
- 69 qualified external alignments; no equivalence or identity axioms introduced.
- Source operation text checked for all 44 atomic pair definitions against the actual archive reports.
- OWL RL inference found no tested disjointness conflict or owl:Nothing instance in the local schema/vocabulary/data closure. Generic constituent inference and separation of Knowledge from KnowledgeSkill were checked.
- No learner-specific records, assessment occurrences, scores or proficiency intervals were manufactured.

The combined instance graph contains **4307 triples**. Ontology and vocabulary dependencies were loaded for validation and are not included in this count or in the instance TTL.

## Query results

| Query | Rows |
|---|---:|
| 01-current-associations.rq | 140 |
| 02-knowledge-level-verbs.rq | 63 |
| 03-composition.rq | 7 |
| 04-structural-cycles.rq | 0 |
| 05-external-alignments.rq | 69 |

The activation query has one row per mode, not one per source association. The pair query repeats shared pairs when referenced by a composite and its atom, and expands multiple verbs. Positive-path structural-cycle inspection returned zero rows in the examined data.

## Provenance audit is separate

The instance checks passed; **source-declared hash integrity did not pass**. There are 160 divergent comparisons and three uncheckable references to a missing conversation note. Those are documentary-reference comparisons, not 160 distinct files. See source-audit.tsv and REVISAO.md.

Reasoning used the supplied local OntoKSD artifact and local Bloom/vocabulary dependencies, not a full externally resolved import closure. HermiT and Pellet were not executed. These results do not certify OWL DL consistency, pedagogical validity, normative compliance or learner attainment.

Current OntoKSD input SHA-256: `bd65ede578b5524a0102a05c590ef55c756353bab7d3bcc15fcc6f64300563e4`.
Archive input SHA-256: `989f6268eea935a4fab049502dffff678ac02a669bfec2e37f0af9dba599ef97`.
