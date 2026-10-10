package com.finserve.service;

import com.finserve.model.Account;
import com.finserve.model.Customer;
import com.finserve.model.Transaction;

import java.util.List;

public class BankingDataService {
    public List<Customer> customers() {
        return List.of(
                new Customer("C001", "Aarav", 18),
                new Customer("C002", "Diya", 44),
                new Customer("C003", "Harsha", 27),
                new Customer("C004", "Meera", 73)
        );
    }

    public List<Account> accounts() {
        return List.of(
                new Account("ACC1001", "C001", 145000),
                new Account("ACC1002", "C002", 87000),
                new Account("ACC1003", "C003", 225000),
                new Account("ACC1004", "C004", 32000)
        );
    }

    public List<Transaction> transactions() {
        return List.of(
                new Transaction("TXN001", "ACC1001", "UPI payment to grocery store", "UPI-GROC-4812", 1850, "A", "B"),
                new Transaction("TXN002", "ACC1002", "NEFT salary credit September", "NEFT-SAL-9931", 62000, "C", "D"),
                new Transaction("TXN003", "ACC1003", "ATM cash withdrawal", "ATM-WDL-7765", 10000, "B", "E"),
                new Transaction("TXN004", "ACC1004", "urgent transfer suspicious OTP request", "IMPS-URG-1122", 48500, "E", "F"),
                new Transaction("TXN005", "ACC1001", "UPI payment to grocery store", "UPI-GROC-4812", 2150, "A", "B"),
                new Transaction("TXN006", "ACC1003", "international wire transfer verify password", "WIRE-INT-9008", 125000, "D", "F"),
                new Transaction("TXN007", "ACC1002", "credit card bill payment", "CARD-BILL-2026", 14800, "C", "A"),
                new Transaction("TXN008", "ACC1003", "merchant refund received", "REFUND-4421", 3400, "F", "C")
        );
    }

    public String combinedDescriptions() {
        StringBuilder sb = new StringBuilder();
        for (Transaction tx : transactions()) {
            if (sb.length() > 0) sb.append(" | ");
            sb.append(tx.getDescription());
        }
        return sb.toString().toLowerCase();
    }
}
