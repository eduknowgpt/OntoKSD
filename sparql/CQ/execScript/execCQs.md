# Run the OntoKSD competency queries in GraphDB

Use `Execute-CQs-GraphDB.ps1` to run the read-only competency queries (CQs) in a batch and save each result.

## Prerequisites

- PowerShell 7 is available as `pwsh` from Windows Command Prompt.
- GraphDB is running and the OntoKSD repository contains the data you want to query.
- You know the repository ID shown in **Setup → Repositories**.
- The script is supplied at [`sparql/CQ/execScript/Execute-CQs-GraphDB.ps1`](Execute-CQs-GraphDB.ps1). Run from the repository root; no file relocation is needed.

## How to execute

Open **Windows Command Prompt (CMD)** and run the following commands. Replace `C:\path\to\OntoKSD` with the OntoKSD directory on your computer and `OntoKSD` with your repository ID.

```bat
cd /d "C:\path\to\OntoKSD"
pwsh -NoProfile -File ".\sparql\CQ\execScript\Execute-CQs-GraphDB.ps1" -QueryFolder ".\sparql\CQ" -Endpoint "http://localhost:7200/repositories/OntoKSD2" -IncludeInferred true -GraphDbVersion "11.0" -OutputFolder ".\local-reports\graphdb"
```

Set `-IncludeInferred false` to exclude inferred triples. Set `-GraphDbVersion` to the version you used. You can add `-Ruleset "your repository ruleset"` and `-DatasetNotes "the data and revisions loaded for this run"` to record the configuration for the evaluation. These two options document the run. The repository settings and data are configured in GraphDB.

The script reads the `CQ*.rq` files directly inside the query folder. It skips the optional `CQ39b` update so that the batch leaves the repository data unchanged.

## Results

The command above creates a run folder under `local-reports/graphdb`. Without `-OutputFolder`, the script uses `%LOCALAPPDATA%\OntoKSD\graphdb-results`. Each run has its own timestamped directory.

| Output | Contents |
| --- | --- |
| `resumo.csv` | One row per file, with the status, row count when available, execution time, query hash, and any error. |
| `resultados\` | One SPARQL JSON result file per successful `SELECT`, including queries that return zero rows. |
| `execucao.json` | Repository endpoint, run timestamps, inference setting, configuration details supplied on the command line, and status totals. |

`OK` means the query returned at least one row. `EMPTY` means the query completed with zero rows. `ERROR` identifies an unsuccessful query, and `SKIPPED_UPDATE` identifies the skipped update. Check `resumo.csv` before reporting the batch results. To assess answer correctness for selected CQs, compare the saved output with the expected result for the dataset and parameters used.

Keep raw outputs in restricted storage if the repository contains historical learner records.
