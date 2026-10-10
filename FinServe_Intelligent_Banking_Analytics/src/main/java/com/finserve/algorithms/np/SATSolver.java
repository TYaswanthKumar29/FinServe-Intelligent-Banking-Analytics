package com.finserve.algorithms.np;

import java.util.Arrays;

public final class SATSolver {
    private SATSolver() {}

    public record Result(boolean satisfiable, boolean[] assignment) {}

    public static Result solve(int variables, int[][] clauses) {
        int total = 1 << variables;
        for (int mask = 0; mask < total; mask++) {
            boolean[] assignment = new boolean[variables];
            for (int i = 0; i < variables; i++) assignment[i] = (mask & (1 << i)) != 0;
            if (satisfies(assignment, clauses)) return new Result(true, assignment);
        }
        return new Result(false, new boolean[variables]);
    }

    private static boolean satisfies(boolean[] assignment, int[][] clauses) {
        for (int[] clause : clauses) {
            boolean clauseTrue = false;
            for (int literal : clause) {
                int var = Math.abs(literal) - 1;
                boolean value = assignment[var];
                if (literal < 0) value = !value;
                clauseTrue |= value;
            }
            if (!clauseTrue) return false;
        }
        return true;
    }

    public static String assignmentToString(boolean[] assignment) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < assignment.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append("x").append(i + 1).append("=").append(assignment[i]);
        }
        return sb.toString();
    }
}
