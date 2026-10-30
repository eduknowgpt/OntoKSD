# CC2020 Competence Examples: Source Excerpt and OntoKSD Representation

This document presents the CC2020 excerpt used to instantiate competence examples A, B, and C in OntoKSD. It brings together the knowledge vocabulary, the competence statements, their knowledge–level pairings, and their dispositions.

Its purpose is to provide a **readable source reference for the corresponding SPARQL insertions**:

| Source example | Insertion file | OntoKSD competence |
| --- | --- | --- |
| A | [`InsertCompetence_A.rq`](sparql/InsertCompetence_A.rq) | `ontoksd:CC2020_Competence_A` |
| B | [`InsertCompetence_B.rq`](sparql/InsertCompetence_B.rq) | `ontoksd:CC2020_Competence_B` |
| C | [`InsertCompetence_C.rq`](sparql/InsertCompetence_C.rq) | `ontoksd:CC2020_Competence_C` |

The source material below comes from the supplied `CC2020 report`, which extracts sections 4.2.4–4.2.5 and Tables 4.1–4.2 from *Computing Curricula 2020*. Tables embedded as images are represented as editable Markdown tables. The competence statements, level assignments, and dispositions retain their source content. Paragraph layout and minor typographical details are normalized for readability.

The relative file links assume that  files is placed in its `sparql` subdirectory.

The introductory explanation and the final OntoKSD mapping section are project documentation; they are not passages from CC2020. This document is a selected excerpt, not the report's complete Body of Knowledge or a complete competence catalog.

## 1. Component elements

**Source:** CC2020, section 4.2.4, printed pages 49–50.

> A competency is a collection of specific components of knowledge, skills, and dispositions. Tables 4.1, 4.2, 4.3, and 4.4 present suggested elements of these dimensions. The knowledge dimension of competency encompasses concepts that are technical (computing concepts), foundational and professional (indicative of a workplace), and domain specific (the task setting). Appendix D elaborates on these component tables in greater detail.

> Table 4.1 illustrates thirty-four abbreviated knowledge areas partitioned into an ordered sequence of six categories.

> While the table is incomplete, it does provide an example of high-level vocabulary for computing knowledge rooted in the collective wisdom of different computing communities.

> This summary of computing knowledge areas represents a well-understood and consistent vocabulary from which computing competency statements can evolve.

### Table 4.1. Elements of Computing Knowledge

The six source columns are presented below as grouped rows. Their order and area membership are retained.

| Category | Knowledge areas |
| --- | --- |
| Users and Organizations | Social Issues and Professional Practice; Security Policy and Management; IS Management and Leadership; Enterprise Architecture; Project Management; User Experience Design. |
| Systems Modeling | Security Issues and Principles; Systems Analysis & Design; Requirements Analysis and Specifications; Data and Information Management. |
| Systems Architecture and Infrastructure | Virtual Systems and Services; Intelligent Systems (AI); Internet of Things; Parallel and Distributed Computing; Computer Networks; Embedded Systems; Integrated Systems Technology; Platform Technologies; Security Technology and Implementation. |
| Software Development | Software Quality, Verification and Validation; Software Process; Software Modeling and Analysis; Software Design; Platform-Based Development. |
| Software Fundamentals | Graphics and Visualization; Operating Systems; Data Structures, Algorithms and Complexity; Programming Languages; Programming Fundamentals; Computing Systems Fundamentals. |
| Hardware | Architecture and Organization; Digital Design; Circuits and Electronics; Signal Processing. |

> The thirteen elements of foundational and professional knowledge listed in Table 4.2 represent a subset of the professional listings derived from the IT2017 report and subsequently from Appendix D in this report. Computing professionals are commonly expected to demonstrate high levels of skill in applying this knowledge which deserves explicit attention in baccalaureate programs.

> Domain knowledge represents elements of the context that situates the task. In the general, these elements may represent the disciplinary (e.g., business, medicine, manufacturing). In the more detailed, they may be more specific (e.g., international currency exchange, radiographic imaging, automobile assembly). In any case, the scope and level of detail of domain knowledge will emerge from the intended use of the competency (i.e., Computing + X or X + Computing, for which see Section 2.4).

### Table 4.2. Elements of Foundational and Professional Knowledge

The meanings in this table reproduce the source descriptions. They should be read as CC2020's explanatory wording rather than as the formal definitions of the OntoKSD Knowledge class.

| Knowledge element | Meaning |
| --- | --- |
| Analytical and Critical Thinking | A mental process of simplifying complex information into basic parts and evaluating results to make proper decisions. |
| Collaboration and Teamwork | Apportion challenging tasks into simpler ones and then work together to complete them efficiently. |
| Ethical and Intercultural Perspectives | Ethical perspectives of the different viewpoints someone uses to view a problem in the context of individual human values. |
| Mathematics and Statistics | Use of numbers and theories abstractly especially in the collection and analysis of numerical data. |
| Multi-Task Prioritization and Management | Processing several issues or tasks at once while arranging them according to importance to do specific one first. |
| Oral Communication and Presentation | Conveying a message orally using real-time presentations with visual aids related audience interests and goals. |
| Problem Solving and Trouble Shooting | A logical and orderly search for the source of a unit problem and making the unit operational again. |
| Project and Task Organization and Planning | A process to provide decisions about a project concerning organization and planning to achieve a successful result. |
| Quality Assurance / Control | Use of techniques, methods, and processes to identify and prevent defects according to defined quality standards. |
| Relationship Management | A strategy to maintain an ongoing level of engagement usually between a business and its customers or other businesses. |
| Research and Self-Starter/Learner | Someone who begins or undertakes work or a project without needing direction or encouragement to do so. |
| Time Management | An ability to use a person's time in an effective or productive manner to work efficiently. |
| Written Communication | Use of a written form of interaction between people and organizations that provides an effective way of messaging. |

