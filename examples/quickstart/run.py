"""Run a fictional OntoKSD example locally; no network, endpoint, or OWL inference."""
from pathlib import Path
import argparse
from rdflib import Graph, Namespace


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--project-root", type=Path, default=Path(__file__).resolve().parents[2])
    args = parser.parse_args()
    folder = Path(__file__).resolve().parent
    ontology = args.project_root / "ontoKSDOntology" / "OntoKSD - SKOS - v7.0.ttl"
    if not ontology.is_file():
        parser.error("Main ontology not found. Run from a complete checkout or set --project-root.")
    graph = Graph().parse(ontology, format="turtle")
    graph.parse(folder / "demo.ttl", format="turtle")
    ex = Namespace("https://example.org/ontoksd-demo/")
    names = ["01-specification.rq", "02-reuse.rq", "03-judgment-trace.rq"]
    answers = {}
    print("OntoKSD: fictional worked example (no learner data, no OWL inference)")
    for name in names:
        result = graph.query((folder / name).read_text(encoding="utf-8-sig"))
        rows = list(result)
        answers[name] = rows
        print("\n" + name)
        print(" | ".join(str(v) for v in result.vars))
        for row in rows:
            print(" | ".join(str(v).replace(str(ex), "demo:") for v in row))
    specification = answers[names[0]]
    reuse = answers[names[1]]
    trace = answers[names[2]]
    if {row.constituent for row in specification} != {ex.AlgorithmKnowledge, ex.ExplanationSkill}:
        raise RuntimeError("The specification did not return its two expected constituents.")
    if {row.activation for row in reuse} != {ex.LabUse, ex.ProjectUse} or len(reuse) != 2:
        raise RuntimeError("The two expected contextual uses were not recovered.")
    if len(trace) != 2 or {row.evidence for row in trace} != {ex.WrittenEvidence, ex.OralEvidence}:
        raise RuntimeError("The judgment did not recover both expected evidence records.")
    if any(row.learner != ex.Learner or row.occasion != ex.Occasion for row in trace):
        raise RuntimeError("Learner and assessment-occasion references do not agree.")
    print("\nPASS: two constituents, two instructional uses, and two evidence records retrieved.")
    print("This demonstrates retrieval on fictional data, not assessment validity or a complete OWL check.")


if __name__ == "__main__":
    main()
