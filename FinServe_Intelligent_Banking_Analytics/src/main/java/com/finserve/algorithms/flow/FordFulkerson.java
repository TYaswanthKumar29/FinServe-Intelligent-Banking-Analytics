package com.finserve.algorithms.flow;

public final class FordFulkerson {
    private FordFulkerson() {}

    public static int maxFlow(int[][] capacity, int source, int sink) {
        int n = capacity.length;
        int[][] residual = copy(capacity);
        int flow = 0;
        while (true) {
            boolean[] visited = new boolean[n];
            int pushed = dfs(source, sink, Integer.MAX_VALUE, residual, visited);
            if (pushed == 0) break;
            flow += pushed;
        }
        return flow;
    }

    private static int dfs(int u, int sink, int pushed, int[][] residual, boolean[] visited) {
        if (u == sink) return pushed;
        visited[u] = true;
        for (int v = 0; v < residual.length; v++) {
            if (!visited[v] && residual[u][v] > 0) {
                int send = dfs(v, sink, Math.min(pushed, residual[u][v]), residual, visited);
                if (send > 0) {
                    residual[u][v] -= send;
                    residual[v][u] += send;
                    return send;
                }
            }
        }
        return 0;
    }

    static int[][] copy(int[][] matrix) {
        int[][] copy = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) System.arraycopy(matrix[i], 0, copy[i], 0, matrix.length);
        return copy;
    }
}
