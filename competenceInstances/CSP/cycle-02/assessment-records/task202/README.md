# Task202 historical assessment

Task202 is the principal documentary example of cycle-02 assessment. Its sources include seven tutor reviews, six reports, eight JFLAP models, a student-authored group diary, official grades and 25 individual questionnaire submissions. Original source contents remain restricted; the RDF includes coded references, source hashes and group-level paraphrases.

Load `task202-records.ttl` or execute the equivalent `task202-records.rq`, following the [historical-layer instructions](../README.md). The case contains four distinct assessment specifications and aggregate occurrences: process, product, final-result consolidation and the complementary questionnaire. Exact session dates, attendance and release times are not invented.

## Official results

Thirty-two learners were conservatively matched from review rosters to the grade sheet. Each has separate process, product and final participation records: 96 scored records in total, all xsd:decimal. The mean equals (process + product) / 2. The final record links to the two input records through prov:wasDerivedFrom and is not another performance observation. Both tutors are coded on process/product participations; no independent-rater interpretation is asserted.

The official spreadsheet contains two further 0/0 results not linked to the review rosters. They remain in the shared `../PENDING.md` as administrative results; they are not turned into observed participations, absences or invented artifact evidence.

Twenty-seven confirmed learner identities are reused from Task201. Five task-specific codes do not assert identity or difference from unresolved Task201 codes. Counts of learner IRIs across the two tasks must not be reported as a count of distinct people.

## Evidence and questionnaires

Fourteen evidence views distinguish the process and product dimensions of the seven group reviews. Product views reference available reports and models, while the Group 5 process view additionally references its student-authored diary. A view generated through evaluation is distinct from creation of the original artifact. The original problem statement is linked as a document source; its surveillance/team-control scope is not silently equated with the adjusted CSP Task202 specification.

Twenty-five questionnaire references retain submission timestamps. Twenty-four are linked to learner participations; one is kept without a presumed learner link. Questionnaire participation has no score. The instrument mixes peer assessment, self-reported learning and educational feedback; it is not an independent evaluation of the ontology.

There are 120 participation records and 39 evidence references in this case. The review values differ from official grades in every group. The documentary values remain in source descriptions and in the shared comparison CSV, without overriding hasScore or inventing a revision chronology. Group 2's blank qualitative fields and missing delivery in the provided collection remain explicit.

No EvidencedCompetence, EvidencedCompetenceConstituent or proficiency assignment is fabricated from these task-level scores. Any future constituent-level interpretation must identify its textual basis, scope, learner and retrospective interpretation status.

## Checks

The five supplied queries return 32 final results, 29 evidence-to-document links, 25 questionnaire references, 32 dimensional comparisons and zero mean violations. Shared queries identify the one unresolved questionnaire linkage and source provenance. See the shared and local validation reports for scope and actual results. These checks do not establish full OWL DL consistency with all imports, pedagogical effectiveness or independent human validation.

## Batch insertion

The task directory contains one INSERT script; its read-only SELECT files are in `queries/`. A non-recursive batch updater may execute the task directory, but SELECT queries must be run in the query interface. Choose the RDF-loading or INSERT method rather than treating their equivalent files as different layers.

## Turtle and SPARQL formats

The `.ttl` file is valid Turtle for RDF import. The `.rq` file contains the same grouped individuals inside `INSERT DATA`, using SPARQL `PREFIX` declarations. `INSERT DATA` is not Turtle syntax and therefore is not placed inside a `.ttl` file. Choose the format appropriate to the loading method.
