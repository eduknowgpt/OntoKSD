# Skill-Level and Action-Verb Vocabulary

An OWL vocabulary for representing framework-specific skill levels, their ordinal organization, and action verbs used to describe expected performance in computing education.

This module belongs to [OntoKSD](https://github.com/eduknowgpt/OntoKSD). It supports competence specification by providing controlled level values for knowledge–level requirements and a separately maintained vocabulary of supporting action expressions. It can also be loaded independently for vocabulary exploration and querying.

## Files

| File | Purpose |
| --- | --- |
| [`bloom-taxonomy.ttl`](bloom-taxonomy.ttl) | Core ontology: classes, properties, restrictions, and ten named skill-level individuals. |
| [`verbs/bloom-taxonomy-actionVerbType-insert.rq`](verbs/bloom-taxonomy-actionVerbType-insert.rq) | SPARQL Update that inserts the three controlled verb-type individuals. |
| [`verbs/bloom-taxonomy-verbs-insert.rq`](verbs/bloom-taxonomy-verbs-insert.rq) | SPARQL Update that inserts verbs, level associations, type memberships, labels, definitions, examples, and source references. |
| [`verbs/bloom-taxonomy-dbpedia-insert.rq`](verbs/bloom-taxonomy-dbpedia-insert.rq) | Optional SPARQL Update that adds informational DBpedia links through `rdfs:seeAlso`. |

The core ontology contains no named `ActionVerb` or `ActionVerbType` individuals. Their population is deliberately separated so that lexical content can evolve independently of the core model.

The published population provides **154 action-verb individuals**, **216 verb–level associations**, **three verb types**, and **42 optional DBpedia links**.

## Namespaces and identity

```turtle
@prefix bloom:   <http://purl.org/edu/bloom#> .
@prefix cs2023:  <http://purl.org/edu/cs2023#> .
```

- Ontology IRI: `http://purl.org/edu/bloom`
- Version IRI: `http://purl.org/edu/bloom/2.0.0`

The namespace IRIs identify vocabulary terms. The repository files provide the artifacts to download and load; applications should not assume that every namespace IRI resolves to an ontology document.

## Skill-level model

`bloom:SkillLevel` has two disjoint subclasses:

| Scheme class | Named individuals in adopted order |
| --- | --- |
| `bloom:CC2020SkillLevel` | `bloom:Remembering`, `bloom:Understanding`, `bloom:Applying`, `bloom:Analyzing`, `bloom:Evaluating`, `bloom:Creating` |
| `cs2023:CS2023SkillLevel` | `cs2023:Explain`, `cs2023:Apply`, `cs2023:Evaluate`, `cs2023:Develop` |

The six CC2020-oriented values follow the revised Bloom cognitive-process categories. The four CS2023 values preserve that framework's terminology.

**Specific levels are individuals, not classes.** The current module does not use OWL punning. The scheme classes group the values and support framework-specific restrictions. Their disjointness prevents one individual from belonging to both schemes; it does not assert that distinct schemes cannot be compared.

A `SkillLevel` qualifies expected performance. It is distinct from an OntoKSD `Skill` constituent and from a `ProficiencyLevel` used to interpret assessed performance or express a curricular proficiency expectation. Similarly named values, such as `bloom:Applying` and `cs2023:Apply`, are not asserted to be identical or equivalent.

### Ordering

| Property | Meaning and characteristics |
| --- | --- |
| `bloom:directPrecedes` | Immediate successor; functional, asymmetric, and irreflexive. |
| `bloom:directFollows` | Immediate predecessor; functional and inverse of `directPrecedes`. |
| `bloom:hasOrdinalPosition` | Functional integer-valued property; each framework-specific level requires exactly one `xsd:int` value. |

Functionality permits at most one immediate successor and predecessor. Irreflexivity excludes a level preceding itself; asymmetry excludes reciprocal immediate ordering. The inverse relation inherits the corresponding exclusions. Neither ordering property is declared transitive, because an indirect successor is not an immediate successor. Scheme-specific restrictions keep ordering links within their scheme.

These axioms do not establish a complete linear order by themselves. Longer cycles, duplicate ordinal positions, and agreement between positions and ordering links require separate checks. Ordinal positions organize categories; they do not measure cognitive distances, establish cross-scheme equivalence, prescribe instructional prerequisites, or describe learner progression.

## Action-verb model

`bloom:ActionVerb` represents an identifiable lexical action expression. Each verb requires at least one association with a `SkillLevel` and at least one `ActionVerbType`.

| Property | Direction | Inverse |
| --- | --- | --- |
| `bloom:isActionVerbFor` | `ActionVerb` → `SkillLevel` | `bloom:hasActionVerb` |
| `bloom:hasActionVerbType` | `ActionVerb` → `ActionVerbType` | `bloom:isActionVerbTypeFor` |

These associations are not functional: a verb can occur at multiple levels and belong to multiple types. `ActionVerb`, `ActionVerbType`, and `SkillLevel` are mutually disjoint classes.

Verb entries use the `bloom:` namespace even when they are listed in CS2023. For example, `bloom:explain` denotes a lexical entry, whereas `cs2023:Explain` denotes a skill-level category. They serve different purposes.

### Controlled verb types

| Individual | Membership criterion |
| --- | --- |
| `bloom:StandardVerb` | Listed in the baseline list in Table 1 of *Bloom's for Computing*. “Standard” refers to that source list, not a universal or exclusive list of Bloom verbs. |
| `bloom:ComputingVerb` | Listed in the enhanced list in Table 2 of *Bloom's for Computing*. It does not classify every verb used in computing. |
| `bloom:CS2023Verb` | Explicitly listed under a skill-level category in Table 1 of CS2023. It does not imply that the expression originated in CS2023. |

The types are individuals of `ActionVerbType`, not subclasses of `ActionVerb`. Membership can overlap and does not independently assign a level.

### Sources and interpretation

The population references:

- [*Bloom's for Computing*, DOI 10.1145/3587276](https://doi.org/10.1145/3587276), Tables 1–2.
- [CS2023 report](https://csed.acm.org/wp-content/uploads/2025/11/CS2023-Report.htm), Table 1, for framework-specific applicable verbs.

`dcterms:references` records sources on vocabulary entries and types. The current population does not use `owl:Axiom` annotations to attribute individual verb–level links. Consequently, when an entry has multiple references and level associations, the graph alone does not identify which source supports each particular association. Consult the source tables when that distinction matters.

Definitions and examples are project-authored explanatory glosses. They are not source quotations or independently validated task classifications. A verb alone does not determine cognitive demand: interpretation also depends on the knowledge, expected result, task conditions, and assessment criteria.

The optional DBpedia links provide related information about a relevant sense, process, tool, or product. They do not express identity, exact lexical equivalence, or evidence for assigning a skill level.

## Loading the vocabulary

### Core ontology only

Open `bloom-taxonomy.ttl` in Protégé or another OWL-compatible tool. This provides the schema, ten level individuals, and their ordering, without populating verbs or verb types.

### Core ontology and verb population

Use an RDF store or library supporting SPARQL Update:

1. Load `bloom-taxonomy.ttl` as Turtle.
2. Execute `verbs/bloom-taxonomy-actionVerbType-insert.rq`.
3. Execute `verbs/bloom-taxonomy-verbs-insert.rq`.
4. Optionally execute `verbs/bloom-taxonomy-dbpedia-insert.rq`.

The `.rq` files are executable `INSERT DATA` updates, not Turtle documents or OWL imports. To inspect the populated vocabulary in Protégé, export the resulting graph as Turtle and open it there.

For the examples below, load the files into the same default graph, or configure the query dataset to include their graphs. When using named graphs, adapt the queries and updates to the store's graph policy.

Start from a clean population when replacing an older version. `INSERT DATA` adds statements; it does not remove obsolete mappings, categories, references, or links. Repeating insertion of the same ground triples does not create duplicate RDF statements, but it also does not synchronize an outdated dataset.

## Integration with OntoKSD

OntoKSD uses `hasSkillLevel` to associate a `KnowledgeSkill` constituent with a required level. An optional `hasBloomLevelJustificationVerb` association records wording supporting the assignment.

The following fragment is illustrative application data. Replace `https://example.org/ontoksd#` with the namespace used by your OntoKSD deployment; the example namespace is not an identifier declared by this module.

```turtle
@prefix onto:  <https://example.org/ontoksd#> .
@prefix ex:    <https://example.org/data/> .
@prefix bloom: <http://purl.org/edu/bloom#> .

ex:knowledgeSkill01 a onto:KnowledgeSkill ;
    onto:hasKnowledgeComponent ex:algorithmKnowledge ;
    onto:hasSkillLevel bloom:Applying ;
    onto:hasBloomLevelJustificationVerb bloom:apply .
```

The OntoKSD properties in this example belong to the host ontology, not this imported module. Assigning a level states a specification requirement; it does not establish learner attainment. Referencing a verb does not automatically infer or validate that level.

## Example queries

### List levels in each scheme

```sparql
PREFIX bloom:  <http://purl.org/edu/bloom#>
PREFIX cs2023: <http://purl.org/edu/cs2023#>

SELECT ?scheme ?level ?position
WHERE {
  VALUES ?scheme { bloom:CC2020SkillLevel cs2023:CS2023SkillLevel }
  ?level a ?scheme ;
         bloom:hasOrdinalPosition ?position .
}
ORDER BY ?scheme ?position
```

### Retrieve CS2023-listed verbs associated with Develop

```sparql
PREFIX bloom:  <http://purl.org/edu/bloom#>
PREFIX cs2023: <http://purl.org/edu/cs2023#>
PREFIX rdfs:   <http://www.w3.org/2000/01/rdf-schema#>

SELECT DISTINCT ?verb ?label
WHERE {
  ?verb bloom:hasActionVerbType bloom:CS2023Verb ;
        bloom:isActionVerbFor cs2023:Develop ;
        rdfs:label ?label .
}
ORDER BY ?label
```

### Find subsequent levels through the asserted sequence

```sparql
PREFIX bloom: <http://purl.org/edu/bloom#>

SELECT ?level ?position
WHERE {
  bloom:Applying bloom:directPrecedes+ ?level .
  ?level bloom:hasOrdinalPosition ?position .
}
ORDER BY ?position
```

These queries use explicitly populated properties and do not require inverse-property materialization. Retrieving an inverse direction through a property that is not explicitly asserted requires reasoning, materialization, or rewriting the query to use the asserted direction.

## Scope and validation

The module supports controlled references, vocabulary filtering, framework-specific level selection, and ordinal navigation. It does not automatically classify tasks from verbs, infer equivalence between schemes, assess learners, or determine proficiency.

OWL restrictions express logical conditions under open-world semantics. An existential restriction does not by itself report that an expected explicit statement is missing. Operational checks should separately examine population completeness, source correspondence, cycle absence, ordinal uniqueness, and compatibility with the host ontology's competence-pattern restrictions.

The source references document the educational basis of the vocabulary; they do not imply endorsement of this implementation by the source organizations.
