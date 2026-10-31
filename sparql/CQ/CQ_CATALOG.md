# OntoKSD competency questions

This catalog helps readers select a competency question, understand its answer and identify the data needed to run it. Queries are grouped by educational purpose rather than by implementation details.

## Using the catalog

Each entry states the **question**, the **information returned** and the **meaning of the result**. Follow its query link to execute it in GraphDB. All entries are read-only SELECT queries except **CQ39b**, which is an optional data update.

- **Specifications and activation:** load competence definitions, instructional units and their qualified associations.
- **Evidence and profiles:** additionally load learner-specific participations, evidence and judgments. Planning examples alone do not supply these records.
- **Proficiency:** load the applicable schemes, levels and, for numeric interpretation, scales and intervals.

A row is not necessarily an entity count: multiple labels, verbs, modes, evidence items or judgments can produce several rows. Use distinct identifiers when counting entities. A blank optional field indicates missing information in that view; an empty answer does not by itself establish a defect or lack of learner competence.

### Selecting a competence, profile or course

CQ1 and CQ2 use `ontoksd:C02` as the example competence. Change that IRI to select another competence. CQ3 lists all pairings by default; add `VALUES ?knowledgeSkill` to select one.

CQ39a, CQ39b, CQ40a–CQ40d, CQ42b and CQ42c require an explicit profile/course pair. Replace `ontoksd:Profile_Example` and `ontoksd:Course_Example` with the intended identifiers. The selection does not assert enrollment or the applicability of every course to every profile.

### Interpreting a proficiency comparison

The comparison views require an explicitly identified common scheme, unambiguous ordinal positions and an eligible dated judgment. They consider the latest non-expired judgment in that scheme as of the reference time. A sufficient, superseded, undated, expired, future or ambiguous record must not be interpreted as a current below-minimum result.

The expected scheme is identified through `dcterms:references` on the course specification; the judgment uses `interpretedUsingScheme`. A curricular minimum expresses intended depth of coverage. Comparing it with individual performance is an analytical policy, not an automatic passing rule or diagnosis. The query files and the [usage guide](README.md) state the detailed eligibility conditions.

### Interpreting absence of evidence

“No judgment record” means that no matching record is present in the selected dataset. It does not mean zero proficiency, inability or a confirmed learning need. Explicit needs, analytical comparison results and missing records are separate answers.

## Competence specifications

### CQ1 — Which Knowledge Skill  and Disposition elements  constitute a given competence

**Question:** Which direct constituents specify the selected competence, including explicit K/S/D and KnowledgeSkill pairings?

**Returns:** The competence, each directly associated constituent, its kind and, for a KnowledgeSkill pairing, its knowledge component and required level.

**Interpretation:** The knowledge/level columns describe a pairing when present; do not multiply pairings by dispositions.

[Open CQ1](CQ1%20-%20Which%20Knowledge%20Skill%20%20and%20Disposition%20elements%20%20constitute%20a%20given%20competence.rq)

### CQ2 — Which KnowledgeSkill units are associated with a given competence

**Question:** Which knowledge-level pairings and justification verbs specify the selected competence?

**Returns:** Each knowledge–level pairing, its knowledge component, assigned skill level and any recorded justification verb.

**Interpretation:** One row per pairing and verb; a verb does not infer the level.

[Open CQ2](CQ2%20-%20Which%20KnowledgeSkill%20units%20are%20associated%20with%20a%20given%20competence.rq)

### CQ3 — Which Knowledge element and which SkillLevel constitute a given KnowledgeSkill unit

**Question:** Which knowledge and skill level constitute each KnowledgeSkill?

**Returns:** Each populated KnowledgeSkill pairing and the knowledge and skill level that it connects.

**Interpretation:** Add VALUES ?knowledgeSkill to select one pairing. This retrieval is not a cardinality validator.

[Open CQ3](CQ3%20-%20Which%20Knowledge%20element%20and%20which%20SkillLevel%20constitute%20a%20given%20KnowledgeSkill%20unit.rq)

### CQ4 — Which SpecificationPattern is used to describe a given competence

**Question:** Which specification-pattern value is declared for each competence?

**Returns:** Competences and their associated SpecificationPattern values.

**Interpretation:** Pattern identifiers classify the adopted representation; constituent presence alone is insufficient.

[Open CQ4](CQ4%20-%20Which%20SpecificationPattern%20is%20used%20to%20describe%20a%20given%20competence.rq)

