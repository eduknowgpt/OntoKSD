# Decisões da revisão das CQs e da seção da tese

Data da revisão: **2026-11-01**. Este documento explica as decisões editoriais, operacionais e metodológicas. O catálogo continua orientado às perguntas do leitor; este registro concentra as justificativas da revisão.

## 1. Preservar identificadores, sem renumerar os arquivos

**Decisão:** manter CQ1–CQ42 como 42 famílias históricas, preservando os nomes existentes e seus sufixos. A coleção revisada possui 58 arquivos na raiz, sendo 57 SELECT e um UPDATE opcional. Scripts de carga e verificação em subdiretórios não integram essa contagem.

**Justificativa:** depois da qualificação, algumas perguntas foram desdobradas em vistas operacionais. Renumerar todos os arquivos em uma sequência única produziria identificadores novos para informações já referenciadas na tese, em exemplos e em código. A manutenção permite reconhecer essas correspondências. A ordem numérica não é uma medida de completude e o sufixo não significa um requisito novo.

**Implementação:** `CQ_NUMBERING.md` relaciona cada arquivo à família, à operação e às equivalências. A tese explica a distinção entre família e arquivo. Sufixos aninhados como CQ20c1/CQ20c2 são conservados como referências estáveis, embora uma coleção criada do zero pudesse adotar nomes mais simples.

**Limite:** não se deve escrever “58 CQs independentes foram validadas”. Existem vistas repetidas, operações auxiliares e uma extensão desativada. O número de testes independentes depende de seus casos e critérios, não da numeração.

## 2. Completar CQ14–CQ17 sem alegar execução histórica

**Constatação:** essas famílias estavam na tabela da tese, mas seus quatro arquivos não existiam no diretório examinado. CQ10 já recuperava parte das informações de qualificadores; isso não constituía quatro implementações individuais documentadas.

**Decisão:** acrescentar implementações explícitas de CQ14 (papel), CQ15 (restrição, tipo e descrição), CQ16 (qualificação conjunta) e CQ17 (declaração instrucional, organização colaborativa e contexto planejado).

CQ16 mantém o identificador da ativação e os qualificadores opcionais: um qualificador ausente não exclui a ativação. Combinações de modos, papéis e restrições podem gerar várias linhas, que não representam ativações adicionais.

CQ17 parte da unidade instrucional, não de uma ativação obrigatória. Portanto, uma unidade com declaração instrucional e sem competência associada continua recuperável. O teste local inclui precisamente esse caso. A cadeia `a/rdfs:subClassOf*` permite reconhecer tipos especializados quando a hierarquia está carregada; alternativamente, a configuração de inferência pode fornecer a classificação. Contextos de ocorrências concretas são recuperados por CQ20c/CQ20c2, evitando confundir planejamento e execução.

**Efeito na tese:** CQ17 passa a mapear FR5 e FR6, em lugar de manter uma reivindicação genérica sobre condições avaliativas de FR11. Recuperar a declaração não prova que o texto original da tarefa foi integralmente representado. Os quatro novos arquivos precisam de execução final no GraphDB; o teste local não a substitui.

## 3. Tornar os parâmetros executáveis e explícitos

**Constatação:** o arquivo local de CQ39a e as variantes de CQ40 e CQ42 ainda continham `Profile_Example`/`Course_Example`. Esses identificadores não selecionam automaticamente um caso carregado.

**Decisão:** os oito arquivos parametrizados (CQ39a, CQ39b, CQ40a–CQ40d, CQ42b e CQ42c) passam a selecionar `Profile_Learner_01` e `Course_FormalLanguages`, o exemplo fictício já disponível. Cabeçalhos e documentação indicam que ambos devem ser substituídos para outro caso. Se alguma cópia já estivesse ajustada pelo usuário, seu par não genérico seria preservado pelo procedimento de preparação.

**Justificativa:** oferece um caso reproduzível sem retirar a seleção explícita de perfil e curso. Não se deve eliminar `VALUES` e cruzar todos os perfis e cursos: a ontologia não estabelece matrícula nem aplicabilidade universal das expectativas.

**Limite:** os exemplos são sintéticos. O valor padrão não transforma o resultado em evidência real sobre alunos. CQ39b mantém natureza de UPDATE e não é executada automaticamente contra o GraphDB.

## 4. Atualizar CQ30 sem inventar uma causa para o problema anterior

O diagnóstico informado pelo usuário recuperou oito associações `usesScale` no dataset padrão, inclusive o exemplo de 0–100. Isso demonstrou que as relações estavam disponíveis. A CQ30 originalmente inspecionada tinha esse mesmo vínculo como única condição obrigatória; não há base para afirmar que a carga estava ausente ou que os OPTIONAL explicavam o retorno vazio anterior.

