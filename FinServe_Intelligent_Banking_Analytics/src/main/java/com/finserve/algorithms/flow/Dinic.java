package com.finserve.algorithms.flow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Dinic {
    private static class Edge {
        int to, rev, capacity;
        Edge(int to, int rev, int capacity) { this.to = to; this.rev = rev; this.capacity = capacity; }
    }

    private final List<List<Edge>> graph;
    private final int[] level;
    private final int[] ptr;

    public Dinic(int n) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        level = new int[n];
        ptr = new int[n];
    }

    public void addEdge(int u, int v, int capacity) {
        Edge a = new Edge(v, graph.get(v).size(), capacity);
        Edge b = new Edge(u, graph.get(u).size(), 0);
        graph.get(u).add(a);
        graph.get(v).add(b);
    }

    public int maxFlow(int source, int sink) {
        int flow = 0;
        while (bfs(source, sink)) {
            Arrays.fill(ptr, 0);
            int pushed;
            while ((pushed = dfs(source, sink, Integer.MAX_VALUE)) > 0) flow += pushed;
        }
        return flow;
    }

    private boolean bfs(int source, int sink) {
        Arrays.fill(level, -1);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        level[source] = 0;
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (Edge e : graph.get(u)) {
                if (e.capacity > 0 && level[e.to] == -1) {
                    level[e.to] = level[u] + 1;
                    queue.add(e.to);
                }
            }
        }
        return level[sink] != -1;
    }

    private int dfs(int u, int sink, int pushed) {
        if (pushed == 0) return 0;
        if (u == sink) return pushed;
        for (; ptr[u] < graph.get(u).size(); ptr[u]++) {
            Edge e = graph.get(u).get(ptr[u]);
            if (level[e.to] != level[u] + 1 || e.capacity <= 0) continue;
            int tr = dfs(e.to, sink, Math.min(pushed, e.capacity));
            if (tr == 0) continue;
            e.capacity -= tr;
            graph.get(e.to).get(e.rev).capacity += tr;
            return tr;
        }
        return 0;
    }
}
