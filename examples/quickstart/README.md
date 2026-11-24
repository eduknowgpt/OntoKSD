# A small OntoKSD worked example

This explicitly fictional dataset shows what a competence specifies, how it is used in two instructional units, and what evidence and criterion support one judgment.

From the repository root:

```bash
python -m pip install -r examples/quickstart/requirements.txt
python examples/quickstart/run.py
```

| File | Purpose |
| --- | --- |
| [demo.ttl](demo.ttl) | Specification, two activations, and one judgment with written and oral evidence records. |
| [01-specification.rq](01-specification.rq) | Retrieve two explicit constituents. |
| [02-reuse.rq](02-reuse.rq) | Retrieve both uses and their modes. |
| [03-judgment-trace.rq](03-judgment-trace.rq) | Retrieve learner, occasion, evidence documents, criterion, and source rubric. |
| [run.py](run.py) | Execute read-only queries in memory and check the expected records. |
| [requirements.txt](requirements.txt) | Dependency version. |

The runner reads the main ontology as Turtle without fetching imports or invoking a reasoner. Its checks concern expected query results, not complete model validation. No data is submitted to GraphDB, written back to the ontology, or attributed to real learners.

Written and oral evidence remain different records. A criterion and source rubric use Dublin Core references. The illustration assigns no proficiency level and makes no claim about educational validity or the authorship of submitted work.

[Setup and interpretation](../../docs/quickstart.md) · [Historical cases](../../docs/use-cases.md)
