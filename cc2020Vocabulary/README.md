# CC2020 Vocabulary for OntoKSD

Three SPARQL Update files provide reusable knowledge and disposition entries for competence specification in [OntoKSD](https://github.com/eduknowgpt/OntoKSD), based on the *Computing Curricula 2020* (CC2020) report.

## Files

| File | Content |
| --- | --- |
| [`cc2020-knowledge-insert.rq`](cc2020-knowledge-insert.rq) | The 34 computing knowledge areas in CC2020 Table 4.1. Curricular concepts are organized in one SKOS scheme and six collections, and linked to separate OntoKSD `Knowledge` individuals through `curricularlyRepresentsKnowledge`. |
| [`cc2020-foundational-knowledge-insert.rq`](cc2020-foundational-knowledge-insert.rq) | The 13 foundational and professional knowledge entries in CC2020 Table 4.2. Each curricular concept is linked to a separate `Knowledge` individual. Their allocation to foundational or professional types is an OntoKSD interpretation, not an explicit partition in the report. |
| [`ontoKSD-disposition-insert.rq`](ontoKSD-disposition-insert.rq) | The 11 prospective dispositions listed in CC2020 Table 4.4 and 16 OntoKSD-curated extensions, organized in separate SKOS schemes. Entries are reusable `Disposition` individuals; extensions are explicitly distinguished from the report's listed entries. |

Definitions are project-authored interpretations, not quotations from CC2020. The vocabularies support specification and reuse; they do not constitute complete competences or evidence of learner attainment. Required skill levels are assigned separately, for example through `KnowledgeSkill` pairings.

## Usage

1. Load the current OntoKSD ontology into an RDF environment supporting SPARQL Update.
2. Execute the required `.rq` files. There is no required order among the three scripts.
3. Query the resulting dataset or export it as Turtle for inspection in Protégé.

The files use `INSERT DATA`; they are not Turtle documents or OWL imports. They add statements without removing previous data. When replacing an older vocabulary version, replace its dedicated population graph or remove obsolete statements selectively while preserving application data.

The revised files preserve the existing IRIs of `Knowledge` and `Disposition` constituents. Curricular queries should follow `curricularlyRepresentsKnowledge` to connect the new curricular concepts to the knowledge constituents.

## Source

[Computing Curricula 2020](https://www.acm.org/binaries/content/assets/education/curricula-recommendations/cc2020.pdf), Tables 4.1, 4.2, and 4.4, printed pages 49–51.
