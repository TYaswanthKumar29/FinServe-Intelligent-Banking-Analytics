package com.finserve.algorithms.randomparallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ReservoirSampling {
    private ReservoirSampling() {}

    public static <T> List<T> sample(List<T> stream, int k, long seed) {
        if (k < 0 || k > stream.size()) throw new IllegalArgumentException("Invalid sample size");
        List<T> reservoir = new ArrayList<>(stream.subList(0, k));
        Random random = new Random(seed);
        for (int i = k; i < stream.size(); i++) {
            int j = random.nextInt(i + 1);
            if (j < k) reservoir.set(j, stream.get(i));
        }
        return reservoir;
    }
}
