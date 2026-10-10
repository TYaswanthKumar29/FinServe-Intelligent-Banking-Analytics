package com.finserve.algorithms.flow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public final class MinCut {
    private MinCut() {}

    public record Edge(int from, int to, int capacity) {}

    public static List<Edge> criticalEdges(int[][] capacity, int source, int sink) {
        int[][] residual = FordFulkerson.copy(capacity);
        int[] parent = new int[capacity.length];
        while (EdmondsKarp.bfs(residual, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) pathFlow = Math.min(pathFlow, residual[parent[v]][v]);
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }
        }
        boolean[] reachable = new boolean[capacity.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(source); reachable[source] = true;
        while (!q.isEmpty()) {
            int u = q.remove();
            for (int v = 0; v < capacity.length; v++) {
                if (!reachable[v] && residual[u][v] > 0) { reachable[v] = true; q.add(v); }
            }
        }
        List<Edge> cut = new ArrayList<>();
        for (int u = 0; u < capacity.length; u++) {
            for (int v = 0; v < capacity.length; v++) {
                if (reachable[u] && !reachable[v] && capacity[u][v] > 0) cut.add(new Edge(u, v, capacity[u][v]));
            }
        }
        return cut;
    }
}
