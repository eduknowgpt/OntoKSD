# IS2020 Competency Specifications in OntoKSD

This directory represents the competencies in **Appendix 3 — Competencies, Knowledge-Skill Pairs and Dispositions by Competency Area** of *IS2020: A Competency Model for Undergraduate Programs in Information Systems*, published by the Joint ACM/AIS IS2020 Task Force in December 2020.

The dataset covers **178 competences across 19 competency areas**, with **688 knowledge–level pairings**, **650 reusable Knowledge instances**, and the source-listed key dispositions. It supports querying and reusing curricular expectations within OntoKSD. These are specifications of expected performance, not records of learner attainment or assessment evidence.

## Competency areas

There is one insertion file per area. Each row links to that area's file. The prefix used in competence identifiers distinguishes areas whose local competency numbers restart at 1.

| Area | Competences | Pairings | Insertion file |
| --- | ---: | ---: | --- |
| Foundations of Information Systems | 8 | 44 | [foundations](is2020_foundations_competencies_insert.rq) |
| Data / Information Management | 6 | 16 | [data_information_management](is2020_data_information_management_competencies_insert.rq) |
| Data / Business Analytics | 7 | 18 | [data_business_analytics](is2020_data_business_analytics_competencies_insert.rq) |
| Data / Information Visualization | 7 | 14 | [data_information_visualization](is2020_data_information_visualization_competencies_insert.rq) |
| IT Infrastructure | 9 | 33 | [it_infrastructure](is2020_it_infrastructure_competencies_insert.rq) |
| Secure Computing | 8 | 22 | [secure_computing](is2020_secure_computing_competencies_insert.rq) |
| Emerging Technologies | 8 | 26 | [emerging_technologies](is2020_emerging_technologies_competencies_insert.rq) |
| Systems Analysis and Design | 8 | 49 | [systems_analysis_and_design](is2020_systems_analysis_and_design_competencies_insert.rq) |
| Application Development and Programming | 16 | 59 | [app_dev_programming](is2020_app_dev_programming_competencies_insert.rq) |
| Object-Oriented Paradigm | 9 | 40 | [object_oriented_paradigm](is2020_object_oriented_paradigm_competencies_insert.rq) |
| Web Development | 13 | 63 | [web_development](is2020_web_development_competencies_insert.rq) |
| Mobile Development | 13 | 52 | [mobile_development](is2020_mobile_development_competencies_insert.rq) |
| User Interface Design | 5 | 25 | [user_interface_design](is2020_user_interface_design_competencies_insert.rq) |
| IS Ethics, Sustainability, Use and Implications for Society | 9 | 38 | [is_ethics_sustainability_society](is2020_is_ethics_sustainability_society_competencies_insert.rq) |
| IS Management and Strategy | 11 | 32 | [is_management_and_strategy](is2020_is_management_and_strategy_competencies_insert.rq) |
| Digital Innovation | 7 | 24 | [digital_innovation](is2020_digital_innovation_competencies_insert.rq) |
| Business Process Management | 7 | 20 | [business_process_management](is2020_business_process_management_competencies_insert.rq) |
| IS Project Management | 15 | 66 | [is_project_management](is2020_is_project_management_competencies_insert.rq) |
| IS Practicum | 12 | 47 | [is_practicum](is2020_is_practicum_competencies_insert.rq) |

## Files

- `00 - IS2020 Source and Pattern.rq`: shared source documentation and the controlled `IS2020SpecificationPattern` individual.
- `is2020_*_competencies_insert.rq`: knowledge descriptions, competence specifications, and knowledge–level pairings for each area.
- [competency-examples.md](competency-examples.md): readable competence statements, key dispositions, and source pairing tables, including source anomalies.
- [source-requirements.json](source-requirements.json): structured source requirements for all competencies.
- [source-pair-mapping.tsv](source-pair-mapping.tsv): correspondence between source table rows, pair IRIs, knowledge instances, and level individuals, with interpretation notes.
- [source-issues.json](source-issues.json): the identified source ambiguities, duplication, and omission.
- `queries/`: queries for requirements, area counts, and source interpretation notes.
- [validation.json](validation.json): validation results, limitations, and fingerprints of the reference files.

## Representation process

