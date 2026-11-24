# Get a first result with OntoKSD

Choose a small local example, ontology inspection in Protégé, or the larger GraphDB datasets.

## Route 1 — local worked example

The demonstration shows one specification reused in two instructional units and a recorded judgment linked to evidence and a criterion. It runs without a server or network access after installing its dependency.

From a complete checkout:

```bash
python -m venv .venv
```

Activate the environment on Windows PowerShell:

```powershell
.\.venv\Scripts\Activate.ps1
```

Or on macOS/Linux:

```bash
source .venv/bin/activate
```

Then run:

```bash
python -m pip install -r examples/quickstart/requirements.txt
python examples/quickstart/run.py
```

RDFLib 7.6.0 is the version used for the documented offline regression. The runner loads the main Turtle file and [fictional data](../examples/quickstart/demo.ttl) into memory. It does not fetch imports or invoke an OWL reasoner.

### Expected results

| Query | Question | Expected result |
| --- | --- | --- |
| [01-specification](../examples/quickstart/01-specification.rq) | What does the competence specify? | Two constituents: algorithm knowledge and an explanation skill. |
| [02-reuse](../examples/quickstart/02-reuse.rq) | Where and how is the same specification addressed? | Two activations: a written laboratory explanation and an oral project discussion. |
| [03-judgment-trace](../examples/quickstart/03-judgment-trace.rq) | What supports the judgment? | Two evidence records, the same learner and occasion, and the criterion and source rubric. |

The last message is:

```text
PASS: two constituents, two instructional uses, and two evidence records retrieved.
```

Written and oral records may contribute to the same judgment without implying identical evidential meaning. No proficiency level is assigned. The example does not establish educational adequacy or complete ontology conformance.

Criteria use `dcterms:references`, following the documentary convention in the retrospective case. This is not a new OntoKSD property or an automatic rule. The [historical cases](use-cases.md) have separate evidential roles.

## Route 2 — inspect the ontology

Open [the main Turtle file](../ontoKSDOntology/OntoKSD%20-%20SKOS%20-%20v7.0.ttl) in Protégé. The adjacent [catalog](../ontoKSDOntology/catalog-v001.xml) maps imports to relative paths within the checkout. Keep the structure intact and verify import resolution in your installation.

Start with `Competence`, `CompetenceActivation`, `AssessmentParticipation`, and `EvidencedCompetence`; the [model guide](model.md) explains their roles.

## Route 3 — explore the larger datasets

Use the [GraphDB loader](../loadInGraphDB/README.md) and select an [instance dataset](../competenceInstances/README.md). Loading updates changes the selected repository; the local example does not contact it. Turtle snapshots and corresponding insertion scripts are alternatives, not independent datasets to be loaded together.

Explore the [query collection](../sparql/CQ/README.md) or the read-only [CQ1 desktop explorer](../sparql/CQ/viewer/README.md).

## Run the existing offline regression

From the repository root, in the same dependency environment:

```bash
python sparql/CQ/validation/validate_local.py --project-root .
```

The runner prints the temporary report directory. It parses 58 root-level CQ files, executes 57 SELECTs, and checks selected expected behaviors. The optional update runs only on a disposable in-memory graph. See the [validation guide](../sparql/CQ/validation/README.md) for scope; this is not a full OWL consistency test or a new GraphDB batch.

[Overview](../README.md) · [Evaluation](evaluation.md)
