# CSP-derived OntoKSD instances

These packages encode selected products of the [Competency Specification Process](https://github.com/eduknowgpt/OCSP), where the source rationale and maintained catalogs are documented.

| Package | Scope |
| --- | --- |
| [Cycle 1](cycle-01/README.md) | Initial Theory of Computation specifications, reference tasks, and qualified uses. |
| [Cycle 2](cycle-02/README.md) | Reuse and specification decisions, with separate assessment layers. |
| [Cycle 3](cycle-03/README.md) | Catalog-guided task design and additional specifications/relationships. |
| [Cycle 4](cycle-04/README.md) | Introductory-programming CM catalog, compositions, and curricular correspondences. |

## Cycle 2 layers

- [Operational layer](cycle-02/cycle-02-operational-layer/README.md): optional course/proficiency information.
- [Historical assessment records](cycle-02/assessment-records/README.md): source-qualified records.
- [Task201](cycle-02/assessment-records/task201/README.md) and [Task202](cycle-02/assessment-records/task202/README.md): specific records and checks.
- [Retrospective C05 supplement](cycle-02/assessment-records/task202/retrospective-c05/README.md): an additional adopted judgment, distinct from the tutor assessment and original batch.

Cycle insertion scripts and `cycleXX-instances.ttl` snapshots are alternatives. Follow the cycle dependencies and [loader guide](../../loadInGraphDB/README.md). Learning and assessment activations are qualified uses, not additional competence identities.

The CM catalog is separate from the C catalog. CSP task links and their reuse counts should not be equated automatically with the number of qualified ontology records.

[Collections](../README.md) · [Cases](../../docs/use-cases.md)
