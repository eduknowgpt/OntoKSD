# CQ1 Explorer

A Java Swing interface for selecting a competence in GraphDB and displaying the constituents returned by OntoKSD CQ1.

## Run in Eclipse

1. Copy `ontoKSD/CompetenceCQ1Viewer.java` into the `ontoKSD` package of the existing RDF4J Java project.
2. Check the constants at the start of the class:
   - `QUERY_ENDPOINT`: the repository query endpoint, such as `http://localhost:7200/repositories/OntoKSD2` (without `/statements`).
   - `CQ1_FILE`: the full path to the CQ1 `.rq` file in `sparql/CQ`.
3. Start GraphDB, load the ontology and instance data, and run the class as a **Java Application**.

The application uses `SPARQLRepository`, as does the existing loader. Swing is included in Java. For a Maven project without RDF4J, use `org.eclipse.rdf4j:rdf4j-repository-sparql:4.2.3` and its transitive dependencies. Keep library versions compatible; RDF4J 4.2.3 requires Java 11 or later.

## Use

The interface is in Portuguese. Filter by part of a name or IRI, then select a competence. Selection executes CQ1. **Consultar** reruns the query; **Atualizar lista** refreshes the competence list.

- **Constituintes**: constituent type and name; knowledge and required level for `KnowledgeSkill` pairings.
- **Resultado SPARQL**: every row and projected variable returned by CQ1.
- **Consulta para reprodução**: an equivalent query with the selected competence, ready to copy into GraphDB.

Labels are preferred in Portuguese, then English, then untagged labels and other languages. Missing labels fall back to the IRI local name. The main table groups rows differing only in labels; the SPARQL tab retains the complete result.

## How CQ1 is used

The UTF-8 query file is read for every execution. Its standalone example `VALUES ?competence { ontoksd:C02 }` block is removed only from the in-memory copy. A new `VALUES` block receives the selected IRI, serialized as an RDF term rather than interpolated as unchecked text. The source file is never changed.

CQ1 must be a SELECT query projecting `?competence` and `?constituent`. The detailed presentation also uses `?constituentLabel`, `?constituentType`, `?knowledge` and `?skillLevel`, as in the current CQ1. Competence listing and supplementary label retrieval use auxiliary queries; constituent retrieval executes the CQ1 file.

The interface displays semantic constituents, rather than competence composition through `hasComponentCompetence`. An empty result only means that the query returned no constituents for the selected competence in the queried repository.

Queries request available inferred statements (`INCLUDE_INFERRED = true`) and have a 30-second execution limit each. This does not configure GraphDB inference. Queries use the endpoint default dataset; if data are held exclusively in named graphs, adjust the dataset or CQ1 accordingly.

The application is read-only. Authentication is not configured in this local example.
