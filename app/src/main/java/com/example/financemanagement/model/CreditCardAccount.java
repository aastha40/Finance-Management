package com.example.financemanagement.model;

import java.util.Date;

public class CreditCardAccount extends Account {

    public CreditCardAccount() {
        super();
        this.type = "Credit Card Account";
    }

    public CreditCardAccount(String id, String name, String currency, double balance, Date createdAt) {
        super(id, name, "Credit Card Account", currency, balance, createdAt);
    }

    @Override
    public String getAccountTypeDescription() {
        return "A credit card account.";
    }
}
