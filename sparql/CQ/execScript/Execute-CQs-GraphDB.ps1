#Requires -Version 7.0
[CmdletBinding()]
param(
    [Parameter(Mandatory)]
    [string]$QueryFolder,

    [uri]$Endpoint = 'http://localhost:7200/repositories/OntoKSD2',

    [string]$OutputFolder = (Join-Path $env:LOCALAPPDATA 'OntoKSD/graphdb-results'),

    [ValidateSet('true', 'false')]
    [string]$IncludeInferred = 'true',

    [ValidateRange(1, 86400)]
    [int]$TimeoutSeconds = 120,

    [string]$GraphDbVersion,
    [string]$Ruleset,
    [string]$DatasetNotes,
    [PSCredential]$Credential
)

$ErrorActionPreference = 'Stop'

if (!$Endpoint.IsAbsoluteUri -or $Endpoint.Scheme -notin @('http', 'https') -or
    $Endpoint.AbsolutePath.TrimEnd('/') -notmatch '/repositories/[^/]+$' -or
    $Endpoint.UserInfo -or $Endpoint.Query -or $Endpoint.Fragment) {
    throw 'Informe o endpoint de consulta no formato http://host:7200/repositories/REPOSITORIO.'
}
if ($Credential -and $Endpoint.Scheme -ne 'https' -and !$Endpoint.IsLoopback) {
    throw 'Para autenticar em um servidor remoto, use HTTPS.'
}

$queries = @(Get-ChildItem -LiteralPath $QueryFolder -Filter 'CQ*.rq' -File |
    Sort-Object Name)
if (!$queries.Count) {
    throw 'Nenhum arquivo CQ*.rq encontrado na pasta informada.'
}

$runName = [DateTimeOffset]::UtcNow.ToString("yyyyMMdd-HHmmss-fff'Z'") + '-' + $PID
$runPath = Join-Path ([IO.Path]::GetFullPath($OutputFolder)) $runName
[IO.Directory]::CreateDirectory((Join-Path $runPath 'resultados')) | Out-Null
$summaryPath = Join-Path $runPath 'resumo.csv'
$metadataPath = Join-Path $runPath 'execucao.json'
$metadata = [ordered]@{
    endpoint = $Endpoint.AbsoluteUri
    queryFolder = (Resolve-Path -LiteralPath $QueryFolder).Path
    graphDbVersion = $GraphDbVersion
    ruleset = $Ruleset
    datasetNotes = $DatasetNotes
    includeInferred = ($IncludeInferred -eq 'true')
    startedUtc = [DateTimeOffset]::UtcNow.ToString('o')
    finishedUtc = $null
    queryFiles = $queries.Count
    statusCounts = $null
}
$metadata | ConvertTo-Json | Set-Content -LiteralPath $metadataPath -Encoding utf8

$requestOptions = @{
    Uri = $Endpoint
    Method = 'Post'
    ContentType = 'application/x-www-form-urlencoded'
    Headers = @{ Accept = 'application/sparql-results+json' }
    TimeoutSec = $TimeoutSeconds
    MaximumRedirection = 0
    SkipHttpErrorCheck = $true
}
if ($Credential) {
    $requestOptions.Authentication = 'Basic'
    $requestOptions.Credential = $Credential
    if ($Endpoint.IsLoopback -and $Endpoint.Scheme -eq 'http') {
        $requestOptions.AllowUnencryptedAuthentication = $true
    }
}

$counts = [ordered]@{ OK = 0; EMPTY = 0; ERROR = 0; SKIPPED_UPDATE = 0 }
Write-Host "Resultados: $runPath"

foreach ($file in $queries) {
    $entry = [ordered]@{
        File = $file.Name
        Sha256 = $null
        StartedUtc = [DateTimeOffset]::UtcNow.ToString('o')
        Status = 'ERROR'
        Rows = $null
        HttpStatus = $null
        DurationMs = $null
        Result = $null
        Detail = $null
    }
    $timer = [Diagnostics.Stopwatch]::StartNew()
    try {
        $entry.Sha256 = (Get-FileHash -LiteralPath $file.FullName -Algorithm SHA256).Hash.ToLowerInvariant()
        if ($file.BaseName -match '^CQ39b(?:\s|$)') {
            $entry.Status = 'SKIPPED_UPDATE'
            $entry.Detail = 'Atualizacao excluida do lote de leitura.'
        } else {
            $query = [IO.File]::ReadAllText($file.FullName, [Text.Encoding]::UTF8)
            if ($query -notmatch '(?im)^\s*SELECT\b') {
                throw 'Arquivo fora do formato SELECT esperado para este lote.'
            }
            $response = Invoke-WebRequest @requestOptions -Body @{
                query = $query
                infer = $IncludeInferred
            }
            $entry.HttpStatus = [int]$response.StatusCode
            $responseText = if ($response.Content -is [byte[]]) {
                [Text.Encoding]::UTF8.GetString($response.Content)
            } else {
                [string]$response.Content
            }
            if ($entry.HttpStatus -lt 200 -or $entry.HttpStatus -ge 300) {
                $body = $responseText.Trim() -replace '\s+', ' '
                $detail = $body.Substring(0, [Math]::Min(350, $body.Length))
                throw "HTTP $($entry.HttpStatus) $detail"
            }
            $data = ConvertFrom-Json -InputObject $responseText -AsHashtable
            if ($data -isnot [System.Collections.IDictionary] -or
                $data['results'] -isnot [System.Collections.IDictionary] -or
                $data['results']['bindings'] -isnot [array]) {
                throw 'O servidor nao retornou o formato SPARQL JSON de uma consulta SELECT.'
            }

            $entry.Rows = $data['results']['bindings'].Count
            $relativePath = Join-Path 'resultados' ($file.BaseName + '.json')
            [IO.File]::WriteAllText((Join-Path $runPath $relativePath), $responseText)
            $entry.Result = $relativePath
            $entry.Status = if ($entry.Rows -eq 0) { 'EMPTY' } else { 'OK' }
        }
    } catch {
        $entry.Detail = $_.Exception.Message
    } finally {
        $entry.DurationMs = $timer.ElapsedMilliseconds
        $counts[$entry.Status]++
        [pscustomobject]$entry | Export-Csv -LiteralPath $summaryPath -Append -NoTypeInformation -Encoding utf8
        Write-Host ("{0,-15} {1}" -f $entry.Status, $file.Name)
        if ($entry.Status -eq 'ERROR') {
            Write-Host "                $($entry.Detail)" -ForegroundColor Red
        }
    }
}

$metadata.finishedUtc = [DateTimeOffset]::UtcNow.ToString('o')
$metadata.statusCounts = $counts
$metadata | ConvertTo-Json | Set-Content -LiteralPath $metadataPath -Encoding utf8
Write-Host "OK: $($counts.OK) | Vazias: $($counts.EMPTY) | Erros: $($counts.ERROR) | Atualizacoes puladas: $($counts.SKIPPED_UPDATE)"
if ($counts.ERROR -gt 0) { exit 1 }
