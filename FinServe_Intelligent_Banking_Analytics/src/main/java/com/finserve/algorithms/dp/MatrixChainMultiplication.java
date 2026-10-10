package com.finserve.algorithms.dp;

public final class MatrixChainMultiplication {
    private MatrixChainMultiplication() {}

    public record Result(long minimumCost, String parenthesization) {}

    public static Result optimize(int[] dimensions) {
        int n = dimensions.length - 1;
        long[][] dp = new long[n][n];
        int[][] split = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Long.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    long cost = dp[i][k] + dp[k + 1][j]
                            + (long) dimensions[i] * dimensions[k + 1] * dimensions[j + 1];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        split[i][j] = k;
                    }
                }
            }
        }
        return new Result(dp[0][n - 1], build(0, n - 1, split));
    }

    private static String build(int i, int j, int[][] split) {
        if (i == j) return "A" + (i + 1);
        int k = split[i][j];
        return "(" + build(i, k, split) + " x " + build(k + 1, j, split) + ")";
    }
}
