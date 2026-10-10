# FinServe Viva / Demonstration Guide

## 30-second project introduction

"FinServe is an intelligent banking analytics platform built in Java to demonstrate advanced DSA algorithms in a practical financial domain. The project processes sample customers, accounts and transactions. It supports fast transaction searching, fraud-pattern detection, document indexing, error correction, transaction-network flow analysis, NP-complete constraint demonstrations, continuous-stream sampling and parallel financial aggregation. Each course outcome is implemented in a separate package and can be demonstrated independently from the IntelliJ console menu."

## What to show first

Run `FinServeApplication.java` and choose **8 - Run Complete FinServe Demo**. Then, if the faculty asks for a specific algorithm, run its individual module from the menu and open the corresponding class.

## CO-wise speaking points

### M1 - CO1: String Algorithms
- KMP searches transaction descriptions without rechecking already matched characters.
- Z-Function finds repeated description patterns efficiently.
- Rabin-Karp uses rolling hash to search payment codes.
- Aho-Corasick searches many fraud patterns in one scan using trie + failure links.

### M2 - CO2: Suffix Structures
- Suffix Array indexes all suffixes of a financial document.
- SA-IS demonstrates induced sorting for scalable suffix-array construction.
- Kasai computes LCP in linear time once suffix order is available.
- Suffix Automaton answers substring-existence queries efficiently.

### M3 - CO3: Advanced DP
- Levenshtein corrects mistyped transaction descriptions.
- Damerau-Levenshtein additionally handles swapped adjacent characters.
- Weighted edit distance gives different penalties to banking-data errors.
- Bitmask DP chooses the best analytics operations under a processing budget.
- Matrix-chain multiplication minimizes the cost of chained analytics transformations.

### M4 - CO4: Network Flow
- Accounts/channels are vertices and transaction capacities are edges.
- Ford-Fulkerson finds augmenting paths.
- Edmonds-Karp uses BFS for predictable polynomial behavior.
- Dinic uses level graphs and blocking flow for larger networks.
- Bipartite matching assigns analysts to fraud cases.
- Min-Cut identifies critical transaction channels.

### M5 - CO5: NP-Completeness and Approximation
- 3-SAT represents complex financial rules as Boolean clauses.
- The demo constructs the standard 3-SAT to CLIQUE graph.
- Complement relation demonstrates CLIQUE to INDEPENDENT-SET.
- Independent Set and Vertex Cover are related by `|VC| = |V| - |IS|` for complementary selections.
- A 2-approximation quickly covers dependency edges when exact Vertex Cover is expensive.

### M6 - CO6: Randomized and Parallel Algorithms
- Randomized QuickSort reduces dependence on bad input ordering.
- Miller-Rabin efficiently checks primality of large integers.
- Reservoir Sampling keeps a uniform sample from a transaction stream without storing the whole stream.
- Blelloch Scan computes prefix aggregates in parallel-friendly form.
- Parallel Reduce aggregates transaction amounts with divide-and-conquer tasks.
- Brent's theorem relates total work, critical path and number of processors.

## Likely viva questions

**Why did you choose banking?**  Banking generates large strings, documents, networked transfers, rule constraints and continuous streams, so all six advanced-algorithm families have natural applications.

**Is this a full production banking system?**  No. It is an academic DSA analytics model. The financial data layer is demo data; the implemented focus is the algorithmic engine.

**Which algorithm is best for multiple fraud keywords?**  Aho-Corasick because it can search many patterns together in one pass after building the automaton.

**Why three max-flow algorithms?**  They solve the same core problem but show different algorithmic strategies and performance characteristics: DFS augmenting paths, BFS shortest augmenting paths and level-graph blocking flow.

**Why use reservoir sampling?**  A bank can receive an unbounded stream; reservoir sampling maintains a fixed-size unbiased sample using bounded memory.
