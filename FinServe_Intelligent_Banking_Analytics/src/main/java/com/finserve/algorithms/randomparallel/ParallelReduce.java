package com.finserve.algorithms.randomparallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public final class ParallelReduce {
    private ParallelReduce() {}

    public static long sum(long[] values) {
        return ForkJoinPool.commonPool().invoke(new SumTask(values, 0, values.length));
    }

    private static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 4;
        private final long[] values;
        private final int start, end;

        SumTask(long[] values, int start, int end) {
            this.values = values; this.start = start; this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) sum += values[i];
                return sum;
            }
            int mid = (start + end) >>> 1;
            SumTask left = new SumTask(values, start, mid);
            SumTask right = new SumTask(values, mid, end);
            left.fork();
            long rightValue = right.compute();
            return left.join() + rightValue;
        }
    }
}
