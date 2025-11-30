package com.example.financemanagement.model;

import java.util.Date;

public class BankAccount extends Account {

    public BankAccount() {
        super();
        this.type = "Bank Account";
    }

    public BankAccount(String id, String name, String currency, double balance, Date createdAt) {
        super(id, name, "Bank Account", currency, balance, createdAt);
    }

    @Override
    public String getAccountTypeDescription() {
        return "A standard bank account.";
    }
}
