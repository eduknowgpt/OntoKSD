package ontoKSD;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.eclipse.rdf4j.rio.RDFFormat;

/** Loads OntoKSD modules, vocabularies and examples in dependency order. */
public class SparqlBatchInserter {

    // Change these three values for another checkout or GraphDB repository.
    private static final Path ROOT = Paths.get("I:\\Meu Drive\\OntoKSD");
    private static final String GRAPHDB_SERVER = "http://localhost:7200";
    private static final String REPOSITORY_ID = "OntoKSD2";

    private static int rdfFilesLoaded = 0;
    private static int updatesExecuted = 0;
    private static String currentFile = "(no file selected)";

    public static void main(String[] args) {
        // Keep the SPARQLRepository already used by this project.
        // Provide separate query and update endpoints. RDF4J converts RDF imports
        // into SPARQL updates through connection.add(...).
        // Initialize it once, reuse one connection and release resources at the end.
        String queryEndpoint = GRAPHDB_SERVER + "/repositories/" + REPOSITORY_ID;
        String updateEndpoint = queryEndpoint + "/statements";
        Repository repository = new SPARQLRepository(queryEndpoint, updateEndpoint);
        int exitCode = 0;
        long started = System.currentTimeMillis();

        System.out.println("Starting OntoKSD load");
        System.out.println("Project: " + ROOT);
        System.out.println("Repository: " + GRAPHDB_SERVER + "/repositories/" + REPOSITORY_ID);

        try {
            repository.init();
            try (RepositoryConnection connection = repository.getConnection()) {

                // 1. Load local RDF modules explicitly.
                // Resolving owl:imports in Protege does not load these files into GraphDB.
                // If SKOS or RCD is already loaded, comment out its respective call.
                section("1. Ontology modules and CS2023 Body of Knowledge");
                //loadRdf(connection, "ontoKSDOntology/SKOS.rdf", RDFFormat.RDFXML);
                //loadRdf(connection, "ontoKSDOntology/gufo.ttl", RDFFormat.TURTLE);
                //loadRdf(connection, "rcd/RCD.ttl", RDFFormat.TURTLE);
                //loadRdf(connection, "bloomTaxonomy/bloom-taxonomy.ttl", RDFFormat.TURTLE);
                //loadRdf(connection, "ontoKSDOntology/OntoKSD - SKOS - v7.0.ttl", RDFFormat.TURTLE);
                //loadRdf(connection, "cs2023Vocabulary/BoKCS2023_ontoksd.ttl", RDFFormat.TURTLE);

                // 2. Populate shared CC2020-oriented vocabularies.
                section("2. CC2020 vocabularies");
                executeFile(connection, "cc2020Vocabulary/ontoKSD-disposition-insert.rq");
                executeFile(connection, "cc2020Vocabulary/cc2020-knowledge-insert.rq");
                executeFile(connection, "cc2020Vocabulary/cc2020-foundational-knowledge-insert.rq");

                // 3. Load verb types before verbs, then optional informational links.
                section("3. Action-verb vocabulary");
                executeFile(connection, "bloomTaxonomy/verbs/bloom-taxonomy-actionVerbType-insert.rq");
                executeFile(connection, "bloomTaxonomy/verbs/bloom-taxonomy-verbs-insert.rq");
                // Optional: comment out this call if DBpedia links are not needed.
                executeFile(connection, "bloomTaxonomy/verbs/bloom-taxonomy-dbpedia-insert.rq");

                // 4-6. Shared files starting with "00" are loaded first by filename order.
                // Read immediate .rq files only; never recurse into query directories.
                section("4. CC2020 competence examples");
                executeDirectory(connection, "competenceInstances/CC2020/sparql");

                section("5. CS2023 competence examples");
                executeDirectory(connection, "competenceInstances/CS2023/sparql");

                section("6. IS2020 competence examples");
                executeDirectory(connection, "competenceInstances/IS2020/sparql");

                // 7. Each CSP cycle needs its root-level auxiliary files before
                // competence definitions and task activations. Preserve cycle order.
                section("7. CSP cycle 01");
                executeFile(connection, "competenceInstances/CSP/cycle-01/00 - Cycle01 Sources and Knowledge.rq");
                executeFile(connection, "competenceInstances/CSP/cycle-01/01 - Cycle01 Justification Verbs.rq");
                executeDirectory(connection, "competenceInstances/CSP/cycle-01/competencies");
                executeDirectory(connection, "competenceInstances/CSP/cycle-01/tasks");

                section("8. CSP cycle 02");
                executeFile(connection, "competenceInstances/CSP/cycle-02/00 - Cycle02 Sources and Vocabulary.rq");
                executeDirectory(connection, "competenceInstances/CSP/cycle-02/competencies");
                executeDirectory(connection, "competenceInstances/CSP/cycle-02/tasks");
                
                // Load historical assessment records after cycle-02 planning.
                executeFile(connection,
                    "competenceInstances/CSP/cycle-02/assessment-records/task201/task201-records.rq");

                executeFile(connection,
                    "competenceInstances/CSP/cycle-02/assessment-records/task202/task202-records.rq");                

                section("9. CSP cycle 03");
                executeFile(connection, "competenceInstances/CSP/cycle-03/00 - Cycle03 Sources and Vocabulary.rq");
                executeFile(connection, "competenceInstances/CSP/cycle-03/01 - Cycle03 Justification Verbs.rq");
                executeDirectory(connection, "competenceInstances/CSP/cycle-03/competencies");
                executeDirectory(connection, "competenceInstances/CSP/cycle-03/tasks");

                section("10. CSP cycle 04");
                executeFile(connection, "competenceInstances/CSP/cycle-04/00 - Cycle04 Sources and Vocabulary.rq");
                executeFile(connection, "competenceInstances/CSP/cycle-04/01 - Cycle04 Justification Verbs.rq");
                executeDirectory(connection, "competenceInstances/CSP/cycle-04/competencies");
                executeDirectory(connection, "competenceInstances/CSP/cycle-04/tasks");
                executeFile(connection, "competenceInstances/CSP/cycle-04/02 - Cycle04 External Alignments.rq");
                executeFile(connection, "competenceInstances/CSP/cycle-04/03 - Cycle04 Documentary History.rq");

                // Optional course/proficiency policy: uncomment BOTH calls if required.
                // These files are not necessary for the basic CSP instance dataset.
                executeFile(connection, "competenceInstances/CSP/cycle-02/cycle-02-operational-layer/second_cycle_shared_proficiency_scheme.rq");
                executeFile(connection, "competenceInstances/CSP/cycle-02/cycle-02-operational-layer/task201_204_course_layer.rq");
                
                
                executeFile(connection, "proficiency level/01 - ProficiencyLevel.rq");
                executeFile(connection, "proficiency level/02 - Proficiency_Instances.rq");
                executeFile(connection, "proficiency level/03 - Gap_Analisys.rq");                
                
             // Synthetic prerequisite fixture; not historical CSP data.
                section("11. Optional CQ11 synthetic prerequisite test");
                executeFile(connection,
                    "sparql/CQ/tests/CQ11/01-load-synthetic-data.rq");
                
                
                // Synthetic prerequisite fixture; not historical CSP data.
                section("12. Optional CQ27 synthetic prerequisite test");
                executeFile(connection,
                    "sparql/CQ/tests/CQ27/01-load-synthetic-data.rq");                

                // Do not also load cycleXX-instances.ttl: those files are alternatives
                // to the cycle update scripts above, not additional instance datasets.
            }
            System.out.println("\n[SUCCESS] All selected files were submitted.");
        } catch (Exception e) {
            exitCode = 1;
            System.err.println("\n[ERROR] Loading stopped at: " + currentFile);
            System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            System.err.println("Earlier successful requests remain loaded; no batch rollback is performed.");
            System.err.println("Check GraphDB before retrying a failed or timed-out request.");
        } finally {
            try {
                repository.shutDown();
            } catch (Exception e) {
                exitCode = 1;
                System.err.println("[ERROR] Could not close the repository client: " + e.getMessage());
            }
            System.out.println("\nRDF files submitted: " + rdfFilesLoaded);
            System.out.println("SPARQL updates executed: " + updatesExecuted);
            System.out.printf(Locale.ROOT, "Elapsed time: %.1f seconds%n",
                    (System.currentTimeMillis() - started) / 1000.0);
        }
        if (exitCode != 0) System.exit(exitCode);
    }

