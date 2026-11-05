# Cycle 2 historical assessment records

This layer reconstructs documented Task201 and Task202 assessment records from the revised MATC94 PBL 2022 source collection. Task202 is the principal documentary example; Task201 is complementary. It separates qualitative process observations, numeric process/product summaries and their final arithmetic means. It does not create automatic competence or constituent judgments.

## Load

After the OntoKSD schema, vocabulary dependencies and the cycle-02 planning graph, choose one method:

1. Load `cycle02-historical-assessments.ttl` for both historical tasks; or
2. load `task201/task201-records.ttl` and `task202/task202-records.ttl`; or
3. execute the equivalent INSERT-only scripts in those two directories.

The methods contain the same historical triples. Do not treat them as different datasets. Re-execution inserts the same RDF statements. The historical layer does not replace the schema or the planning graph and is distinct from `cycle-02-operational-layer` assumptions.

**Previously loaded history:** INSERT does not remove earlier statements. Reload this layer in an empty dedicated instance dataset, or replace its previously loaded named graph using the repository's normal graph-management workflow. Adding it over the earlier Task201 layer would retain obsolete evidence-origin links and descriptions. No destructive update is included in this package.

## Coverage

| Record | Task201 | Task202 |
| --- | ---: | ---: |
| Learner identities referenced within the case | 34 | 32 |
| Scored process participations | 34 | 32 |
| Scored product participations | 34 | 32 |
| Final-result consolidations | 34 | 32 |
| Linked questionnaire participations | 27 | 24 |
| Questionnaire evidence references | 27 | 25 |
| Group process/product evidence views | 14 | 14 |
| Assessment specifications / occurrences | 4 / 4 | 4 / 4 |
| Whole-competence or constituent judgments | 0 | 0 |

The combined layer has 249 participation records, 80 evidence references and eight assessment occurrences. These are counts of RDF records, not numbers of independent assessments or validators. Final means consolidate process and product scores; they are not third independent observations. Questionnaire participation records have no hasScore.

Twenty-seven verified learner identities are reused between the two tasks. Five Task202-specific codes are retained where no direct cross-task identity was reconstructed. The combined 39 learner IRIs therefore do not establish 39 different people: unresolved cross-task identities may denote the same person. No AllDifferent or speculative identity equivalence is asserted.

## Source authority

The revised collection is the principal documentary basis, as identified by the author. `Notas.xlsx` supplies official process, product and mean values. The detailed and condensed grade spreadsheets agree on task results. Values in tutor reviews are preserved separately as documentary descriptions; the Task202 comparison is also available in `documentary-score-comparison.csv`. The reason or sequence of changes is not invented.

The complete CSV exports contain 27 Task201 and 25 Task202 submissions. Their older copies in Autoavaliação contain 24 and 19 respectively and are exact subsets. Only the complete exports are used as questionnaire references. ZIP copies, consolidated text and duplicate document formats are not counted as independent evidence.

`SOURCES.md` lists restricted sources by relative path and SHA-256. The package does not bundle grade workbooks, student artifacts, response contents, names, email addresses, academic identifiers or private identity maps. Pseudonyms are not a guarantee of irreversible anonymity.

## Interpretation

The process assessment concerns knowledge mobilization and dispositions. Tutor observations are qualitative; a process score summarizes that assessment. The product score concerns submitted artifacts. Every numeric result is an xsd:decimal on AssessmentParticipation, with one score per record. Sources and dimensional input records are connected through prov:wasDerivedFrom. Administrative consolidation is not asserted to be another competence demonstration.

The two coded assessors represent the tutors credited in the records. Their involvement does not establish independent ratings, agreement, authorship of every comment or observation of every session. Original session dates and attendance are not fabricated from a planned schedule.

Evidence views reference the appropriate historical assessment occurrence. Original submitted artifacts remain Document references: their production is not attributed to the later product-assessment event. The group diary is student-authored, while the tutor review has a different provenance. Associating either with a profile does not establish individual competence attainment.

Historical specifications are related to adjusted CSP planning through dcterms:relation, not owl:sameAs. The 2022 observations are not presented as assessments originally performed using the current OntoKSD vocabulary. The source problem's inconsistent delivery dates are not silently resolved or used as actual event times.

## Pending reconciliation

- The three previously unlocated Task201 questionnaire references now have matching timestamps and groups in the complete export; none remains unlocated.
- Seven legacy Task201 learner codes preserve their recorded group membership and the group's reconciled official dimension values without an independently reconstructed nominal code-to-person link.
- One Task202 questionnaire submission is represented as evidence without an inferred learner participation.
- Two official Task202 0/0 administrative results remain in `PENDING.md`, without fabricated observed participation, attendance, absence or supporting artifacts.
- Group 2 has a review with unfilled qualitative fields and no delivery located in this source collection. Its documentary existence is represented without inventing observations.

The original 204 proxy judgments are not restored. Historical proficiency thresholds were not verified in the primary sources, so planning or operational scheme intervals do not automatically classify these scores.

## Queries and verification

Each task folder contains queries for final results, evidence documents, questionnaire references, process/product/mean comparison and mean violations. Shared queries inspect source provenance, assessor involvement and the unresolved questionnaire linkage. `VALIDATION.md` reports their executed row counts against this layer alone. Queries may return additional records when other datasets are combined; checks must retain their stated scope.

`VALIDATION.md` reports local RDF, datatype, cardinality, provenance, source-score, query and INSERT equivalence checks. It distinguishes those checks from full OWL DL reasoning with all imports and from independent human evaluation. These historical student assessments demonstrate a documentary application; the tutors and students are not counted as evaluators of OntoKSD.

## Batch insertion

The task directory contains one INSERT script; its read-only SELECT files are in `queries/`. A non-recursive batch updater may execute the task directory, but SELECT queries must be run in the query interface. Choose the RDF-loading or INSERT method rather than treating their equivalent files as different layers.

## Turtle and SPARQL formats

The `.ttl` file is valid Turtle for RDF import. The `.rq` file contains the same grouped individuals inside `INSERT DATA`, using SPARQL `PREFIX` declarations. `INSERT DATA` is not Turtle syntax and therefore is not placed inside a `.ttl` file. Choose the format appropriate to the loading method.
