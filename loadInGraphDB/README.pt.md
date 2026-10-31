# Carga da OntoKSD no GraphDB — Java

Substitua a classe `ontoKSD.SparqlBatchInserter` do projeto Eclipse pelo arquivo [SparqlBatchInserter.java](ontoKSD/SparqlBatchInserter.java).

A sequência completa está no próprio `main`. Não há manifestos, argumentos de execução ou configuração adicional de arquivos. Ajuste apenas as constantes `ROOT`, `GRAPHDB_SERVER` e `REPOSITORY_ID`, se necessário.

## Ordem da carga

1. SKOS, gUFO, RCD, Bloom, ontologia principal e BoKCS2023.
2. Disposições, conhecimentos e conhecimentos fundamentais do CC2020.
3. Tipos de verbo, verbos e links DBpedia.
4. Competências CC2020.
5. Declarações compartilhadas e competências CS2023.
6. Declarações compartilhadas e competências IS2020.
7. Ciclos CSP 01, 02, 03 e 04: arquivos auxiliares na raiz, competências e tarefas. No ciclo 04, também os alinhamentos externos e o histórico documental.

As chamadas dos dois arquivos opcionais de curso/proficiência do ciclo 02 também estão no código, comentadas. Descomente ambas apenas se desejar incluir essa camada. Os links DBpedia são opcionais; sua chamada pode ser comentada.

**Se SKOS e RCD já estiverem carregados, comente suas chamadas `loadRdf`.** O mesmo cuidado vale para outros módulos previamente carregados: repetir a importação de RDF com nós em branco pode acrescentar estruturas duplicadas.

## Execução e dependências

O programa mantém o `SPARQLRepository` já utilizado no projeto. São informados separadamente o endpoint de consulta e o endpoint de atualização, terminado em `/statements`. Não é necessário acrescentar o módulo `rdf4j-repository-http`.

A carga RDF usa `connection.add(...)`, que o SPARQLRepository converte em atualizações SPARQL. Os parsers RDF4J de Turtle e RDF/XML devem estar disponíveis nas bibliotecas do projeto (`rdf4j-rio-turtle` e `rdf4j-rio-rdfxml`, na mesma versão dos demais módulos).

No Eclipse, execute `SparqlBatchInserter` como uma aplicação Java. O repositório `OntoKSD2` deve existir e o GraphDB deve estar em execução. O código não cria nem limpa repositórios e não executa automaticamente consultas dos diretórios `queries/`.

Os arquivos `.rq` de cada diretório são executados em ordem de nome, sem percorrer subdiretórios. Assim, os arquivos iniciados por `00` são carregados antes dos exemplos que dependem deles. Todos os caminhos usados estão explícitos no código.

O programa mostra a etapa, o arquivo em processamento e a confirmação de envio, com comentários e mensagens em inglês. Ao ocorrer um erro, interrompe a sequência e informa o arquivo afetado. Os arquivos enviados anteriormente permanecem carregados; não há reversão do lote completo.

Não carregue também os arquivos `cycleXX-instances.ttl`: eles são alternativas aos scripts dos ciclos, já incluídos no programa. Uma carga bem-sucedida não substitui as consultas de conferência ou a validação lógica da ontologia.

Verificação realizada: a versão com SPARQLRepository compilou com JDK 19 e RDF4J 4.2.3, sem o módulo rdf4j-repository-http no classpath. A sequência de seis chamadas RDF e 137 atualizações foi conferida anteriormente com conexão simulada; a troca do cliente foi conferida por compilação e inspeção da API. Nenhum dado foi enviado ao GraphDB durante esses testes.

Referência da API: [RDF4J Repository API](https://rdf4j.org/documentation/programming/repository/).