**Decisão:** adotar a estrutura simplificada que funcionou no GraphDB e os rótulos opcionais discutidos posteriormente, usando as variáveis `scheme`, `scale`, `minimum`, `maximum` e `step`. A mudança dos nomes das variáveis não altera a associação consultada.

Os filtros de rótulo ficam dentro de OPTIONAL e aceitam português ou ausência de idioma. Entidades com rótulos apenas em inglês permanecem no resultado, com coluna de rótulo vazia. Essa regra não seleciona uma preferência ordenada nem implementa fallback para inglês; múltiplos rótulos aceitos podem multiplicar linhas.

**Efeito na avaliação:** a execução simplificada foi confirmada pelo usuário. A redação final com rótulos deve ser associada ao seu próprio hash e registro de execução; o sucesso de uma versão não comprova automaticamente a execução de todas as versões. Esquemas qualitativos sem escala continuam fora do escopo da CQ30 por definição.

## 5. Manter CQ18, CQ20c e CQ27 quando o problema é o dado exigido

CQ18 exige julgamentos tipados ligados a evidência; carregar apenas competências e tarefas não fornece esses dados. O arquivo 02 de proficiência contém os registros pertinentes.

CQ20c exige uma ocorrência tipada, mas deixa avaliação e contexto opcionais. A ausência de rótulos ou contexto não explica um retorno vazio quando a ocorrência está no dataset consultado. O usuário confirmou posteriormente que a consulta funcionou.

CQ27 exige um nível atribuído ao constituinte. O exemplo original documenta deliberadamente que esse nível não é copiado da nota global. Assim, um resultado vazio nesse exemplo é esperado, e tornar a proficiência opcional na CQ27 mudaria sua pergunta. Uma consulta diagnóstica opcional pode ser usada separadamente, sem alterar a definição da CQ.

**Decisão:** preservar a lógica dessas consultas e documentar dependências e resultados esperados. Não inserir níveis artificiais nos registros anteriores só para produzir resultados.

## 6. Usar cenários sintéticos para CQ11 e CQ27

O CSP não forneceu ativações de pré-requisito, e o cenário original de proficiência não graduou seus constituintes. Os cenários em `tests/CQ11` e `tests/CQ27` permitem testar esses recursos sem atribuí-los aos resultados do CSP.

CQ11 testa três associações positivas e três controles, inclusive a mesma competência como objetivo em outra unidade e uma ligação declarada pela inversa. A pergunta não trata de dependência entre disciplinas nem verifica elegibilidade individual.

CQ27 testa dois julgamentos específicos com rubricas textuais e níveis distintos, além de um registro com nível e esquema ausente. Os controles sem nível e de competência completa ficam fora do retorno. Os registros propositalmente incompletos ou arbitrados são controles de comportamento, não conclusões pedagógicas justificadas.

**Decisão:** preservar grafos e namespaces próprios. O arquivo de consulta de cada cenário delimita seu dataset com FROM. Identificar um grafo como sintético não o torna invisível em um dataset padrão que reúna grafos nomeados; é preciso excluí-lo de análises empíricas.

**Limite:** três resultados corretos demonstram comportamento no cenário, não necessidade pedagógica dos pré-requisitos, validade dos descritores ou uso observado no CSP. Critérios textuais e referências documentais não implementam classificação automática.

## 7. Distinguir resultado abaixo do mínimo de ausência de registro

CQ39a/CQ40b/CQ42b comparam um julgamento existente a um mínimo curricular. CQ40a e CQ42c procuram ausência de julgamento. No exemplo de proficiência, a competência já possui um julgamento, mesmo estando abaixo do mínimo: portanto, as consultas de ausência devem ficar vazias.

CQ40a considera apenas inclusões obrigatórias; CQ42c também apresenta inclusões não obrigatórias e seu status. Essa diferença justifica manter ambas. CQ40c procura ausência no nível dos constituintes diretos: pode recuperar o par KnowledgeSkill não julgado, sem afirmar que o conhecimento interno ao par não foi evidenciado.

**Decisão:** não modificar os filtros de ausência para forçar respostas positivas. Um teste positivo de ausência exigiria um cenário apropriado, separado do caso em que os registros já existem. Não se considera o vazio como falha sem definir a expectativa correspondente.

## 8. Reconhecer consultas equivalentes

CQ26 e CQ32a recuperam o mesmo nível já atribuído à competência completa. CQ32a não sintetiza nem agrega níveis. A ambição original de síntese permanece não implementada e fora do requisito FR12, que distingue granularidades mas não exige agregação automática.

