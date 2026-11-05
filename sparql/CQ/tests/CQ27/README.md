# CQ27 — Synthetic constituent proficiency test

This fixture tests CQ27: **Which proficiency level and interpretive scheme are assigned to each constituent judgment?** Its data are artificial and independent of the CSP cycles and the existing `proficiency level` example.

## Run in GraphDB

With the current OntoKSD loaded, execute these files individually in the same test repository:

1. `01-load-synthetic-data.rq` — **SPARQL Update**, inserting the fixture into its dedicated graph.
2. `02-run-CQ27-on-synthetic-data.rq` — **SELECT**, retaining CQ27 logic and adding `FROM` to isolate the graph. Expect **three rows**, one with an unbound scheme.
3. `03-check-expected-results.rq` — **SELECT**. Expect **five rows, all with `passed = true`**.

Only file 01 is an update. No other example files are required. The original CQ27 is unchanged.

Graph: `http://purl.org/ontoksd/test/cq27`.

Individual namespace: `http://purl.org/ontoksd/test/cq27#`.

Loading does not modify CSP data, the previous proficiency example or ontology axioms. Repeated loads use identical RDF terms. The graph identifies synthetic data but does not automatically hide them from default-dataset queries; exclude it from empirical analyses.

## Independent interpretations

A fictional assessment occurrence, learner, assessor and participation ground shared response evidence. There is no participation score. A local qualitative scheme defines Emerging, Developing and Consolidated categories, with ordinal positions 1–3 and no numeric scale or intervals.

Two textual rubric documents provide constituent-specific descriptors. Each main judgment references its own rubric through `dcterms:references`; descriptions explain the assigned category. These links document interpretation, rather than implement automatic rubric processing.

- `knowledgeJudgment`: correctly identifies DFA elements and explains acceptance and rejection; assigned `consolidated`.
- `skillJudgment`: constructs a solution for usual cases but mishandles the empty string; assigned `developing`.

Levels are explicitly assigned, not calculated or propagated from a whole-competence result. Sharing evidence does not require identical constituent interpretations.

## Expected CQ27 results

| Record | Constituent | Level | Scheme |
|---|---|---|---|
| `knowledgeJudgment` | `knowledge` | `consolidated` | `scheme` |
| `skillJudgment` | `skill` | `developing` | `scheme` |
| `missingSchemeJudgment` | `knowledge` | `emerging` | unbound |

The third record is an intentionally incomplete control, not a second justified knowledge judgment. Scheme membership of its level does not cause CQ27 to fill the missing record-level scheme link. Retrieving a record does not certify interpretive quality.

Each labeled entity has one Portuguese label to avoid language-related row multiplication.

## Controls

The first two records must return with their schemes. The missing-scheme record must still return because the scheme pattern is optional. `ungradedDispositionJudgment` must not return because it has no assigned level; the material does not justify grading that disposition. Missing proficiency is not zero proficiency.

`wholeJudgment` must not return because it is an `EvidencedCompetence`, rather than an `EvidencedCompetenceConstituent`. Its arbitrary level exists exclusively for the typing control and is not a justified holistic conclusion or aggregation of constituent levels. Do not use control records as pedagogical conclusions.

Control exclusions describe query behavior, not OWL negative assertions. Missing information remains subject to open-world semantics. Scheme identification, criteria and other validation conditions remain necessary for justified proficiency interpretation and ordinal comparison.

## Verification and scope

On 2026-10-31, local RDFLib/SPARQL 1.1 tests without OWL reasoning passed all expected-result and control checks. Testing covered the exact three expected results from the original and graph-scoped CQ27, all five controls, repeated loading, independent criteria references, and the different effects of removing a scheme or a level.

These tests do not replace GraphDB execution or an OWL consistency check with the imports. They do not validate rubric adequacy, evidence sufficiency or educational outcomes. Report ontology version, inference settings and actual GraphDB results separately, and identify the fixture as synthetic.
