# CSP cycle 04 — OntoKSD instance dataset

This dataset represents the introductory-programming competence catalog and instructional specifications documented in [CSP cycle 04](https://github.com/eduknowgpt/OCSP/tree/main/CSP/cycles/cycle-04). It preserves the current reports, the authoritative TASK401 adjustment, within-cycle reuse and qualified external alignment.

The package contains **23 active competence identities**, **11 reference tasks**, **11 learning tasks**, **11 assessment specifications** and **60 current direct task–competence associations**. Each association is represented separately for learning and assessment, giving **120 competence activations**. These are prospective specifications, not observed learner attainment.

## Competence catalog

| Identifier | Reusable specification | Structure |
|---|---|---|
| CM01 | Analyze computational requirements | Atomic |
| CM02 | Maintain program data and state | Atomic |
| CM03 | Implement decisions with Boolean conditions | Atomic |
| CM04 | Implement repetition with a stopping condition | Atomic |
| CM05 | Implement input and output interaction | Atomic |
| CM06 | Validate inputs and operation preconditions | Atomic |
| CM07 | Organize a solution into functions | Atomic |
| CM08 | Verify program behavior with test cases | Atomic |
| CM09 | Diagnose a program failure | Atomic |
| CM10 | Explain a computational solution | Composite |
| CM11 | Trace program execution | Atomic |
| CM12 | Explain arithmetic expression evaluation | Atomic |
| CM13 | Explain data organization | Atomic |
| CM14 | Explain conditional execution | Atomic |
| CM15 | Explain repeated execution | Atomic |
| CM16 | Explain function organization | Atomic |
| CM17 | Explain variable updates | Atomic |
| CM18 | Explain variable updates and arithmetic evaluation | Composite |
| CM19 | Rearrange formulas to isolate a quantity | Atomic |
| CM20 | Implement numeric calculations | Atomic |
| CM21 | Validate input values and operation conditions | Atomic |
| CM22 | Interpret physical quantities and their relationships | Atomic |
| CM23 | Implement a function with parameters and a return value | Atomic |

All active CM identities originate in cycle 04. The 60 direct associations comprise 18 creation associations and 42 within-cycle reuse associations. Three atoms—CM13, CM16 and CM17—are component-only: they have no fabricated direct task association. CM14 and CM15 originate in the TASK401 adjustment and also have direct reuse elsewhere. Reuse here does not mean importation of competence identities from cycles 01–03.

## Reference tasks and instruction

| Identifier | Subject | Current direct associations |
|---|---|---:|
| TASK401 | Tic-Tac-Toe | 11 |
| TASK402 | Variable Manipulation | 2 |
| TASK403 | Average Velocity | 9 |
| TASK404 | Area of Regular Polygons | 4 |
| TASK405 | Factorials Using Functions and Loops | 6 |
| TASK406 | School Census | 5 |
| TASK407 | Prime Numbers and Smallest Divisors | 7 |
| TASK408 | Independent IF Statements and IF-ELSE | 1 |
| TASK409 | Excess Fishing Weight | 4 |
| TASK410 | Football Preferences Survey | 5 |
| TASK411 | Finding the Snake in a Grid | 6 |

`Task` describes reference performance. Separate `LearningTask` and `Assessment` instances represent instructional and evaluative planning. `hasInstructionalStatement` preserves independent requirements such as deliverables, input assumptions, coordinate conventions, optional checks and individual accountability. The source task may serve as a specification reference for an atom without that atom receiving a direct activation in its origin task.

The 46 mandatory, eight optional and six conditional source associations retain their roles and concurrent modes. The same source qualifications are recorded for both planned instructional and assessment associations. This modeling does not assert that an assessment was administered or that optional/conditional performances occurred.

Only TASK401 explicitly establishes team-based work in the represented specification. Its adjustment requires functions and a coordinated individual explanation prospectively. The other tasks retain their own deliverable limits; no general oral defense or universal implementation requirement is added.

## Constituents, composition and verbs

The competences use `CC2020SpecificationPattern` with revised Bloom level individuals. This describes their knowledge–level/disposition organization, not authorship in CC2020 or formal validation by a reviewer. Each `KnowledgeSkill` has exactly one knowledge component and one level.

There are **44 distinct KnowledgeSkill instances** and **54 competence–pair associations**. CM10 and CM18 reuse the exact pairs of their complete atomic components rather than duplicating or selecting subsets of them:

- CM10 integrates CM13, CM14, CM15, CM16 and CM17, with seven knowledge–Understanding pairs.
- CM18 integrates CM17 and CM12, with three knowledge–Understanding pairs.

The seven composition links are separate from task counts. Integration descriptions explain the coordinated performance expected beyond isolated answers. Composition does not aggregate learner judgments automatically or raise Understanding to Analyzing.

The vocabulary includes 15 source-local knowledge items: 13 programming curricular topics and two interdisciplinary topics for TASK403. K15 (code comments) remains a curricular reference without an unsupported constitutive level assignment. No K19 is introduced. Curricular SKOS concepts remain distinct from knowledge constituents. No equivalence to a previous-cycle knowledge identifier or to a CS2023 topic is inferred merely from a similar label.

There are **53 pair–verb links** through `hasBloomLevelJustificationVerb`. Leading operative verbs and explicitly coordinated operations are extracted from the source observable-operation statements. This is a documented local extraction, not a claim that every word in an operation is a justification verb. Missing vocabulary entries receive contextual declarations. A verb does not determine a level automatically or establish an official/exclusive Bloom placement.

Existing disposition values are reused from the OntoKSD disposition vocabulary. `cspOrganized` and `cspConsiderate` preserve source orientations without equating them to other dispositions. The supplementary modes `amOperational`, `amEvaluative` and `amExplanatory` retain the source terminology; they are controlled ActivationMode individuals, not new ontology classes.

## Qualified external alignment

`02 - Cycle04 External Alignments.rq` contains **69 source-reported qualified correspondences**. Each record has one internal competence, one external reference, one AlignmentType and a textual justification. Broad GC/CE contributions are distinguished from narrower or partial skill-code correspondences. Digital-presentation and explicit-evaluation conditions remain stated in the justification; OWL does not enforce them automatically.

GC and CE references use BNCCGeneralCompetence and BNCCSpecificCompetence respectively. The skill-coded items EM13CO02, EM13CO06 and EM13CO19 use an explicitly documented **ExternalCompetence adapter** to fit the current alignment range, while retaining their source category as skills. They are not classified as BNCC general or specific competences. Identifying labels do not reproduce a full normative statement. These records preserve the reports' interpretation; this package is not an independent normative validation or a declaration of full coverage, identity or equivalence.

## Files and loading

Load the current OntoKSD ontology and the Bloom skill-level module, then the disposition vocabulary and the action-verb/type vocabulary from the OntoKSD project. **Prior-cycle instance datasets are not required.**

Execute the INSERT-only files in this practical order:

1. `00 - Cycle04 Sources and Vocabulary.rq` — source references, knowledge and supplementary controlled values.
2. `01 - Cycle04 Justification Verbs.rq` — contextual verb declarations.
3. All 23 files in `competencies/` — complete specifications, including fixed composite unions.
4. All 11 files in `tasks/` — reference tasks, requirements, planned assessments, contexts and qualified activations.
5. `02 - Cycle04 External Alignments.rq` and `03 - Cycle04 Documentary History.rq` — qualified correspondences and source-reported documentary history.

Alternatively, load **`cycle04-instances.ttl`** after the same ontology/vocabulary dependencies. It contains the union of all insertion files, including alignments and history; it does not bundle the ontology or its dependencies. The INSERT files contain no deletions. Repeating an insertion was tested for graph idempotence.

Run the five files in `queries/` to inspect activations, pairs/verbs, composition, structural cycles and external alignments. Query rows may multiply for concurrent modes, several verbs or pair reuse. They are not automatically counts of distinct source associations; see `query-results.tsv`.

`source-references.tsv` identifies actual archive files and calculated hashes. `source-audit.tsv` compares source-declared hashes with the corresponding supplied report files. `validation-checks.tsv` lists the executed checks; `VALIDATION.md` summarizes their scope. The package contains no JSON files and no copies of CSP source documents.

## Documentary history and scope

The history file retains 71 source-reported lifecycle records and two historical association records as documentary PROV entities, not assessment events or formal approvals. The former TASK402–CM10 association is replaced by CM18. CM24 is preserved only as a withdrawn identifier in documentary records; it is not instantiated as a current competence and has no current pair or activation.

Course aggregation expresses proposed organization around the Algorithms and Programming Logic course plan. It does not certify delivery of all tasks in one class, institution or year. The package creates no course-level mandatory competence specification, proficiency scheme, score interval, learner profile, assessment occurrence, participation, evidence record or individual competence judgment. The syllabus's recovery-assessment scale and 60% recovery condition are not converted into general competence-proficiency thresholds.

**Provenance limitation:** the archive lacks the declared `evidence/` snapshots and conversation decision note. Corresponding current reports were identified under `en/`; 160 hash comparisons differ, and three references to the missing note cannot be checked. The package records actual references and hashes without certifying the CSV-declared source versions. See `REVISAO.md` for interpretation and limitations.
