package com.finserve.model;

public class Transaction {
    private final String id;
    private final String accountNumber;
    private final String description;
    private final String paymentCode;
    private final double amount;
    private final String source;
    private final String destination;

    public Transaction(String id, String accountNumber, String description, String paymentCode,
                       double amount, String source, String destination) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.description = description;
        this.paymentCode = paymentCode;
        this.amount = amount;
        this.source = source;
        this.destination = destination;
    }

    public String getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public String getDescription() { return description; }
    public String getPaymentCode() { return paymentCode; }
    public double getAmount() { return amount; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }

    @Override
    public String toString() {
        return id + " | " + accountNumber + " | " + paymentCode + " | Rs." + amount + " | " + description;
    }
}
