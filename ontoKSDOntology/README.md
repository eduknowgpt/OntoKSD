# OntoKSD ontology package

The main artifact is [OntoKSD - SKOS - v7.0.ttl](OntoKSD%20-%20SKOS%20-%20v7.0.ttl). Its version IRI is `http://purl.org/ontoksd#7.0.0`; the filename retains `v7.0`.

| File | Purpose |
| --- | --- |
| [Main Turtle](OntoKSD%20-%20SKOS%20-%20v7.0.ttl) | Documented OWL ontology for specifications and educational relationships. |
| [RDF/XML](OntoKSD%20-%20SKOS%20-%20v7.0.rdf) | Accompanying RDF/XML artifact; identify the serialization and revision used. |
| [Import catalog](catalog-v001.xml) | Local import resolution for a complete checkout. |
| [gUFO](gufo.ttl) | Locally supplied foundational resource. |
| [SKOS RDF](SKOS.rdf) · [SKOS OWL](SKOS.owl) | Locally supplied SKOS resources. |

## Local imports

Keep the checkout structure intact and open the Turtle file in Protégé. The catalog maps:

| Import | Local file |
| --- | --- |
| `http://purl.org/edu/bloom/2.0.0` | `../bloomTaxonomy/bloom-taxonomy.ttl` |
| `http://purl.org/nemo/gufo#/1.0.0` | `gufo.ttl` |
| `http://purl.org/ontoksd/rcd` | `../rcd/RCD.ttl` |
| `http://www.w3.org/2004/02/skos/core.rdf` | `SKOS.rdf` |

The catalog also retains aliases for the unversioned Bloom IRI and the optional CS2023 knowledge model. Relative resolution changes file locations, not ontology IRIs or axioms. Verify import resolution in your Protégé installation; this revision checked the XML and file targets, not full desktop application behavior.

Loading Turtle into an RDF store does not necessarily retrieve imports. For GraphDB, follow the [loader guide](../loadInGraphDB/README.md). Verb population is separate from the imported level ontology.

The main ontology declares CC BY 4.0. Imported resources retain their conditions. Preserve versions, data, queries, and configuration when reporting a result.

[Model](../docs/model.md) · [Quickstart](../docs/quickstart.md)
