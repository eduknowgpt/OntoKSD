# CSP Cycle 01: Tasks and Competence Specifications

This dataset represents the five instructional problems in CSP cycle 01 and their reviewed competence specifications in the current OntoKSD vocabulary. It contains **17 competences, 45 KnowledgeSkill pairings, five reference Tasks, five LearningTasks, five Assessments, and 52 explicitly qualified competence activations**.

The dataset is grounded in the English-language documentation of CSP cycle 01, including problem statements, CSP reports, expert reviews, implemented adjustments, and Task01’s second-iteration decision. These documents are maintained in the [CSP repository](https://github.com/eduknowgpt/OCSP/tree/main/CSP/cycles/cycle-01) and referenced as the basis for the OntoKSD instances.

## Task and competence coverage

| Task | Problem | Reviewed competence set |
| --- | --- | --- |
| Task01 | Vending machine | C01, C06, C02, C03, C04, C05 |
| Task02 | Traffic control | C07, C08, C09, C10, C05 |
| Task03 | Farmer Robot | C06, C11, C03, C04, C05 |
| Task04 | Return of the Farmer Robot | C12, C13, C14, C03, C05 |
| Task05 | Farmer Robot and Feeder Robot | C07.01, C08.01, C09.01, C10, C05 |

Task01 retains C01 and adds C06, following its documented additive refinement. C01 is not silently replaced. Task05 uses its three reviewed specializations while retaining C10 and C05 through reuse.

## Competence specifications

The levels in the following table are the reviewed CSP decisions. They are not recalculated from action verbs or inferred from the apparent complexity of a problem.

| Competence | Specification | Knowledge–level requirements |
| --- | --- | --- |
| C01 | Develop problem solutions using Automata | Finite State Machines — Creating; Requirements Engineering — Applying; Analytical and Critical Thinking — Applying |
| C02 | Justify the use of Deterministic Finite Automata (DFAs) | Deterministic Finite Automata — Understanding; Requirements Engineering — Applying; Analytical and Critical Thinking — Applying |
| C03 | Test automata using simulators | Finite State Machines — Applying; Problem Solving and Troubleshooting — Applying; Modeling and Simulation — Applying |
| C04 | Define Regular Expressions for Finite Automata | Finite State Machines — Understanding; Regular Expressions — Applying; Analytical and Critical Thinking — Applying |
| C05 | Write a technical report | Written Communication — Applying |
| C06 | Develop Problem-Solving Solutions Using Finite State Machines | Finite State Machines — Creating; Requirements Engineering — Applying; Analytical and Critical Thinking — Applying |
| C07 | Develop Problem-Solving Solutions Using Turing Machines | Turing Machines — Creating; Requirements Engineering — Applying; Analytical and Critical Thinking — Applying |
| C08 | Identify Turing Machine Variants | Turing Machine Variants — Understanding; Analytical and Critical Thinking — Applying |
| C09 | Apply Turing Machine Variants | Turing Machine Variants — Applying; Analytical and Critical Thinking — Applying |
| C10 | Test Turing Machines Using Simulators | Turing Machines — Applying; Problem Solving and Troubleshooting — Applying; Modeling and Simulation — Applying |
| C11 | Identify Patterns in Finite State Machines | Finite State Machines — Analyzing; Analytical and Critical Thinking — Applying |
| C12 | Develop Problem-Solving Solutions Using Pushdown Automata | Pushdown Automata — Creating; Requirements Engineering — Applying; Analytical and Critical Thinking — Applying |
| C13 | Interpret Rule-Based Notation | Context-Free Grammars — Understanding; Pushdown Automata — Understanding; Analytical and Critical Thinking — Applying |
| C14 | Differentiate Classifications of Formal Grammars | Regular Languages — Understanding; Context-Free Languages — Understanding; Analytical and Critical Thinking — Applying |
| C07.01 | Develop Integrated Multi-Agent Solutions Using Turing Machines | Turing Machines — Creating; Requirements Engineering — Applying; Analytical and Critical Thinking — Analyzing |
| C08.01 | Analyze the System-Level Adequacy of Turing Machine Variants | Turing Machine Variants — Analyzing; Analytical and Critical Thinking — Analyzing |
| C09.01 | Apply and Coordinate Turing Machine Variants in Integrated Architectures | Turing Machine Variants — Applying; Requirements Engineering — Applying; Analytical and Critical Thinking — Analyzing |

`cycle01-specifications.json` also records the dispositions, action verbs, knowledge IRIs, curricular references, and source document for every specification. `knowledge-level-mapping.tsv` makes the relationship to the input references inspectable.

## Modeling process

### Source scope and specification state

The adjusted/reviewed cycle-01 records determine this dataset's competence content. Task01's second iteration is additive; Task02–Task05 use their adjustment decisions. Unreviewed proposal content is not automatically carried forward merely because it appears in a preliminary report.

C05.01, C13.01, C15–C23, and the distinct composite/abstract legacy C01 resources are outside the reviewed set. The broader repository is not rewritten by this dataset.

This is a coherent **cycle-01 specification snapshot**. In particular, C13 here denotes the Task04 interpretation using Context-Free Grammars, Pushdown Automata, and Apply-level analytical reasoning. It does not assert the later, domain-independent C13 specification. The same concern applies to the distinct legacy composite/abstract C01 resources: no `owl:sameAs` equivalence is asserted between them and cycle-01 C01.

Load this snapshot into its own clean instance dataset. Combining conflicting specification states under the same IRI would mix different definitions; these insertions do not delete assertions already present in another dataset.

### Competence constituents and patterns

Each competence uses `CC2020SpecificationPattern`, explicit `KnowledgeSkill` constituents, source-listed dispositions, and at least one reference task, as required by the current `CC2020Competence` restrictions. The pattern describes the adopted knowledge–level/disposition structure; it does not claim that the CSP competence statements were authored by CC2020.

Every pairing has exactly one `hasKnowledgeComponent` and one `hasSkillLevel`. Levels use the named Bloom individuals, such as `bloom:Creating` and `bloom:Applying`, from the current skill-level module. 

All competence instances are represented without component competences and use the local Atomic convention. Multiple knowledge constituents or interacting task modules do not by themselves establish `CompositeCompetence`. No undocumented composition is manufactured.


### Knowledge and curricular anchors

Knowledge references are checked by their **content**, not merely by whether their IRI exists. Turing Machines are anchored to the actual Turing Machine content; Pushdown Automata to the Pushdown content; regular/context-free classifications to their relevant category content. Pumping-lemma proofs, algorithmic correctness, and decidability are not used as substitutes for those concepts.

Four local Knowledge instances express content for which an existing topic would be too broad, too narrow, or conceptually inappropriate: Deterministic Finite Automata, Requirements Engineering, Modeling and Simulation, and Context-Free Grammars. They are explicitly documented from the cycle records.

`hasCurricularKnowledgeReference` is used on a `KnowledgeSkill` and points to a curricular SKOS concept. It is not applied to Knowledge itself: its domain would otherwise infer that Knowledge is also a KnowledgeSkill, conflicting with the constituent disjointness. Foundational/professional pairings reference the curricular concepts corresponding to the reusable CC2020 knowledge instances.

The reviewed CSP decisions originally refer to earlier curricular vocabularies. The present CS2023 anchors are a curated interoperability mapping; they are not a claim that the original CSP records used these CS2023 IRIs.

### Justification action verbs

Every action verb specified in a reviewed knowledge–level assignment is represented through `hasBloomLevelJustificationVerb` on its `KnowledgeSkill` instance. For example:

```turtle
ontoksd:KS_C03_FiniteStateMachines_Applying
    ontoksd:hasSkillLevel bloom:Applying ;
    ontoksd:hasBloomLevelJustificationVerb
        bloom:experiment, bloom:relate, bloom:simulate .
```

The three verbs support the Applying assignment for this pairing. They are object-property values, not a list embedded in `dcterms:description`.

Existing verb individuals are reused. `01 - Cycle01 Justification Verbs.rq` declares the ten supplemental verbs not populated in the checked external vocabulary, typing each as `bloom:ActionVerb`, identifying its CSP-recorded type, and documenting its source-supported level associations. These declarations do not claim official or exclusive placement in a Bloom verb list. `supplemental-justification-verbs.json` records their pairing-specific uses.

The justification relation is contextual: a verb supports the level assigned to this particular knowledge requirement. It neither computes that level automatically nor requires the same word to justify only one level in every context.

### Reference tasks, instructional units, and assessment plans

Each problem has three distinct entities:

- `Task`: the reusable reference-performance specification and its analytical elements;
- `LearningTask`: the bounded instructional unit;
- `Assessment`: the evaluative specification based on the learning task.

The original instructional statement is retained through a language-tagged `hasInstructionalStatement` on the LearningTask. Thus, scenario, process, deliverables, and constraints remain available independently of competence associations. An expected automaton or report is an expected artifact, not produced Evidence. The original statement may mention knowledge later removed from the assessed target set, such as the Church–Turing thesis in Task02. Preserving that source text does not reintroduce it as a competence constituent.

Each competence in the reviewed task set has two separate activation records: one for the LearningTask and one for the Assessment. The assessment activation IRIs are retained; instructional activation IRIs use the `_Learning` suffix. These are explicit planning associations, not an automatic inference from `isBasedOnActivity` or an assertion that instruction or assessment occurred.

The acquisition contexts describe the pedagogical settings used for these specifications. No learner, assessment occurrence, participation, score, evidence, or individual competence judgment is fabricated.

### Activation qualification

Activation records are instances of `CompetenceActivation`. Function, role, and mode use the existing controlled values. Roles and modes are contextual curation choices grounded in the task design; the source records do not enumerate every such RDF qualifier.

Each activation has its own `ActivationConstraint`, with exactly one `hasActivationConstraintType` and an explicit language-tagged `hasActivationConstraintDescription`. A type value such as `acMandatory` or `conditional` is not used as if it were a constraint instance.

Task01's regular-expression condition preserves the source alternative: provide the expression and its construction when feasible, otherwise justify the limitation. Task02's variant application is conditional on selecting a variant; considering whether one is needed remains part of the task. A theoretical comparison of computational power is not added as an obligation.

Task04's documented reuse of C03 is retained with a scope note: the activation concerns PDA simulation while C03's reference knowledge pairing remains FSM-based. This preserves the CSP decision but does not prove that an unchanged FSM pairing covers all PDA-specific simulation requirements. That adequacy question remains explicit.

### Direct specialization

C07.01, C08.01, and C09.01 use `isSpecializationOf` to reference C07, C08, and C09 respectively. `hasSpecializationDescription` records the integrated multi-agent scope that narrows each base purpose.

Specialization is irreflexive because a specification cannot be its own direct specialization. It is asymmetric because a narrower specification and its direct base cannot specialize each other in that same relation. It is not transitive because the property identifies direct bases; longer ancestry can be queried through paths. These characteristics do not rule out cycles of three or more nodes, so a supplied path query checks for such cycles.

These are relationships between competence individuals, not OWL subclass declarations. They do not automatically copy the base's property values or turn integration into competence composition.

### Proficiency interpretation

Each task retains its 0–10 Scale, three ProficiencyLevels, and a ProficiencyScheme:

- Novice: `[0, 5]`;
- Competent: `(5, 9]`;
- Advanced: `(9, 10]`.

The selected score granularity is 0.1. Task01–Task03 explicitly document this as a CSP specification assumption; Task05 also states it. Task04's printed starts at 5.1 and 9.1 are interpreted on this one-decimal domain. This does not make off-grid scores such as 5.05 valid: range and step adherence require external validation.

The Assessment references its planned scheme through the documentary property `dcterms:references`. `usesProficiencyScheme` is not used on Assessment, because its domain is AssessmentOccurrence and would imply an actual event. No level is assigned to a learner or evidence record.

## Files and loading

1. Load the current OntoKSD schema and Bloom/skill-level module, resolving imports in your environment.
2. Load `cs2023Vocabulary/BoKCS2023_ontoksd.ttl`.
3. Execute the CC2020 foundational-knowledge and disposition insertion files.
4. If using the verb links, execute the Bloom action-verb-type and verb insertion files.
5. Execute `00 - Cycle01 Sources and Knowledge.rq` and `01 - Cycle01 Justification Verbs.rq`, then the files in `competencies/`, and the five files in `tasks/` in the selected cycle-01 instance dataset.

All data files use `INSERT DATA`, contain named resources, and can be re-executed without adding duplicate instances. Competence and task files are intended to be loaded together; an isolated competence file may depend on task links asserted by a task file. Reverse insertion order produces the same final graph.

The `.rq` files are SPARQL Update operations, not Turtle ontologies. For Protégé, `cycle01-instances.ttl` provides the combined instance graph directly. Load it together with the schema and the dependencies above. It is an alternative representation of the same data, not a replacement for the schema.

Supporting files:

- `cycle01-specifications.json`: reviewed competence content and task associations;
- `knowledge-level-mapping.tsv`: input and corrected knowledge/level references;
- `scope-exclusions.json`: input files outside the reviewed cycle-01 scope;
- `supplemental-justification-verbs.json`: supplemental verb declarations and their contextual uses;
- `queries/`: task activation, specialization, interval-boundary, and cycle queries;
- `validation.json`: checks, results, and dependency fingerprints;


