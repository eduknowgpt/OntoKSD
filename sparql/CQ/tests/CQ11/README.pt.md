# CQ11 — Pré-requisitos instrucionais: avaliação com dados sintéticos

A CQ11 pergunta **quais competências são explicitamente pressupostas como pré-requisitos de uma unidade instrucional**. Na OntoKSD, essa associação é representada por uma `CompetenceActivation` com `hasActivationFunction prerequisiteFunction`.

Os ciclos do CSP não produziram associações com essa função. Portanto, o resultado vazio nesses dados é compatível com seu conteúdo e não constitui, por si só, falha da consulta. O cenário deste diretório permite avaliar esse recurso sem atribuí-lo aos resultados do CSP.

## Alcance da avaliação

O teste verifica se a representação e a consulta conseguem recuperar pré-requisitos declarados e distingui-los de outras ativações. Todos os exemplos são **sintéticos**, criados para esse fim. Não foram extraídos de um currículo institucional nem validados por especialistas como pré-requisitos pedagogicamente necessários.

A CQ11 trata de competências pressupostas em **unidades instrucionais**. Ela não representa diretamente uma disciplina como pré-requisito de outra, não estabelece dependências entre competências e não verifica se um estudante satisfaz um requisito de participação ou matrícula. Um estudo desses outros usos exigiria escopo, dados e consultas próprios.

## Arquivos e execução no GraphDB

Com a OntoKSD carregada no repositório de teste, execute individualmente, na ordem:

| Arquivo | Operação | Finalidade |
|---|---|---|
| `01-load-synthetic-data.rq` | SPARQL Update (`INSERT DATA`) | Insere exclusivamente o cenário sintético em seu grafo próprio. |
| `02-run-CQ11-on-synthetic-data.rq` | SELECT | Executa a lógica da CQ11 com `FROM` apontando apenas para esse grafo. Deve retornar **três linhas**. |
| `03-check-expected-results.rq` | SELECT | Compara o retorno esperado de seis casos com a correspondência efetiva ao padrão da CQ11. Todas as linhas devem apresentar **`passed = true`**. |

O arquivo 02 preserva a lógica da CQ11 existente, adicionando apenas a delimitação do dataset. O arquivo 03 é uma verificação complementar; não substitui a execução da CQ11.

**Não execute todos os arquivos como atualizações:** apenas o primeiro é um SPARQL Update; os outros dois são consultas SELECT.

Grafo: `http://purl.org/ontoksd/test/cq11`.

Namespace dos indivíduos: `http://purl.org/ontoksd/test/cq11#`.

A separação por grafo permite identificar e excluir o cenário de análises empíricas. Ela não garante invisibilidade: consultas sobre o dataset padrão podem incluir grafos nomeados, dependendo da configuração do GraphDB. Para executar a CQ11 original somente neste cenário, use o arquivo 02. Nenhuma instância do CSP nem axioma da ontologia é alterado pelo arquivo de carga. Executá-lo novamente reinsere os mesmos identificadores e triplas, sem criar novos indivíduos a cada execução.

## Cenário

São usadas três competências mínimas e três tarefas de aprendizagem:

- `traceIteration`: rastrear algoritmos iterativos;
- `traceRecursion`: rastrear algoritmos recursivos;
- `compareAlgorithms`: comparar soluções algorítmicas.

Na prática introdutória, rastrear iteração é um objetivo de aprendizagem. Na prática recursiva, essa mesma competência é pressuposta. Na comparação de algoritmos, são pressupostas as competências de rastrear iteração e recursão, enquanto comparar soluções é o objetivo.

Os nomes das unidades são ilustrativos. As competências estão tipadas na classe geral `Competence`, sem lhes atribuir artificialmente um padrão CC2020, CS2023 ou uma especificação completa de constituintes. Essa simplificação isola o aspecto avaliado pela CQ11.

## Resultados esperados da CQ11

Os identificadores abaixo pertencem ao namespace de teste.

| Unidade instrucional | Competência pré-requisito | Ativação |
|---|---|---|
| `recursionPractice` | `traceIteration` | `prerequisiteIterationForRecursion` |
| `algorithmComparison` | `traceIteration` | `prerequisiteIterationForComparison` |
| `algorithmComparison` | `traceRecursion` | `prerequisiteRecursionForComparison` |

A ordem das linhas segue os critérios da consulta. Cada entidade tem um único rótulo, evitando multiplicação de linhas por idioma nesse teste.

## Casos de verificação

| Caso | Expectativa | Aspecto exercitado |
|---|---|---|
| P1 | Incluído | Pré-requisito declarado com `hasActivationInstructionalUnit`. |
| P2 | Incluído | Reutilização da mesma competência como pré-requisito de outra unidade, em uma ativação distinta. |
| P3 | Incluído | Ligação com a unidade declarada por `hasCompetenceActivation`, inversa de `hasActivationInstructionalUnit`. A consulta a recupera sem depender de inferência dessa inversa. |
| N1 | Excluído | A mesma competência de P1 aparece como `targetFunction` em outra unidade. Ser pré-requisito não é uma característica intrínseca da competência. |
| N2 | Excluído | Um objetivo da unidade não deve ser recuperado como pré-requisito por estar ao lado de outras ativações com essa função. |
| N3 | Excluído | Uma ativação sem função explicitamente informada não atende ao padrão consultado. |

Nos casos de controle, `expected = false` significa **não retornar nesta consulta**, e não uma afirmação OWL de que a competência não pode ser um pré-requisito. A ausência de declaração não estabelece sua negação sob a hipótese de mundo aberto.

## Verificação realizada

Em 2026-10-31, os arquivos foram analisados e executados localmente com RDFLib/SPARQL 1.1, sem inferência OWL:

- a CQ11 original e sua cópia com `FROM` retornaram exatamente as três associações previstas;
- os seis casos retornaram `passed = true`;
- repetir a carga preservou o conjunto de triplas;
- remover a ligação inversa do caso P3 retirou somente essa associação do resultado.

Esses resultados verificam o comportamento das consultas sobre o cenário. **Não substituem a execução no GraphDB nem uma verificação de consistência OWL com os módulos importados.** Para a avaliação da tese, registre a versão da OntoKSD, a configuração de inferência do repositório e os resultados obtidos no GraphDB, preservando a identificação sintética dos dados.

## Texto sugerido para a tese

> A CQ11 foi avaliada por meio de um cenário sintético controlado, pois os ciclos de aplicação do CSP não produziram associações de ativação qualificadas como pré-requisitos. O cenário distingue competências pressupostas para participação em unidades instrucionais de competências tratadas como objetivos de aprendizagem, incluindo a reutilização da mesma competência em funções contextuais diferentes. A consulta recuperou as três associações esperadas e excluiu as ativações de controle no teste local. Esse resultado sustenta a capacidade de recuperação de pré-requisitos explicitamente representados no cenário examinado; não constitui evidência empírica de seu uso no CSP nem validação da necessidade pedagógica dos pré-requisitos propostos.

O relato final deve incorporar os resultados da execução no GraphDB, sem confundi-los com o teste local já realizado.
