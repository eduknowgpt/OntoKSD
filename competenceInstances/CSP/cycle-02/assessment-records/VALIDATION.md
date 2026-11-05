# Historical assessment verification

Executed local RDF, source reconciliation, SELECT, SPARQL Update and OWL RL checks. Imported ontologies were not fetched; this is not full OWL DL consistency or independent human validation.

The structural and source checks passed 2310 assertions. This is a count of automated assertions, not independent evaluation criteria or human validators.

## Coverage

| Task | Triples | Process/product/final scored records | Participations including questionnaires | Evidence references |
| --- | ---: | ---: | ---: | ---: |
| Task201 | 1859 | 102 | 129 | 41 |
| Task202 | 1741 | 96 | 120 | 39 |

96 Task202 and 81 nominally reconciled Task201 dimensional results checked directly; all 34 legacy Task201 final means preserved. Seven Task201 codes retain group-based reconstruction with the identity limitation documented.

## Query results

The following row counts were executed against the historical layer. Zero rows in the mean-violation and proxy-judgment queries are the expected results.

| Query | Expected rows |
| --- | ---: |
| [task201/queries/01-official-scores.rq](task201/queries/01-official-scores.rq) | 34 |
| [task201/queries/02-shared-reviews.rq](task201/queries/02-shared-reviews.rq) | 14 |
| [task201/queries/03-questionnaire-references.rq](task201/queries/03-questionnaire-references.rq) | 27 |
| [task201/queries/04-process-product-final.rq](task201/queries/04-process-product-final.rq) | 34 |
| [task201/queries/05-mean-violations.rq](task201/queries/05-mean-violations.rq) | 0 |
| [task202/queries/01-official-scores.rq](task202/queries/01-official-scores.rq) | 32 |
| [task202/queries/02-shared-reviews.rq](task202/queries/02-shared-reviews.rq) | 29 |
| [task202/queries/03-questionnaire-references.rq](task202/queries/03-questionnaire-references.rq) | 25 |
| [task202/queries/04-process-product-final.rq](task202/queries/04-process-product-final.rq) | 32 |
| [task202/queries/05-mean-violations.rq](task202/queries/05-mean-violations.rq) | 0 |
| [queries/01-score-provenance.rq](queries/01-score-provenance.rq) | 198 |
| [queries/02-unlinked-questionnaire.rq](queries/02-unlinked-questionnaire.rq) | 1 |
| [queries/03-assessor-involvement.rq](queries/03-assessor-involvement.rq) | 264 |
| [queries/04-no-proxy-judgments.rq](queries/04-no-proxy-judgments.rq) | 0 |

## Inference and insertion

- The INSERT scripts were executed locally and compared with their Turtle graphs; re-execution preserved the same graph.
- Local OWL RL closure covered 6332 asserted triples from the schema, planning and history, producing 13847 triples. No engine errors, named disjointness conflicts or owl:Nothing members were found.
- Imported ontologies were not fetched. This is not a full OWL DL consistency check with HermiT or Pellet.
- The ontology schema and GraphDB repository were not modified.

## Readability verification

The regrouped Turtle and prefixed INSERT files are checked for graph equivalence. All 14 SELECT queries are re-executed after formatting. No data value, IRI or relationship is intentionally changed by this presentation update.
