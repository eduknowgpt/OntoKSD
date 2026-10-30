# CS2023 Competency Examples in OntoKSD

This directory represents the 22 **Sample Competency Specifications** in the “Competency Framework Examples” section of *Computer Science Curricula 2023* (January 2024), printed pages 398–406. The examples cover Software (5), Systems (8), and Applications (9).

The purpose is to make these examples available as reusable competence specifications in OntoKSD, linking their statements to reference tasks, knowledge requirements, framework-specific skill levels, and desirable dispositions. These instances describe expected performance; they do not record learners, assessment results, or demonstrated competence.

## Contents

- `00 - KnowledgeUnitContent.rq`: shared declarations of 67 unit-level `Knowledge` constituents and their links to CS2023 knowledge units.
- `Competence CS2023_Software*.rq`: five software competency specifications.
- `Competence CS2023_System*.rq`: eight systems competency specifications. Filenames use `System`; source identifiers and resource IRIs use `Systems`.
- `Competence CS2023_Applications*.rq`: nine application competency specifications.
- [competency-examples.md](competency-examples.md): readable source task statements, competence statements, knowledge lists, levels, and dispositions for all 22 examples.
- [source-requirements.json](source-requirements.json): the same source requirements in structured form.
- [knowledge-level-mapping.tsv](knowledge-level-mapping.tsv): all 101 explicit knowledge–level assignments, identified as local modeling choices.
- `queries/`: queries for exploring specifications and comparing source levels with explicit assignments.
- [validation.json](validation.json): executable-check results and the reference-file fingerprints used for validation.

## Competency examples

The table summarizes the instructional subject matter; complete source wording is provided in `competency-examples.md`. “Source levels” reproduces the report's lists and does not imply a level hierarchy or an assignment of every level to every knowledge unit.

| Example | Reference task | Source levels | Dispositions |
| --- | --- | --- | --- |
| Software1 | Develop test cases to determine if a program is functionally correct. | Develop | Meticulous, Persistent, Responsible |
| Software2 | Perform code review for a teammate. | Apply | Collaborative, Communicative, Meticulous |
| Software3 | Work on a team effectively. | Apply | Collaborative, Communicative, Proactive, Responsive |
| Software4 | Make an informed decision regarding which programming language/paradigm to select and use for a specific application. | Explain, Evaluate | Inventive |
| Software5 | Effectively use a programming language’s type system to develop safe and secure software. | Develop | Inventive, Meticulous |
| Systems1 | Develop a version of a CPU-based application to run on a hardware accelerator (GPU, TPU, NPU). | Evaluate, Develop | Meticulous, Inventive |
| Systems2 | Produce a white paper assessing the social and ethical implications of collecting and storing data from a new (or existing) application. | Evaluate, Explain | Meticulous, Responsible, Proactive |
| Systems3 | Secure data from unauthorized access. | Develop | Meticulous, Proactive |
| Systems4 | Create a database for a new application. | Develop | Inventive, Meticulous, Responsible |
| Systems5 | Evaluate the performance of a network. | Evaluate | Meticulous, Proactive |
| Systems6 | Deploy an application component on an operating system/runtime/virtualized operating system/container. | Apply | Meticulous, Persistent, Proactive |
| Systems7 | Improve the performance of a sequential application or component by introducing parallelism. | Evaluate, Develop | Meticulous, Persistent, Proactive |
| Systems8 | Find the performance bottleneck of a given system. | Apply, Evaluate, Develop | Meticulous, Persistent |
| Applications1 | Implement an agent to make strategic decisions in a two-player adversarial game with uncertain actions (e.g., a board game, strategic stock purchasing). | Apply, Develop | Inventive, Persistent |
| Applications2 | Analyze tabular data (e.g., customer purchases) to identify trends and predict variables of interest. | Apply, Develop | Meticulous, Persistent, Responsible |
| Applications3 | Critique a deployed machine learning model in terms of potential bias and correct the issues. | Explain | Responsible |
| Applications4 | Visualize a region’s temperature record. | Apply, Evaluate, Develop | Inventive, Persistent |
| Applications5 | Evaluate and provide recommendations to improve a user-facing system. | Evaluate | Meticulous, Responsible |
| Applications6 | Determine the aspects of an implementation that require revision to support internationalization. | Evaluate | Meticulous, Proactive, Responsible |
| Applications7 | Document the professional implications of a service/software/product for the company that produced it. | Explain | Meticulous, Proactive, Responsible |
| Applications8 | Determine whether to develop an app as a native app or as a cross-platform app. | Explain | Inventive, Meticulous, Responsible |
| Applications9 | Build and optimize a secure web page for evolving business needs using a variety of appropriate programming languages. | Develop | Adaptable, Meticulous, Proactive, Responsible |

