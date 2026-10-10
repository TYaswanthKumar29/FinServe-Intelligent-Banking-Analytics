package com.finserve.algorithms.np;

public final class GraphReductions {
    private GraphReductions() {}

    public static boolean[][] threeSatToCliqueGraph(int[][] clauses) {
        int verticesPerClause = 3;
        int n = clauses.length * verticesPerClause;
        boolean[][] graph = new boolean[n][n];
        for (int c1 = 0; c1 < clauses.length; c1++) {
            for (int c2 = c1 + 1; c2 < clauses.length; c2++) {
                for (int i = 0; i < verticesPerClause; i++) {
                    for (int j = 0; j < verticesPerClause; j++) {
                        int lit1 = clauses[c1][i];
                        int lit2 = clauses[c2][j];
                        if (lit1 != -lit2) {
                            int u = c1 * verticesPerClause + i;
                            int v = c2 * verticesPerClause + j;
                            graph[u][v] = graph[v][u] = true;
                        }
                    }
                }
            }
        }
        return graph;
    }

    public static boolean[][] complement(boolean[][] graph) {
        int n = graph.length;
        boolean[][] comp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) if (i != j) comp[i][j] = !graph[i][j];
        }
        return comp;
    }

    public static String explain(int clauseCount, int graphVertices) {
        return "3-SAT with " + clauseCount + " clauses -> CLIQUE graph with " + graphVertices
                + " literal-vertices. CLIQUE in G becomes INDEPENDENT-SET in complement(G); "
                + "an independent set of size k corresponds to a vertex cover of size |V|-k.";
    }
}
