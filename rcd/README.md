# Reusable Competency Definition Profile for OntoKSD

An RDF/OWL application profile for reusable competency-definition records, with SHACL validation and examples of integration with [OntoKSD](https://github.com/eduknowgpt/OntoKSD).

The profile is inspired by the data-model purpose of [IEEE 1484.20.1-2007](https://standards.ieee.org/ieee/1484.20.1/4012/): identifying, describing, and sharing reusable competency definitions. It supplies a documentary representation that remains distinct from OntoKSD's internal competence specification and learner-specific evidential records.

The OWL model and shapes define this project's RDF profile. Passing its validation does not establish complete IEEE conformance or conformance to an external XML binding. No automatic exporter or importer for such a binding is included.

## Files

| File | Purpose |
| --- | --- |
| [`RCD.ttl`](RCD.ttl) | Six structural classes, properties, OWL restrictions, and documentation. |
| [`RCD_shapes.ttl`](RCD_shapes.ttl) | SHACL Core and SPARQL constraints for the RDF profile and its OntoKSD mapping. |
| [`RCD_examples.ttl`](RCD_examples.ttl) | Four positive examples: complete, minimal, OntoKSD-linked, and name-only Statement. |
| [`RCD_invalid_examples.ttl`](RCD_invalid_examples.ttl) | Fourteen negative test cases, represented by fifteen records. |
| [`Testes Protege.txt`](Testes%20Protege.txt) | Portuguese instructions for inspection, reasoning, and SHACL validation. |
| [`catalog-v001.xml`](catalog-v001.xml) | Local resolution of the RCD ontology IRI to `RCD.ttl`. |
| [`validation.json`](validation.json) | Executed checks, expected negative cases, and validation scope. |

The supplied text and Turtle validation reports provide the actual positive and negative SHACL results.

## Namespace

```turtle
@prefix rcd:     <http://purl.org/ontoksd/rcd#> .
@prefix ontoksd: <http://purl.org/ontoksd#> .
```

The ontology IRI is `http://purl.org/ontoksd/rcd`. It identifies this project module, not an official IEEE namespace.

## Model

```text
RCD
├── hasIdentifier → LongIdentifier
├── title / description
├── hasDefinition → Definition
│   ├── modelSource
│   └── hasStatement → Statement
│       ├── hasStatementID → LongIdentifier
│       ├── statementName
│       ├── statementText
│       └── hasStatementToken → Vocabulary
└── hasMetadata → Metadata
```

| Class | Role and profile constraints |
| --- | --- |
| `RCD` | A reusable definition record with exactly one identifier, at least one language-tagged title, and optional description, definition, and metadata. |
| `LongIdentifier` | A compound identifier with exactly one `catalog` and one `entry`. |
| `Definition` | A structured description with one or more Statements and an optional single `modelSource`. |
| `Statement` | A characteristic of the definition, expressed through at least one of ID, name, text, or token. |
| `Vocabulary` | A controlled token with exactly one `vocabularySource` and one `vocabularyValue`. |
| `Metadata` | Optional schema label, schema version, and additional literal metadata. |

These six structural types are mutually disjoint in the profile. This prevents a component from simultaneously serving as incompatible record types.

The profile selects at most one structured Definition and one Metadata block per RCD. The corresponding object properties are functional because each record has at most one such target. This local design choice should not be generalized to every competency exchange model.

`hasStatementID`, `statementName`, and `hasStatementToken` are optional and have maximum cardinality one per Statement. Statement text can be expressed in several languages. A Statement containing only a name is permitted, as is text combined with a token. A target exchange binding may impose additional choices that an exporter must handle.

## Literal and language conventions

- Titles, descriptions, and statement texts use `rdf:langString`.
- Each of those properties permits at most one value per language in SHACL.
- Identifier fields, model source, statement name, vocabulary fields, and metadata schema fields use `xsd:string`.
- Present textual fields must contain non-whitespace content under the shapes.
- `additionalMetadata` accepts literal content. An arbitrary RDF resource is not a value of that datatype property.

The literal representation of additional metadata is an application approximation. It does not preserve every possible structured metadata block from another interchange model. Missing optional schema fields do not cause OWL or SHACL to insert default values.

Statements are unordered associations. RDF graphs do not preserve repeated identical triples as bag occurrences. Ordering or multiplicity beyond separately identified component nodes requires a dedicated representation or serialization policy.

## Identifiers

An identifier combines a cataloguing scheme and its entry:

```turtle
@prefix ex:  <https://example.org/records/> .
@prefix rcd: <http://purl.org/ontoksd/rcd#> .

ex:identifier a rcd:LongIdentifier ;
    rcd:catalog "URI" ;
    rcd:entry "https://example.org/competency-definitions/dfa-design" .
```

The shapes detect two records using the same catalog/entry pair within the graph being validated. They do not certify global uniqueness, URI resolution, registry ownership, or complete URI lexical conformance. These require application or registry procedures.

No OWL key automatically merges records or identifier nodes merely because their strings match.

## Integration with OntoKSD

OntoKSD supplies the specialized class and inverse mapping:

```text
OntoKSDRCD SubClassOf rcd:RCD

Competence --hasRCD--> OntoKSDRCD
OntoKSDRCD --representsCompetence--> Competence
```

Each OntoKSDRCD represents exactly one internal Competence under the OntoKSD class restriction. A generic `rcd:RCD` is not required to have that project-specific association.

The linked positive example includes:

```turtle
@prefix ex:      <http://purl.org/ontoksd/rcd/examples#> .
@prefix rcd:     <http://purl.org/ontoksd/rcd#> .
@prefix ontoksd: <http://purl.org/ontoksd#> .

ex:RCD_Valid_03 a rcd:RCD, ontoksd:OntoKSDRCD ;
    rcd:title "Design deterministic finite automata"@en ;
    rcd:hasIdentifier ex:ID_RCD_Valid_03 ;
    ontoksd:representsCompetence ex:Competence_DFADesign .

ex:Competence_DFADesign a ontoksd:Competence ;
    ontoksd:hasRCD ex:RCD_Valid_03 .
```

The complete identifier is provided in `RCD_examples.ttl`. The record and the represented competence have distinct nodes. The mapping does not assert `owl:sameAs`, transfer learner evidence, or make a record a competence constituent.

### Content mapping

An export policy should state how OntoKSD content is represented:

| OntoKSD information | RCD representation |
| --- | --- |
| Internal Competence reference | `representsCompetence`, with inverse `hasRCD`. |
| Exchange-record identifier | `hasIdentifier`, using an explicitly selected catalog and entry. |
| Competence title | Selected language-tagged `rcd:title` values. |
| Human-readable competence description | Optional `rcd:description`. |
| Structured specification content | Statements under an identified `modelSource`. |
| Additional documentary information | Metadata literals or explicitly documented extension predicates. |

The exact treatment of KnowledgeSkill, dispositions, reference tasks, and other specification structures belongs to that export policy. Their meanings are not automatically reconstructed from free-text statements. A learner's score, proficiency interpretation, and evidence remain in OntoKSD's individual-record layer.

The base RCD ontology does not import OntoKSD. This keeps the documentary vocabulary independent and avoids a circular dependency when OntoKSD imports RCD.

## Validation

The shapes validate:

- required fields, maximum counts, datatypes, and nonblank content;
- language uniqueness for multilingual fields;
- linked identifiers, definitions, statements, tokens, and metadata;
- separation of the six structural record types;
- local catalog/entry uniqueness;
- exactly one directly asserted represented Competence for OntoKSDRCD;
- distinct record and competence nodes in that mapping.

The shapes are open: additional predicates may be used without a general closed-property prohibition. They require a processor supporting **SHACL Core and SHACL-SPARQL**. The duplicate-identifier and self-mapping checks use SPARQL constraints.

OWL checks logical consequences and consistency under open-world semantics. Missing explicit values may remain compatible with existential restrictions. SHACL checks the supplied data graph, including absent required fields. These are complementary operations; importing the shapes into an OWL editor does not run validation.

## Running the examples

### Protégé

Open `RCD.ttl` and load the positive data into a test ontology. For the complete meaning of the OntoKSD mapping, also load OntoKSD with its imports resolved. The supplied catalog maps only the RCD ontology IRI; other imports need their own resolution.

Use the available OWL reasoner for logical inspection. Use a SHACL processor, or an installed compatible extension, for data validation. See `Testes Protege.txt` for the separate procedures and expected outcomes.

### Command line

Install pySHACL in the Python environment used for validation:

```shell
python -m pip install pyshacl
```

Validate positive examples:

```shell
python -m pyshacl -s RCD_shapes.ttl -e RCD.ttl -i none -m -f human RCD_examples.ttl
```

Expected: `Conforms: True`, exit code `0`.

Validate the negative cases separately:

```shell
python -m pyshacl -s RCD_shapes.ttl -e RCD.ttl -i none -m -f human RCD_invalid_examples.ttl
```

Expected: `Conforms: False`, exit code `1`. Nonconformance is the intended test result. Combining the positive and negative data produces a nonconforming graph.

`-e` supplies the ontology, `-i none` avoids pre-validation inference, and `-m` checks the shapes against Meta-SHACL. The example mappings explicitly include their types and directions, so these commands do not require remote imports or inverse-property materialization.

## Executed checks

The delivered examples and shapes passed 41 checks: all four positive cases conform, all fourteen negative cases are rejected with their expected constraint components, the shapes pass Meta-SHACL, and the positive integration example conforms with the available OntoKSD schema loaded.

The negative cases exercise missing fields, empty content, repeated language values, incompatible metadata values, identifier duplication, excessive cardinality, incomplete nested records, self-mapping, and conflicting structural types. Case 09 uses two records to make identifier duplication observable.

The positive graph also passed explicit disjointness checks after OWL RL closure. HermiT/Pellet and complete external-import closure were not executed. Validation results concern this profile and its examples, not general IEEE certification.

## References

- [IEEE 1484.20.1-2007 — Data Model for Reusable Competency Definitions](https://standards.ieee.org/ieee/1484.20.1/4012/).
- [1EdTech RDCEO Information Model](https://www.imsglobal.org/competencies/rdceov1p0/imsrdceo_infov1p0.html).
- [W3C OWL 2 Primer](https://www.w3.org/TR/owl2-primer/).
- [W3C Shapes Constraint Language](https://www.w3.org/TR/shacl/).
