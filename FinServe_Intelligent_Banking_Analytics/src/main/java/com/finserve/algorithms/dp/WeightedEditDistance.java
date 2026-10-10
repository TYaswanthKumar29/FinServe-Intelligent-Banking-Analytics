package com.finserve.algorithms.dp;

public final class WeightedEditDistance {
    private WeightedEditDistance() {}

    public static int distance(String a, String b, int insertCost, int deleteCost, int replaceCost) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) dp[i][0] = i * deleteCost;
        for (int j = 1; j <= b.length(); j++) dp[0][j] = j * insertCost;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int substitution = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : replaceCost;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + deleteCost, dp[i][j - 1] + insertCost),
                        dp[i - 1][j - 1] + substitution
                );
            }
        }
        return dp[a.length()][b.length()];
    }
}
