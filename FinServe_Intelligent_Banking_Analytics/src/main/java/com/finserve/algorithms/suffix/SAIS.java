package com.finserve.algorithms.suffix;

import java.util.Arrays;

public final class SAIS {
    private SAIS() {}

    public static int[] build(String text) {
        if (text.isEmpty()) return new int[0];
        int[] s = new int[text.length()];
        int upper = 0;
        for (int i = 0; i < text.length(); i++) {
            s[i] = text.charAt(i);
            upper = Math.max(upper, s[i]);
        }
        return saIs(s, upper);
    }

    private static int[] saIs(int[] s, int upper) {
        int n = s.length;
        if (n == 0) return new int[0];
        if (n == 1) return new int[]{0};
        if (n == 2) return s[0] < s[1] ? new int[]{0, 1} : new int[]{1, 0};

        int[] sa = new int[n];
        boolean[] ls = new boolean[n];
        ls[n - 1] = false;
        for (int i = n - 2; i >= 0; i--) {
            ls[i] = s[i] == s[i + 1] ? ls[i + 1] : s[i] < s[i + 1];
        }

        int[] sumL = new int[upper + 2];
        int[] sumS = new int[upper + 2];
        for (int i = 0; i < n; i++) {
            if (!ls[i]) sumS[s[i]]++;
            else sumL[s[i] + 1]++;
        }
        for (int i = 0; i <= upper; i++) {
            sumS[i] += sumL[i];
            sumL[i + 1] += sumS[i];
        }

        int[] lmsMap = new int[n + 1];
        Arrays.fill(lmsMap, -1);
        int m = 0;
        for (int i = 1; i < n; i++) {
            if (!ls[i - 1] && ls[i]) lmsMap[i] = m++;
        }
        int[] lms = new int[m];
        int p = 0;
        for (int i = 1; i < n; i++) if (!ls[i - 1] && ls[i]) lms[p++] = i;

        induce(s, upper, ls, sumL, sumS, lms, sa);

        if (m > 0) {
            int[] sortedLms = new int[m];
            int idx = 0;
            for (int v : sa) if (v >= 0 && lmsMap[v] != -1) sortedLms[idx++] = v;

            int[] recS = new int[m];
            int recUpper = 0;
            recS[lmsMap[sortedLms[0]]] = 0;
            for (int i = 1; i < m; i++) {
                int l = sortedLms[i - 1];
                int r = sortedLms[i];
                int lIndex = lmsMap[l];
                int rIndex = lmsMap[r];
                int endL = lIndex + 1 < m ? lms[lIndex + 1] : n;
                int endR = rIndex + 1 < m ? lms[rIndex + 1] : n;
                boolean same = endL - l == endR - r;
                if (same) {
                    while (l < endL) {
                        if (s[l] != s[r] || ls[l] != ls[r]) { same = false; break; }
                        l++; r++;
                    }
                }
                if (!same) recUpper++;
                recS[lmsMap[sortedLms[i]]] = recUpper;
            }

            int[] recSA = saIs(recS, recUpper);
            int[] orderedLms = new int[m];
            for (int i = 0; i < m; i++) orderedLms[i] = lms[recSA[i]];
            induce(s, upper, ls, sumL, sumS, orderedLms, sa);
        }
        return sa;
    }

    private static void induce(int[] s, int upper, boolean[] ls, int[] sumL, int[] sumS,
                               int[] lms, int[] sa) {
        Arrays.fill(sa, -1);
        int[] buf = Arrays.copyOf(sumS, sumS.length);
        for (int d : lms) if (d < s.length) sa[buf[s[d]]++] = d;

        buf = Arrays.copyOf(sumL, sumL.length);
        sa[buf[s[s.length - 1]]++] = s.length - 1;
        for (int i = 0; i < s.length; i++) {
            int v = sa[i];
            if (v >= 1 && !ls[v - 1]) sa[buf[s[v - 1]]++] = v - 1;
        }

        buf = Arrays.copyOf(sumL, sumL.length);
        for (int i = s.length - 1; i >= 0; i--) {
            int v = sa[i];
            if (v >= 1 && ls[v - 1]) sa[--buf[s[v - 1] + 1]] = v - 1;
        }
    }
}