    /** Imports one RDF file into the default graph using its declared format. */
    private static void loadRdf(RepositoryConnection connection, String relativePath,
            RDFFormat format) throws IOException {
        File file = requireFile(relativePath);
        System.out.println("[RDF] " + relativePath);
        connection.add(file, file.toURI().toString(), format);
        rdfFilesLoaded++;
        System.out.println("      [OK] RDF file submitted.");
    }

    /** Executes one UTF-8 SPARQL update; removes an optional UTF-8 BOM. */
    private static void executeFile(RepositoryConnection connection, String relativePath)
            throws IOException {
        File file = requireFile(relativePath);
        String update = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        if (update.startsWith("\uFEFF")) update = update.substring(1);
        if (update.trim().isEmpty()) throw new IOException("Empty SPARQL file: " + file);
        System.out.println("[SPARQL] " + relativePath);
        connection.prepareUpdate(QueryLanguage.SPARQL, update).execute();
        updatesExecuted++;
        System.out.println("         [OK] Update executed.");
    }

    /** Executes immediate .rq files in filename order, without reading subfolders. */
    private static void executeDirectory(RepositoryConnection connection, String relativePath)
            throws IOException {
        File directory = ROOT.resolve(relativePath).toFile();
        currentFile = directory.toString();
        if (!directory.isDirectory()) throw new IOException("Directory not found: " + directory);
        File[] files = directory.listFiles(file -> file.isFile()
                && file.getName().toLowerCase(Locale.ROOT).endsWith(".rq"));
        if (files == null || files.length == 0)
            throw new IOException("No .rq files found in: " + directory);
        Arrays.sort(files, Comparator.comparing(File::getName));
        System.out.println("Directory: " + relativePath + " (" + files.length + " updates)");
        for (File file : files) {
            executeFile(connection, ROOT.relativize(file.toPath()).toString());
        }
    }

    private static File requireFile(String relativePath) throws IOException {
        File file = ROOT.resolve(relativePath).toFile();
        currentFile = file.toString();
        if (!file.isFile()) throw new IOException("File not found: " + file);
        return file;
    }

    private static void section(String title) {
        System.out.println("\n=== " + title + " ===");
    }
}
