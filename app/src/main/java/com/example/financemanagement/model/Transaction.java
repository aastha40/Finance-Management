package com.example.financemanagement.model;

import java.util.Date;
import java.util.List;

public class Transaction {
    private String id;
    private String accountId;
    private String categoryId;
    private String type;
    private double amount;
    private String currency;
    private Date dateTime;
    private String note;
    private List<String> tags;
    private boolean isRecurring;
    private String recurrenceRule;

    public Transaction() {}

    public Transaction(String id, String accountId, String categoryId, String type, double amount, String currency, Date dateTime, String note, List<String> tags, boolean isRecurring, String recurrenceRule) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.dateTime = dateTime;
        this.note = note;
        this.tags = tags;
        this.isRecurring = isRecurring;
        this.recurrenceRule = recurrenceRule;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public Date getDateTime() { return dateTime; }
    public void setDateTime(Date dateTime) { this.dateTime = dateTime; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public boolean isRecurring() { return isRecurring; }
    public void setRecurring(boolean recurring) { isRecurring = recurring; }
    public String getRecurrenceRule() { return recurrenceRule; }
    public void setRecurrenceRule(String recurrenceRule) { this.recurrenceRule = recurrenceRule; }
}