### CQ5 — Is a given competence modeled as atomic or composite

**Question:** Which atomic/composite structure is declared or classified?

**Returns:** Competences, atomic/composite structure values and the basis of the result: a declared value or class membership.

**Interpretation:** No atomic classification is inferred from absent component triples. Basis rows may repeat the same entity.

[Open CQ5](CQ5%20-%20Is%20a%20given%20competence%20modeled%20as%20atomic%20or%20composite.rq)

### CQ6 — Competence composition

**Question:** What are the direct composition relationships and integration descriptions?

**Returns:** Direct whole/component relationships in both directions and the available integration description.

**Interpretation:** Both directions are normalized without inverse materialization. Composition is irreflexive (no own part), asymmetric (no reciprocal part links), and non-transitive (direct parts only); longer cycles need separate checks.

[Open CQ6](CQ6%20-%20Competence%20composition.rq)

### CQ7 — Competence semantic relations

**Question:** What direct specialization relationships and narrower-scope descriptions exist?

**Returns:** Direct narrower/base relationships in both directions and the available scope description.

**Interpretation:** Irreflexive means no self-specialization; asymmetric means the base is not reciprocally narrower. Non-transitivity preserves direct bases. No prerequisite, subclass inheritance or composition is implied.

[Open CQ7](CQ7%20-%20Competence%20semantic%20relations.rq)

### CQ8 — Competence record type

**Question:** Which records are specifications, whole-competence judgments or constituent judgments?

**Returns:** Specification records, whole-competence judgments and constituent judgments, with their available reference and participation information.

**Interpretation:** Retrieves declared categories; incomplete records require separate validation.

[Open CQ8](CQ8%20-%20Competence%20record%20type.rq)

## Curricular organization and instructional activation

### CQ9 — Competence activation contexts

**Question:** Which instructional units are associated through competence activation?

**Returns:** Each competence activation, its instructional unit and available instructional-unit types.

**Interpretation:** Course-level expectations are queried separately by CQ33. Inference can add type rows.

[Open CQ9](CQ9%20-%20Competence%20activation%20contexts.rq)

### CQ10 — Activation qualification

**Question:** How is each activation qualified by function, role, mode or constraint?

**Returns:** Each populated qualification of an activation, including function, role, mode, generic qualifier or constraint type and description.

**Interpretation:** ActivationFunction remains separate from ActivationQualifier. Only populated qualifications are returned.

[Open CQ10](CQ10%20-%20Activation%20qualification.rq)

### CQ11 — Prerequisite competences

**Question:** Which competences are explicitly positioned as instructional prerequisites?

**Returns:** Instructional units and competences explicitly associated through prerequisiteFunction.

**Interpretation:** A function is a planned instructional qualification, not proof of learner possession or attainment.

[Open CQ11](CQ11%20-%20Prerequisite%20competences.rq)

### CQ12 — Target competences

**Question:** Which competences are explicitly positioned as instructional targets?

**Returns:** Instructional units and competences explicitly associated through targetFunction.

**Interpretation:** A function is a planned instructional qualification, not proof of learner possession or attainment.

[Open CQ12](CQ12%20-%20Target%20competences.rq)

### CQ13 — Activation mode

**Question:** Which modes qualify each competence activation?

**Returns:** Each activation and its declared modes of mobilization.

**Interpretation:** Modes may coexist and do not redefine the competence.

[Open CQ13](CQ13%20-%20Activation%20mode.rq)

### CQ33 — Competence instructional associations

**Question:** Which curricular expectations and instructional activations associate each competence?

**Returns:** A combined view of curricular course expectations and instructional activations, retaining the kind and identity of each association.

**Interpretation:** Curricular inclusion does not prove instructional coverage or delivered instruction.

[Open CQ33](CQ33%20-%20Competence%20instructional%20associations.rq)

### CQ34 — External competence alignment

**Question:** Which qualified external alignments, types and justifications are recorded?

**Returns:** Internal and external competences, alignment records, external identifiers, alignment types and available justifications.

**Interpretation:** Alignment records do not entail owl:sameAs, equivalence, compliance or inherited competence.

[Open CQ34](CQ34%20-%20External%20competence%20alignment.rq)

### CQ35 — Competence reuse contexts

**Question:** Where is the same competence structurally reused across multiple units or courses?

**Returns:** Instructional units, courses and acquisition contexts associated with competences having more than one unit or more than one course.

