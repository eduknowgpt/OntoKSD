# Supplementary SPARQL queries

[Português](README.pt.md) · [Competency questions](CQ/CQ_CATALOG.md)

Use this directory for vocabulary exploration and operational views that complement the CQs. All nine `.rq` files are **read-only SELECT queries** for GraphDB / SPARQL 1.1. The [CQ directory](CQ/) remains the entry point for competence structure, activation, evidence provenance, profiles and learning-need questions.

## Running a query

Load the ontology/modules and relevant data, open the chosen file in GraphDB and execute it. Query scope follows the selected repository/dataset. Labels are optional and may occur in multiple languages; count distinct identifiers rather than assuming each row is one entity.

Vocabulary catalogs include resources not yet used in a competence. An empty learner-assessment view can be expected when only specification/planning examples have been loaded.

## Vocabulary exploration

| Query | Purpose | Interpretation |
|---|---|---|
| [Knowledge_Catalog.rq](Knowledge_Catalog.rq) | Which knowledge individuals and curricular references are available? | Lists available knowledge, including items not yet used in competences. Optional classification and curricular links can multiply rows. |
| [KnowledgeType_Catalog.rq](KnowledgeType_Catalog.rq) | Which knowledge-type categories are available? | Lists controlled knowledge types independently of whether a competence currently uses them. |
| [Disposition_Catalog.rq](Disposition_Catalog.rq) | Which dispositions and attitudes are available? | Uses the class hierarchy to include Attitude without requiring materialized subclass typing; these are specification values, not learner traits. |
| [Skill_Catalog.rq](Skill_Catalog.rq) | Which explicit skills, dimensions and applied knowledge are available? | Skill is distinct from SkillLevel. No hasCognitiveLevel or hasSkillLevel relation is assumed on Skill. |
| [SkillLevel_ActionVerbs.rq](SkillLevel_ActionVerbs.rq) | Which action verbs are associated with each framework-specific skill level? | Vocabulary associations are contextual and may be multiple. They neither infer a competence requirement nor establish cross-scheme equivalence. |
| [KnowledgeSkill_CandidateVerbs.rq](KnowledgeSkill_CandidateVerbs.rq) | Which vocabulary verbs are available for the level assigned to each knowledge-level pairing? | Selects C02 by default; replace that competence IRI. Unlike CQ2, lists vocabulary candidates for an already assigned level. It does not assign a level or validate a justification. A recorded verb may be absent from this view if it has no vocabulary link to that level. |

## Assessment and proficiency views

| Query | Purpose | Interpretation |
|---|---|---|
| [Proficiency_Comparison.rq](Proficiency_Comparison.rq) | How does each latest comparable judgment relate to the selected curricular minimum? | Replace Profile_Example and Course_Example. Uses the same eligibility policy as CQ39a but includes below/equal/above cases; this is not a passing-grade or diagnosis rule. |
| [Evidenced_Competence_Constituents.rq](Evidenced_Competence_Constituents.rq) | Which constituent judgments are linked to each whole-competence judgment, with which independent levels? | A joint detail view of parent/child judgments; no proficiency aggregation or transfer between granularities is inferred. Standalone constituent judgments remain covered by CQ23/CQ38. |
| [Proficiency_Score_Interpretation.rq](Proficiency_Score_Interpretation.rq) | Which numeric intervals match each participation score, and how do they relate to a recorded judgment? | Read-only numeric matching, not automatic proficiency attribution. Explicit record schemes take precedence over occurrence defaults. All matching intervals remain visible; overlap is flagged. No row can mean an uncovered/out-of-scale score, missing data or excluded scheme ambiguity. |

## Comparison and score interpretation

`Proficiency_Comparison.rq` uses the same explicit profile/course, scheme, date and ambiguity policy as CQ39a, but returns **below, at minimum and above** cases. Replace `Profile_Example` and `Course_Example`. A course minimum expresses curricular depth; the comparison is an external analytical view, not an admission, passing or diagnostic rule.

`Proficiency_Score_Interpretation.rq` finds numeric intervals matching each participation score, honoring inclusive/exclusive endpoints and scale bounds. An explicit judgment scheme takes precedence over an occurrence scheme. Ambiguous scheme selection is excluded; overlapping matching intervals remain visible and are flagged. A result does not assign a proficiency level or establish that a participation-wide score is pedagogically appropriate for every competence or constituent.

No matching interval can indicate a score outside the scale, an uncovered range, missing structure or excluded scheme ambiguity; absence alone does not identify the cause. This is not a complete SHACL/OWL validation of the proficiency model.

`Evidenced_Competence_Constituents.rq` places parent and child judgments side by side with their own levels and schemes. It does not aggregate levels or infer that the parent and children must have equal proficiency.

## Use the CQs for these questions

| Information needed | CQ |
|---|---|
| Constituents and knowledge–level pairings | CQ1–CQ3 |
| Competence records and profiles | CQ8, CQ37, CQ38 |
| Supporting evidence, type and origin | CQ18, CQ19, CQ20, CQ21a |
| Learner participation, score and derived judgments | CQ24a, CQ41 |
| Scale and interval structure | CQ30, CQ31 |
| Course competence expectations | CQ33 |
| Below-minimum comparisons | CQ39a, CQ40b, CQ42b |
| Explicit learning needs and their documented basis | CQ39c, CQ42a |

The CQs include an optional UPDATE at CQ39b; do not execute the entire CQ directory as if every file were read-only. These supplementary files perform no updates.
