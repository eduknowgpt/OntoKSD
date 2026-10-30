# CC2020 Competence Examples for OntoKSD

Three SPARQL Update files instantiate the competence examples A, B, and C from section 4.2.5 of *Computing Curricula 2020* using the OntoKSD competence model.

## Files and examples

| File | Source context | Competence focus | Knowledge-level pairs |
| --- | --- | --- | ---: |
| [`InsertCompetence_A.rq`](InsertCompetence_A.rq) | Computer Engineering | Computer-system and digital-circuit design | 7 |
| [`InsertCompetence_B.rq`](InsertCompetence_B.rq) | Information Technology | Comparison of enterprise cloud networking topologies | 5 |
| [`InsertCompetence_C.rq`](InsertCompetence_C.rq) | Software Engineering | Requirements elicitation and documentation with stakeholders | 4 |

The statements, knowledge-level assignments, and dispositions follow the supplied CC2020 excerpt. Each file provides a specification, not a learner's attainment, an assessment occurrence, or a claim of instructional coverage.

## Representation

Each competence adopts `CC2020SpecificationPattern` and is represented as `CC2020Competence`. Its structure includes:

- KnowledgeSkill constituents through `hasKnowledgeSkill`;
- the source dispositions through `hasDisposition`;
- an explicit reference Task through `hasTask`;
- an Atomic structure value, indicating that component competences are not separately modeled in this example.

Each KnowledgeSkill has exactly one Knowledge through `hasKnowledgeComponent` and one required Bloom-based level through `hasSkillLevel`. KnowledgeSkill is a specification constituent; the files do not model it as a Relator or replace its level with an explicit Skill individual.

| Example | Source level assignments | Dispositions |
| --- | --- | --- |
| A | Creating for three computing-knowledge entries; Applying for four foundational/professional entries. | Self-directed, Meticulous, Inventive |
| B | Analyzing for two computing-knowledge entries; Applying for three foundational/professional entries. | Self-directed, Purpose-driven, Responsible |
| C | Evaluating for Requirements Analysis; Applying for three foundational/professional entries. | Purpose-driven, Responsible, Collaborative |

The levels reproduce the source assignments. They are not inferred from verbs in the competence statement. The level individuals belong to `bloom:CC2020SkillLevel` in the skill-level vocabulary.

## Knowledge references

The insertions reuse individuals from the computing and foundational/professional CC2020 vocabularies. They reference Knowledge constituents, keeping their curricular concepts available through the vocabulary's explicit mappings.

Some source labels are abbreviated, such as Quality Assurance and Requirements Analysis. The pair descriptions retain those source labels while referencing the corresponding project entries, such as Quality Assurance / Control and Requirements Analysis and Specifications. Notes document these label alignments without asserting `owl:sameAs` or unrestricted semantic equivalence.

## Reference tasks and granularity

Each Task reuses the source competency statement through the language-tagged property `hasTaskStatement`. It expresses reference performance at the specification level, rather than a LearningTask or an execution event.

The explicit Task satisfies the current OWL restrictions on CC2020Competence. Atomic is a project granularity choice: multiple KnowledgeSkill constituents or several action verbs do not by themselves constitute a CompositeCompetence. Composition would require explicit component-competence relationships.

## Identifiers and documentation

Competences use `CC2020_Competence_A`, `CC2020_Competence_B`, and `CC2020_Competence_C`. Pair identifiers follow `KS_A_01`, `KS_B_01`, and corresponding sequences.

Readable labels describe the competence; `skos:notation` retains A, B, or C. Source references identify the report, its individual example, and the supplied excerpt. The excerpt is documented by a SHA-256 fingerprint.

Source locators are printed page 51 for A and printed page 52 for B and C, corresponding to PDF pages 52 and 53.

## Loading

Load the current OntoKSD and the [Bloom skill-level vocabulary](https://github.com/eduknowgpt/OntoKSD/blob/main/bloomTaxonomy/bloom-taxonomy.ttl), then execute the three CC2020 vocabulary populations:

1. `cc2020-knowledge-insert.rq`
2. `cc2020-foundational-knowledge-insert.rq`
3. `ontoKSD-disposition-insert.rq`

Execute `InsertCompetence_A.rq`, `InsertCompetence_B.rq`, and `InsertCompetence_C.rq` in a SPARQL Update environment. The three competence insertions are independent once their shared vocabularies are available.

To inspect the populated data in Protégé, export the dataset as Turtle. The `.rq` files are executable updates, not Turtle ontology documents.

## Example query

```sparql
PREFIX ontoksd: <http://purl.org/ontoksd#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

SELECT ?competence ?title ?pair ?knowledge ?level
WHERE {
  VALUES ?competence {
    ontoksd:CC2020_Competence_A
    ontoksd:CC2020_Competence_B
    ontoksd:CC2020_Competence_C
  }
  ?competence rdfs:label ?title ; ontoksd:hasKnowledgeSkill ?pair .
  ?pair ontoksd:hasKnowledgeComponent ?knowledge ;
        ontoksd:hasSkillLevel ?level .
}
ORDER BY ?competence ?pair
```

This query retrieves the 16 source knowledge-level pairings without requiring inference.

## Verification

[`validation.json`](validation.json) records 108 passing checks covering source correspondence, referenced vocabulary individuals, level schemes, task statements, identifier preservation, replacement of the provided example assertions, preservation of unrelated data, repeat insertion, order independence, and useful class/property entailments after OWL RL closure.

The checks used the available local OntoKSD and CC2020 vocabulary files. HermiT/Pellet with all imports were not executed. The data describe the three source examples and the stated OntoKSD modeling choices, not an independent validation of competence attainment.

## Source

*Computing Curricula 2020*, section 4.2.5, examples A–C, printed pages 51–52, as reproduced in the supplied `CC2020 instances.docx` excerpt.

[CC2020 report](https://www.acm.org/binaries/content/assets/education/curricula-recommendations/cc2020.pdf).