### 1. Identify each competence and its source

Each numbered competency becomes a `Competence` with a reusable IRI and an area-qualified identifier, such as `ISF-1`. The source competency statement is recorded in `skos:definition`, with whitespace normalized and an English language tag.

A separate documentary `Source_*` instance records the source's competency identifier, stated key dispositions, and the starting printed/PDF page of the competency table. The competence and its pairings refer to this record through `prov:wasDerivedFrom`. Source records are `prov:Entity` instances; they are not extra competences or learner records.

Competence labels and knowledge labels may be editorial descriptions. Exact source knowledge wording and the original number/label skill-level cells are retained in the mapping files and in `dcterms:description` on each pairing.

### 2. Use the IS2020 pattern explicitly

All competences use:

```turtle
ontoksd:hasSpecificationPattern is2020:IS2020SpecificationPattern .
```

This controlled value is typed as `ontoksd:SpecificationPattern`. It expresses the IS2020 arrangement of explicit knowledge–Bloom-level pairs and source-listed key dispositions. It extends the set of pattern values using the existing class; it does not add a new ontology class or property.

The competences are not forced into `CC2020Competence` or `CS2023Competence`. In the current OntoKSD implementation, those classes require a separate `Task`. Appendix 3 provides competence statements and paired requirements without a separate reference-task field. No task is invented merely to meet a different pattern's restriction.

The presence of this custom pattern value does not provide a new OWL equivalent-class definition or automatically impose structural cardinalities. Completeness for this dataset is checked explicitly by validation. It should not be claimed that the schema alone enforces the entire IS2020 profile.

### 3. Preserve knowledge–level pairings

Unlike the independent knowledge and level lists in the CS2023 examples, IS2020 pairs knowledge with a level in each table row. Each represented row therefore becomes a `KnowledgeSkill` constituent with exactly one knowledge reference and one skill-level individual:

```turtle
is2020c:KS_ISF_1_01
    a ontoksd:KnowledgeSkill ;
    ontoksd:hasKnowledgeComponent
        <http://purl.org/is2020/knowledge#ComponentsOfISTechnologyDataPeopleProcedures> ;
    ontoksd:hasSkillLevel bloom:Understanding ;
    ontoksd:isKnowledgeSkillOf is2020c:Competence_ISF_1 .
```

`hasKnowledgeSkill` attaches the qualified constituent to its competence. `hasKnowledge` additionally provides the direct knowledge association already used by this dataset; it does not replace the level-bearing pairing. When counting constituents, knowledge instances and pairings are different entities. Use the appropriate relation and `DISTINCT` for the question being asked.

Knowledge descriptions are curated content references drawn from Appendix 3. They are not a separate official IS2020 body-of-knowledge taxonomy. The dataset also organizes these descriptions as SKOS concepts in area-specific schemes. SKOS classification supports vocabulary organization; it does not turn a knowledge reference into an observed skill or a learner achievement. Shared knowledge instances may appear in several area schemes.

### 4. Map the source labels to the imported Bloom vocabulary

| Source label | Level individual |
| --- | --- |
| 1 — Remember | `bloom:Remembering` |
| 2 — Understand | `bloom:Understanding` |
| 3 — Apply | `bloom:Applying` |
| 4 — Analyze | `bloom:Analyzing` |
| 5 — Evaluate | `bloom:Evaluating` |
| 6 — Create | `bloom:Creating` |

The individuals belong to `bloom:CC2020SkillLevel`, the existing six-category scheme class in the imported module. Reusing those level individuals does not classify an IS2020 competence as `CC2020Competence` or identify the two curricular frameworks.

Assignments follow the source pairing tables. They are not recalculated from the main verb of a competence statement. The source spellings “Analysis” and “Appy” are interpreted as Analyzing and Applying respectively, with the original text retained in documentary annotations.

### 5. Reuse key dispositions

The eleven disposition values reuse the existing OntoKSD individuals provided by `cc2020Vocabulary/ontoKSD-disposition-insert.rq`. They are not redeclared inside these insertion files. IS2020 itself references the CC2020 disposition list in Table A3-2.

Only dispositions emphasized in each competency table are asserted. The absence of a value is not a statement that it is irrelevant: Appendix 3 explicitly distinguishes key dispositions from the broader disposition set.

