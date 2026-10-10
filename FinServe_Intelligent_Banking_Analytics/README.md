# FinServe - Intelligent Banking Analytics Platform

FinServe is an IntelliJ IDEA-ready **Java 21 Advanced DSA project** that models a banking and financial analytics platform. It demonstrates all six course modules using realistic transaction, fraud, document-indexing, flow, constraint, streaming and parallel-processing examples.

## What the project demonstrates

| Module | Banking use case | Algorithms implemented |
|---|---|---|
| M1 - CO1 | Transaction/payment-code search and fraud pattern scanning | KMP, Z-Function, Rabin-Karp, Aho-Corasick |
| M2 - CO2 | Financial-document and transaction-description indexing | Suffix Array, SA-IS, Kasai LCP, Suffix Automaton |
| M3 - CO3 | Error correction and resource-constrained analytics | Levenshtein, Damerau-Levenshtein, Weighted Edit Distance, Bitmask DP, Matrix-Chain Multiplication |
| M4 - CO4 | Transaction-network capacity and case assignment | Ford-Fulkerson, Edmonds-Karp, Dinic, Bipartite Matching, Min-Cut |
| M5 - CO5 | Financial-rule constraints and dependency coverage | SAT/3-SAT brute-force demonstrator, 3-SAT to CLIQUE graph, complement reduction, Vertex-Cover 2-approximation |
| M6 - CO6 | Transaction ranking, streaming and parallel analytics | Randomized QuickSort, Miller-Rabin, Reservoir Sampling, Blelloch Scan, Parallel Reduce, Brent's theorem |

## Open in IntelliJ IDEA

1. Extract the ZIP.
2. Open **IntelliJ IDEA**.
3. Choose **Open**.
4. Select the folder `FinServe_Intelligent_Banking_Analytics`.
5. IntelliJ will detect `pom.xml` as a Maven project.
6. Set Project SDK to **JDK 21** (your installed Java 21.0.11 is suitable).
7. Open:
   `src/main/java/com/finserve/app/FinServeApplication.java`
8. Click the green **Run** button beside `main()`.

No external database, web server, or third-party library is required.

### Run on Windows without installing Maven

Since the project includes `run.bat`, you can run it using your installed JDK:
1. Open the extracted project folder in File Explorer.
2. Double-click `run.bat`, or open IntelliJ Terminal in the project root and run `.\run.bat`.
3. The script compiles all Java sources with `javac --release 21` and launches the application.

If Windows reports that `javac` is not recognized, configure a full JDK 21 in your PATH. Java's `java -version` command alone does not confirm that the compiler is available.

### IntelliJ settings
- Project SDK: JDK 21
- Language level: 21
- Maven importer JDK: 21 (if the Maven tool window is used)

## One-click complete demonstration

Run `FinServeApplication` and choose:

`8. Run Complete FinServe Demo`

Or from terminal:

```bash
mvn clean compile exec:java
```

For non-interactive full demo:

```bash
mvn -q clean compile exec:java -Dexec.args="--demo"
```

## Project structure

```text
FinServe_Intelligent_Banking_Analytics/
├── pom.xml
├── README.md
├── VIVA_GUIDE.md
└── src/main/
    ├── java/com/finserve/
    │   ├── app/
    │   │   ├── FinServeApplication.java
    │   │   └── DemoRunner.java
    │   ├── model/
    │   ├── service/
    │   └── algorithms/
    │       ├── stringalg/
    │       ├── suffix/
    │       ├── dp/
    │       ├── flow/
    │       ├── np/
    │       └── randomparallel/
    └── resources/
        └── transactions.csv
```

## Architecture

```text
Sample Banking Data
       |
       v
BankingDataService
       |
       v
DemoRunner / FinServeApplication
       |
       +--> M1 Search & Fraud Detection
       +--> M2 Document Indexing
       +--> M3 Error Correction & Optimization
       +--> M4 Transaction Flow Analysis
       +--> M5 Constraint / NP Analysis
       +--> M6 Streaming & Parallel Analytics
```

## Important notes for demonstration

- The project uses deterministic sample data so your output is repeatable during evaluation.
- The algorithms are separated into packages, so you can open each class and explain the implementation to your faculty.
- The banking layer is intentionally lightweight: the academic focus is the mapping of advanced algorithms to realistic financial use cases.
