# CS2013 Body of Knowledge — Historical Vocabulary

**Status: historical artifact.** This vocabulary was used during the initial development of the Competency Specification Process (CSP) and its integration with OntoKSD. It was subsequently replaced in that workflow by the [CS2023 vocabulary](../cs2023Vocabulary/BoKCS2023_ontoksd.ttl).

[`BoK2013Ontology.ttl`](BoK2013Ontology.ttl) is retained to document this earlier stage, preserve its identifiers, and support the interpretation of records that reference it. Its historical status concerns its role in this project; it does not imply that the CS2013 report has no continuing educational value.

## Origin and initial purpose

At the beginning of the CSP–OntoKSD integration, a structured computing-knowledge vocabulary was needed to support the identification and reuse of knowledge elements in competence specifications. Appendix A, *The Body of Knowledge*, of **Computer Science Curricula 2013 (ACM/IEEE-CS)** provided the source material for this extraction.

The resulting Turtle file made curricular entries and their relationships available as identifiable RDF resources. It provided an initial reference for selecting knowledge content, exploring curricular organization, and connecting that content to OntoKSD.

This is a project-produced representation of the report, not an official ACM/IEEE-CS ontology or a complete competence model.

## File contents

The supplied file contains the following explicitly typed resources:

| Resource type | Count | Purpose |
| --- | ---: | --- |
| `cs2013:KnowledgeArea` | 18 | Broad curricular knowledge areas. |
| `cs2013:KnowledgeUnit` | 162 | Units associated with knowledge areas. |
| `cs2013:Topic` | 1,466 | Knowledge topics and their subdivisions. |
| `cs2013:LearningOutcome` | 1,292 | Expected learning-outcome statements. |
| `cs2013:MasteryLevel` | 3 | Familiarity, Usage, and Assessment. |

These counts describe the stored extraction; they do not certify complete or error-free reproduction of every source entry.

The ontology IRI is `http://purl.org/cs2013`. Its main vocabulary namespace is `http://purl.org/cs2013/`. The file defines classes and properties, represents curricular entries as individuals, and uses SKOS labels, definitions, and broader/narrower annotations. It is a lightweight curricular representation rather than a fully axiomatized domain ontology or a complete implementation of a SKOS concept scheme.

## Early integration with OntoKSD

All 1,466 topic individuals are also explicitly typed as `ontoksd:Knowledge`. This direct typing records an early modeling choice that made curricular topics immediately reusable as knowledge constituents.

It should not be read as a general requirement of the current OntoKSD architecture. Where curricular organization and reusable knowledge content are represented separately, the current `curricularlyRepresentsKnowledge` relation can connect them. Such a mapping has not been retroactively imposed on this historical file.

The CS2013 mastery categories describe expected learning outcomes. They are not learner-specific proficiency records. In particular, the category named `Assessment` is distinct from the OntoKSD class `Assessment`, which represents an evaluative instructional specification. No equivalence with current Bloom-based or CS2023 skill-level values should be assumed from labels alone.

## Transition to CS2023

As the project evolved, the knowledge-reference role was transferred to the CS2023-based vocabulary. The CS2013 extraction remains available as evidence of the earlier implementation approach, rather than as the default vocabulary for new CSP–OntoKSD specifications.

This transition should not be interpreted as a validated one-to-one migration of all identifiers, topics, learning outcomes, or levels. Comparing or migrating historical records requires explicit correspondence checks; retaining the old vocabulary preserves the meaning and context of their original references.

The CSP is maintained in the separate [OCSP repository](https://github.com/eduknowgpt/OCSP). This directory preserves a vocabulary artifact associated with its early integration with OntoKSD, rather than the process implementation itself.

## Preservation and use

- Keep the historical TTL and its identifiers stable when interpreting earlier records.
- Use the CS2023 vocabulary for the project's subsequent knowledge-reference workflow.
- Load this file separately when examining legacy data; do not treat it as a required import of the current OntoKSD.
- Distinguish later corrections or migration mappings from the preserved historical artifact.

The README documents provenance and project history. It does not claim a comprehensive validation of the original extraction, curricular coverage, or compatibility with all current OntoKSD restrictions.

## Source

**ACM/IEEE-CS Joint Task Force on Computing Curricula. Computer Science Curricula 2013.** Appendix A: *The Body of Knowledge*. The report's guidance on learning outcomes and mastery levels appears in Chapter 4.
