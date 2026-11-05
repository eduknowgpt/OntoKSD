# Task201 historical assessment

This complementary historical case preserves the existing learner, profile, final-participation and evidence IRIs. Load `task201-records.ttl` or execute its equivalent `task201-records.rq`, following the [historical-layer loading instructions](../README.md).

The case contains 34 process scores, 34 product scores and 34 official final means, each on its own AssessmentParticipation. The retained `Task201_GroupAssessmentOccurrence` now identifies final-result consolidation, not a third independent observation. New process/product occurrences provide the proper origin for the 14 group review evidence views.

The complete questionnaire export supplies 27 references, all matched by exact timestamp and group; it resolves the three previously pending references. All 27 have corresponding complementary participation records, without questionnaire scores. There are 129 participation records and 41 evidence references in this case.

Official dimension values come from the final spreadsheet. Existing final means are preserved and checked against those dimensions. Twenty-seven code-to-person correspondences were reconciled through questionnaire submissions and the roster. Seven legacy codes retain their documented group and its uniform official dimension values without claiming an independently reconstructed nominal identity. The participant code is not a new assessment of that person's competence.

Group process observations are qualitative and the process grade is their numeric summary. The product grade concerns submitted artifacts. Documentary values in review files remain distinct from official spreadsheet results. No individual constituent score, competence judgment, proficiency classification or validation date is inferred. The two coded tutors are involved in the dimensional assessment records without implying independent ratings or per-comment authorship.

`../PENDING.md` records the remaining identity limitation; it no longer lists unlocated form submissions. `../VALIDATION.md` and the five SELECT queries describe local verification. The previous 204 proxy competence judgments are excluded.

## Batch insertion

The task directory contains one INSERT script; its read-only SELECT files are in `queries/`. A non-recursive batch updater may execute the task directory, but SELECT queries must be run in the query interface. Choose the RDF-loading or INSERT method rather than treating their equivalent files as different layers.

## Turtle and SPARQL formats

The `.ttl` file is valid Turtle for RDF import. The `.rq` file contains the same grouped individuals inside `INSERT DATA`, using SPARQL `PREFIX` declarations. `INSERT DATA` is not Turtle syntax and therefore is not placed inside a `.ttl` file. Choose the format appropriate to the loading method.
