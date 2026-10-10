package com.finserve.algorithms.suffix;

public final class KasaiLCP {
    private KasaiLCP() {}

    public static int[] build(String text, int[] suffixArray) {
        int n = text.length();
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) rank[suffixArray[i]] = i;
        int[] lcp = new int[n];
        int h = 0;
        for (int i = 0; i < n; i++) {
            int r = rank[i];
            if (r == n - 1) { h = 0; continue; }
            int j = suffixArray[r + 1];
            while (i + h < n && j + h < n && text.charAt(i + h) == text.charAt(j + h)) h++;
            lcp[r] = h;
            if (h > 0) h--;
        }
        return lcp;
    }

    public static String longestRepeatedSubstring(String text, int[] sa, int[] lcp) {
        int best = 0, index = -1;
        for (int i = 0; i < lcp.length; i++) {
            if (lcp[i] > best) {
                best = lcp[i];
                index = sa[i];
            }
        }
        return best == 0 ? "" : text.substring(index, index + best);
    }
}
