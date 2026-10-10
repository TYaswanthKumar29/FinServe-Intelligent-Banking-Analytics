package com.finserve.app;

import com.finserve.algorithms.dp.BitmaskOperationSelector;
import com.finserve.algorithms.dp.DamerauLevenshtein;
import com.finserve.algorithms.dp.Levenshtein;
import com.finserve.algorithms.dp.MatrixChainMultiplication;
import com.finserve.algorithms.dp.WeightedEditDistance;
import com.finserve.algorithms.flow.BipartiteMatching;
import com.finserve.algorithms.flow.Dinic;
import com.finserve.algorithms.flow.EdmondsKarp;
import com.finserve.algorithms.flow.FordFulkerson;
import com.finserve.algorithms.flow.MinCut;
import com.finserve.algorithms.np.GraphReductions;
import com.finserve.algorithms.np.SATSolver;
import com.finserve.algorithms.np.VertexCoverApproximation;
import com.finserve.algorithms.randomparallel.BlellochScan;
import com.finserve.algorithms.randomparallel.BrentTheorem;
import com.finserve.algorithms.randomparallel.MillerRabin;
import com.finserve.algorithms.randomparallel.ParallelReduce;
import com.finserve.algorithms.randomparallel.RandomizedQuickSort;
import com.finserve.algorithms.randomparallel.ReservoirSampling;
import com.finserve.algorithms.stringalg.AhoCorasick;
import com.finserve.algorithms.stringalg.KMP;
import com.finserve.algorithms.stringalg.RabinKarp;
import com.finserve.algorithms.stringalg.ZFunction;
import com.finserve.algorithms.suffix.KasaiLCP;
import com.finserve.algorithms.suffix.SAIS;
import com.finserve.algorithms.suffix.SuffixArray;
import com.finserve.algorithms.suffix.SuffixAutomaton;
import com.finserve.model.Transaction;
import com.finserve.service.BankingDataService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DemoRunner {
    private final BankingDataService data = new BankingDataService();

    public void showBankingData() {
        header("BANKING DATA OVERVIEW");
        System.out.println("Customers    : " + data.customers().size());
        System.out.println("Accounts     : " + data.accounts().size());
        System.out.println("Transactions : " + data.transactions().size());
        System.out.println("\nSample transactions:");
        for (Transaction tx : data.transactions()) System.out.println("  " + tx);
    }

    public void runStringAlgorithms() {
        header("M1 - CO1: STRING ALGORITHMS");
        String descriptions = data.combinedDescriptions();
        String paymentCodes = String.join(" | ", data.transactions().stream().map(Transaction::getPaymentCode).toList());

        System.out.println("1) KMP - search 'wire transfer' in transaction descriptions");
        System.out.println("   Match indexes: " + KMP.search(descriptions, "wire transfer"));

        System.out.println("\n2) Z-Function - detect repeated 'upi payment' description pattern");
        System.out.println("   Match indexes: " + ZFunction.search(descriptions, "upi payment"));

        System.out.println("\n3) Rabin-Karp - search financial code 'UPI-GROC-4812'");
        System.out.println("   Match indexes: " + RabinKarp.search(paymentCodes, "UPI-GROC-4812"));

        System.out.println("\n4) Aho-Corasick - scan multiple fraud indicators in one pass");
        List<String> fraudPatterns = List.of("urgent transfer", "suspicious", "otp", "verify password", "international wire");
        AhoCorasick fraudDetector = new AhoCorasick(fraudPatterns);
        List<String> fraudMatches = fraudDetector.findMatches(descriptions);
        for (String match : fraudMatches) System.out.println("   ALERT: " + match);
        if (fraudMatches.isEmpty()) System.out.println("   No fraud patterns found.");
    }

    public void runSuffixAlgorithms() {
        header("M2 - CO2: SUFFIX STRUCTURES");
        String document = "upi payment grocery | salary credit | upi payment grocery | wire transfer | card payment";

        int[] sa = SuffixArray.build(document);
        System.out.println("1) Suffix Array built for financial document");
        System.out.println("   First 12 suffix positions: " + Arrays.toString(Arrays.copyOf(sa, Math.min(12, sa.length))));
        System.out.println("   Query 'wire transfer' found at index: " + SuffixArray.search(document, sa, "wire transfer"));

        int[] sais = SAIS.build(document);
        System.out.println("\n2) SA-IS induced-sorting suffix array");
        System.out.println("   First 12 positions: " + Arrays.toString(Arrays.copyOf(sais, Math.min(12, sais.length))));
        System.out.println("   Same ordering as doubling suffix array: " + Arrays.equals(sa, sais));

        int[] lcp = KasaiLCP.build(document, sa);
        String repeated = KasaiLCP.longestRepeatedSubstring(document, sa, lcp);
        System.out.println("\n3) Kasai LCP - repeated transaction text");
        System.out.println("   Longest repeated substring: '" + repeated + "'");

        SuffixAutomaton automaton = new SuffixAutomaton(document);
        System.out.println("\n4) Suffix Automaton - fast substring query");
        System.out.println("   Contains 'salary credit'? " + automaton.contains("salary credit"));
        System.out.println("   Contains 'crypto mining'? " + automaton.contains("crypto mining"));
    }

    public void runDynamicProgramming() {
        header("M3 - CO3: ADVANCED DYNAMIC PROGRAMMING");
        String entered = "UPI paymant grocery";
        String correct = "UPI payment grocery";

        System.out.println("1) Levenshtein distance for transaction-description correction");
        System.out.println("   '" + entered + "' -> '" + correct + "' = " + Levenshtein.distance(entered, correct));

        System.out.println("\n2) Damerau-Levenshtein for transposition errors");
        System.out.println("   'NEFT trasnfer' -> 'NEFT transfer' = " + DamerauLevenshtein.distance("NEFT trasnfer", "NEFT transfer"));

        System.out.println("\n3) Weighted Edit Distance (replacement errors are more expensive)");
        System.out.println("   Weighted cost = " + WeightedEditDistance.distance("ACC1003", "ACC1903", 1, 1, 3));

        List<BitmaskOperationSelector.Operation> operations = List.of(
                new BitmaskOperationSelector.Operation("Fraud scan", 4, 10),
                new BitmaskOperationSelector.Operation("Credit scoring", 5, 12),
                new BitmaskOperationSelector.Operation("AML report", 6, 14),
                new BitmaskOperationSelector.Operation("Portfolio analysis", 3, 7),
                new BitmaskOperationSelector.Operation("Audit export", 2, 4)
        );
        BitmaskOperationSelector.Selection selection = BitmaskOperationSelector.select(operations, 12);
        System.out.println("\n4) Bitmask DP - best analytics operations under processing budget 12");
        System.out.println("   Selected: " + selection.operations().stream().map(BitmaskOperationSelector.Operation::name).toList());
        System.out.println("   Cost=" + selection.totalCost() + ", Analytics value=" + selection.totalValue());

        MatrixChainMultiplication.Result matrix = MatrixChainMultiplication.optimize(new int[]{40, 20, 30, 10, 30});
        System.out.println("\n5) Matrix-Chain Multiplication - optimize transaction-analysis pipeline");
        System.out.println("   Minimum scalar operations: " + matrix.minimumCost());
        System.out.println("   Best order: " + matrix.parenthesization());
    }

    public void runNetworkFlow() {
        header("M4 - CO4: NETWORK FLOW");
        int[][] network = sampleCapacityNetwork();

        int ff = FordFulkerson.maxFlow(network, 0, 5);
        int ek = EdmondsKarp.maxFlow(network, 0, 5);
        System.out.println("1) Ford-Fulkerson max transaction flow : " + ff);
        System.out.println("2) Edmonds-Karp max transaction flow    : " + ek);

        Dinic dinic = new Dinic(6);
        addDinicEdges(dinic);
        System.out.println("3) Dinic max transaction flow           : " + dinic.maxFlow(0, 5));

        boolean[][] analystCases = {
                {true, true, false, false},
                {false, true, true, false},
                {true, false, false, true},
                {false, false, true, true}
        };
        System.out.println("\n4) Bipartite Matching - analysts assigned to fraud cases: "
                + BipartiteMatching.maximumMatching(analystCases));

        System.out.println("\n5) Min-Cut - critical transaction channels");
        for (MinCut.Edge edge : MinCut.criticalEdges(network, 0, 5)) {
            System.out.println("   Channel " + edge.from() + " -> " + edge.to() + " capacity=" + edge.capacity());
        }
    }

    public void runNPAlgorithms() {
        header("M5 - CO5: NP-COMPLETENESS AND APPROXIMATION");
        int[][] clauses = {
                {1, -2, 3},
                {-1, 2, 3},
                {1, 2, -3}
        };

        SATSolver.Result result = SATSolver.solve(3, clauses);
        System.out.println("1) SAT/3-SAT financial-rule constraint solver");
        System.out.println("   Satisfiable: " + result.satisfiable());
        if (result.satisfiable()) System.out.println("   Assignment : " + SATSolver.assignmentToString(result.assignment()));

        boolean[][] cliqueGraph = GraphReductions.threeSatToCliqueGraph(clauses);
        System.out.println("\n2) 3-SAT -> CLIQUE reduction");
        System.out.println("   " + GraphReductions.explain(clauses.length, cliqueGraph.length));

        boolean[][] independentSetGraph = GraphReductions.complement(cliqueGraph);
        System.out.println("\n3) CLIQUE -> INDEPENDENT-SET -> VERTEX-COVER");
        System.out.println("   Complement graph vertices: " + independentSetGraph.length);

        boolean[][] dependencyGraph = {
                {false, true, true, false, false, false},
                {true, false, true, true, false, false},
                {true, true, false, false, true, false},
                {false, true, false, false, true, true},
                {false, false, true, true, false, true},
                {false, false, false, true, true, false}
        };
        Set<Integer> cover = VertexCoverApproximation.twoApprox(dependencyGraph);
        System.out.println("\n4) Vertex-Cover 2-approximation for financial dependencies");
        System.out.println("   Approximate cover vertices: " + cover);
    }

    public void runRandomizedParallel() {
        header("M6 - CO6: RANDOMIZED AND PARALLEL ALGORITHMS");
        double[] amounts = data.transactions().stream().mapToDouble(Transaction::getAmount).toArray();
        RandomizedQuickSort.sort(amounts, 42);
        System.out.println("1) Randomized QuickSort - ranked transaction amounts");
        System.out.println("   " + Arrays.toString(amounts));

        long largeNumber = 2_147_483_647L;
        System.out.println("\n2) Miller-Rabin primality test");
        System.out.println("   " + largeNumber + " is prime? " + MillerRabin.isProbablePrime(largeNumber));

        List<String> ids = data.transactions().stream().map(Transaction::getId).toList();
        System.out.println("\n3) Reservoir Sampling - sample 3 transactions from stream");
        System.out.println("   " + ReservoirSampling.sample(ids, 3, 42));

        long[] integerAmounts = data.transactions().stream().mapToLong(tx -> Math.round(tx.getAmount())).toArray();
        long[] prefix = BlellochScan.exclusivePrefixSum(integerAmounts);
        System.out.println("\n4) Blelloch Scan - exclusive cumulative transaction totals");
        System.out.println("   " + Arrays.toString(prefix));

        System.out.println("\n5) Parallel Reduce - aggregate total transaction value");
        System.out.println("   Total = Rs." + ParallelReduce.sum(integerAmounts));

        double bound = BrentTheorem.upperBound(1200, 90, 8);
        System.out.println("\n6) Brent's theorem - 8-processor time upper bound");
        System.out.println("   T_p <= T1/p + T_infinity = " + bound + " abstract time units");
    }

    public void runAll() {
        showBankingData();
        runStringAlgorithms();
        runSuffixAlgorithms();
        runDynamicProgramming();
        runNetworkFlow();
        runNPAlgorithms();
        runRandomizedParallel();
        header("DEMO COMPLETE");
        System.out.println("FinServe successfully demonstrated M1-CO1 through M6-CO6.");
    }

    private int[][] sampleCapacityNetwork() {
        return new int[][]{
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
    }

    private void addDinicEdges(Dinic dinic) {
        int[][] g = sampleCapacityNetwork();
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) if (g[i][j] > 0) dinic.addEdge(i, j, g[i][j]);
        }
    }

    private void header(String title) {
        System.out.println("\n============================================================");
        System.out.println(title);
        System.out.println("============================================================");
    }
}
