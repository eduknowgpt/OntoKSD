# Optional Curricular and Proficiency Layer for CSP Cycle 02

These two insertions complement the corrected [CSP cycle 02](https://github.com/eduknowgpt/OCSP/tree/main/CSP/cycles/cycle-02) instance dataset with a **user-defined operational scenario**. They express selected course expectations and a shared numeric interpretation for experimentation, comparison and future record-level gap analysis.

They are optional additions. In a dataset where they are loaded, the curricular expectations become asserted facts of that selected scenario. Their provenance identifies the modeling assumptions; it does not make the RDF assertions logically conditional or establish that the CSP documents imposed those assumptions.

## What each file does

| File | Function |
| --- | --- |
| `task201_204_course_layer.rq` | Associates the existing Course with the selected instructional units and creates 15 CourseCompetenceSpecification records, each mandatory with Competent as the minimum expected proficiency. |
| `second_cycle_shared_proficiency_scheme.rq` | Defines the operational 0–10 Scale, three ProficiencyLevels and three ScaleIntervals, and identifies the planned shared scheme for the four Assessments. |
| `cycle02-operational-layer.ttl` | The same optional instance layer in Turtle, for loading with the corrected datasets and schema. |

`policy.json` summarizes the assumptions and fingerprints the supplied input files. `validation.json` records the checks. The `queries/` directory contains four usage queries; no learner data or CSP source documents are bundled.

## Curricular expectations

The course IRI is the one already used in the corrected dataset:

```turtle
ontoksd:Course_FormalLanguagesAndTheoryOfComputation
```

Its eight selected instructional units are `LT_Task201` through `LT_Task204` and `AS_Task201` through `AS_Task204`. Reference Task entities are not treated as InstructionalUnits. A second course is not created under the legacy `TheoryOfComputationCourse` identifier.

The 15 CourseCompetenceSpecification IRIs from the supplied course-layer file are retained. Each record identifies:

- exactly one course through `mediatesCourse`;
- exactly one of the current cycle-02 competences through `mediatesCourseCompetence`;
- `isMandatory true`;
- `requiresMinimumProficiencyLevel SecondCycle_CompetentLevel`;
- the applicable `SecondCycle_ProficiencyScheme` through `dcterms:references`.

The last association is a documented local convention for identifying the interpretive scheme of a curricular minimum. `usesProficiencyScheme` is not used here because its domain is AssessmentOccurrence.

The mandatory status and minimum level preserve the operational assumptions explicitly supplied with the original course-layer file. They are not inferred from a task activation or a course syllabus. They express intended curricular depth, not an admission prerequisite, a student's demonstrated achievement, or a course approval rule.

A competence may be mandatory in the course while optional in a particular task. This is not itself a logical contradiction, but it requires examining whether the planned course provides adequate opportunities to address the expectation. The existing task associations alone do not establish that coverage.

## Shared operational proficiency policy

The policy preserves the numeric boundaries actually present in the supplied file:

| Level | Interval | Ordinal position |
| --- | --- | ---: |
| Novice | `[0,7)` | 1 |
| Competent | `[7,9)` | 2 |
| Advanced | `[9,10]` | 3 |

Scale and interval bounds use `xsd:decimal`; ordinal positions use `xsd:int`; inclusion flags use `xsd:boolean`. The Scale explicitly declares minimum 0 and maximum 10. No score step is invented.

These categories are linked to the scheme by `definesLevel`, and the intervals by `definesInterval`. Every interval identifies its scale and exactly one corresponding level. The instance structure supplies the classification rules, but OWL alone does not compare a score with numeric bounds or execute classification.

### Distinction from the documented Task201 scheme

The corrected base dataset retains the report's Task201 policy:

- Novice `[0,5]`;
- Competent `(5,9]`;
- Advanced `(9,10]`.

The operational shared policy is different. For example:

| Score | Task201 documented policy | Shared operational policy |
| ---: | --- | --- |
| 6.70 | Competent | Novice |
| 7.00 | Competent | Competent |
| 9.00 | Competent | Advanced |

The same labels do not establish cross-scheme equivalence. An ordinal position is meaningful for comparison within its identified scheme; it does not make classifications from different policies interchangeable.

The shared policy is a scenario choice for Task201–Task204, not evidence that all four original tasks used the same thresholds. The source documents specify numeric intervals for Task201. This operational layer preserves that documentary scheme rather than overwriting or equating it with the shared scheme.

### Planned assessment use

Each of the four Assessments has a documentary `dcterms:references` link to the operational scheme. Task201 may therefore reference both its documentary scheme and this optional scenario scheme. A reference does not select the policy of an actual assessment event or assign a proficiency level automatically.

When an AssessmentOccurrence and individual judgments are created from real evaluation data, identify the policy actually used through the occurrence and judgment relations, including `interpretedUsingScheme` on the judgment. Keep those records traceable to their participation, evidence and criteria. Do not infer individual attainment from this planning layer.

## How to use the files

1. Load the current OntoKSD schema and the vocabularies required by the corrected cycle-01/cycle-02 packages.
2. Load the corrected cycle-01 and cycle-02 instance data.
3. In the dataset selected for this operational scenario, execute `second_cycle_shared_proficiency_scheme.rq` and then `task201_204_course_layer.rq`.
4. Alternatively, load `cycle02-operational-layer.ttl`, which contains the same additional triples.

The final graph is independent of the order of these two insertions. Both use `INSERT DATA` and are idempotent. The `.rq` files require a SPARQL Update-capable environment; the Turtle is the convenient alternative for Protégé.

Use the corrected files in place of the earlier variants. Re-running INSERT DATA does not remove old double-valued bounds, obsolete unit references or previous policies already asserted in an existing graph. A coherent instance dataset is required; this package does not include deletion operations.

## Queries and gap analysis

1. `01 - Course expectations.rq` lists the 15 curricular expectations with their minima and applicable scheme.
2. `02 - Compare documented and operational intervals.rq` classifies example numeric values under both policies. The values are query inputs, not stored learner scores.
3. `03 - Mandatory course expectation with only explicit optional activations.rq` highlights curricular expectations whose associated cycle-02 activations have only explicit optional constraint statuses. In the current data this returns C09. Unspecified statuses are not treated as optional, and a mixed optional/mandatory configuration is not classified as only optional. This is a planning diagnostic, not proof of a curriculum defect or a learner gap. C16's unresolved qualifier alternatives require separate inspection.
4. `04 - Below-target evidenced records in the same scheme.rq` compares evidenced records with a curricular minimum **only when both belong to the same scheme**. It excludes ambiguous ordinal assignments. With the delivered planning dataset it returns no rows, because no learner judgments are manufactured.

Before using the last query for a learner-level decision, select the applicable evidential record and context, including recency where relevant. An older lower-level record alongside a newer higher-level record is not automatically a current learning need. Missing evidence is not a zero level. The query reports records for examination; it does not insert LearningNeed instances.

## Validation

The layer passed **1,116 automated checks**. The four supplied queries were executed: 15 expectations, 14 example classifications across two schemes, one optional-only activation diagnostic, and no manufactured learner-level comparisons.

The checks cover SPARQL parsing/execution, repeated loading, insertion order, identity alignment with the existing course and units, the 15 specification restrictions, datatype correctness, interval coverage and boundaries, comparison-query behavior, and OWL RL inference with the corrected cycle data.

Test-only fixtures verify that below-target records in the same scheme are returned, while Competent/Advanced, cross-scheme records, missing records and ambiguous ordinal assignments do not produce a below-target result. These fixtures are not included in the delivered instance layer.

OWL RL does not replace complete OWL DL consistency verification with HermiT or Pellet. No assessment occurrence, learner profile, evidence, score or learning need is generated by these insertions.

## Source and assumption traceability

The CSP documentation remains in [cycle 02 of the CSP repository](https://github.com/eduknowgpt/OCSP/tree/main/CSP/cycles/cycle-02). `SecondCycle_OperationalScenario`, represented as a documentary `prov:Entity`, identifies the additional user-defined curricular and proficiency assumptions. This separation preserves both the source record and the selected operational policy.
