# CSP cycle 03 — OntoKSD instances

This dataset represents the competence specifications and instructional planning documented in [CSP cycle 03](https://github.com/eduknowgpt/OCSP/tree/main/CSP/cycles/cycle-03). It contains five reference tasks, five learning tasks, five assessment specifications, eight newly specified competences and 42 current task–competence associations involving 21 competence identities. Each association is represented separately for learning and assessment, yielding 84 competence activations.

## Competences

| Identifier | Specification |
|---|---|
| C05.01 | Write mathematically rigorous answers; direct specialization of C05 |
| C17 | Apply operations on formal languages |
| C18 | Model real-world problems using formal language concepts |
| C19 | Apply homomorphisms in formal languages |
| C20 | Model and analyze musical collections as formal languages; composite |
| C21 | Analyze computational complexity and implications of NP-hardness |
| C22 | Define context-free grammars for pushdown automata |
| C23 | Interpret and apply algebraic notation for strings and languages |

C20 integrates C18, C17, C19, C04, C23 and C05.01. These are distinct component competences, not knowledge–level constituents. C05.01 specializes C05 without changing its definition. Specialization is a relationship between specification individuals, not an OWL subclass axiom. C23 has its own identity: it is not a renamed C13.01.

## Tasks

| Reference task | Subject | Current associations |
|---|---|---:|
| TASK301 | Music collection as formal languages | 8 |
| TASK302 | Vending machine | 8 |
| TASK303 | Minotaur’s labyrinth | 10 |
| TASK304 | Traffic control | 8 |
| TASK305 | Online judges and computational complexity | 8 |

Reference `Task` instances describe expected performance. Separate `LearningTask` and `Assessment` instances describe instructional and evaluative planning. Their activations preserve contextual roles, modes and constraints. A conditional or optional activation remains a target association; it is not thereby an admission prerequisite.

## Files and loading

1. Load the current OntoKSD ontology and its imported modules, together with the foundational knowledge, disposition and action-verb vocabularies.
2. Load the corrected cycle-01 and cycle-02 instance datasets. This package deliberately reuses their competence and knowledge IRIs. See `dependencies.json` for the inherited identities. The optional curricular/proficiency layer of cycle 02 is not required.
3. Execute `00 - Cycle03 Sources and Vocabulary.rq` and `01 - Cycle03 Justification Verbs.rq`.
4. Execute the eight files under `competencies/`, followed by the five files under `tasks/`.
5. Run the queries under `queries/` to inspect associations, pairings and structural cycles.

Alternatively, load `cycle03-instances.ttl` after the same dependencies. It contains the same cycle-03 triples; it does not bundle the ontology, prior cycles or source documents. The SPARQL files contain only `INSERT DATA`, without deletion or ontology replacement.


## Modeling choices

The new specifications use the CC2020 pattern and revised Bloom level individuals. This identifies their representational structure, not CC2020 authorship. The 33 new `KnowledgeSkill` instances each have one knowledge component and one skill level. Source rows listing two levels are represented by separate pairings, preserving both qualifications rather than selecting the highest one.

Recorded justification verbs are linked using `hasBloomLevelJustificationVerb`. The supplementary verb file supplies missing vocabulary entries with contextual provenance. A CSP-recorded association does not make a verb an official Bloom recommendation and does not automatically infer a required level.

Existing disposition individuals are reused where applicable. Epistemic rigor and intellectual responsibility receive explicitly documented local disposition values. Curricular references remain separate from the knowledge instances used in the pairings.

Independent instructional statements are retained even where a statement does not translate directly into a competence association. Source resources are linked externally. The course aggregates the ten instructional units without becoming a dated course offering.