### 6. Keep structural classification separate

The instances use `hasStructureType Atomic` and are explicitly typed `AtomicCompetence`. This is a local specification convention: each numbered statement is represented without component competences. Atomic does not mean that its performance involves a single action, a single knowledge element, or a simple learning task.

No composition, specialization, instructional activation, assessment occurrence, evidence record, or proficiency score is generated from the source table alone.

## Source interpretation notes

The following cases are preserved explicitly rather than silently rewritten:

| Competence | Source observation | Representation policy |
| --- | --- | --- |
| `ISDIV-7` | “The art of the narrative” has **5 — Analyze**, printed p. 111. | Use `bloom:Analyzing`, following the textual label. Preserve the original number 5 and flag the conflict with Table A3-1. |
| `ISMS-4` | “Evaluation and monitoring techniques for IT operations” has **4 — Evaluate**, printed p. 161; its competency starts on p. 160. | Use `bloom:Evaluating`, following the textual label. Preserve the original number 4 and flag the conflict. |
| `ISF-7` | The same network-transmission knowledge row appears twice, printed p. 100. | Preserve both rows in the source transcription; represent the repeated knowledge–level requirement once. |
| `ISET-3` | Its competency table has no Key Dispositions entry, printed p. 120. | Preserve the omission; do not invent dispositions or assert that none can apply. |

The number/label decisions are local interpretations, not official errata. Consequently, 689 transcribed source table rows correspond to 688 pairings. The dataset is usable with these disclosed interpretation choices; the two ambiguous source cells do not permit an unqualified claim of numerical and textual agreement with the source simultaneously.

## Loading and use

1. Load the current OntoKSD ontology and its skill-level module, resolving ontology imports for your environment.
2. Execute `cc2020Vocabulary/ontoKSD-disposition-insert.rq` in the intended instance dataset.
3. Execute `00 - IS2020 Source and Pattern.rq`.
4. Execute one or more area insertion files. Area files can be loaded in any order.

All operations are `INSERT DATA`. Re-execution inserts the same named resources and triples, so it does not create duplicate instances. They contain no deletion operations. Updates target the default graph of the selected SPARQL dataset; choose the intended instance graph/environment before executing them.

The `.rq` files are SPARQL Update operations, not Turtle ontologies. To inspect the instances in Protégé, execute them in an update-capable environment and load/import a Turtle export of the resulting instance graph along with its ontology and vocabularies. The instance dataset is separate from the schema ontology.

## Validation and limits

The delivered dataset passed 5,706 automated checks; the three supplied queries were also executed (688 pairings, one source omission, and two ambiguous level cells).

Validation compares the source PDF with the supplied transcription, pairs every source row with its RDF representation, checks the knowledge/level references and source-listed dispositions, and executes the insertions. It also checks idempotence, loading order, preferred-label consistency, generic constituent/inverse relationships, and atomic classification under OWL RL inference.

OWL RL validation does not replace a full OWL DL consistency test with HermiT or Pellet. Dataset-level checks are necessary because OWL's open-world semantics do not establish that documentary requirements have all been explicitly supplied. The source omissions and conflicts above remain documentary limitations, not logical contradictions that an OWL reasoner can resolve.

The dataset represents curriculum expectations. Its existence does not establish course coverage, instructional adequacy, learner participation, or demonstrated competence.

## Namespaces and source

| Prefix | Namespace | Purpose |
| --- | --- | --- |
| `ontoksd` | `http://purl.org/ontoksd#` | Ontology vocabulary and reusable disposition values |
| `is2020` | `http://purl.org/is2020#` | Source document and the IS2020 pattern value |
| `is2020c` | `http://purl.org/is2020/competency#` | Competences, pairings, and documentary source records |
| `is2020k` | `http://purl.org/is2020/knowledge#` | Curated knowledge references and SKOS schemes |
| `bloom` | `http://purl.org/edu/bloom#` | Imported Bloom level individuals |

Source: Joint ACM/AIS IS2020 Task Force, *IS2020: A Competency Model for Undergraduate Programs in Information Systems*, December 2020, Appendix 3. The supplied PDF is the reference used for the checks; printed page numbers differ from PDF page numbers by one.
