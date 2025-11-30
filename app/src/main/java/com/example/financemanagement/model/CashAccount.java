package com.example.financemanagement.model;

import java.util.Date;

public class CashAccount extends Account {

    public CashAccount() {
        super();
        this.type = "Cash Account";
    }

    public CashAccount(String id, String name, String currency, double balance, Date createdAt) {
        super(id, name, "Cash Account", currency, balance, createdAt);
    }

    @Override
    public String getAccountTypeDescription() {
        return "A physical cash account.";
    }
}
