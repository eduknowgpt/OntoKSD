# CSP Cycle 02 Instances for OntoKSD

This package represents the reviewed results of [CSP cycle 02](https://github.com/eduknowgpt/OCSP/tree/main/CSP/cycles/cycle-02) in OntoKSD. The source problems, specification reports, expert reviews and adjustments remain in the CSP repository; they are **not bundled** in this package.

The dataset is incremental: it introduces **C13.01, C15 and C16**, reuses the cycle-01 competence identities, and represents Task201–Task204 and their adjusted associations. It records expected instruction and assessment, not observed learner performance.

## Coverage

| Task | Problem | Adjusted competence set |
| --- | --- | --- |
| Task201 | Surveillance Drone Prototype | C06, C02, C03, C04, C05, C11 |
| Task202 | Team Control | C12, C03, C13.01, C05, C14 |
| Task203 | Parking Control | C07, C08, C09, C10, C05 |
| Task204 | Online Judges at Logistic Solutions | C06, C02, C03, C14, C05, C15, C16 |

There are 23 current task–competence associations: three creations and 20 reuses. They involve 15 active competence identities. C13 is retained as the historical base of C13.01, without becoming an additional current Task202 target. The package contains four reference Tasks, four LearningTasks, four Assessments and 46 activation records: one instructional and one assessment association for every current task–competence pair.

## Files

- `00 - Cycle02 Sources and Vocabulary.rq`: external source references, locally curated knowledge, the Analytical Rigor disposition, the conceptual activation-mode value, shared resources, and the syllabus-based Course aggregate.
- `competencies/C13.01.rq`, `C15.rq`, `C16.rq`: the three new specifications and their ten knowledge–level pairings.
- `tasks/TASK201.rq` through `TASK204.rq`: reference tasks, instructional requirements, assessment plans, acquisition contexts, activations and qualifications.
- `cycle02-instances.ttl`: the same incremental instance graph, ready for loading with the schema and dependencies.
- `REVISAO.md`: modeling decisions and source limitations requiring attention.

## New competence specifications

| Competence | Knowledge | Required level |
| --- | --- | --- |
| C13.01 | Context-Free Grammars | Understanding |
| C13.01 | Pushdown Automata | Analyzing |
| C13.01 | Formal Reasoning in Computation Theory | Analyzing |
| C15 | Halting Problem | Understanding |
| C15 | Computability | Understanding |
| C15 | Turing Machines | Understanding |
| C15 | Analytical and Critical Thinking | Applying |
| C16 | Turing Machines | Understanding |
| C16 | Computability Theory | Understanding |
| C16 | Analytical and Critical Thinking | Applying |

Each pairing has exactly one `hasKnowledgeComponent`, one `hasSkillLevel`, and explicit `hasBloomLevelJustificationVerb` links. Verbs are object-property values; descriptions do not replace those links. Existing verb individuals are reused from the required vocabularies and cycle-01 supplement.

For example, C15's Halting Problem requirement uses Understanding with `describe`, `explain` and `justify`. The word `justify` does not automatically raise the assigned level: the source pairs these actions with conceptual understanding in this context.

C13.01's source explicitly assigns Understand to Context-Free Grammars and Analyze to both Pushdown Automata and Formal Reasoning. Its source action list is distributed across those pairings as a documented local interpretation. The reused pairing identifier containing `PushdownAutomata_Understanding` remains a stable identifier; its actual `hasSkillLevel` value is `bloom:Analyzing`.

### Dispositions

C15 and C16 reuse `collaborative`, `responsible`, `proactive` and `creative`. C13.01 uses a locally documented Analytical Rigor value and the existing `responsible`, `reflective` and `collaborative` orientations. Its scope note preserves the detailed formal-consistency, reflective-reasoning and collaborative-reasoning context. Analytical Rigor is not silently equated with Meticulous, and Reflective Reasoning is not replaced by Investigative.

### C16's detailed knowledge

The source keeps Universal Turing Machine in C16's detailed knowledge specification but gives no separate level for it. It is therefore retained through `hasKnowledge`, without inventing a fourth KnowledgeSkill pairing or copying another component's level. C16 remains distinct from C15; the source does not establish independent assessment evidence for their overlapping explanatory content.

## Reuse and contextual activation

Reused competences are referenced by their existing IRIs. Their knowledge, dispositions, patterns and reference levels are not redefined by this cycle. C04's analytical/conceptual use in Task201 does not replace its reference pairings or create a derived competence. C09 remains an optional contextual extension in Task203, without specialization.

Task-level predominant Bloom categories are retained as documentary descriptions and `dcterms:references` links on activation records. They are not assigned with `hasSkillLevel`, whose domain is KnowledgeSkill. This separates contextual emphasis from the reusable competence requirements. No predominant level is invented for Task204 from its component pairings.

Every activation uses `CompetenceActivation`, mediates exactly one competence and one instructional unit, and uses `targetFunction`. Supporting, extension and transversal roles do not turn a competence into an admission prerequisite. The instructional and assessment records are separate explicit planning associations.

### Unspecified and alternative qualifiers

The source data dictionary distinguishes semicolon-separated lists from vertical-bar alternatives:

- Explicit concurrent mode/role values are mapped through the corresponding object properties.
- `not_specified` is left unassigned. It is not converted to Mandatory.
- Explicit single statuses become ActivationConstraint instances with a type and description.
- Alternatives are preserved in separately identifiable `prov:Entity` option records linked to the relevant activations. They use `dcterms:references` to identify the admissible values and do not assert that all values apply together.

Three alternative fields remain unresolved by the source:

| Association | Field | Documented alternatives |
| --- | --- | --- |
| Task203–C09 | Mode | analytical or constructive |
| Task204–C16 | Constraint status | optional or conditional |
| Task204–C16 | Role | supporting or extension |

Consequently, these fields are not arbitrarily fixed on the activation itself. Choosing an operational configuration requires a further documented instructional decision. The option records are queryable RDF metadata; they are not a new OntoKSD qualification class.

## Reference tasks and independent requirements

A Task describes reference performance. A LearningTask represents the instructional unit, and Assessment represents evaluative design based on it. Problem statements remain available through the linked CSP records; the RDF contains curated, language-tagged instructional requirements and analytical task elements.

The requirements preserve important boundaries:

- Task201 includes structural state/transition estimation as expected work, not only an Advanced-level bonus. Analytical C04 reuse does not require a separate regular-expression artifact.
- Task202 preserves `Vi >= ni * NVi`, with `ni > 0`, including equality. Its adopted PDA model is not presented as a proof that every bounded encoding rules out finite-state computation.
- Task203 includes arrivals, departures, release of spaces, daily rejected-request counters by category, and continuation after rejection.
- Task204 retains its simulation and Chomsky-Hierarchy objectives despite the conditional/optional labels of C03/C14. Those labels alone do not authorize deleting source-task requirements. TSP analysis is retained as an instructional requirement without creating an undocumented additional competence.

The syllabus-based Course aggregates the eight selected instructional units. This grouping is not a semester-specific course offering, a claim of complete curricular coverage, or an invented CourseCompetenceSpecification with mandatory status or minimum proficiency.

## Specialization and the C13 reference

The cited review addresses the base C13; C13.01 is introduced in the adjustments. No separate review or approval event for C13.01 is invented.

C13.01 is linked to C13 through `isSpecializationOf`, with its inverse and an explicit specialization description. The relation is between competence individuals, not an OWL subclass declaration.

Direct specialization is irreflexive because a competence cannot be its own direct base, and asymmetric because the narrower and base specifications cannot simultaneously specialize each other. It is not declared transitive because the property records immediate bases. A property-path query checks longer cycles, which those characteristics alone do not exclude.

The source describes C13 as a general rule-interpretation base. The earlier cycle-01 instance package gave C13 a Task04-specific CFG/PDA formulation. This dataset preserves the base identity and does not overwrite that definition. The source-scope difference remains a documented reconciliation issue; recording the documented specialization does not prove that the two base descriptions already denote the same formal specification state.

The initial C13–Task202 association is retained as documentary provenance. It is not instantiated as a second current activation alongside C13.01.

## Proficiency and assessment scope

Only Task201 has an explicitly documented proficiency scheme: Novice `[0,5]`, Competent `(5,9]`, and Advanced `(9,10]`. Scale bounds use `xsd:decimal`. No numeric step is invented, and the scheme is not copied to the other three tasks.

The Assessment refers documentarily to this planned scheme. `usesProficiencyScheme` is not applied to Assessment because its domain is AssessmentOccurrence. No assessment occurrence, participation, learner score, produced Evidence or individual competence judgment is created.

## Loading

1. Load the current OntoKSD schema and imported skill-level module.
2. Load the CS2023 body-of-knowledge vocabulary, CC2020 foundational-knowledge and disposition instances, and Bloom action-verb/type instances.
3. Load `cycle02-instances.ttl`, **or** execute the root shared `.rq` file, the three competence files and the four task files in the intended instance dataset.

The `.rq` files are SPARQL Update operations, not Turtle ontologies. All use `INSERT DATA`; re-execution is idempotent and final content is independent of insertion order. The combined Turtle contains only the cycle-02 increment, so it does not replace the schema, cycle-01 instances or vocabulary dependencies.

## Validation

The dataset passed **1,521 automated checks**. All six supplied queries were executed, including 23 current associations, 26 pairing–verb links, six admissible qualifier values and no specialization cycles.

Validation checks source CSV counts and membership, preservation of inherited specifications, external reference resolution, exact pairing cardinalities and verb links, qualifiers against the source fields, unselected alternatives, historical-only C13 handling, and all cent-valued test points from 0 to 10 for the Task201 intervals. Queries additionally inspect counts, alternatives and cycles.

OWL RL inference is checked together with the corrected cycle-01 dependency. It is not a full OWL DL consistency test with HermiT or Pellet. Logical consistency and documentary fidelity do not establish learner attainment, independent assessment outcomes, complete curriculum coverage, or resolution of the C13 scope issue.

## CSP reference

The authoritative process documentation is maintained in [CSP cycle 02](https://github.com/eduknowgpt/OCSP/tree/main/CSP/cycles/cycle-02). File references use the actual names present in the supplied archive, including `TASK202-CSP-Adustments.md` and `TASK204-CSRP-Report.md`; shorter path aliases found in the source CSVs are not blindly used as file URLs.
