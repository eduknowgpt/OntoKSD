# CS2023 Body of Knowledge for OntoKSD

An RDF/OWL and SKOS representation of knowledge from *Computer Science Curricula 2023*, organized for curricular navigation and reuse in OntoKSD competence specifications.

The vocabulary connects two complementary structures: **the curricular organization of the CS2023 Body of Knowledge** and **the knowledge constituents used by OntoKSD**. It supports identifying relevant content, tracing its curricular context, and selecting knowledge references during the [Competency Specification Process (CSP)](https://github.com/eduknowgpt/OCSP).

## Contents

[`BoKCS2023_ontoksd.ttl`](BoKCS2023_ontoksd.ttl) contains the class and property definitions, curricular entries, controlled classification concepts, provenance information, and OntoKSD Knowledge individuals.

| Resource | Count | Purpose |
| --- | ---: | --- |
| Knowledge areas | 17 | Broad thematic areas of computer science. |
| Knowledge units | 160 | Related curricular content grouped within each area. |
| Topics and subtopics | 2,548 | Content entries at different levels of detail. |
| Knowledge individuals | 2,552 | Reusable content constituents for OntoKSD specifications. |
| Illustrative learning outcomes | 114 | Represented expectations concerning student performance. |

These counts describe the resources included in the file. The represented learning outcomes form a subset of the outcomes in the report.

## Knowledge areas

| Code | Area |
| --- | --- |
| AI | Artificial Intelligence |
| AL | Algorithmic Foundations |
| AR | Architecture and Organization |
| DM | Data Management |
| FPL | Foundations of Programming Languages |
| GIT | Graphics and Interactive Techniques |
| HCI | Human-Computer Interaction |
| MSF | Mathematical and Statistical Foundations |
| NC | Networking and Communication |
| OS | Operating Systems |
| PDC | Parallel and Distributed Computing |
| SDF | Software Development Fundamentals |
| SE | Software Engineering |
| SEC | Security |
| SEP | Society, Ethics, and the Profession |
| SF | Systems Fundamentals |
| SPD | Specialized Platform Development |

## Namespaces

| Prefix | Namespace | Use |
| --- | --- | --- |
| `cs2023:` | `http://purl.org/cs2023#` | Curricular classes, properties, entries, and source records. |
| `cs2023k:` | `http://purl.org/cs2023/knowledge#` | OntoKSD Knowledge individuals. |
| `ontoksd:` | `http://purl.org/ontoksd#` | OntoKSD classes and curricular mapping properties. |
| `skos:` | `http://www.w3.org/2004/02/skos/core#` | Concept organization and documentation. |
| `prov:` | `http://www.w3.org/ns/prov#` | Source derivation and provenance. |
| `dcterms:` | `http://purl.org/dc/terms/` | Identifiers, references, and descriptive metadata. |

The ontology resource is `cs2023:BoKCS2023Ontology`.

The curricular namespace `http://purl.org/cs2023#` is distinct from `http://purl.org/edu/cs2023#`, used for skill-level values in the [skill-level and action-verb vocabulary](../bloomTaxonomy/README.md).

## Curricular structure

The vocabulary defines the following class hierarchy:

```text
skos:Concept
├── cs2023:KnowledgeElement
│   ├── cs2023:KnowledgeArea
│   ├── cs2023:KnowledgeUnit
│   └── cs2023:Topic
└── cs2023:IllustrativeLearningOutcome
```

Specific areas, units, topics, and outcomes are represented as individuals of these classes. Topics include subtopics, whose position is expressed through hierarchical relationships.

`cs2023:BoKCS2023Scheme` groups the curricular content. Its top concepts are the 17 knowledge areas.

| Property | Relationship |
| --- | --- |
| `cs2023:hasKnowledgeUnit` | Knowledge area → knowledge unit |
| `cs2023:inKnowledgeArea` | Knowledge unit → knowledge area |
| `cs2023:hasTopic` | Knowledge unit → directly associated topic |
| `cs2023:inKnowledgeUnit` | Topic → directly associated knowledge unit |
| `skos:broader` | Concept → broader concept |
| `skos:related` | Associative connection between concepts |

The local area/unit and unit/topic property pairs have assertions in both directions where provided; they are not declared OWL inverse-property pairs. Hierarchical paths through `skos:broader` provide navigation across topics, subtopics, units, and areas.

Use `skos:broader+` to retrieve descendants at every depth. A subtopic can belong to a unit through an intermediate topic without having a direct `inKnowledgeUnit` assertion.

Knowledge areas and units organize curricular content. They do not represent courses or instructional activities: a CS2023 KnowledgeUnit is distinct from an OntoKSD InstructionalUnit.

## Topics and OntoKSD Knowledge

A **Topic** identifies content within the CS2023 curricular structure. An **OntoKSD Knowledge** individual represents content that can be reused as a competence constituent.

The association is expressed through:

```text
Curricular concept --curricularlyRepresentsKnowledge--> Knowledge
Knowledge --isCurricularlyRepresentedBy--> Curricular concept
```

These properties are inverses. Both directions are explicitly represented in the dataset.

For example:

```turtle
@prefix cs2023:  <http://purl.org/cs2023#> .
@prefix cs2023k: <http://purl.org/cs2023/knowledge#> .
@prefix ontoksd: <http://purl.org/ontoksd#> .
@prefix skos:    <http://www.w3.org/2004/02/skos/core#> .

cs2023:Topic_AL_Complexity_1
    a cs2023:Topic ;
    skos:prefLabel "Complexity Analysis Framework"@en ;
    ontoksd:curricularlyRepresentsKnowledge
        cs2023k:Knowledge_AL_Complexity_1 .

cs2023k:Knowledge_AL_Complexity_1
    a ontoksd:Knowledge ;
    ontoksd:isCurricularlyRepresentedBy
        cs2023:Topic_AL_Complexity_1 .
```

Each Topic has one Knowledge association in this dataset. This population pattern does not impose an exact-one restriction on the general mapping property.

The separation allows curricular organization and competence specification to be queried together while retaining their different roles. A mapping identifies a content correspondence; it does not assign a required performance level or establish learner attainment.

## Illustrative learning outcomes

`cs2023:IllustrativeLearningOutcome` represents an expected form of student performance. Its individuals belong to `cs2023:IllustrativeLearningOutcomeScheme` and are associated with their source knowledge units.

| Property | Relationship |
| --- | --- |
| `cs2023:hasIllustrativeLearningOutcome` | Knowledge unit → outcome |
| `cs2023:isIllustrativeLearningOutcomeOf` | Outcome → knowledge unit; inverse of the preceding property. |
| `cs2023:hasLearningOutcomeCoreStatus` | Outcome → source core-status category |

For example, `cs2023:ILO_FPL_Syntax_NonCore_2` identifies an outcome in Syntax Analysis. Its identifier records the knowledge unit, curricular section, and item number.

Topics describe curricular content; outcomes describe expected performance. Their numbering does not establish a one-to-one correspondence. Selected outcomes also provide a curricular reference for a Knowledge content interpretation through `curricularlyRepresentsKnowledge`, while remaining distinct from the Knowledge constituent itself.

## Curricular classification

Topics use `cs2023:hasCoreStatus` with concepts from `cs2023:CoreStatusScheme`:

| Value | Meaning |
| --- | --- |
| `cs2023:CSCore` | Content expected of every computer science graduate. |
| `cs2023:KACore` | Content recommended for deeper study within a knowledge area, following the report's area-specific guidance. |
| `cs2023:NonCore` | Additional content outside the core categories. |

Outcomes use `hasLearningOutcomeCoreStatus` for their source classification. This separate property preserves their distinction from Topic, the domain of `hasCoreStatus`.

Core status expresses a curricular classification. Required skill levels, course-specific expectations, and individual proficiency are represented separately in OntoKSD.

The vocabulary also provides `hasCurationStatus` and `CurationStatusScheme` for describing the treatment of curricular entries. This metadata concerns vocabulary management rather than curricular importance or learner proficiency.

## Integration with OntoKSD

A Knowledge individual can be selected as the content constituent of a competence or a KnowledgeSkill pairing. The CSP establishes the required performance in relation to the task and adopted specification pattern.

The following fragment illustrates a KnowledgeSkill requirement. It is application data, not an assertion that CS2023 assigns this particular level to the selected topic:

```turtle
@prefix ex:          <https://example.org/data/> .
@prefix ontoksd:     <http://purl.org/ontoksd#> .
@prefix cs2023k:     <http://purl.org/cs2023/knowledge#> .
@prefix cs2023level: <http://purl.org/edu/cs2023#> .

ex:complexityRequirement a ontoksd:KnowledgeSkill ;
    ontoksd:hasKnowledgeComponent
        cs2023k:Knowledge_AL_Complexity_1 ;
    ontoksd:hasSkillLevel cs2023level:Apply .
```

The BoK vocabulary supplies the content reference, OntoKSD supplies the competence structure, and the skill-level vocabulary supplies the performance category. Their explicit associations support traceability between curricular content and competence specifications.

## Metadata and source traceability

Entries use labels, identifiers, notations, definitions, and source references where available:

- `skos:prefLabel` and `rdfs:label` provide readable names.
- `dcterms:identifier` and `skos:notation` provide identifiers and curricular notation.
- `skos:definition` supplies descriptive text where present.
- `prov:wasDerivedFrom` identifies source derivation.
- `dcterms:references` and `dcterms:bibliographicCitation` provide source locators where recorded.

These properties allow a knowledge reference to be followed back to its curricular entry and documented source. Metadata availability varies between entries.

## Loading the vocabulary

1. Open [`BoKCS2023_ontoksd.ttl`](BoKCS2023_ontoksd.ttl) in Protégé or load it into an RDF store as Turtle.
2. Load the [OntoKSD ontology](../ontoKSDOntology/) when its classes, properties, and restrictions are needed.
3. Load the [skill-level vocabulary](../bloomTaxonomy/) when assigning its level values in competence specifications.
4. Configure the query dataset to include the required graphs, or select them explicitly through `GRAPH` clauses.

The file has no `owl:imports` declarations. Referencing external IRIs does not automatically load their ontology definitions.

## Example queries

The following queries use explicitly stored triples and do not require inference.

### List the knowledge areas

```sparql
PREFIX cs2023: <http://purl.org/cs2023#>
PREFIX skos: <http://www.w3.org/2004/02/skos/core#>

SELECT ?area ?label
WHERE {
  ?area a cs2023:KnowledgeArea ;
        skos:prefLabel ?label .
}
ORDER BY ?label
```

### Retrieve topics and subtopics in Algorithmic Foundations

```sparql
PREFIX cs2023: <http://purl.org/cs2023#>
PREFIX ontoksd: <http://purl.org/ontoksd#>
PREFIX skos: <http://www.w3.org/2004/02/skos/core#>

SELECT DISTINCT ?topic ?label ?knowledge
WHERE {
  ?topic a cs2023:Topic ;
         skos:broader+ cs2023:AL ;
         skos:prefLabel ?label ;
         ontoksd:curricularlyRepresentsKnowledge ?knowledge .
}
ORDER BY ?topic
```

### Retrieve CS Core topics

```sparql
PREFIX cs2023: <http://purl.org/cs2023#>
PREFIX skos: <http://www.w3.org/2004/02/skos/core#>

SELECT ?topic ?label
WHERE {
  ?topic a cs2023:Topic ;
         cs2023:hasCoreStatus cs2023:CSCore ;
         skos:prefLabel ?label .
}
ORDER BY ?topic
```

### Retrieve illustrative outcomes from Syntax Analysis

```sparql
PREFIX cs2023: <http://purl.org/cs2023#>
PREFIX skos: <http://www.w3.org/2004/02/skos/core#>

SELECT ?outcome ?statement ?status
WHERE {
  cs2023:KU_FPL_Syntax cs2023:hasIllustrativeLearningOutcome ?outcome .
  ?outcome skos:prefLabel ?statement ;
           cs2023:hasLearningOutcomeCoreStatus ?status .
}
ORDER BY ?outcome
```

## Reference

ACM, IEEE Computer Society, and AAAI. *Computer Science Curricula 2023*. January 2024. See the knowledge-model discussion and the detailed Body of Knowledge.

[CS2023 Body of Knowledge](https://csed.acm.org/wp-content/uploads/2024/01/Body-of-Knowledge-v1-bookmarksv2.pdf).
