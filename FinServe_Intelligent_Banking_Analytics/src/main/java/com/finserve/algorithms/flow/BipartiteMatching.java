package com.finserve.algorithms.flow;

import java.util.Arrays;

public final class BipartiteMatching {
    private BipartiteMatching() {}

    public static int maximumMatching(boolean[][] canHandle) {
        int analysts = canHandle.length;
        int cases = canHandle[0].length;
        int[] assignedAnalyst = new int[cases];
        Arrays.fill(assignedAnalyst, -1);
        int matches = 0;
        for (int analyst = 0; analyst < analysts; analyst++) {
            boolean[] seen = new boolean[cases];
            if (augment(analyst, canHandle, seen, assignedAnalyst)) matches++;
        }
        return matches;
    }

    private static boolean augment(int analyst, boolean[][] graph, boolean[] seen, int[] assigned) {
        for (int c = 0; c < graph[analyst].length; c++) {
            if (graph[analyst][c] && !seen[c]) {
                seen[c] = true;
                if (assigned[c] == -1 || augment(assigned[c], graph, seen, assigned)) {
                    assigned[c] = analyst;
                    return true;
                }
            }
        }
        return false;
    }
}