**Interpretation:** Distinct planned units (including a learning task and its assessment) establish structural reuse, not necessarily independent source problems or delivered events.

[Open CQ35](CQ35%20-%20Competence%20reuse%20contexts.rq)

### CQ36 — Reuse activation configuration

**Question:** How do activation qualifications vary across units reusing the same competence?

**Returns:** Functions, modes, roles and constraint details for competences activated in multiple instructional units.

**Interpretation:** Independent modes, roles and constraints can multiply rows; row count is not activation count.

[Open CQ36](CQ36%20-%20Reuse%20activation%20configuration.rq)

## Assessment, participation and evidence

### CQ18 — Evidence support

**Question:** Which evidence supports each whole or constituent judgment?

**Returns:** Whole-competence or constituent judgments, the specification they refer to when available, and their supporting evidence.

**Interpretation:** Shared evidence can support several records; support is not proof of validity or sufficiency.

[Open CQ18](CQ18%20-%20Evidence%20support.rq)

### CQ19 — Evidence type classification

**Question:** How is each evidence item classified?

**Returns:** Evidence items and their EvidenceType classifications.

**Interpretation:** Classification does not establish reliability, validity or sufficiency.

[Open CQ19](CQ19%20-%20Evidence%20type%20classification.rq)

### CQ20 — Evidence provenance

**Question:** Which occurrence produced or grounds each evidence item?

**Returns:** Evidence items and their originating assessment occurrences, using either direction of the origin relation.

**Interpretation:** General origin query, independent of inverse materialization.

[Open CQ20](CQ20%20-%20Evidence%20provenance.rq)

### CQ20a — Evidence assessment occurrence

**Question:** Which occurrence produced or grounds each evidence item?

**Returns:** Evidence items connected by the producesEvidence assertion direction.

**Interpretation:** Uses producesEvidence only; with inference off, inverse-only data can return no rows.

[Open CQ20a](CQ20a%20-%20Evidence%20assessment%20occurrence.rq)

### CQ20b — Evidence based on occurrence

**Question:** Which occurrence produced or grounds each evidence item?

**Returns:** Evidence items connected by the evidenceBasedOn assertion direction.

**Interpretation:** Uses evidenceBasedOn only; with inference off, forward-only data can return no rows.

[Open CQ20b](CQ20b%20-%20Evidence%20based%20on%20occurrence.rq)

### CQ20c — Assessment occurrence context

**Question:** Which assessment and acquisition context are associated with each occurrence?

**Returns:** An overview of assessment occurrences, the assessment specifications they realize and their acquisition contexts.

**Interpretation:** Optional columns expose missing links; CQ20c1/20c2 provide separate focused views.

[Open CQ20c](CQ20c%20-%20Assessment%20occurrence%20context.rq)

### CQ20c1 — Assessment occurrence context

**Question:** Which assessment specification is realized by each occurrence?

**Returns:** Each occurrence and the assessment specification it realizes.

**Interpretation:** Specification and occurrence remain distinct entities.

[Open CQ20c1](CQ20c1%20-%20Assessment%20occurrence%20context.rq)

### CQ20c2 — Assessment occurrence context

**Question:** In which acquisition context does each occurrence take place?

**Returns:** Each occurrence and its acquisition context.

**Interpretation:** One SPARQL operation per file; acquisition context specializes Context, not conversely.

[Open CQ20c2](CQ20c2%20-%20Assessment%20occurrence%20context.rq)

### CQ21a — Evidenced competence evidence occurrence

**Question:** What evidence and evidence-origin occurrences support whole-competence judgments?

**Returns:** Whole-competence judgments, supporting evidence and the occurrences in which that evidence originated.

**Interpretation:** Evidence-origin occurrence is distinct from the participation occurrence; equality is not assumed.

[Open CQ21a](CQ21a%20-%20Evidenced%20competence%20evidence%20occurrence.rq)

### CQ21b — Evidenced competence participation

**Question:** From which participation and occurrence does each whole-competence judgment derive?

**Returns:** Whole-competence judgments, the participations from which they derive and the corresponding occurrences.

**Interpretation:** Does not require the redundant direct isEvidencedInAssessmentOccurrence assertion.

[Open CQ21b](CQ21b%20-%20Evidenced%20competence%20participation.rq)

### CQ22 — Evidenced competence records

**Question:** How does each whole-competence judgment connect specification, profile, evidence and participation occurrence?

**Returns:** Whole-competence judgments connected to their competence, evidence, profile, participation and occurrence; any separately asserted occurrence is shown in another column.

