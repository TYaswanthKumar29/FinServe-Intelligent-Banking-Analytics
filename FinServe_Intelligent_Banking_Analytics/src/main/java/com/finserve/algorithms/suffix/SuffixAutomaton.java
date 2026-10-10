package com.finserve.algorithms.suffix;

import java.util.HashMap;
import java.util.Map;

public class SuffixAutomaton {
    private static class State {
        int len;
        int link = -1;
        Map<Character, Integer> next = new HashMap<>();
    }

    private final State[] states;
    private int size = 1;
    private int last = 0;

    public SuffixAutomaton(String text) {
        states = new State[Math.max(2, 2 * text.length())];
        states[0] = new State();
        for (char c : text.toCharArray()) extend(c);
    }

    private void extend(char c) {
        int cur = size++;
        states[cur] = new State();
        states[cur].len = states[last].len + 1;
        int p = last;
        while (p != -1 && !states[p].next.containsKey(c)) {
            states[p].next.put(c, cur);
            p = states[p].link;
        }
        if (p == -1) {
            states[cur].link = 0;
        } else {
            int q = states[p].next.get(c);
            if (states[p].len + 1 == states[q].len) {
                states[cur].link = q;
            } else {
                int clone = size++;
                states[clone] = new State();
                states[clone].len = states[p].len + 1;
                states[clone].next.putAll(states[q].next);
                states[clone].link = states[q].link;
                while (p != -1 && states[p].next.getOrDefault(c, -1) == q) {
                    states[p].next.put(c, clone);
                    p = states[p].link;
                }
                states[q].link = states[cur].link = clone;
            }
        }
        last = cur;
    }

    public boolean contains(String pattern) {
        int state = 0;
        for (char c : pattern.toCharArray()) {
            Integer next = states[state].next.get(c);
            if (next == null) return false;
            state = next;
        }
        return true;
    }
}
