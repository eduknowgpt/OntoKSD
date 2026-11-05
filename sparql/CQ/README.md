# OntoKSD competency questions

[Português](README.pt.md) · [Question catalog](CQ_CATALOG.md) · [Identifier map](CQ_NUMBERING.md)

The post-qualification collection retains **42 historical question families** and contains **58 root-level `.rq` files: 57 SELECT queries and one optional UPDATE**. Suffixes identify views or operations within a family. Files are not independent requirements or independent successful tests.

## Run a query

1. Load the current ontology, required imported modules and the data relevant to the question into GraphDB.
2. Open one `.rq` file and check the parameters and operation stated in its header.
3. Select the intended repository and dataset. Ordinary queries impose no `FROM`; named-graph visibility depends on the endpoint configuration.
4. Record parameters, inference settings, reference time and exported results in [the run-log template](GRAPHDB_RUN_LOG.template.csv).

CQ1/CQ2 select `ontoksd:C02`. Profile/course comparisons now default to the **synthetic** proficiency example:

```sparql
VALUES (?profile ?course) {
  (ontoksd:Profile_Learner_01 ontoksd:Course_FormalLanguages)
}
```

Replace both IRIs for a different case. This selection does not establish enrollment. To reproduce that example, load the scripts in `../../proficiency level` in order: 01 vocabulary; 02 assessment/evidential instances and the example score interpretation; 03 curricular specification and the local gap policy. These are updates, separate from the read-only queries.

## Interpret results

Empty results can reflect an absent association, a parameter mismatch, information outside the dataset, optional levels not assigned, or a deliberate exclusion. They are not automatically errors or evidence of learner inability. Missing optional labels do not remove entities. Multi-valued labels or qualifiers can multiply rows; count distinct relevant IRIs.

CQ30 accepts Portuguese and untagged optional labels. English-only entities remain visible with blank label fields. This is an accepted-language filter, not ranked fallback or a guarantee of one row per scale.

- CQ11 has a [dedicated synthetic test](tests/CQ11/README.md): CSP cycles did not provide prerequisite activations. The query concerns competence–instructional unit prerequisites, not course-to-course prerequisites.
- CQ27 has a [dedicated synthetic test](tests/CQ27/README.md): constituent levels use independent qualitative criteria. They are not copied from a participation score. The original proficiency example intentionally assigns no constituent levels.
- CQ20c needs an `AssessmentOccurrence` type; its realization and context links are optional.
- CQ40a/CQ42c report absent judgments. Their empty answer in the proficiency example is expected because the course's specified competence has a judgment. An existing below-minimum judgment is not an absent record.

## Comparison and optional operations

CQ39a is the canonical below-minimum comparison. CQ40b and CQ42b retain equivalent executable views for traceability. CQ26 is canonical for whole-competence level retrieval; CQ32a is its alias, **not an implementation of proficiency synthesis**.

The comparison requires a selected mandatory curricular expectation, an explicitly identified common scheme, dated and non-expired judgments, and unambiguous ordinal categories. Its latest eligible record policy excludes future records and conflicts; a newer unclassified record can block the use of an older low record. Scheme-specific ordering is not cross-scheme comparability or interval measurement.

The course specification identifies the expected scheme through `dcterms:references`; the judgment uses `interpretedUsingScheme`. This documentary convention is not a new ontology property. Comparing individual performance with intended curricular coverage is an analytical policy, not a passing rule or diagnosis.

For reproducibility, replace `BIND(NOW() AS ?asOf)` with a fixed typed `VALUES ?asOf` timestamp and retain the executed query. The distributed queries use `NOW()`; local regression tests freeze the comparison instant and document it.

**CQ40d remains disabled.** A whole-competence minimum is not automatically a minimum for each constituent. Enable its flag only under an explicitly justified external policy; it is excluded from claims of established constituent-gap support.

**CQ39b is an UPDATE.** Preview CQ39a first and execute deliberately, separately from retrieval tests. It materializes candidates, not diagnoses; repeated execution on the same basis is idempotent, but old candidates are not automatically withdrawn when new evidence arrives. No live GraphDB update was executed during this file revision.

## Evaluation and documentation

[Local validation](validation/README.md) separates parser/execution checks from live GraphDB evidence. [Revision decisions](DECISIONS.pt.md) explain changes, unchanged conditions and limits. Use [CQ_NUMBERING.md](CQ_NUMBERING.md) when citing a family and its operational files.

The thesis table and the FR-to-CQ appendix must use the same family mapping. New files CQ14–CQ17 implement the previously listed role, constraint, joint-qualification and instructional-condition questions; they require fresh GraphDB runs. Query execution does not establish full OWL consistency, criterion adequacy or educational validity.