**Interpretation:** Different asserted/participation occurrences are visible, not silently equated.

[Open CQ22](CQ22%20-%20Evidenced%20competence%20records.rq)

### CQ23 — Evidenced constituent records

**Question:** How does each constituent judgment connect its constituent, learner profile, evidence and occurrence?

**Returns:** Constituent judgments connected to their constituent, evidence, participation, learner profile and occurrence, with an optional parent judgment.

**Interpretation:** A constituent judgment can exist without a whole-competence judgment.

[Open CQ23](CQ23%20-%20Evidenced%20constituent%20records.rq)

### CQ24a — Learner assessment participation

**Question:** Which learner participates in which assessment occurrence, with which score and assessor?

**Returns:** Learners, their assessment participations and occurrences, with optional individual scores and assessors.

**Interpretation:** No score is taken directly from AssessmentOccurrence.

[Open CQ24a](CQ24a%20-%20Learner%20assessment%20participation.rq)

### CQ24b — Participation derived records

**Question:** Which judgment records derive from each assessment participation?

**Returns:** Whole-competence and constituent judgments derived from each participation.

**Interpretation:** Participation provenance does not transfer a whole judgment to its constituents.

[Open CQ24b](CQ24b%20-%20Participation%20derived%20records.rq)

### CQ24c — Participation occurrence evidence

**Question:** What evidence originated in the occurrence associated with each participation?

**Returns:** Evidence produced in the occurrence of each participation, regardless of whether it supports a particular learner judgment.

**Interpretation:** Co-occurring evidence is not automatically supporting evidence of this learner; CQ18/CQ41 establish record-specific links.

[Open CQ24c](CQ24c%20-%20Participation%20occurrence%20evidence.rq)

### CQ25a — Evidenced competence timeline

**Question:** Which dated whole-competence judgments are recorded for profiles and competences?

**Returns:** Dated whole-competence judgments ordered by profile, competence and judgment date, with available evidence.

**Interpretation:** A chronological record is not proof of learning progress; undated judgments are outside this view.

[Open CQ25a](CQ25a%20-%20Evidenced%20competence%20timeline.rq)

### CQ25b — Evidence timeline

**Question:** When was supporting evidence generated or validated?

**Returns:** Supporting evidence and its available generation and validation dates.

**Interpretation:** Undated evidence remains visible; dates alone establish neither progress nor validity.

[Open CQ25b](CQ25b%20-%20Evidence%20timeline.rq)

### CQ41 — Participation evidenced records

**Question:** Which judgment targets and proficiency interpretations derive from each participation?

**Returns:** Each participation and its derived whole-competence or constituent judgments, their specification targets, levels and schemes.

**Interpretation:** Whole-competence and constituent judgments remain distinct; no automatic aggregation is inferred.

[Open CQ41](CQ41%20-%20Participation%20evidenced%20records.rq)

## Proficiency interpretation

### CQ26 — Evidenced competence proficiency

**Question:** Which proficiency level and interpretive scheme are assigned to each whole-competence judgment?

**Returns:** Whole-competence judgments, their assigned proficiency levels and available interpretation schemes.

**Interpretation:** Missing scheme remains visible and prevents justified ordinal comparison.

[Open CQ26](CQ26%20-%20Evidenced%20competence%20proficiency.rq)

### CQ27 — Evidenced constituent proficiency

**Question:** Which proficiency level and interpretive scheme are assigned to each constituent judgment?

**Returns:** Constituent judgments, their assigned proficiency levels and available interpretation schemes.

**Interpretation:** Missing scheme remains visible and prevents justified ordinal comparison.

[Open CQ27](CQ27%20-%20Evidenced%20constituent%20proficiency.rq)

### CQ28 — Proficiency in assessment context

**Question:** In which participation occurrence was each proficiency judgment grounded?

**Returns:** Whole-competence or constituent proficiency judgments traced through participation to an assessment occurrence.

**Interpretation:** An occurrence scheme and an individual interpretation scheme are distinct assertions.

[Open CQ28](CQ28%20-%20Proficiency%20in%20assessment%20context.rq)

### CQ29 — Assessment proficiency scheme

**Question:** Which proficiency schemes are explicitly used by assessment occurrences?

**Returns:** Assessment occurrences and the schemes explicitly associated with them for proficiency interpretation.

**Interpretation:** Does not apply usesProficiencyScheme to an Assessment specification.

