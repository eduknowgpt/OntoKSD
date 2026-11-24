# OntoKSD model guide

OntoKSD separates expected performance, planned instructional use, and individual assessment interpretation. This guide introduces its structures; the [ontology package](../ontoKSDOntology/README.md) supplies their formal definitions.

## Core structures


### Competence specifications

`Competence` represents a reusable specification of expected performance, rather than a learner's attainment. `CompetenceConstituent` provides a common reference for `Knowledge`, `Skill`, `Disposition`, and `KnowledgeSkill`; `Attitude` specializes `Disposition` within the adopted model.

`KnowledgeSkill` identifies a knowledge element together with a required `SkillLevel`. This keeps an explicit skill description, a required cognitive-performance category, and an attributed proficiency level distinct.

OntoKSD represents four specification patterns:

| Pattern | Representation |
| --- | --- |
| KS | Explicit knowledge and skill constituents |
| KSA | Explicit knowledge, skill, and attitude constituents |
| CC2020-oriented | Knowledge–level pairings, dispositions, and a reference task, using CC2020-oriented Bloom levels |
| CS2023-oriented | Knowledge–level pairings, dispositions, and a reference task, using Explain, Apply, Evaluate, and Develop |

The two framework-oriented patterns share a structural approach but retain distinct pattern values and level schemes. A task reference is present in both. `TaskActivity`, `TaskComponent`, and `TaskConstraint` support optional analysis of a task statement.

`AtomicCompetence` and `CompositeCompetence` express declared composition structure. Direct composition and specialization use separate properties, with descriptions recording intended integration and narrower scope.

### Curricular and instructional use

`CourseCompetenceSpecification` qualifies the inclusion of a competence in a course, including mandatory status and an optional minimum expected proficiency level.

`CompetenceActivation` qualifies the association between a competence and an `InstructionalUnit`. It can identify a target or prerequisite function, an instructional contribution, modes of mobilization, and applicable constraints.

Instructional units include `LearningActivity`, `LearningTask`, and `Assessment`. A specification-level `Task` remains distinct from a planned `LearningTask`. Educational resources and acquisition contexts are represented through separate associations.

### Assessment, evidence, and individual judgments

`AssessmentOccurrence` realizes an assessment specification. `AssessmentParticipation` identifies one learner's involvement in an occurrence and can record assessors and an optional score.

`Evidence` records an evidential basis; supporting `Document` instances identify associated artifacts or documentary records. `EvidencedCompetence` and `EvidencedCompetenceConstituent` record individual judgments referring to the relevant specification, participation, and supporting evidence. Shared artifacts can support separate participant-specific judgments.

### Proficiency and learner records

`ProficiencyScheme` and `ProficiencyLevel` describe interpretation categories. Numeric interpretation additionally uses `Scale` and `ScaleInterval`, separating a score from its classification. `Profile` organizes learner-related records, and `LearningNeed` records an identified educational need.

### External references and interchange

`ExternalCompetenceAlignment` records a qualified correspondence with an externally defined competence, including BNCC references. It identifies the endpoints, alignment type, and a supporting explanation where provided. Alignment does not assert identity or automatically establish curricular compliance.

`OntoKSDRCD` links internal competences to records in the accompanying Reusable Competency Definition (RCD) OWL profile. The `rcd/` directory includes the profile, examples, and SHACL shapes. This is a documentary interoperability representation; complete IEEE conformity or lossless exchange of all OntoKSD relationships requires separate verification.


## Logical and operational responsibilities

OWL supports classification and consistency under the selected semantics. SPARQL retrieves and calculates information under an explicit dataset and selection policy. Integrity validation checks agreement or required explicit data. These are complementary operations.

A skill level qualifies a specification requirement; a proficiency level belongs to an interpretation scheme or curricular expectation. A score is separately recorded. Composition is not just several competencies associated with the same activity; specialization is not an OWL subclass relation between competence individuals.

The built-in patterns do not exhaust possible values. The [IS2020 dataset](../competenceInstances/IS2020/README.md) uses a custom `SpecificationPattern` value with separate checks, without adding an equivalent-class definition.

## Ontology and process

The [CSP](https://github.com/eduknowgpt/OCSP) guides formulation, review, and maintenance. OntoKSD encodes selected products and relationships. Some justifications remain in descriptions or external documents. Reifying an association as a record does not, by itself, make it a gUFO relator.

[Cases](use-cases.md) connect these structures to applications; [evaluation](evaluation.md) locates their evidence and scope.
