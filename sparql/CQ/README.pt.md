# Questões de competência da OntoKSD

[English](README.md) · [Catálogo de perguntas](CQ_CATALOG.md) · [Mapa de identificadores](CQ_NUMBERING.md)

A coleção mantém **42 famílias históricas** e contém **58 arquivos `.rq` na raiz: 57 SELECT e um UPDATE opcional**. Os sufixos identificam vistas ou operações de uma família. Quantidade de arquivos não equivale a quantidade de requisitos atendidos nem de testes independentes.

## Executar

1. Carregue a ontologia atual, os módulos necessários e as instâncias relevantes no GraphDB.
2. Abra um arquivo, confira sua operação e ajuste os parâmetros do cabeçalho.
3. Selecione repositório e dataset. As consultas gerais não impõem `FROM`; a visibilidade de grafos nomeados depende da configuração.
4. Registre a execução em [GRAPHDB_RUN_LOG.template.csv](GRAPHDB_RUN_LOG.template.csv): parâmetros, configuração de inferência, instante de comparação e exportação dos resultados.

CQ1/CQ2 selecionam `ontoksd:C02`. As comparações e buscas de ausência selecionam explicitamente o exemplo **sintético** de proficiência:

```sparql
VALUES (?profile ?course) {
  (ontoksd:Profile_Learner_01 ontoksd:Course_FormalLanguages)
}
```

Troque ambas as IRIs para outro caso. A seleção não declara matrícula. Para reproduzir o exemplo, execute os arquivos de `../../proficiency level` em ordem: 01 vocabulário; 02 instâncias e interpretação ilustrativa da nota; 03 expectativa curricular e política local de lacuna. São atualizações de dados, não CQs somente de leitura.

## Interpretar

- Resultado vazio pode refletir associação não declarada, parâmetros incorretos, dataset diferente, nível não atribuído ou exclusão intencional. Não equivale automaticamente a erro ou incapacidade do aprendiz.
- Rótulos e qualificadores múltiplos podem multiplicar linhas. Conte identificadores distintos quando a unidade de análise for uma entidade.
- A CQ30 mantém entidades com rótulos apenas em inglês, mas deixa seus rótulos vazios: o filtro opcional aceita português ou ausência de idioma. Não é fallback nem seleção de um único rótulo.
- A CQ20c exige tipagem de `AssessmentOccurrence`; contexto e avaliação são opcionais.
- A CQ11 possui [cenário sintético próprio](tests/CQ11/README.pt.md). O CSP não produziu ativações como pré-requisitos. Isso não representa pré-requisito entre disciplinas.
- A CQ27 possui [cenário sintético próprio](tests/CQ27/README.pt.md). Seus julgamentos usam critérios específicos, sem copiar a nota global. O exemplo original de proficiência não atribui níveis aos constituintes.
- CQ40a/CQ42c procuram ausência de julgamento. No exemplo de proficiência, o retorno vazio é esperado: a competência curricular já possui julgamento. Um resultado abaixo do mínimo não é um registro ausente.

## Comparações e operações opcionais

CQ39a é a comparação canônica abaixo do mínimo; CQ40b e CQ42b preservam vistas equivalentes por rastreabilidade. CQ26 é a recuperação canônica de nível da competência completa; CQ32a é equivalente e **não implementa síntese de proficiência**.

A comparação exige expectativa curricular obrigatória selecionada, esquema comum identificado, categorias ordinais inequívocas e julgamento datado elegível. A política considera o mais recente não expirado no esquema, exclui registros futuros e situações ambíguas e impede usar um registro antigo baixo quando existe um posterior ainda sem classificação.

A referência ao esquema esperado por `dcterms:references` é uma convenção documental do exemplo, não uma nova propriedade da ontologia. Comparar proficiência individual com profundidade curricular é uma política analítica adicional; não estabelece aprovação, reprovação ou diagnóstico. Ordinais não representam distâncias nem equivalência entre esquemas.

Para congelar o tempo, substitua `BIND(NOW() AS ?asOf)` por `VALUES ?asOf` com data-hora tipada e preserve a consulta executada. Os arquivos distribuídos usam `NOW()`; o teste local fixa e documenta esse instante.

**CQ40d permanece desativada.** O mínimo da competência completa não é herdado por seus constituintes. Seu uso depende de uma política externa justificada e não conta como suporte estabelecido a lacunas específicas dos constituintes.

**CQ39b altera dados.** Examine CQ39a antes e execute separadamente, de modo deliberado. A operação registra candidatos, não diagnósticos; não retira automaticamente candidatos antigos após novas evidências. Nesta revisão de arquivos, nenhuma atualização foi executada no GraphDB.

## Documentação e avaliação

O [mapa de identificadores](CQ_NUMBERING.md) preserva a numeração após a qualificação. As [decisões detalhadas](DECISIONS.pt.md) justificam os ajustes. A [validação local](validation/README.md) não substitui o registro das execuções no GraphDB.

CQ14–CQ17 receberam implementações explícitas nesta revisão e precisam de execução no GraphDB. A tabela da tese e o apêndice FR–CQ devem compartilhar os mesmos mapeamentos. Execução de consulta não prova consistência OWL completa, adequação de critérios ou validade educacional.
