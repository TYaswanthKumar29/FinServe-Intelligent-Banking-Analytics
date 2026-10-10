package com.finserve.algorithms.randomparallel;

import java.util.Arrays;

public final class BlellochScan {
    private BlellochScan() {}

    public static long[] exclusivePrefixSum(long[] input) {
        int n = 1;
        while (n < input.length) n <<= 1;
        long[] work = new long[n];
        System.arraycopy(input, 0, work, 0, input.length);

        for (int d = 1; d < n; d <<= 1) {
            for (int i = 0; i < n; i += d << 1) {
                int left = i + d - 1;
                int right = i + (d << 1) - 1;
                work[right] += work[left];
            }
        }
        work[n - 1] = 0;
        for (int d = n >> 1; d >= 1; d >>= 1) {
            for (int i = 0; i < n; i += d << 1) {
                int left = i + d - 1;
                int right = i + (d << 1) - 1;
                long temp = work[left];
                work[left] = work[right];
                work[right] += temp;
            }
        }
        return Arrays.copyOf(work, input.length);
    }
}
