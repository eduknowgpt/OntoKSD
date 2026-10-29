# OntoKSD

**An ontology for competence specifications and evidence in Computing Education.**

OntoKSD is a domain ontology expressed in OWL and serialized in RDF/Turtle. It represents reusable competence specifications and connects them to curricular expectations, instructional units, assessment participation, evidence, and proficiency interpretations.

The project adopts a Knowledge–Skill–Disposition perspective while accommodating different specification patterns. Its central distinction is between **what competent performance requires**, **how instruction addresses that requirement**, and **what an individual assessment judgment records**.

## Version and entry point

| Item | Value |
| --- | --- |
| Documented ontology version | 7.0 |
| Main ontology | [`ontoKSDOntology/OntoKSD - SKOS - v7.0.ttl`](ontoKSDOntology/OntoKSD%20-%20SKOS%20-%20v7.0.ttl) |
| Namespace | `http://purl.org/ontoksd#` |
| Version IRI | `http://purl.org/ontoksd#7.0.0` |
| Serialization | RDF/Turtle (`.ttl`) |
| Related process | [Competency Specification Process (CSP)](https://github.com/eduknowgpt/OCSP) |

For reproducible use, record the Git commit as well as the ontology version. Supporting examples and queries should be checked against the ontology revision being used.

## What the ontology represents

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

`Evidence` represents artifacts or documented observations. `EvidencedCompetence` and `EvidencedCompetenceConstituent` record individual judgments referring to the relevant specification, participation, and supporting evidence. Shared artifacts can support separate participant-specific judgments.

### Proficiency and learner records

`ProficiencyScheme` and `ProficiencyLevel` describe interpretation categories. Numeric interpretation additionally uses `Scale` and `ScaleInterval`, separating a score from its classification. `Profile` organizes learner-related records, and `LearningNeed` records an identified educational need.

### External references and interchange

`ExternalCompetenceAlignment` records a qualified correspondence with an externally defined competence, including BNCC references. It identifies the endpoints, alignment type, and a supporting explanation where provided. Alignment does not assert identity or automatically establish curricular compliance.

`OntoKSDRCD` links internal competences to records in the accompanying Reusable Competency Definition (RCD) OWL profile. The `rcd/` directory includes the profile, examples, and SHACL shapes. This is a documentary interoperability representation; complete IEEE conformity or lossless exchange of all OntoKSD relationships requires separate verification.

## Relationship with the CSP

The **Competency Specification Process (CSP)** is documented in a separate repository: [eduknowgpt/OCSP](https://github.com/eduknowgpt/OCSP).

- **CSP** organizes the analysis, authoring, review, and refinement of competence specifications from instructional tasks.
- **OntoKSD** provides the semantic representation of specifications and their educational relationships.

This repository contains ontology-oriented instances and scripts derived from CSP applications. The process documentation and its methodological artifacts remain in the CSP repository.

## Repository guide

| Directory | Contents |
| --- | --- |
| [`ontoKSDOntology/`](ontoKSDOntology/) | Main ontology, local gUFO and SKOS resources, catalog, and supporting knowledge/skill files |
| [`bloomTaxonomy/`](bloomTaxonomy/) | Skill-level ontology and SPARQL scripts for action-verb vocabularies |
| [`cc2020Vocabulary/`](cc2020Vocabulary/) | Scripts for CC2020-oriented knowledge and disposition vocabularies |
| [`cs2013Vocabulary/`](cs2013Vocabulary/) | CS2013 knowledge-model resource |
| [`cs2023Vocabulary/`](cs2023Vocabulary/) | CS2023 knowledge-model resource |
| [`competenceInstances/`](competenceInstances/) | CC2020, CS2023, IS2020, and CSP-related instantiation scripts and materials |
| [`sparql/CQ/`](sparql/CQ/) | Competency-question queries, including variants for selected questions |
| [`sparql/`](sparql/) | Additional retrieval, comparison, and analysis scripts |
| [`proficiency level/`](proficiency%20level/) | Proficiency examples and analysis scripts |
| [`rcd/`](rcd/) | RCD ontology, examples, SHACL shapes, and testing notes |
| [`MOODLE/`](MOODLE/) | CSV examples for competence frameworks and scales, including Portuguese variants |

Files ending in `.rq` may contain either retrieval queries or SPARQL updates. Inspect their contents before execution: updates can insert, modify, or delete repository data.

## Getting started

### 1. Obtain the repository

```bash
git clone https://github.com/eduknowgpt/OntoKSD.git
cd OntoKSD
```

### 2. Open the ontology in Protégé

Open `ontoKSDOntology/OntoKSD - SKOS - v7.0.ttl` in [Protégé](https://protege.stanford.edu/). Check that its imports resolve successfully.

The main ontology imports these resources:

| Import IRI | Local resource |
| --- | --- |
| `http://purl.org/edu/bloom` | `bloomTaxonomy/bloom-taxonomy-punning.ttl` |
| `http://purl.org/nemo/gufo#/1.0.0` | `ontoKSDOntology/gufo.ttl` |
| `http://purl.org/ontoksd/rcd` | `rcd/RCD.ttl` |
| `http://www.w3.org/2004/02/skos/core.rdf` | `ontoKSDOntology/SKOS.rdf` |

**Local import configuration:** the supplied `catalog-v001.xml` contains a Bloom mapping to an absolute path on the development machine. Adjust that mapping for your checkout and configure any other unresolved imports in Protégé. Keep ontology IRIs distinct from local file locations.

### 3. Load selected examples

For SPARQL exploration, load the ontology and required imported resources into a repository such as [GraphDB](https://graphdb.ontotext.com/). Then select the vocabulary and instance scripts needed for a particular example.

Loading the main Turtle file into a triple store does not necessarily retrieve its OWL imports. Likewise, verb-population scripts in `bloomTaxonomy/verbs/` are separate from the imported skill-level ontology.

Review scripts for dependencies, graph targets, existing identifiers, and compatibility with version 7. Avoid executing the entire collection indiscriminately. Execute updates on a dedicated test dataset first.

### 4. Explore the data

The following read-only query retrieves qualified external alignments:

```sparql
PREFIX ontoksd: <http://purl.org/ontoksd#>

SELECT ?competence ?externalCompetence ?alignmentType ?justification
WHERE {
  ?alignment a ontoksd:ExternalCompetenceAlignment ;
      ontoksd:mediatesInternalCompetence ?competence ;
      ontoksd:mediatesExternalCompetence ?externalCompetence ;
      ontoksd:hasAlignmentType ?alignmentType .
  OPTIONAL {
    ?alignment ontoksd:hasAlignmentJustification ?justification .
  }
}
```

Results depend on the loaded instances, graph configuration, and inference settings. More queries are available in [`sparql/CQ/`](sparql/CQ/).

### 5. Use the RCD and Moodle examples

For RCD exploration, inspect `rcd/RCD_examples.ttl` alongside `rcd/RCD.ttl`. Use `rcd/RCD_shapes.ttl` with a SHACL-capable validator when checking the explicit constraints it provides; ontology loading alone does not execute SHACL validation.

The Moodle CSV files illustrate curricular operationalization rather than a complete round-trip OWL integration. Review their format, identifiers, scale configuration, and import requirements for the target Moodle installation before use.

## Interpretation and validation

OntoKSD follows open-world semantics. Missing assertions are not automatically evidence of invalid data or missing learner competence. Domain and range declarations infer types; they are not closed data-entry checks.

Distinguish three kinds of result:

1. **OWL reasoning:** classification, entailments, and consistency under the selected reasoner.
2. **Data validation:** explicit completeness and integrity checks, including the available RCD SHACL shapes.
3. **SPARQL/application processing:** retrieval, arithmetic classification, comparison, and any materialized analytical results.

A consistent ontology does not by itself establish educational validity. Retrieved records do not automatically demonstrate progression, and missing evidence does not diagnose a learning need. Gap-oriented scripts must be interpreted under their stated comparison rules and dataset scope.

The presence of example scripts and queries documents available artifacts, not a claim that every file has been executed successfully under every version or that all referenced curricula are exhaustively covered.

## Research use and citation

OntoKSD is developed within doctoral research on competence specification in Computing Education. Its conceptual references include CoLeaF, CC2020, CS2023, and related curricular resources. Reuse includes gUFO and SKOS; the Bloom and RCD modules provide additional project vocabularies.

When reporting use, identify the ontology version, commit, imported modules, example dataset, and reasoning or query configuration. A repository citation can use:

```bibtex
@misc{gomes_ontoksd_v7,
  author       = {Gomes, Edeyson},
  title        = {{OntoKSD}: An Ontology for Competence Specifications
                  and Evidence in Computing Education},
  howpublished = {GitHub repository},
  url          = {https://github.com/eduknowgpt/OntoKSD},
  note         = {Ontology version 7.0; specify the commit and access date used}
}
```

## Contributions

Use [issues](https://github.com/eduknowgpt/OntoKSD/issues) to report problems or discuss modeling decisions. Contributions may include ontology improvements, documented examples, vocabulary mappings, queries, and reproducible validation cases.

For a reproducible report, include the relevant commit, affected entities, a minimal example, expected behavior, observed behavior, and tool configuration. Methodological contributions to the CSP should be directed to its [separate repository](https://github.com/eduknowgpt/OCSP).

## License

The main ontology's metadata identifies [Creative Commons Attribution 4.0 International (CC BY 4.0)](https://creativecommons.org/licenses/by/4.0/). Imported resources and external curricular material retain their respective attribution and licensing conditions. Consult the relevant source metadata when reusing those resources.
