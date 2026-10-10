package com.finserve.algorithms.randomparallel;

import java.math.BigInteger;

public final class MillerRabin {
    private MillerRabin() {}

    public static boolean isProbablePrime(long n) {
        if (n < 2) return false;
        int[] smallPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
        for (int p : smallPrimes) {
            if (n == p) return true;
            if (n % p == 0) return false;
        }
        long d = n - 1;
        int s = 0;
        while ((d & 1) == 0) { d >>= 1; s++; }
        long[] bases = {2, 3, 5, 7, 11, 13, 17};
        for (long a : bases) {
            if (a >= n) continue;
            long x = modPow(a, d, n);
            if (x == 1 || x == n - 1) continue;
            boolean witness = true;
            for (int r = 1; r < s; r++) {
                x = modMultiply(x, x, n);
                if (x == n - 1) { witness = false; break; }
            }
            if (witness) return false;
        }
        return true;
    }

    private static long modMultiply(long a, long b, long mod) {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
    }

    private static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = modMultiply(result, base, mod);
            base = modMultiply(base, base, mod);
            exp >>= 1;
        }
        return result;
    }
}
