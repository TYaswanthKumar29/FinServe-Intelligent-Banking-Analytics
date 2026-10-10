package com.finserve.model;

public class Customer {
    private final String id;
    private final String name;
    private final int riskScore;

    public Customer(String id, String name, int riskScore) {
        this.id = id;
        this.name = name;
        this.riskScore = riskScore;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getRiskScore() { return riskScore; }
}
