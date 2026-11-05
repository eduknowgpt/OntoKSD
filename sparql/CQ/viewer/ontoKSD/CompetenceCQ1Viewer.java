package ontoKSD;

import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.Collator;
import java.text.Normalizer;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.eclipse.rdf4j.rio.helpers.NTriplesUtil;

/** A read-only Swing interface for executing the CQ1 file against GraphDB. */
public class CompetenceCQ1Viewer extends JFrame {
    private static final long serialVersionUID = 1L;

    // Change only these values to use another project location or repository.
    private static final String QUERY_ENDPOINT = "http://localhost:7200/repositories/OntoKSD2";
    private static final Path CQ1_FILE = Paths.get("I:\\Meu Drive\\OntoKSD\\sparql\\CQ\\CQ1 - Which Knowledge Skill  and Disposition elements  constitute a given competence.rq");
    private static final boolean INCLUDE_INFERRED = true;
    private static final int QUERY_TIMEOUT_SECONDS = 30;

    // The list query includes instances typed through a competence subclass.
    // The constituent query itself is always read from CQ1_FILE, never rebuilt here.
    private static final String COMPETENCE_LIST_QUERY =
        "PREFIX o: <http://purl.org/ontoksd#>\n" +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
        "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
        "PREFIX dcterms: <http://purl.org/dc/terms/>\n" +
        "SELECT DISTINCT ?competence ?label WHERE {\n" +
        " ?competence a/rdfs:subClassOf* o:Competence . FILTER(isIRI(?competence))\n" +
        " OPTIONAL { ?competence (rdfs:label|skos:prefLabel|dcterms:title) ?label . }\n" +
        "}";

    private final SPARQLRepository repository;
    private final Path queryFile;
    private final JTextField filter = new JTextField(25);
    private final JComboBox<Choice> competences = new JComboBox<Choice>();
    private final JButton reload = new JButton("Atualizar lista");
    private final JButton consult = new JButton("Consultar");
    private final JLabel listCount = new JLabel(" ");
    private final JTextField selectedIri = new JTextField();
    private final JLabel status = new JLabel("Carregando competências...");
    private final JProgressBar progress = new JProgressBar();
    private final DefaultTableModel constituentModel = readOnlyModel(
            new String[] {"Tipo", "Constituinte", "Conhecimento do par", "Nível requerido"});
    private final DefaultTableModel rawModel = readOnlyModel(new String[0]);
    private final JTextArea queryText = new JTextArea();
    private final List<Choice> allCompetences = new ArrayList<Choice>();
    private SwingWorker<?, ?> worker;
    private boolean updatingList;
    private boolean closed;

    public CompetenceCQ1Viewer() {
        this(QUERY_ENDPOINT, CQ1_FILE);
    }

