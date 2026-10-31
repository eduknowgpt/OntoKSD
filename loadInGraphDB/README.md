# Loading OntoKSD into GraphDB — Java

[Português](README.pt.md)

Replace the `ontoKSD.SparqlBatchInserter` class in the Eclipse project with [SparqlBatchInserter.java](ontoKSD/SparqlBatchInserter.java).

The complete loading sequence is defined directly in `main`. No manifests, command-line arguments or additional configuration files are required. Adjust only the `ROOT`, `GRAPHDB_SERVER` and `REPOSITORY_ID` constants if necessary.

## Loading order

1. SKOS, gUFO, RCD, Bloom, the main ontology and BoKCS2023.
2. CC2020 dispositions, knowledge and foundational knowledge.
3. Action-verb types, action verbs and DBpedia links.
4. CC2020 competences.
5. Shared declarations and CS2023 competences.
6. Shared declarations and IS2020 competences.
7. CSP cycles 01, 02, 03 and 04: root-level auxiliary files, competences and tasks. Cycle 04 also includes external alignments and documentary history.

The calls for the two optional course/proficiency files from cycle 02 are also included in the code, commented out. Uncomment both only if you want to include that layer. DBpedia links are optional; their loading call can be commented out.

**If SKOS and RCD are already loaded, comment out their respective `loadRdf` calls.** The same consideration applies to other previously loaded modules: importing RDF with blank nodes again may introduce duplicate structures.

## Execution and dependencies

The program retains the `SPARQLRepository` already used by the project. The query endpoint and the update endpoint, ending in `/statements`, are specified separately. The `rdf4j-repository-http` module is not required.

RDF loading uses `connection.add(...)`, which SPARQLRepository converts into SPARQL updates. The RDF4J Turtle and RDF/XML parsers must be available in the project libraries (`rdf4j-rio-turtle` and `rdf4j-rio-rdfxml`, using the same version as the other modules).

In Eclipse, run `SparqlBatchInserter` as a Java application. The `OntoKSD2` repository must already exist and GraphDB must be running. The code does not create or clear repositories and does not automatically execute queries from `queries/` directories.

The `.rq` files in each directory are executed in filename order, without traversing subdirectories. Files beginning with `00` are therefore loaded before the examples that depend on them. All paths are explicitly defined in the code.

The program displays the current stage, the file being processed and confirmation of submission. Comments and console messages are in English. If an error occurs, loading stops and the affected file is identified. Previously submitted files remain loaded; there is no rollback of the entire batch.

Do not also load the `cycleXX-instances.ttl` files: they are alternatives to the cycle scripts already included in the program. A successful load does not replace verification queries or logical validation of the ontology.

Verification performed: the SPARQLRepository version compiled with JDK 19 and RDF4J 4.2.3, without the `rdf4j-repository-http` module on the classpath. The sequence of six RDF calls and 137 updates was previously checked using a mock connection; the client replacement was checked through compilation and API inspection. No data was submitted to GraphDB during these tests.

API reference: [RDF4J Repository API](https://rdf4j.org/documentation/programming/repository/).
