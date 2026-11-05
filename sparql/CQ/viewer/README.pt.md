# Explorador da CQ1

Interface Java Swing para selecionar uma competência no GraphDB e visualizar os constituintes retornados pela CQ1 da OntoKSD.

## Executar no Eclipse

1. Copie `ontoKSD/CompetenceCQ1Viewer.java` para o pacote `ontoKSD` do projeto Java que já utiliza RDF4J.
2. Confira as duas constantes no início da classe:
   - `QUERY_ENDPOINT`: endereço de consulta do repositório. Exemplo: `http://localhost:7200/repositories/OntoKSD2` (sem `/statements`).
   - `CQ1_FILE`: caminho completo do arquivo `.rq` da CQ1 no diretório `sparql/CQ`.
3. Com o GraphDB iniciado e a ontologia e suas instâncias carregadas, execute a classe como **Java Application**.

O programa utiliza `SPARQLRepository`, como o programa de carga existente. Swing faz parte do Java. Para um projeto Maven sem RDF4J, a dependência principal é `org.eclipse.rdf4j:rdf4j-repository-sparql:4.2.3`, com suas dependências transitivas. Use versões compatíveis das bibliotecas; RDF4J 4.2.3 requer Java 11 ou superior.

## Usar a tela

- Digite parte do nome ou IRI em **Filtrar** e selecione uma competência na lista.
- A seleção executa a CQ1. **Consultar** repete a consulta; **Atualizar lista** relê as competências disponíveis no GraphDB.
- **Constituintes** mostra tipo, nome e, para pares `KnowledgeSkill`, conhecimento e nível requerido.
- **Resultado SPARQL** preserva todas as linhas e variáveis retornadas pela CQ1.
- **Consulta para reprodução** apresenta a consulta com a competência selecionada, para copiar no GraphDB.

A preferência de rótulos é português, inglês, sem idioma e demais idiomas. Na ausência de rótulo, aparece o nome local da IRI. A tabela principal reúne linhas que diferem apenas em rótulos; a aba SPARQL mantém os resultados completos.

## Relação com a CQ1

O arquivo é lido em UTF-8 a cada consulta. O bloco de exemplo `VALUES ?competence { ontoksd:C02 }`, em linha própria, é removido **somente da cópia em memória**. Um novo bloco `VALUES` recebe a IRI selecionada, serializada como termo RDF para evitar a inserção de texto não validado na consulta. O arquivo original permanece intacto.

A CQ1 deve ser uma consulta SELECT e retornar `?competence` e `?constituent`. A apresentação detalhada utiliza também `?constituentLabel`, `?constituentType`, `?knowledge` e `?skillLevel`, como na CQ1 atual. A listagem de competências e a busca complementar de rótulos são consultas auxiliares do programa; a busca de constituintes vem do arquivo CQ1.

A tela mostra **constituintes semânticos**, e não a composição entre competências por `hasComponentCompetence`. Um resultado vazio indica apenas que a consulta não retornou constituintes para aquela competência no repositório consultado.

São solicitadas inferências disponíveis no repositório (`INCLUDE_INFERRED = true`), com limite de execução de 30 segundos por consulta. Isso não configura nem substitui o regime de inferência do GraphDB. As consultas usam o dataset padrão do endpoint; caso os dados estejam apenas em grafos nomeados, ajuste o dataset ou a CQ1.

O aplicativo executa apenas consultas e não insere nem altera dados. A senha/autenticação não está configurada neste exemplo local.
