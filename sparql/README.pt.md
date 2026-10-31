# Consultas SPARQL complementares

[English](README.md) · [Catálogo de CQs](CQ/CQ_CATALOG.md)

Este diretório contém **nove consultas de leitura**, organizadas em duas finalidades:

## Explorar os vocabulários

- `Knowledge_Catalog.rq`: conhecimentos disponíveis, tipos e referências curriculares.
- `KnowledgeType_Catalog.rq`: categorias de conhecimento, inclusive as ainda não utilizadas.
- `Disposition_Catalog.rq`: disposições e atitudes disponíveis.
- `Skill_Catalog.rq`: habilidades explícitas, suas dimensões e conhecimentos relacionados.
- `SkillLevel_ActionVerbs.rq`: níveis dos esquemas e seus verbos associados.
- `KnowledgeSkill_CandidateVerbs.rq`: verbos disponíveis para o nível de cada par, distinguindo-os dos verbos efetivamente registrados como justificativa. Por padrão seleciona C02; substitua esse IRI pela competência desejada.

## Examinar avaliações e proficiência

- `Proficiency_Comparison.rq`: compara níveis elegíveis e apresenta situações abaixo, no mínimo e acima da expectativa curricular. Substitua o perfil e o curso de exemplo. A política de elegibilidade é a mesma de CQ39a.
- `Evidenced_Competence_Constituents.rq`: mostra os julgamentos de competência e seus constituintes vinculados, com seus níveis e esquemas independentes; não calcula uma proficiência agregada.
- `Proficiency_Score_Interpretation.rq`: identifica intervalos numéricos compatíveis com o score de participação, considera extremos inclusivos/exclusivos e sinaliza sobreposição. O esquema explícito do julgamento prevalece sobre o padrão da ocorrência.

Execute os arquivos no editor SPARQL do GraphDB após carregar os módulos e dados pertinentes. Todos são SELECT e não modificam o repositório. Os catálogos globais também mostram elementos ainda não usados em competências. As consultas avaliativas precisam de participações e julgamentos individuais; os exemplos de planejamento, isoladamente, não os fornecem.

Uma comparação curricular não é automaticamente um critério de aprovação ou diagnóstico. A correspondência entre score e intervalo é numérica: não demonstra, por si só, adequação pedagógica de usar o mesmo score de participação para todos os julgamentos. Sobreposição é sinalizada, não resolvida escolhendo arbitrariamente um intervalo. Um resultado vazio pode indicar dados insuficientes, ambiguidade ou ausência de correspondência.

As perguntas já atendidas pelas CQs devem ser executadas em [CQ/](CQ/): constituintes e pares em CQ1–CQ3; perfis em CQ37/CQ38; evidência e origem em CQ18–CQ21; participação e julgamentos em CQ24a/CQ41; escalas e intervalos em CQ30/CQ31; expectativas curriculares em CQ33; comparações abaixo do mínimo em CQ39a/CQ40b/CQ42b; necessidades explícitas em CQ39c/CQ42a.

Não execute indiscriminadamente o diretório CQ: CQ39b é uma atualização opcional, diferentemente das nove consultas de leitura desta pasta.
