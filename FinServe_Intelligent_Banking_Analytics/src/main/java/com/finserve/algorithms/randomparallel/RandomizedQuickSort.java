package com.finserve.algorithms.randomparallel;

import java.util.Random;

public final class RandomizedQuickSort {
    private RandomizedQuickSort() {}

    public static void sort(double[] values, long seed) {
        quickSort(values, 0, values.length - 1, new Random(seed));
    }

    private static void quickSort(double[] a, int low, int high, Random random) {
        if (low >= high) return;
        int pivotIndex = low + random.nextInt(high - low + 1);
        swap(a, pivotIndex, high);
        int p = partition(a, low, high);
        quickSort(a, low, p - 1, random);
        quickSort(a, p + 1, high, random);
    }

    private static int partition(double[] a, int low, int high) {
        double pivot = a[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) swap(a, i++, j);
        }
        swap(a, i, high);
        return i;
    }

    private static void swap(double[] a, int i, int j) {
        double t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
