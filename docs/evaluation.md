# Evidence and evaluation guide

The artifacts below support inspection of the project's claims. They address different questions.

| Evidence | What it documents | Entry point |
| --- | --- | --- |
| GraphDB batch | 57 SELECTs completed, three with zero rows; optional update skipped. | [Provenance](../sparql/CQ/graphdb-results/20261108-235342-114Z-26804/dataset-provenance.md) |
| Offline regression | Parsing, execution, and selected expected-result checks without OWL inference or fetched imports. | [Validation](../sparql/CQ/validation/README.md) |
| Task202 C05 | One judgment, its assessment references, two criteria, three documents, and controlled checks. | [Case](../competenceInstances/CSP/cycle-02/assessment-records/task202/retrospective-c05/README.md) |
| Inference comparison | Additional instructional-unit types returned by CQ9 with inferred statements enabled. | [CQ9 record](../sparql/CQ/graphdb-results/20261108-235342-114Z-26804/fr14-cq9-inference.json) |
| Curricular dataset checks | Dataset-specific correspondence and integrity checks. | [IS2020](../competenceInstances/IS2020/README.md) · [CS2023](../competenceInstances/CS2023/README.md) |
| Publication assessment | 24 automated FOOPS! checks passed for the assessed publication. | [Report](../foops/README.md) |

## GraphDB evidence

The batch `20261108-235342-114Z-26804` records GraphDB 11.0, 54 non-empty SELECT results, three empty results, zero errors, and one skipped update. [Raw results and metadata](../sparql/CQ/graphdb-results/README.md) preserve the run.

The original ruleset and dataset-note fields were empty. Supplementary provenance distinguishes the researcher's load/configuration confirmation, a reconstructed manifest, and subsequent Workbench and CQ9 inspection. Execution establishes what ran and returned; correctness and completeness require comparison with identified expectations.

Archived runs concern the files and configurations identified by their records. They do not automatically cover later revisions. Record hashes and a commit when reproducing a result.

## Local verification

Follow the [regression instructions](quickstart.md#run-the-existing-offline-regression). It reads the ontology and selected fictional/synthetic inputs, parses 58 files, executes 57 SELECTs, and checks selected qualification, inclusion/exclusion, alias, and update behaviors. It neither contacts GraphDB nor invokes a full OWL reasoner.

A fresh local run during this documentation revision on **24 November 2026**, using RDFLib 7.6.0, passed the existing assertions. New outputs were kept outside this repository. The historical validation and batch files were not overwritten. The runner's `revision_date` field identifies its scripted revision, not the date of every subsequent run.

The new [worked example](../examples/quickstart/README.md) is a separate accessible demonstration, explicitly fictional. It does not extend the thesis's empirical evaluation.

## Interpretation

Source inspection examines correspondence with documents; reasoning examines consequences of axioms; query checks depend on data and policy; FOOPS! examines publication and metadata. Moodle illustrates a selected operational projection.

These results support reusable expectations, qualified uses, and inspectable judgment records in the examined cases. Independent semantic confirmation, broader interoperability, comparative effectiveness, and educational validity require evidence suited to those questions.

[Overview](../README.md) · [Cases](use-cases.md)
