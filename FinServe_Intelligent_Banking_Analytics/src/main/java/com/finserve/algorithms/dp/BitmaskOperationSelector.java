package com.finserve.algorithms.dp;

import java.util.ArrayList;
import java.util.List;

public final class BitmaskOperationSelector {
    private BitmaskOperationSelector() {}

    public record Operation(String name, int processingCost, int analyticsValue) {}
    public record Selection(List<Operation> operations, int totalCost, int totalValue) {}

    public static Selection select(List<Operation> operations, int maxCost) {
        int n = operations.size();
        if (n > 20) throw new IllegalArgumentException("Demo bitmask DP supports up to 20 operations.");
        int states = 1 << n;
        int[] cost = new int[states];
        int[] value = new int[states];
        int bestMask = 0;
        for (int mask = 1; mask < states; mask++) {
            int bit = Integer.numberOfTrailingZeros(mask);
            int previous = mask & (mask - 1);
            Operation op = operations.get(bit);
            cost[mask] = cost[previous] + op.processingCost();
            value[mask] = value[previous] + op.analyticsValue();
            if (cost[mask] <= maxCost && value[mask] > value[bestMask]) bestMask = mask;
        }
        List<Operation> chosen = new ArrayList<>();
        for (int i = 0; i < n; i++) if ((bestMask & (1 << i)) != 0) chosen.add(operations.get(i));
        return new Selection(chosen, cost[bestMask], value[bestMask]);
    }
}