[Open CQ29](CQ29%20-%20Assessment%20proficiency%20scheme.rq)

### CQ30 — Proficiency scheme scale

**Question:** Which numeric scales are used by proficiency schemes?

**Returns:** Proficiency schemes and their numeric scales, including available lower/upper bounds and optional step.

**Interpretation:** Qualitative schemes without a numeric scale are outside this numeric-scale view.

[Open CQ30](CQ30%20-%20Proficiency%20scheme%20scale.rq)

### CQ31 — Proficiency intervals and levels

**Question:** How do schemes map numeric intervals, endpoints and scales to levels?

**Returns:** The intervals adopted by each scheme, their scales, numeric bounds, endpoint inclusion policies and corresponding levels.

**Interpretation:** This is retrieval, not overlap, coverage, endpoint or score-classification validation.

[Open CQ31](CQ31%20-%20Proficiency%20intervals%20and%20levels.rq)

### CQ32a — Overall evidenced competence proficiency

**Question:** Which proficiency level and interpretive scheme are assigned to each whole-competence judgment?

**Returns:** Proficiency levels directly assigned to whole-competence judgments, with their available schemes.

**Interpretation:** CQ32a is the whole-competence view also available in CQ26; it does not aggregate constituent levels.

[Open CQ32a](CQ32a%20-%20Overall%20evidenced%20competence%20proficiency.rq)

## Profiles and learning needs

### CQ37 — Profile evidenced competences

**Question:** Which whole-competence judgments, levels and schemes are associated with each profile?

**Returns:** Profiles, their whole-competence judgments and referenced competences, with available levels and schemes.

**Interpretation:** No automatic profile-wide aggregation or latest-record selection is applied.

[Open CQ37](CQ37%20-%20Profile%20evidenced%20competences.rq)

### CQ38 — Profile evidenced constituents

**Question:** Which constituent judgments can be traced to each profile?

**Returns:** Profiles and constituent judgments, identifying whether the profile is recovered through participation or through a parent judgment.

**Interpretation:** Parent-free constituent judgments remain retrievable through participation; disagreeing paths need separate integrity checks.

[Open CQ38](CQ38%20-%20Profile%20evidenced%20constituents.rq)

### CQ39a — Derived competence gaps

**Question:** Which latest comparable judgments are below the selected curricular minimum?

**Returns:** The latest eligible judgments below a selected mandatory curricular minimum, with the compared levels, ordinals and scheme.

**Interpretation:** Explicit profile/course pair; mandatory curricular expectation; explicit scheme reference via dcterms:references; same-scheme numeric ordinals; latest dated, non-expired record as of NOW(). No cross-scheme or undated comparison. These variants share the same comparison policy; they are candidates, not independent diagnoses. Comparing individual judgments with curricular depth is an external analytical policy, not a student passing rule.

[Open CQ39a](CQ39a%20-%20Derived%20competence%20gaps.rq)

### CQ39b — Materialize competence gaps

**Question:** Which comparison candidates can be recorded as LearningNeed instances?

**Operation:** UPDATE — optional; preview CQ39a before recording candidates.

**Returns:** LearningNeed candidate records linked to a profile and competence, with references to the curricular expectation, judgment and interpretation scheme used as their basis.

**Interpretation:** UPDATE: modifies data. Preview CQ39a first; execute only intentionally, preferably in a dedicated snapshot repository. Repeated execution is idempotent for the same basis. Previously created records are not automatically withdrawn after new evidence.

[Open CQ39b](CQ39b%20-%20Materialize%20competence%20gaps.rq)

### CQ39c — Profile explicit learning needs

**Question:** Which explicit learning needs target competences or constituents in each profile?

**Returns:** Explicit learning needs associated with profiles, their competence or constituent targets and any documentary references.

**Interpretation:** Includes explicitly recorded needs and rule-derived candidates; not every need is a proficiency gap.

[Open CQ39c](CQ39c%20-%20Profile%20explicit%20learning%20needs.rq)

### CQ40a — Required competences not evidenced

**Question:** Which mandatory competences have no judgment record in the selected profile?

**Returns:** Mandatory competences in the selected course for which the selected profile has no whole-competence judgment record.

**Interpretation:** Absence of a record at any date in the queried dataset is a documentation/assessment gap, not proof of incompetence. A minimum level is optional.

[Open CQ40a](CQ40a%20-%20Required%20competences%20not%20evidenced.rq)

### CQ40b — Required competences below level

