package com.finserve.algorithms.stringalg;

import java.util.ArrayList;
import java.util.List;

public final class RabinKarp {
    private static final long BASE = 256;
    private static final long MOD = 1_000_000_007L;
    private RabinKarp() {}

    public static List<Integer> search(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length(), m = pattern.length();
        if (m == 0 || m > n) return matches;

        long patternHash = 0, windowHash = 0, high = 1;
        for (int i = 0; i < m - 1; i++) high = (high * BASE) % MOD;
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * BASE + pattern.charAt(i)) % MOD;
            windowHash = (windowHash * BASE + text.charAt(i)) % MOD;
        }
        for (int i = 0; i <= n - m; i++) {
            if (patternHash == windowHash && text.regionMatches(i, pattern, 0, m)) matches.add(i);
            if (i < n - m) {
                windowHash = (windowHash - text.charAt(i) * high) % MOD;
                if (windowHash < 0) windowHash += MOD;
                windowHash = (windowHash * BASE + text.charAt(i + m)) % MOD;
            }
        }
        return matches;
    }
}