## 2. Creating competency statements

**Source:** CC2020, section 4.2.5, printed pages 51–52.

> The competency model adopted in this CC2020 Report suggests that statements surrounding competency include knowledge elements paired with skill level and with dispositions. The following examples demonstrate a way to do this. Each of the three example competencies that follow specifies a statement of the task to be undertaken and itemizes the components deemed pertinent to effectively and efficiently accomplishing that task.

### Example A — Computer Engineering

**Source competency title:** A.  
**Source location:** printed page 51; PDF page 52.

#### Competency statement

> Manage the design of a computer system for a manufacturer using appropriate tools, design digital circuits including the basic building blocks of Boolean algebra, computer numbering systems, data encoding, combinatorial and sequential elements.

#### Knowledge–level pairings

| Knowledge element | Required skill level |
| --- | --- |
| Architecture and Organization | Creating |
| Digital Design | Creating |
| Circuits and Electronics | Creating |
| Analytical and Critical Thinking | Applying |
| Mathematics and Statistics | Applying |
| Problem Solving and Trouble Shooting | Applying |
| Research and Self-Starter/Learner | Applying |

#### Dispositions

Self-directed; Meticulous; Inventive.

### Example B — Information Technology

**Source competency title:** B.  
**Source location:** printed page 52; PDF page 53.

#### Competency statement

> Analyze and compare several networking topologies in terms of robustness, expandability, and throughput used within a cloud enterprise.

#### Knowledge–level pairings

| Knowledge element | Required skill level |
| --- | --- |
| Computer Networks | Analyzing |
| Platform Technologies | Analyzing |
| Analytical and Critical Thinking | Applying |
| Mathematics and Statistics | Applying |
| Quality Assurance | Applying |

#### Dispositions

Self-directed; Purpose-driven; Responsible.

### Example C — Software Engineering

**Source competency title:** C.  
**Source location:** printed page 52; PDF page 53.

#### Competency statement

> Identify and document system requirements by applying a known requirements elicitation technique in work sessions with stakeholders, using facilitative skills, as a contributing member of a requirements team.

#### Knowledge–level pairings

| Knowledge element | Required skill level |
| --- | --- |
| Requirements Analysis | Evaluating |
| Oral Communication | Applying |
| Written Communication | Applying |
| Teamwork and Collaboration | Applying |

#### Dispositions

Purpose-driven; Responsible; Collaborative.

## 3. Function within OntoKSD

This section explains the project representation of the source examples.

### Source reference for instantiation

The document identifies what the three insertions are intended to represent. It enables comparison between the readable source and the resulting competence statements, 16 knowledge–level pairings, and disposition associations.

The insertions use `CC2020Competence` with `CC2020SpecificationPattern`. Each source pairing becomes a `KnowledgeSkill` constituent:

```text
Competence
  └── hasKnowledgeSkill → KnowledgeSkill
                            ├── hasKnowledgeComponent → Knowledge
                            └── hasSkillLevel → CC2020SkillLevel individual
```

The level is attached to the pairing, making explicit which performance requirement applies to which knowledge element. It is not an intrinsic level of the Knowledge individual and is not a learner-specific proficiency result.

### Vocabulary reuse

The Knowledge and Disposition individuals are supplied by `cc2020Vocabulary`; the required levels are individuals in the Bloom-based skill-level vocabulary.

| Example | Disposition individuals |
| --- | --- |
| A | `ontoksd:selfDirected`, `ontoksd:meticulous`, `ontoksd:inventive` |
| B | `ontoksd:selfDirected`, `ontoksd:purposeDriven`, `ontoksd:responsible` |
| C | `ontoksd:purposeDriven`, `ontoksd:responsible`, `ontoksd:collaborative` |

Some source examples use abbreviated or differently ordered knowledge labels. The mappings below identify their representation in the project vocabulary; they do not assert unrestricted semantic equivalence.

| Source example label | Project Knowledge entry |
| --- | --- |
| Quality Assurance | `cc2020found:QualityAssuranceControl` — Quality Assurance / Control |
| Requirements Analysis | `cc2020:RequirementsAnalysisAndSpecifications` |
| Oral Communication | `cc2020found:OralCommunication` — Oral Communication and Presentation |
| Teamwork and Collaboration | `cc2020found:Collaboration` — Collaboration and Teamwork |
| Problem Solving and Trouble Shooting | `cc2020found:ProblemSolvingAndTroubleshooting` |

Namespace prefixes used in these mappings are:

```turtle
@prefix ontoksd:    <http://purl.org/ontoksd#> .
@prefix cc2020:     <http://purl.org/cc2020/knowledge#> .
@prefix cc2020found: <http://purl.org/cc2020/foundational#> .
```

### Specification and instructional use

The source statements also provide the text of the explicit reference Task associated with each competence. A Task expresses the reference performance; a LearningTask would instead specify pedagogically planned learner work.

The Atomic classification in the insertions indicates that the example is not decomposed into component competences. This is a project granularity choice, not a claim that the source statement has only one action or one constituent.

The examples support competence specification and traceability. They do not establish that a course addresses the competence, that an assessment has occurred, or that an individual has demonstrated it. Such information requires the corresponding OntoKSD instructional and evidential records.

## Reference

ACM and IEEE Computer Society. *Computing Curricula 2020*. Sections 4.2.4–4.2.5, Tables 4.1–4.2, and examples A–C, printed pages 49–52. 

[CC2020 report](https://www.acm.org/binaries/content/assets/education/curricula-recommendations/cc2020.pdf).
