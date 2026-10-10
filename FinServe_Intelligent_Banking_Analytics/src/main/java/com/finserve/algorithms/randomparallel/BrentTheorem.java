package com.finserve.algorithms.randomparallel;

public final class BrentTheorem {
    private BrentTheorem() {}

    public static double upperBound(double totalWorkT1, double spanTInfinity, int processors) {
        if (processors <= 0) throw new IllegalArgumentException("processors must be positive");
        return totalWorkT1 / processors + spanTInfinity;
    }
}
