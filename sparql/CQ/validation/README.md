# Local regression and GraphDB evidence

This directory contains offline validation artifacts. They do not claim that every final query was executed in GraphDB or that the ontology is OWL-consistent.

`validate_local.py` requires Python and RDFLib (the recorded run used the version in `local-validation.json`). It reads the project ontology and the three proficiency scripts, performs updates only on disposable in-memory graphs, and never contacts GraphDB or fetches imports.

Run after placing this directory under `OntoKSD/sparql/CQ`:

```text
python validate_local.py --project-root "I:\Meu Drive\OntoKSD"
```

An optional `--output` selects the report directory; otherwise a temporary directory is used. `--as-of` changes the fixed analytical reference instant. The fixture uses 2026-11-01T12:00:00Z; this is not an assessment date or claimed live execution time.

## Checks

- Parse all 58 root-level files, identifying 57 SELECTs and CQ39b as UPDATE.
- Execute all SELECTs on the local ontology plus proficiency examples 01–03, without OWL inference.
- Verify the whole-competence retrieval aliases and the three equivalent below-minimum comparisons.
- Check expected positive results and the deliberate empty CQ27/CQ40a/CQ40d/CQ42c answers on that example.
- Exercise CQ14–CQ17, including an instructional unit without competence activation.
- Exercise dedicated CQ11 and CQ27 synthetic scenarios, their graph-scoped queries and all inclusion/exclusion checks.
- Execute CQ39b only in local memory and check repeated execution adds no new triples for the same basis.

The shipped CSV records row counts for the specified example, not independent capabilities or GraphDB outcomes. Query and input hashes identify the tested files. Imports are not fetched, and no reasoner is invoked. Some queries can legitimately return zero on this dataset; only cases with explicit expectations receive non-empty/empty assertions. Parser success and execution without exceptions are weaker evidence than an expected-result check.

The synthetic files are test data, not empirical learner evidence. The activation fixture includes a unit with an independent statement. CQ11/CQ27 include explicitly identified incomplete or negative controls. Keep them separate from empirical analyses.

## GraphDB reporting

Use `../GRAPHDB_RUN_LOG.template.csv` to record each final execution. Its status is initially `not_recorded`; local results must not be copied there as live GraphDB results. Record the selected repository/graphs, ruleset, inferred-statement option, parameters, reference time and exported result. CQ39b is optional and modifies data; do not run it as part of a read-only batch.

A complete thesis claim needs those execution records and separate checks of OWL consistency, path integrity, criteria adequacy and requirement-level coverage. The appendix mapping and question-family table must remain consistent.
