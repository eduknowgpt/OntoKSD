# Task202 — adopted retrospective C05 judgment (FR11)

Edeyson Andrade Gomes adopted the individual judgment on 2026-10-05 for T202-G01-B. The documented writing contribution provides evidence of C05 in a coauthored technical report. The interpretation records a clear structure and some technical explanation, qualified by limitations in conceptual coverage and development detail. The 2022 tutor review concerns the group report. The thesis author confirmed that it concerns the archived article. The exact date of the tutor review remains unknown. The individual judgment assigns no score or proficiency level.

The supplement reuses OntoKSD's existing C05, Task202 historical product assessment occurrence and Group 01 report document. The local learner code is retained without equating it to another anonymized learner IRI. The retrospective occasion and the participation used for the judgment are explicitly derived from the historical occasion and the documented writing participation. The evidence view was assembled for the retrospective occasion and links to the technical report, the team attribution and the tutor review. The two applied criteria point to the exact tutor-review source. Dublin Core carries the adoption date at day precision.

## Load and verify

1. Load the ontology, Cycle 01 C05 specification, Cycle 02 planning and historical assessment records, following the default loader.
2. Import [task202-c05-fr11.ttl](task202-c05-fr11.ttl) into that dataset or execute [task202-c05-fr11.rq](task202-c05-fr11.rq) as a SPARQL UPDATE. For a new batch, the Java loader offers `LOAD_TASK202_RETROSPECTIVE_C05`, disabled by default to preserve the original batch.
3. Run the [FR11 SELECT](queries/01-fr11-retrospective-trace.rq) against a dataset that exposes both the historical graph and the supplement. Expect one judgment across six rows (two criteria and three documents). Compare [local results](validation/results.csv) and [validation checks](validation/checks.json). Separate named graphs can be used if both are visible to the SELECT.

The supplemental SELECT is scoped to this adopted judgment. It checks matching learners, occasions and criterion references. The original competency-question inventory and its archived results are historical baselines. CQ22 also retrieves the judgment when the supplement is visible. The historical-only no-proxy-judgments query gives one row if run over the combined dataset because this layer deliberately adds the adopted retrospective judgment. Run that historical check over the historical layer to reproduce its original zero.

Source locators and fingerprints are in the RDF. The CSP Task202 FR11 fiche holds the adoption statement and documentary analysis. Local RDFLib checks cover this retrospective whole-competence case. They leave results on a live GraphDB endpoint and broader evidence for FR11 outside their scope.