    // This constructor also allows isolated tests with a local mock endpoint.
    public CompetenceCQ1Viewer(String endpoint, Path file) {
        super("OntoKSD — Explorador da CQ1");
        repository = new SPARQLRepository(endpoint);
        queryFile = file;
        buildWindow();
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent event) {
                closed = true;
                if (worker != null) worker.cancel(true);
            }
        });
        // Release RDF4J resources when this standalone application exits.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try { repository.shutDown(); } catch (Exception ignored) { /* JVM is closing. */ }
        }, "cq1-client-shutdown"));
        loadCompetences();
    }

    private void buildWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel page = new JPanel(new BorderLayout(10, 10));
        page.setBorder(BorderFactory.createEmptyBorder(14, 16, 12, 16));
        setContentPane(page);

        JPanel header = new JPanel(new BorderLayout(5, 8));
        JLabel title = new JLabel("Constituintes de uma competência");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 21f));
        JPanel intro = new JPanel(new GridLayout(2, 1, 0, 4));
        intro.add(title);
        intro.add(new JLabel("Selecione uma competência para consultar os elementos de sua especificação."));
        header.add(intro, BorderLayout.NORTH);

        JPanel controls = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 0, 4, 8); c.anchor = GridBagConstraints.WEST;
        c.gridx = 0; c.gridy = 0; controls.add(new JLabel("Filtrar:"), c);
        c.gridx = 1; c.weightx = 1; c.fill = GridBagConstraints.HORIZONTAL; controls.add(filter, c);
        c.gridx = 2; c.weightx = 0; controls.add(reload, c);
        c.gridx = 0; c.gridy = 1; c.fill = GridBagConstraints.NONE; controls.add(new JLabel("Competência:"), c);
        c.gridx = 1; c.weightx = 1; c.fill = GridBagConstraints.HORIZONTAL; controls.add(competences, c);
        c.gridx = 2; c.weightx = 0; controls.add(consult, c);
        c.gridx = 1; c.gridy = 2; controls.add(listCount, c);
        selectedIri.setEditable(false);
        c.gridx = 0; c.gridy = 3; c.fill = GridBagConstraints.NONE; controls.add(new JLabel("IRI:"), c);
        c.gridx = 1; c.gridwidth = 2; c.weightx = 1; c.fill = GridBagConstraints.HORIZONTAL;
        controls.add(selectedIri, c);
        header.add(controls, BorderLayout.CENTER);
        page.add(header, BorderLayout.NORTH);

        competences.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;
            @Override public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean selected, boolean focus) {
                super.getListCellRendererComponent(list, value, index, selected, focus);
                putClientProperty("html.disable", Boolean.TRUE);
                if (value instanceof Choice) setToolTipText(((Choice) value).iri.stringValue());
                return this;
            }
        });
        JTable table = makeTable(constituentModel);
        int[] widths = {155, 360, 350, 190};
        for (int i = 0; i < widths.length; i++) table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        JTable rawTable = makeTable(rawModel);
        queryText.setEditable(false);
        queryText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Constituintes", new JScrollPane(table));
        tabs.addTab("Resultado SPARQL", new JScrollPane(rawTable));
        tabs.addTab("Consulta para reprodução", new JScrollPane(queryText));
        page.add(tabs, BorderLayout.CENTER);
        JPanel footer = new JPanel(new BorderLayout(8, 0));
        progress.setPreferredSize(new Dimension(130, 15)); progress.setVisible(false);
        footer.add(status, BorderLayout.CENTER); footer.add(progress, BorderLayout.EAST);
        page.add(footer, BorderLayout.SOUTH);

        reload.addActionListener(event -> loadCompetences());
        consult.addActionListener(event -> loadSelected());
        competences.addActionListener(event -> {
            if (!updatingList && competences.getSelectedItem() != null) loadSelected();
        });
        filter.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent event) { applyFilter(false); }
            public void removeUpdate(DocumentEvent event) { applyFilter(false); }
            public void changedUpdate(DocumentEvent event) { applyFilter(false); }
        });
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Math.min(1200, screen.width - 60), Math.min(760, screen.height - 90));
        setMinimumSize(new Dimension(850, 520));
        setLocationRelativeTo(null);
    }

    private void loadCompetences() {
        if (closed) return;
        clearResults(); setBusy(true, "Carregando competências do GraphDB...");
        SwingWorker<List<Choice>, Void> task = new SwingWorker<List<Choice>, Void>() {
            @Override protected List<Choice> doInBackground() {
                repository.init();
                Map<IRI, Choice> items = new LinkedHashMap<IRI, Choice>();
                try (RepositoryConnection connection = repository.getConnection()) {
                    TupleQuery query = prepare(connection, COMPETENCE_LIST_QUERY);
                    try (TupleQueryResult result = query.evaluate()) {
                        while (result.hasNext()) {
                            BindingSet row = result.next();
                            Value value = row.getValue("competence");
                            if (!(value instanceof IRI)) continue;
                            IRI iri = (IRI) value;
                            Choice item = items.get(iri);
                            if (item == null) { item = new Choice(iri); items.put(iri, item); }
                            item.label = betterLabel(item.label, row.getValue("label"));
                        }
                    }
                }
                List<Choice> sorted = new ArrayList<Choice>(items.values());
                Collator collator = Collator.getInstance(new Locale("pt", "BR"));
                sorted.sort((a, b) -> collator.compare(a.displayName(), b.displayName()));
                return sorted;
            }
            @Override protected void done() {
                if (closed) return;
                try {
                    allCompetences.clear(); allCompetences.addAll(get());
                    applyFilter(true); setBusy(false, allCompetences.size() + " competências disponíveis.");
                    if (competences.getSelectedItem() != null) loadSelected();
                    else status.setText("Nenhuma competência encontrada. Confira os dados carregados e o filtro.");
                } catch (Exception e) { showError("Não foi possível carregar as competências.", e); }
            }
        };
        worker = task; task.execute();
    }

    private void applyFilter(boolean chooseFirst) {
        Choice previous = (Choice) competences.getSelectedItem();
        String term = normalize(filter.getText());
        DefaultComboBoxModel<Choice> model = new DefaultComboBoxModel<Choice>();
        Choice keep = null;
        for (Choice item : allCompetences) {
            if (normalize(item.displayName() + " " + item.iri.stringValue()).contains(term)) {
                model.addElement(item);
                if (previous != null && previous.iri.equals(item.iri)) keep = item;
            }
        }
        updatingList = true;
        try {
            competences.setModel(model);
            competences.setSelectedItem(keep != null ? keep : chooseFirst && model.getSize() > 0 ? model.getElementAt(0) : null);
        } finally { updatingList = false; }
        listCount.setText(model.getSize() + " de " + allCompetences.size() + " competências");
        consult.setEnabled(!progress.isIndeterminate() && competences.getSelectedItem() != null);
        if (competences.getSelectedItem() == null) {
            clearResults(); status.setText("Selecione uma competência na lista.");
        }
    }

    private void loadSelected() {
        final Choice selected = (Choice) competences.getSelectedItem();
        if (selected == null || closed) return;
        clearResults(); selectedIri.setText(selected.iri.stringValue());
        setBusy(true, "Consultando os constituintes de " + shortName(selected.iri) + "...");
        SwingWorker<Answer, Void> task = new SwingWorker<Answer, Void>() {
            @Override protected Answer doInBackground() throws Exception {
                // Reload the file for every execution so query edits are picked up.
                String original = new String(Files.readAllBytes(queryFile), StandardCharsets.UTF_8);
                String executable = parameterize(original, selected.iri);
                Answer answer = new Answer();
                answer.reproductionQuery = "# Source: " + queryFile + "\n" + executable;
                try (RepositoryConnection connection = repository.getConnection()) {
                    TupleQuery query = prepare(connection, executable);
                    try (TupleQueryResult result = query.evaluate()) {
                        answer.columns.addAll(result.getBindingNames());
                        if (!answer.columns.contains("competence") || !answer.columns.contains("constituent"))
                            throw new IllegalArgumentException("A CQ1 deve retornar as variáveis ?competence e ?constituent.");
                        while (result.hasNext()) {
                            BindingSet row = result.next();
                            Value competence = row.getValue("competence");
                            if (competence == null || !selected.iri.equals(competence))
                                throw new IllegalStateException("A consulta retornou uma competência diferente da selecionada.");
                            Map<String, Value> values = new LinkedHashMap<String, Value>();
                            for (String name : answer.columns) values.put(name, row.getValue(name));
                            answer.rows.add(values);
                        }
                    }
                    // Labels are supplementary display data, not a replacement for the CQ1.
                    try { answer.labels.putAll(loadLabels(connection, answer.rows)); }
                    catch (Exception e) { answer.labelWarning = true; }
                }
                return answer;
            }
            @Override protected void done() {
                if (closed) return;
                try { showAnswer(get()); }
                catch (Exception e) { showError("Não foi possível executar a CQ1. Confira o GraphDB e o arquivo da consulta.", e); }
            }
        };
        worker = task; task.execute();
    }

    static String parameterize(String source, IRI competence) {
        String query = removeExampleSelection(source);
        Matcher where = Pattern.compile("(?i)WHERE\\s*\\{").matcher(query);
        if (!where.find()) throw new IllegalArgumentException("A CQ1 deve conter um bloco WHERE.");
        // Serialize the IRI as an RDF term, rather than interpolating unchecked text.
        // VALUES preserves variables in ORDER BY (some remote bindings rewrite them).
        return where.replaceFirst(Matcher.quoteReplacement("WHERE {\nVALUES ?competence { " +
                NTriplesUtil.toNTriplesString(competence) + " }"));
    }

    static String removeExampleSelection(String source) {
        if (source.startsWith("\uFEFF")) source = source.substring(1);
        // Remove only the standalone example VALUES block for ?competence.
        // The file is not changed; all remaining CQ1 logic is executed as authored.
        Pattern pattern = Pattern.compile("(?im)^\\s*VALUES\\s+\\?competence\\s*\\{[^{}]*\\}\\s*(?:\\r?\\n|$)");
        Matcher matcher = pattern.matcher(source); int count = 0;
        while (matcher.find()) count++;
        if (count > 1) throw new IllegalArgumentException("A CQ1 contém mais de uma seleção VALUES para ?competence.");
        return pattern.matcher(source).replaceAll("");
    }

    private Map<Value, Label> loadLabels(RepositoryConnection connection, List<Map<String, Value>> rows) {
        Set<IRI> entities = new LinkedHashSet<IRI>();
        for (Map<String, Value> row : rows) for (String key : Arrays.asList("constituent", "knowledge", "skillLevel")) {
            Value value = row.get(key); if (value instanceof IRI) entities.add((IRI) value);
        }
        Map<Value, Label> labels = new HashMap<Value, Label>();
        if (entities.isEmpty()) return labels;
        StringBuilder values = new StringBuilder();
        for (IRI iri : entities) values.append(NTriplesUtil.toNTriplesString(iri)).append(' ');
        String text = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
            "PREFIX dcterms: <http://purl.org/dc/terms/>\n" +
            "SELECT ?entity ?label WHERE { VALUES ?entity { " + values + " } " +
            "?entity (rdfs:label|skos:prefLabel|dcterms:title) ?label . }";
        try (TupleQueryResult result = prepare(connection, text).evaluate()) {
            while (result.hasNext()) {
                BindingSet row = result.next(); Value entity = row.getValue("entity");
                labels.put(entity, betterLabel(labels.get(entity), row.getValue("label")));
            }
        }
        return labels;
    }

    private TupleQuery prepare(RepositoryConnection connection, String text) {
        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, text);
        query.setIncludeInferred(INCLUDE_INFERRED);
        query.setMaxExecutionTime(QUERY_TIMEOUT_SECONDS);
        return query;
    }

    private void showAnswer(Answer answer) {
        rawModel.setDataVector(new Object[0][0], answer.columns.toArray());
        Map<List<Value>, Map<String, Value>> distinct = new LinkedHashMap<List<Value>, Map<String, Value>>();
        Set<Value> constituents = new HashSet<Value>();
        for (Map<String, Value> row : answer.rows) {
            Object[] raw = new Object[answer.columns.size()];
            for (int i = 0; i < raw.length; i++) {
                Value value = row.get(answer.columns.get(i));
                raw[i] = value == null ? "" : value.toString();
            }
            rawModel.addRow(raw);
            List<Value> key = Arrays.asList(row.get("constituent"), row.get("constituentType"), row.get("knowledge"), row.get("skillLevel"));
            distinct.putIfAbsent(key, row);
            if (row.get("constituent") != null) constituents.add(row.get("constituent"));
        }
        constituentModel.setRowCount(0);
        for (Map<String, Value> row : distinct.values()) {
            Value constituent = row.get("constituent");
            Label fromQuery = betterLabel(answer.labels.get(constituent), row.get("constituentLabel"));
            constituentModel.addRow(new Object[] {typeName(row.get("constituentType")),
                    display(constituent, fromQuery), display(row.get("knowledge"), answer.labels.get(row.get("knowledge"))),
                    display(row.get("skillLevel"), answer.labels.get(row.get("skillLevel")))});
        }
        queryText.setText(answer.reproductionQuery); queryText.setCaretPosition(0);
        String message = answer.rows.isEmpty()
                ? "Nenhum constituinte retornado pela CQ1 para a competência selecionada."
                : constituents.size() + " constituintes; " + distinct.size() + " linhas exibidas; " + answer.rows.size() + " linhas SPARQL.";
        if (answer.labelWarning) message += " Alguns rótulos complementares não puderam ser consultados.";
        setBusy(false, message);
    }

    private void clearResults() {
        constituentModel.setRowCount(0); rawModel.setRowCount(0); queryText.setText(""); selectedIri.setText("");
    }
    private void setBusy(boolean busy, String message) {
        progress.setIndeterminate(busy); progress.setVisible(busy);
        filter.setEnabled(!busy); competences.setEnabled(!busy); reload.setEnabled(!busy);
        consult.setEnabled(!busy && competences.getSelectedItem() != null);
        setCursor(Cursor.getPredefinedCursor(busy ? Cursor.WAIT_CURSOR : Cursor.DEFAULT_CURSOR));
        status.setText(message);
    }
    private void showError(String message, Exception error) {
        Throwable cause = error instanceof ExecutionException && error.getCause() != null ? error.getCause() : error;
        clearResults(); setBusy(false, message);
        JOptionPane.showMessageDialog(this, message + "\n\nDetalhes: " + cause.getMessage(), "Consulta não concluída", JOptionPane.ERROR_MESSAGE);
    }
    private static DefaultTableModel readOnlyModel(String[] columns) {
        return new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
    }
    private static JTable makeTable(DefaultTableModel model) {
        JTable table = new JTable(model); table.setAutoCreateRowSorter(true);
        table.setRowHeight(29); table.setFillsViewportHeight(true); table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;
            @Override public Component getTableCellRendererComponent(JTable t, Object value, boolean selected, boolean focus, int row, int column) {
                super.getTableCellRendererComponent(t, value, selected, focus, row, column);
                putClientProperty("html.disable", Boolean.TRUE);
                setToolTipText(value == null ? null : "Valor: " + value.toString());
                return this;
            }
        });
        return table;
    }
    static String normalize(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase(Locale.ROOT).trim();
    }
    static Label betterLabel(Label current, Value candidate) {
        if (!(candidate instanceof Literal)) return current;
        Literal literal = (Literal) candidate; String text = literal.getLabel().trim();
        if (text.isEmpty()) return current;
        String language = literal.getLanguage().orElse("").toLowerCase(Locale.ROOT);
        int rank = language.equals("pt") || language.startsWith("pt-") ? 0 : language.equals("en") || language.startsWith("en-") ? 1 : language.isEmpty() ? 2 : 3;
        return current == null || rank < current.rank || rank == current.rank && text.compareToIgnoreCase(current.text) < 0 ? new Label(text, rank) : current;
    }
    static String shortName(Value value) {
        if (value == null) return "—";
        if (value instanceof IRI) return ((IRI) value).getLocalName();
        return value.stringValue();
    }
    private static String display(Value value, Label label) { return value == null ? "—" : label == null ? shortName(value) : label.text; }
    private static String typeName(Value value) {
        switch (shortName(value)) {
            case "Knowledge": return "Conhecimento";
            case "Skill": return "Habilidade";
            case "Disposition": return "Disposição";
            case "Attitude": return "Atitude";
            case "KnowledgeSkill": return "Conhecimento–nível";
            default: return "Constituinte";
        }
    }
    static final class Label {
        final String text; final int rank;
        Label(String text, int rank) { this.text = text; this.rank = rank; }
    }
    private static final class Choice {
        final IRI iri; Label label;
        Choice(IRI iri) { this.iri = iri; }
        String displayName() { return label == null ? shortName(iri) : label.text; }
        @Override public String toString() { return displayName() + " — " + shortName(iri); }
    }
    private static final class Answer {
        final List<String> columns = new ArrayList<String>();
        final List<Map<String, Value>> rows = new ArrayList<Map<String, Value>>();
        final Map<Value, Label> labels = new HashMap<Value, Label>();
        String reproductionQuery; boolean labelWarning;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (Exception ignored) { /* Use Swing's default look and feel. */ }
            new CompetenceCQ1Viewer().setVisible(true);
        });
    }
}