CQ39a, CQ40b e CQ42b possuem a mesma comparação de nível abaixo do mínimo. CQ39a é a referência canônica; as outras mantêm execução autônoma por compatibilidade e rastreabilidade. O teste local verifica equivalência dos resultados sob os mesmos parâmetros e instante.

**Decisão:** manter os arquivos, assinalar os aliases em seus cabeçalhos e documentar a equivalência no catálogo, no mapa e na tese. A contagem de requisitos satisfeitos não aumenta por essas repetições.

## 9. Manter CQ40d desativada

A classe de especificação curricular associa o mínimo à competência completa, não aos seus constituintes individualmente. Aplicar esse mínimo a todos eles é uma hipótese adicional e pode distorcer o significado de uma competência integrada.

**Decisão:** conservar o parâmetro `applyWholeCompetenceThresholdToConstituents` como `false`. A consulta só é uma extensão de política, não evidência estabelecida de suporte a requisitos mínimos dos constituintes. O cenário de CQ27 não fornece a expectativa curricular ausente.

Uma futura avaliação positiva requer expectativas próprias na granularidade dos constituintes ou uma política de propagação explicitamente defendida. Essa revisão não cria propriedades nem axiomas novos para simular tal suporte.

## 10. Explicitar os limites da política de comparação e da materialização

Os arquivos comparativos usam esquema comum, posições ordinais inequívocas, julgamento datado elegível e verificações temporais e de ambiguidade. A referência de esquema esperado em `dcterms:references` é uma convenção documental do exemplo, não uma inferência OWL.

A revisão preserva essa política. Ordinais expressam ordem, não distância e nem comparabilidade entre esquemas. Um julgamento posterior sem classificação pode bloquear o uso de um anterior baixo, nos termos das condições implementadas. Ausência de data ou esquema não deve ser convertida em proficiência zero.

Os arquivos distribuídos usam NOW(). O teste local fixa `2026-11-01T12:00:00Z` como instante analítico, para que seus resultados não dependam do relógio da máquina. Esse instante não é data de execução real nem data de avaliação do aluno. Para repetir uma análise no GraphDB, preserve a versão parametrizada e seu instante.

CQ39b registra candidatos e não diagnósticos. Embora seja idempotente para a mesma base, não revoga automaticamente registros antigos após nova evidência. O teste de materialização desta revisão ocorreu apenas em memória local descartável, nunca no repositório GraphDB do usuário.

## 11. Atualizar a argumentação da tese e o mapeamento de requisitos

A seção anterior dizia que CQ6/CQ7 não recuperavam necessariamente descrições e que CQ34 não examinava qualificações. Os arquivos atuais já projetam descrição de integração, escopo de especialização, tipo e justificativa de alinhamento. A tese foi corrigida para situar o limite em completude, adequação e escopo, sem negar campos existentes.

CQ3 ainda não projeta explicitamente o esquema de referência do SkillLevel: esse aspecto de FR2 exige exame complementar do módulo importado e da tipagem dos níveis. FR9 e a dimensão de critérios de FR11 continuam exigindo avaliações complementares de expectativas, critérios e interpretação. Recuperar um documento não comprova adequação de seu conteúdo.

CQ17 fornece agora um teste de recuperação de requisitos independentes para FR6, mas não prova completude em relação à tarefa de origem. FR14 exige registrar o que foi consultado e sob qual inferência; não se satisfaz apenas pela quantidade de arquivos ou de respostas não vazias.

A seção reescrita mantém os rótulos LaTeX anteriores, atualiza a tabela de 42 famílias e separa famílias, arquivos, equivalências, dados, política, resultados locais e evidências de execução. O fragmento de mapeamento FR–CQ serve para alinhar o apêndice; não foi localizado nem sobrescrito um arquivo de apêndice da tese.

## 12. Separar o que foi testado do que foi apenas preparado

A validação local examinou sintaxe dos 58 arquivos, executou os 57 SELECT sobre a ontologia local e os exemplos de proficiência, verificou os novos CQ14–CQ17 com dados artificiais, recuperou os resultados previstos nos cenários CQ11/CQ27 e examinou aliases e materialização local. Importações não foram buscadas e não houve raciocínio OWL.

**Não se afirma que os 58 arquivos finais foram executados no GraphDB.** Confirmações interativas anteriores do usuário não substituem exportações e registros para os hashes finais. As alterações, principalmente os novos CQ14–CQ17 e a versão final da CQ30, devem ter suas execuções registradas. O CSV de execução está preenchido com identificadores e hashes, mas seus resultados permanecem `not_recorded` para não inventar evidência.

Os relatórios locais registram arquivos e hashes de entrada, quantidade de linhas e condições de execução. Resultados vazios esperados são avaliados como tal. Verificação SPARQL, consistência OWL e validade educacional são responsabilidades distintas.
