# CQ27 — Proficiência de constituintes: cenário sintético

Este cenário avalia a CQ27: **quais níveis de proficiência e esquemas interpretativos foram atribuídos aos registros de constituintes evidenciados?** Os dados são artificiais e independentes dos ciclos do CSP e do exemplo da pasta `proficiency level`.

A CQ27 exige `EvidencedCompetenceConstituent`, `refersToCompetenceConstituent` e `hasProficiencyLevel`. O vínculo `interpretedUsingScheme` é opcional na consulta: sua ausência deve ficar visível como coluna vazia, sem eliminar o registro.

## Executar no GraphDB

Com a OntoKSD atual carregada, execute individualmente no mesmo repositório de teste:

| Arquivo | Operação | Resultado esperado |
|---|---|---|
| `01-load-synthetic-data.rq` | SPARQL Update (`INSERT DATA`) | Insere o cenário em um grafo próprio. |
| `02-run-CQ27-on-synthetic-data.rq` | SELECT | Retorna **três linhas**, uma delas com o esquema vazio. |
| `03-check-expected-results.rq` | SELECT | Retorna **cinco casos**, todos com `passed = true`. |

Apenas o arquivo 01 é uma atualização. Os arquivos 02 e 03 devem ser executados como consultas, não como updates. Não é necessário carregar os arquivos da pasta `proficiency level` para executar este cenário.

O arquivo 02 preserva a lógica da CQ27 original e acrescenta `FROM` para consultar somente o grafo de teste. A CQ27 original não foi modificada.

- Grafo: `http://purl.org/ontoksd/test/cq27`.
- Namespace dos indivíduos: `http://purl.org/ontoksd/test/cq27#`.

A carga não altera registros do CSP, o exemplo anterior nem axiomas da ontologia. Sua repetição usa os mesmos identificadores e não acrescenta indivíduos com nomes novos. A separação por grafo identifica a origem sintética, mas não garante invisibilidade em consultas gerais: o dataset padrão do GraphDB pode incluir grafos nomeados. Exclua este grafo de análises empíricas.

## Critérios específicos e interpretação

O cenário contém uma avaliação fictícia de autômatos finitos determinísticos, uma ocorrência, um aprendiz, um avaliador, uma participação e um material de resposta compartilhado. A participação identifica a pessoa e a ocasião, mas **não possui nota numérica**.

O esquema qualitativo local contém `Inicial`, `Em desenvolvimento` e `Consolidado`, com posições ordinais 1, 2 e 3. Não há escala numérica nem intervalos: as atribuições são interpretações explícitas de descritores. As posições não medem distância entre níveis nem estabelecem equivalência com outros esquemas.

Duas rubricas sintéticas são representadas como `Document`, com descritores em `dcterms:description`. Cada julgamento principal referencia sua rubrica por `dcterms:references`. Essa documentação textual fornece rastreabilidade; não implementa um motor de rubricas ou classificação automática.

| Julgamento | Critério atendido na resposta fabricada | Nível atribuído |
|---|---|---|
| `knowledgeJudgment` | Identifica estados, alfabeto, transições e estados inicial e finais; explica aceitação e rejeição. | `consolidated` |
| `skillJudgment` | Constrói uma solução funcional para casos usuais, mas falha no tratamento da cadeia vazia. | `developing` |

O mesmo material pode apoiar interpretações distintas para constituintes diferentes. Os níveis são declarados no arquivo, com justificativa própria; não são calculados nem copiados de uma proficiência global.

## Resultados esperados da CQ27

Todos os identificadores abaixo pertencem ao namespace de teste.

| Registro | Constituinte | Nível | Esquema |
|---|---|---|---|
| `knowledgeJudgment` | `knowledge` | `consolidated` | `scheme` |
| `skillJudgment` | `skill` | `developing` | `scheme` |
| `missingSchemeJudgment` | `knowledge` | `emerging` | **vazio** |

Cada entidade rotulada tem um único rótulo em português, evitando multiplicação de linhas por idioma neste teste. O terceiro registro é um **controle propositalmente incompleto**, não uma segunda conclusão justificada sobre o conhecimento. Ele demonstra que recuperar uma linha não certifica a qualidade de sua interpretação.

## Casos de controle

| Caso | Comportamento esperado |
|---|---|
| P1 — conhecimento | Retornado com nível e esquema. |
| P2 — habilidade | Retornado com nível distinto e esquema. |
| P3 — esquema ausente | Retornado com nível e coluna de esquema vazia. A CQ27 não preenche o esquema a partir da definição do nível. |
| N1 — nível ausente | `ungradedDispositionJudgment` não é retornado. O registro é de controle de completude: o material não sustenta uma graduação da disposição. Ausência de nível não significa proficiência zero. |
| N2 — competência completa | `wholeJudgment` não é retornado, pois pertence a `EvidencedCompetence`. Seu nível é arbitrado exclusivamente para testar a distinção de classes; não representa síntese dos constituintes nem conclusão holística validada. |

Os controles não devem ser utilizados como conclusões pedagógicas. Nos testes, `false` significa que um registro não é recuperado pelo padrão consultado, e não uma negação OWL. Sob mundo aberto, ausência de uma relação não prova sua inexistência.

O registro sem esquema mantém um vínculo ausente **no próprio julgamento**, mesmo que o nível esteja listado no esquema do vocabulário. A consulta não assume que esse vínculo possa ser preenchido automaticamente. Comparações ordinais e interpretações justificadas exigem identificar o esquema aplicável e os critérios, além de outras condições que a CQ27 não valida.

## Verificação e limites

Em 2026-10-31, a verificação local com RDFLib/SPARQL 1.1, sem inferência OWL, confirmou todos os resultados esperados e os cinco casos de verificação. Foram examinados: o conjunto exato de três resultados da CQ27 original e de sua cópia delimitada por grafo; os cinco casos de controle; a repetição da carga; as referências às rubricas; e a diferença entre remover um esquema e remover um nível.

Esse teste não substitui a execução no GraphDB nem uma verificação de consistência OWL com os módulos importados. O arquivo de consulta não avalia a correção pedagógica dos descritores, a suficiência do material ou a validade dos julgamentos. Na tese, identifique os dados como sintéticos e registre a versão da ontologia, a configuração de inferência e os resultados efetivos do GraphDB.

## Texto sugerido para a tese

> A CQ27 foi exercitada com um cenário sintético de interpretação de proficiência no nível dos constituintes. Dois julgamentos receberam níveis distintos, documentados por critérios específicos de conhecimento conceitual e habilidade de construção, sem propagação de uma nota global. Casos de controle verificaram a recuperação de um registro com nível e sem esquema, a exclusão de um registro sem nível e a distinção entre julgamentos de constituintes e de competência completa. A avaliação examina o comportamento da consulta sobre atribuições explícitas e a visibilidade de informação ausente; não constitui validação empírica dos critérios nem dos níveis propostos.

Acrescente ao relato os resultados observados no GraphDB após executar os arquivos 02 e 03.
