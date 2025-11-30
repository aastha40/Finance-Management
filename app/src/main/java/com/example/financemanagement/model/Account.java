package com.example.financemanagement.model;

import java.util.Date;

public abstract class Account {
    protected String id;
    protected String name;
    protected String type;
    protected String currency;
    protected double balance;
    protected Date createdAt;

    public Account() {}

    public Account(String id, String name, String type, String currency, double balance, Date createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public abstract String getAccountTypeDescription();
}
