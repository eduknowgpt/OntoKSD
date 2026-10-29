# Proficiency Interpretation and Learning-Need Example

Three SPARQL Update files demonstrate how [OntoKSD](https://github.com/eduknowgpt/OntoKSD) represents a numeric scale, a proficiency scheme, an individual assessment judgment, and a learning need identified through an explicit comparison policy.

All learners, assessment records, scores, thresholds, and expectations in these files are illustrative.

## Files

| File | Purpose |
| --- | --- |
| [`01 - ProficiencyLevel.rq`](01%20-%20ProficiencyLevel.rq) | Defines a decimal 0–100 scale, four proficiency categories, their ordinal positions, and numeric intervals. |
| [`02 - Proficiency_Instances.rq`](02%20-%20Proficiency_Instances.rq) | Creates a fictional DFA assessment example and interprets its whole-competence score using the scheme. |
| [`03 - Gap_Analisys.rq`](03%20-%20Gap_Analisys.rq) | Defines a course-level reference and conditionally identifies a LearningNeed through a same-scheme ordinal comparison. |

## Scale and proficiency scheme

The example uses `Scale_0_100` and `ProficiencyScheme_0_100_FourLevels`:

| Proficiency level | Decimal score interval | Ordinal position |
| --- | --- | ---: |
| Insufficient | `[0,36)` | 1 |
| Basic | `[36,61)` | 2 |
| Proficient | `[61,80)` | 3 |
| Advanced | `[80,100]` | 4 |

Square brackets include an endpoint; parentheses exclude it. The intervals cover the full decimal range without gaps or overlaps. A score of `35.50` is Insufficient; `36.00` is Basic; `79.99` is Proficient; `80.00` is Advanced.

Scores and bounds use `xsd:decimal`. Decimal literals use a period, for example `"6.70"^^xsd:decimal`. The scale imposes neither a fixed step nor automatic rounding. Ordinal positions use `xsd:int` and indicate order within this scheme, not measured distances between categories.

The names and thresholds express a local example policy. OntoKSD does not prescribe them. Insufficient denotes a low interpreted result, not the absence of evidence. A numeric category alone does not establish autonomy, transfer, or performance in more complex settings.

## Assessment example

The second file represents:

- a Learner and Profile;
- a reusable competence concerning deterministic finite automata;
- explicit Knowledge and Skill constituents;
- a KnowledgeSkill pairing with `hasKnowledgeComponent` and the required CS2023 skill level Develop through `hasSkillLevel`;
- an Assessment, its AssessmentOccurrence, and a learner-specific AssessmentParticipation;
- a decimal participation score of `74.00`;
- evidence, a supporting document, and contextualized competence and constituent judgments.

The explicit Skill is associated with the competence separately; it is not an internal component of KnowledgeSkill. The competence remains an instance of the general Competence class in this example.

The participation score is interpreted for the selected whole-competence judgment. The final update in file 02 assigns Proficient only when there is one matching interval, an unambiguous decimal score and scheme, and compatible evidence origin. It does not copy that level to constituent judgments, which would require their own assessment criteria or results.

Develop is a required **SkillLevel** within the specification. Proficient is an interpreted **ProficiencyLevel** associated with a learner-specific judgment. These values serve different purposes.

## Learning-need comparison

The third file uses the course *Formal Languages and Automata* as an explicitly selected reference. Its CourseCompetenceSpecification identifies Advanced as the minimum level the course intends to address for DFA construction.

For this example, the comparison policy adopts that curricular expectation as the desired reference for the selected learner profile. It checks:

1. The expectation and judgment concern the same competence.
2. The judgment belongs to the selected profile and its learner participation.
3. Its evidence originates in the corresponding occurrence.
4. The observed and required levels belong to the same identified proficiency scheme.
5. Both levels have unambiguous ordinal positions, with the observed position lower than the required position.

With Proficient at position 3 and Advanced at position 4, the update creates `LearningNeed_DFAConstruction_Learner_01`. The need references the judgment, curricular specification, and scheme through `dcterms:references`.

In this example, `dcterms:references` on CourseCompetenceSpecification documents the applicable proficiency scheme. The update uses that explicit convention; it does not apply `usesProficiencyScheme` to a course specification, because that OntoKSD property has domain AssessmentOccurrence.

The rule does not subtract ordinal positions, compare unrelated schemes, or treat missing evidence or levels as zero proficiency. Course expectations do not automatically constitute student-approval thresholds. The assessment passing score, required SkillLevel, curricular proficiency reference, and individual judgment remain distinct.

The named output is marked with `dcterms:creator GapAnalysisRule_DFAExample`. Repeating the third script refreshes only the output owned by this rule. An independently authored need without that marker is not overwritten. The judgment is selected explicitly; no latest, highest, or lowest-result policy is inferred.

Numeric interpretation and gap identification are executed by SPARQL Update. They are not automatic OWL reasoner conclusions.

## Running the example

1. Load the current OntoKSD ontology.
2. Load the [skill-level vocabulary](../bloomTaxonomy/bloom-taxonomy.ttl), which supplies the Develop individual.
3. Execute files **01 → 02 → 03**, in that order, in a SPARQL Update environment using an isolated example dataset.
4. Query the resulting graph, or export it as Turtle for inspection in Protégé.

The `.rq` files are executable updates, not Turtle documents. They target the default graph; configure the dataset or adapt graph targets explicitly for a named-graph deployment.

Files 01 and 02 populate the stated example facts. File 02 also recalculates the selected judgment's proficiency. Its final interpretation updates can be executed separately after changing the participation score. Then rerun the comparison section of file 03 to refresh the derived need.

Changing the example's stored curricular reference and rerunning the comparison section allows another desired level to be used. Executing the complete third file also inserts its stated Advanced reference, so it should not be used to overwrite a different application policy.

The same inputs can be executed repeatedly without accumulating duplicate statements. When changing a score or minimum level in an existing graph, replace its value rather than adding a second value.

## Inspecting the result

```sparql
PREFIX ontoksd: <http://purl.org/ontoksd#>

SELECT ?judgment ?score ?level ?scheme ?need
WHERE {
  VALUES ?judgment {
    ontoksd:EvidencedCompetence_DFAConstruction_Learner_01
  }
  ?judgment ontoksd:derivedFromParticipation ?participation ;
            ontoksd:hasProficiencyLevel ?level ;
            ontoksd:interpretedUsingScheme ?scheme .
  ?participation ontoksd:hasScore ?score .
  OPTIONAL {
    ontoksd:Profile_Learner_01 ontoksd:hasLearningNeed ?need .
    ?need ontoksd:targetsCompetence ontoksd:Competence_DFAConstruction .
  }
}
```

The expected result is score `74.00`, level Proficient, the four-level scheme, and the identified learning need.

