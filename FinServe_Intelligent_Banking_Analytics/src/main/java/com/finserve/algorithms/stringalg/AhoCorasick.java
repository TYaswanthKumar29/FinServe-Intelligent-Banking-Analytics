package com.finserve.algorithms.stringalg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AhoCorasick {
    private static final int ALPHABET = 128;

    private static class Node {
        Node[] next = new Node[ALPHABET];
        Node fail;
        List<String> output = new ArrayList<>();
    }

    private final Node root = new Node();

    public AhoCorasick(List<String> patterns) {
        for (String pattern : patterns) add(pattern.toLowerCase());
        buildFailureLinks();
    }

    private void add(String pattern) {
        Node current = root;
        for (char c : pattern.toCharArray()) {
            if (c >= ALPHABET) continue;
            if (current.next[c] == null) current.next[c] = new Node();
            current = current.next[c];
        }
        current.output.add(pattern);
    }

    private void buildFailureLinks() {
        Queue<Node> queue = new ArrayDeque<>();
        root.fail = root;
        for (int c = 0; c < ALPHABET; c++) {
            if (root.next[c] != null) {
                root.next[c].fail = root;
                queue.add(root.next[c]);
            }
        }
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            for (int c = 0; c < ALPHABET; c++) {
                Node child = current.next[c];
                if (child == null) continue;
                Node f = current.fail;
                while (f != root && f.next[c] == null) f = f.fail;
                if (f.next[c] != null && f.next[c] != child) child.fail = f.next[c];
                else child.fail = root;
                child.output.addAll(child.fail.output);
                queue.add(child);
            }
        }
    }

    public List<String> findMatches(String text) {
        List<String> matches = new ArrayList<>();
        Node current = root;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c >= ALPHABET) {
                current = root;
                continue;
            }
            while (current != root && current.next[c] == null) current = current.fail;
            if (current.next[c] != null) current = current.next[c];
            for (String pattern : current.output) {
                matches.add(pattern + " @ index " + (i - pattern.length() + 1));
            }
        }
        return matches;
    }
}
