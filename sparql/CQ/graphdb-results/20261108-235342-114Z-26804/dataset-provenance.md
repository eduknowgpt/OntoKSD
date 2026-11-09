# GraphDB batch provenance

- **Batch ID:** `20261108-235342-114Z-26804`
- **Repository:** `OntoKSD2`
- **Batch time:** 8 November 2026, 20:53 America/Bahia (23:53 UTC)
- **Original records:** [execucao.json](execucao.json) and [resumo.csv](resumo.csv)
- **Batch outcome:** 57 SELECT files completed, including three with zero rows. The optional update was skipped.

## Input data

The researcher confirmed that the repository used for the batch was populated by executing `loadInGraphDB/ontoKSD/SparqlBatchInserter.java` with the loading configuration inspected for this evaluation. The [OntoKSD repository](https://github.com/eduknowgpt/OntoKSD) provides the loader and its inputs. [java-load-manifest.json](java-load-manifest.json) records the loader hash and the 146 enabled SPARQL input files in execution order, with their SHA-256 hashes. The manifest was reconstructed from the inspected files after the batch, and its association with the batch is based on the researcher's confirmation.

The inspected loader has six commented RDF loading calls. The corresponding paths are listed in the manifest as baseline module references, separately from the enabled SPARQL updates. The Workbench import history independently records the completed imports of `OntoKSD - SKOS - v7.0.ttl` and `BoKCS2023_ontoksd.ttl`. See [graphdb-metadata-audit.json](graphdb-metadata-audit.json). The Workbench history covers its recorded uploads, while the researcher-confirmed Java load identifies the SPARQL inputs.

## Query configuration

The original batch metadata records GraphDB 11.0 and the inclusion of inferred triples. The `owl2-rl` ruleset is associated with the original batch on the basis of the researcher's confirmation that the same configuration was used. It was independently observed in `OntoKSD2` during the subsequent CQ9 comparison, which identified the server as GraphDB 11.0.2. See [fr14-cq9-inference.json](fr14-cq9-inference.json).

The original batch files were retained as generated. The load manifest, Workbench audit, and CQ9 record are supplementary documentation.
