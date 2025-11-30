package com.example.financemanagement.model;

import java.util.Date;

public class Budget {
    private String id;
    private String categoryId;
    private double amount;
    private Date periodStart;
    private Date periodEnd;

    public Budget() {}

    public Budget(String id, String categoryId, double amount, Date periodStart, Date periodEnd) {
        this.id = id;
        this.categoryId = categoryId;
        this.amount = amount;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Date getPeriodStart() { return periodStart; }
    public void setPeriodStart(Date periodStart) { this.periodStart = periodStart; }
    public Date getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(Date periodEnd) { this.periodEnd = periodEnd; }
}