## Representation process

### 1. Preserve the source requirements

Each example retains its competence statement in `skos:definition` and its reference task in `hasTaskStatement`, with English language tags. Text wrapping and typographical whitespace are normalized.

A separate `prov:Entity`, such as `cs2023c:Source_Software4`, records the example's full requirement lists and starting page. Its `dcterms:requires` links identify the original knowledge units and skill-level individuals independently. This generic source-document relationship does not assign a skill level to a knowledge unit and is not a new OntoKSD property. The two categories can be distinguished by their types in the loaded vocabularies.

This separation matters for **Software4** (Explain and Evaluate) and **Systems2** (Evaluate and Explain): their complete source-level lists are retained even though the explicit local pairings use Evaluate. Explain is not inferred from Evaluate, replaced by it, or inferred merely from an action verb.

### 2. Identify the competence pattern

Each competence is explicitly typed as `Competence` and `CS2023Competence`, with `hasSpecificationPattern CS2023SpecificationPattern`. It has a reference `Task`, at least one `KnowledgeSkill`, and the source dispositions.

The four required level values are the **individuals** `level:Explain`, `level:Apply`, `level:Evaluate`, and `level:Develop` in `http://purl.org/edu/cs2023#`. The namespace `http://purl.org/cs2023#` denotes the body-of-knowledge vocabulary and document resources; it is not used for these level values.

No atomic/composite classification is imposed. A source description of a “compound task” does not by itself establish an OntoKSD `CompositeCompetence`: that class requires identified component competences and their integration.

### 3. Represent knowledge at the stated granularity

The source lists units such as `AI-Search`, `FPL-Types`, and `DM-Relational`, rather than particular numbered topics. A `KnowledgeUnitContent_*` instance therefore represents knowledge content at that unit-level granularity. For example:

```turtle
cs2023c:KnowledgeUnitContent_AI_Search
    a ontoksd:Knowledge ;
    ontoksd:isCurricularlyRepresentedBy cs2023:KU_AI_Search .

cs2023:KU_AI_Search
    ontoksd:curricularlyRepresentsKnowledge
        cs2023c:KnowledgeUnitContent_AI_Search .
```

The curricular unit remains a `cs2023:KnowledgeUnit`; the semantic constituent is a separate `ontoksd:Knowledge`. Their association does not identify the unit with a competence constituent, assert every topic in the unit as individually required, or reduce the unit to its first topic. Topic-specific knowledge already present in the BoK remains available for more detailed specifications.

### 4. Make knowledge–level assignments explicit

Each `KnowledgeSkill` has exactly one `hasKnowledgeComponent` and one `hasSkillLevel`. Its comment identifies the pairing as a local assignment based on the source requirements. Multiple source levels are neither automatically distributed to every unit nor collapsed into a presumed highest level.

For instance, Applications1 associates AI-Search with Develop and AI-KRR with Apply. Both units and both levels occur in CS2023, but **this particular allocation is an OntoKSD interpretation**, not a correspondence explicitly stated by the report.

The 101 assignments cover every unit listed for each example. They are a usable local realization of the KSD-T pattern, while the independent source records retain the full source requirements. They must not be presented as a uniquely determined or fully paired transcription of CS2023. Additional bindings require an explicit curricular interpretation, particularly when a source level has no assigned pair.

