# Archived GraphDB execution evidence

This directory preserves batch [20261108-235342-114Z-26804](20261108-235342-114Z-26804/dataset-provenance.md).

| Record | Purpose |
| --- | --- |
| [execucao.json](20261108-235342-114Z-26804/execucao.json) | Original metadata and status totals. |
| [resumo.csv](20261108-235342-114Z-26804/resumo.csv) | Per-file status, row counts, timing, and hashes. |
| [resultados/](20261108-235342-114Z-26804/resultados/) | Raw SPARQL JSON results. |
| [Provenance](20261108-235342-114Z-26804/dataset-provenance.md) | Original records and supplementary documentation distinguished. |
| [Load manifest](20261108-235342-114Z-26804/java-load-manifest.json) | Reconstructed input order/fingerprints, linked to the batch through researcher confirmation. |
| [Metadata audit](20261108-235342-114Z-26804/graphdb-metadata-audit.json) | Subsequent configuration and upload inspection. |
| [CQ9 comparison](20261108-235342-114Z-26804/fr14-cq9-inference.json) | Selected result with inferred statements included/excluded. |

The batch completed 57 SELECTs: 54 non-empty, three empty, and no query errors. CQ39b was skipped because it is an update. Completion and non-empty answers are not independent tests of correctness or completeness.

Preserve these records when performing [new runs](../execScript/execCQs.md). Use the separate [validation guide](../validation/README.md) for offline checks.

[Evaluation guide](../../../docs/evaluation.md)
