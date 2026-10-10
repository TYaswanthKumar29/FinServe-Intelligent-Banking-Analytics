package com.finserve.algorithms.suffix;

import java.util.Arrays;

public final class SuffixArray {
    private SuffixArray() {}

    public static int[] build(String text) {
        int n = text.length();
        Integer[] order = new Integer[n];
        int[] rank = new int[n];
        int[] nextRank = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
            rank[i] = text.charAt(i);
        }
        for (int k = 1; k < n; k <<= 1) {
            final int step = k;
            final int[] currentRank = rank;
            Arrays.sort(order, (a, b) -> {
                if (currentRank[a] != currentRank[b]) return Integer.compare(currentRank[a], currentRank[b]);
                int ra = a + step < n ? currentRank[a + step] : -1;
                int rb = b + step < n ? currentRank[b + step] : -1;
                return Integer.compare(ra, rb);
            });
            nextRank[order[0]] = 0;
            for (int i = 1; i < n; i++) {
                int prev = order[i - 1], cur = order[i];
                boolean different = currentRank[prev] != currentRank[cur];
                int prevSecond = prev + step < n ? currentRank[prev + step] : -1;
                int curSecond = cur + step < n ? currentRank[cur + step] : -1;
                if (prevSecond != curSecond) different = true;
                nextRank[cur] = nextRank[prev] + (different ? 1 : 0);
            }
            int[] temp = rank;
            rank = nextRank;
            nextRank = temp;
            if (rank[order[n - 1]] == n - 1) break;
        }
        int[] sa = new int[n];
        for (int i = 0; i < n; i++) sa[i] = order[i];
        return sa;
    }

    public static int search(String text, int[] sa, String pattern) {
        int lo = 0, hi = sa.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int pos = sa[mid];
            String suffix = text.substring(pos);
            int cmp = suffix.compareTo(pattern);
            if (suffix.startsWith(pattern)) return pos;
            if (cmp < 0) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }
}