Software3 retains two pairing identifiers that both reference SE-Teamwork at Apply at unit-level granularity. They do not represent two different semantic requirements; count distinct knowledge–level combinations when that distinction matters.

Resource identifiers are stable names. The numeric segments in pairing IRIs are identifiers, not a formal assertion that their current knowledge component is a particular numbered topic. Use `hasKnowledgeComponent` and `hasSkillLevel` to obtain their actual semantics.

### 5. Link desirable dispositions and analyze tasks

Dispositions reuse the existing `http://purl.org/ontoksd#` individuals from `cc2020Vocabulary/ontoKSD-disposition-insert.rq`, including `persistent` and `communicative`. They are not redeclared in every competence file. Their occurrence in the CS2023 examples is documented by the corresponding source record, independently of the vocabulary's folder name.

`TaskActivity`, `TaskComponent`, and `TaskConstraint` provide an optional analytical decomposition of the reference task and its competence statement. These 163 elements are modeling interpretations; they are not a claim that the report individually lists all of them. They do not denote learning activities, instructional execution, or component competences.

A `Task` remains distinct from a `LearningTask`: the former specifies reference performance, while the latter is an instructional unit designed for learners.

## Loading

1. Load the current OntoKSD ontology and its Bloom/skill-level module, resolving imports as appropriate for your environment.
2. Load `cs2023Vocabulary/BoKCS2023_ontoksd.ttl` and execute `cc2020Vocabulary/ontoKSD-disposition-insert.rq` in the intended instance graph.
3. Execute `00 - KnowledgeUnitContent.rq`.
4. Execute any selected competence files, or all 22. They have no ordering dependency on one another.

All files use `INSERT DATA` and contain no deletion operations. Re-execution adds the same RDF triples and does not duplicate resources. Updates target the default graph of the SPARQL dataset; select the intended instance dataset in your application. Instance data are separate from the schema ontology.

The files are SPARQL Update operations, not Turtle ontology documents. Use a SPARQL Update-capable environment; a Turtle parser cannot load a `.rq` file directly. When using Protégé, import a Turtle export of the resulting instance graph together with the ontology and vocabularies.

## Exploring the data

`queries/01 - Competence requirements.rq` lists competences, reference tasks, knowledge-unit requirements, and their assigned levels. `queries/02 - Source levels without pair assignments.rq` identifies source-listed levels not allocated to a pair. The latter returns Software4–Explain and Systems2–Explain for this dataset; these are explicitly documented allocation decisions, not missing source records.

## Validation and scope

Validation checks SPARQL parsing and execution, insertion idempotence and order independence, source wording and requirements, all external references, pair cardinalities, level namespaces and types, and generic constituent/inverse relationships under OWL RL inference. The instance set contains 22 competences, 22 reference tasks, 101 pairings, and 67 shared knowledge references.

Closed-dataset checks establish that the required assertions are present; OWL's open-world semantics alone would not establish documentary completeness. OWL RL checks do not replace full OWL DL consistency verification with HermiT or Pellet, and structural validity does not validate every pedagogical choice of a local pairing.

These are sample specifications, not a complete curriculum or an exhaustive CS2023 competence catalog. They establish neither learner attainment nor sufficiency of instructional coverage. No proficiency scores, assessments, or evidential judgments are introduced.

## Source and namespaces

Source: Joint Task Force on Computer Science Curricula, *Computer Science Curricula 2023*, January 2024, “Competency Framework Examples”, “Sample Competency Specifications”, printed pages 398–406. See the [official CS2023 website](https://csed.acm.org/).

| Prefix | Namespace | Purpose |
| --- | --- | --- |
| `ontoksd` | `http://purl.org/ontoksd#` | Ontology classes, properties, and disposition values |
| `cs2023` | `http://purl.org/cs2023#` | Body-of-knowledge units and report resources |
| `cs2023c` | `http://purl.org/cs2023/competency#` | Example competences, tasks, pairings, source records, and unit-level knowledge |
| `level` | `http://purl.org/edu/cs2023#` | CS2023 skill-level individuals |
