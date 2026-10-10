package com.finserve.algorithms.np;

import java.util.LinkedHashSet;
import java.util.Set;

public final class VertexCoverApproximation {
    private VertexCoverApproximation() {}

    public static Set<Integer> twoApprox(boolean[][] graph) {
        int n = graph.length;
        boolean[][] remaining = new boolean[n][n];
        for (int i = 0; i < n; i++) System.arraycopy(graph[i], 0, remaining[i], 0, n);
        Set<Integer> cover = new LinkedHashSet<>();
        while (true) {
            int u = -1, v = -1;
            outer:
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (remaining[i][j]) { u = i; v = j; break outer; }
                }
            }
            if (u == -1) break;
            cover.add(u); cover.add(v);
            for (int i = 0; i < n; i++) {
                remaining[u][i] = remaining[i][u] = false;
                remaining[v][i] = remaining[i][v] = false;
            }
        }
        return cover;
    }
}
