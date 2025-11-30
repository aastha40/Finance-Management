package com.example.financemanagement.model;

public interface TransactionProcessor {
    void process(Transaction transaction) throws InsufficientFundsException;
}
