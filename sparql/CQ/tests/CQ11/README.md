# CQ11 — Synthetic instructional prerequisite test

This directory tests CQ11: **Which competences are explicitly positioned as instructional prerequisites?** The representation uses `CompetenceActivation`, qualified by `hasActivationFunction prerequisiteFunction`.

The CSP cycles did not produce such associations. Empty results on those records therefore do not, by themselves, indicate a query defect. This isolated synthetic scenario exercises the query without treating its examples as CSP findings or authentic institutional curriculum requirements.

## Run in GraphDB

With OntoKSD loaded, execute these files individually in the same test repository:

1. `01-load-synthetic-data.rq`: **SPARQL Update** inserting the fixture into its own named graph.
2. `02-run-CQ11-on-synthetic-data.rq`: **SELECT**, preserving CQ11 logic and adding `FROM` to isolate the synthetic graph. Expect **three rows**.
3. `03-check-expected-results.rq`: **SELECT**, comparing six expected inclusion/exclusion cases with the query pattern. Expect **six rows, all with `passed = true`**.

Only file 01 is an update. Do not submit files 02 and 03 as updates.

Graph: `http://purl.org/ontoksd/test/cq11`.

Individual namespace: `http://purl.org/ontoksd/test/cq11#`.

The load does not modify CSP instances or ontology axioms. Repeating the load inserts the same RDF terms and triples. The graph identifies synthetic data but does not automatically hide them from default-dataset queries: GraphDB configuration may expose named-graph data there. Use file 02 to limit retrieval to this fixture, and exclude it from empirical analyses.

## Scenario and expected CQ11 output

The three competences concern tracing iterative algorithms, tracing recursive algorithms, and comparing algorithmic solutions. In the introductory task, iterative tracing is a learning target. In recursive practice it is a prerequisite. Algorithm comparison presupposes both tracing competences and targets the comparison competence.

| Instructional unit | Prerequisite competence | Activation |
|---|---|---|
| `recursionPractice` | `traceIteration` | `prerequisiteIterationForRecursion` |
| `algorithmComparison` | `traceIteration` | `prerequisiteIterationForComparison` |
| `algorithmComparison` | `traceRecursion` | `prerequisiteRecursionForComparison` |

The third association is asserted through the inverse unit link `hasCompetenceActivation`; CQ11 retrieves it without inverse-property inference. Controls exclude the same competence used as a target elsewhere, a target alongside prerequisites, and an activation with no explicit function.

Each entity has one label so this fixture produces no language-related row multiplication. Competences are minimal instances of general `Competence`; complete constituent patterns are outside this test's scope.

## Interpretation and limits

Prerequisite status qualifies a competence–instructional unit association, rather than the competence intrinsically. CQ11 does not retrieve course-to-course prerequisites or competence-to-competence dependencies, and does not check learner eligibility or attainment. Exclusion of an unqualified activation is a query result, not an OWL negative assertion.

These artificial requirements have not been pedagogically validated. The evaluation concerns explicit representation and query retrieval, not the necessity of a prerequisite, educational effectiveness, or empirical use in the CSP.

## Local verification

On 2026-10-31, RDFLib/SPARQL 1.1 testing without OWL inference verified that:

- the original CQ11 and the graph-scoped copy return exactly the three expected associations;
- all six inclusion/exclusion checks pass;
- repeated loading preserves the RDF triple set;
- removing the inverse link removes only the corresponding third result.

This verifies local query behavior. It does not replace execution in GraphDB or an OWL consistency check with the ontology imports. Record GraphDB inference settings, the ontology version and observed results when reporting the thesis evaluation. Keep synthetic evidence distinct from CSP-derived observations.
