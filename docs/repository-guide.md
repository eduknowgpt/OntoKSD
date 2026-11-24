# Repository map

This guide locates the implementation and research materials without requiring a full dataset load.

| Directory | Role | Documentation |
| --- | --- | --- |
| `ontoKSDOntology/` | Main OWL artifact and local foundational imports. | [Ontology package](../ontoKSDOntology/README.md) |
| `bloomTaxonomy/` | Required cognitive-level values and action-verb vocabulary. | [Vocabulary](../bloomTaxonomy/README.md) |
| `cc2020Vocabulary/` | Knowledge and disposition population. | [Guide](../cc2020Vocabulary/README.md) |
| `cs2013Vocabulary/` | CS2013 knowledge organization. | [Guide](../cs2013Vocabulary/README.md) |
| `cs2023Vocabulary/` | CS2023 knowledge organization. | [Guide](../cs2023Vocabulary/README.md) |
| `competenceInstances/` | Curricular specifications and CSP-derived encodings. | [Datasets](../competenceInstances/README.md) |
| `sparql/CQ/` | Question-family views, fixtures, and execution records. | [Queries](../sparql/CQ/README.md) |
| `sparql/` | Additional retrieval and analysis views. | [Guide](../sparql/README.md) |
| `loadInGraphDB/` | Java loading sequence and dependencies. | [Loader](../loadInGraphDB/README.md) |
| `proficiency level/` | Fictional score/category and comparison policies. | [Example](../proficiency%20level/README.md) |
| `rcd/` | Documentary exchange profile, examples, and shapes. | [Profile](../rcd/README.md) |
| `MOODLE/` | Framework and scale projections. | [Moodle](../MOODLE/README.md) |
| `foops/` | Captured publication assessment. | [FOOPS!](../foops/README.md) |
| `examples/quickstart/` | New fictional demonstration for first use. | [Worked example](../examples/quickstart/README.md) |
| `docs/` | Presentation, concepts, cases, and evidence. | [Guides](README.md) |

Files ending in `.rq` can be SELECT queries or updates. The core CQ collection contains 57 SELECTs and one optional update; other directories have their own loading operations. Read the dataset guide before choosing a loading path.

The main ontology is the stable representational reference. A guide, dataset, or script can have a different revision date. Preserve the relevant commit and fingerprints when reporting an experiment.

[Project overview](../README.md)