**Question:** Which mandatory competence expectations have a latest comparable judgment below their minimum?

**Returns:** Mandatory curricular expectations whose latest eligible judgment falls below the minimum in the same scheme.

**Interpretation:** Explicit profile/course pair; mandatory curricular expectation; explicit scheme reference via dcterms:references; same-scheme numeric ordinals; latest dated, non-expired record as of NOW(). No cross-scheme or undated comparison. These variants share the same comparison policy; they are candidates, not independent diagnoses. Comparing individual judgments with curricular depth is an external analytical policy, not a student passing rule.

[Open CQ40b](CQ40b%20-%20Required%20competences%20below%20level.rq)

### CQ40c — Required constituents not evidenced

**Question:** Which direct constituents of mandatory competences have no judgment in the selected profile?

**Returns:** Direct constituents of mandatory competences that have no corresponding constituent judgment traceable to the selected profile.

**Interpretation:** A Knowledge nested inside KnowledgeSkill is not automatically a separately declared direct constituent; missing constituent evidence is not a diagnosed LearningNeed.

[Open CQ40c](CQ40c%20-%20Required%20constituents%20not%20evidenced.rq)

### CQ40d — Required constituents below level

**Question:** Can constituent judgments be compared with the curricular minimum assigned to the whole competence?

**Default:** disabled. An empty result is expected until the external threshold policy is explicitly enabled.

**Returns:** Only when its external-policy flag is enabled, constituent judgments below the whole-competence curricular minimum under the stated comparison policy.

**Interpretation:** Default result is empty by design. Setting the policy flag to true is an external analytical assumption, not an OntoKSD axiom; no constituent-specific curricular minimum exists in the current model.

[Open CQ40d](CQ40d%20-%20Required%20constituents%20below%20level.rq)

### CQ42a — Explicit profile competence gaps

**Question:** Which explicit competence-targeted learning needs and documented comparison bases exist?

**Returns:** Explicit competence-targeted learning needs and, where documented, the curricular specification and judgment that form their comparison basis.

**Interpretation:** Referenced levels describe the stored basis; they need not be the latest current judgment. A candidate record is not automatically confirmed or still applicable.

[Open CQ42a](CQ42a%20-%20Explicit%20profile%20competence%20gaps.rq)

### CQ42b — Derived gaps from proficiency comparison

**Question:** Which selected curricular expectations have a latest comparable proficiency mismatch?

**Returns:** Same-scheme proficiency mismatches between eligible individual judgments and the selected mandatory curricular expectations.

**Interpretation:** Explicit profile/course pair; mandatory curricular expectation; explicit scheme reference via dcterms:references; same-scheme numeric ordinals; latest dated, non-expired record as of NOW(). No cross-scheme or undated comparison. These variants share the same comparison policy; they are candidates, not independent diagnoses. Comparing individual judgments with curricular depth is an external analytical policy, not a student passing rule.

[Open CQ42b](CQ42b%20-%20Derived%20gaps%20from%20proficiency%20comparison.rq)

### CQ42c — Expected competences not evidenced

**Question:** Which curricular expectations have no judgment in the selected profile?

**Returns:** All selected curricular expectations without a whole-competence judgment in the profile, retaining their mandatory/non-mandatory status.

**Interpretation:** Returns no recorded judgment, not a causal diagnosis or assumed zero proficiency; optional inclusion is identified rather than treated as mandatory.

[Open CQ42c](CQ42c%20-%20Expected%20competences%20not%20evidenced.rq)

## Choosing between related views

| Reader's need | Query |
|---|---|
| Inspect all evidence origins, regardless of which inverse was asserted | CQ20 |
| Inspect a particular origin assertion direction | CQ20a or CQ20b |
| See assessment realization and context together | CQ20c |
| Focus on realization or acquisition context | CQ20c1 or CQ20c2 |
| Retrieve a level assigned to a whole-competence judgment | CQ26 or CQ32a |
| Find eligible below-minimum judgments | CQ39a, CQ40b or CQ42b |
| Retrieve all explicit learning-need target kinds | CQ39c |
| Inspect the basis of a competence-targeted need | CQ42a |
| Find mandatory inclusions with no judgment | CQ40a |
| Find any curricular inclusion with no judgment | CQ42c |

Equivalent or complementary views support different reading tasks; they do not constitute independent proof that additional functional requirements have been satisfied. For reproducible experiments, retain the query parameters, dataset and inference configuration together with the result.
