package com.example.financemanagement.model;

import java.util.Date;

public class RecurringRule {
    private String frequency;
    private int interval;
    private Date nextRunDate;

    public RecurringRule() {}

    public RecurringRule(String frequency, int interval, Date nextRunDate) {
        this.frequency = frequency;
        this.interval = interval;
        this.nextRunDate = nextRunDate;
    }

    // Getters and Setters
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public int getInterval() { return interval; }
    public void setInterval(int interval) { this.interval = interval; }
    public Date getNextRunDate() { return nextRunDate; }
    public void setNextRunDate(Date nextRunDate) { this.nextRunDate = nextRunDate; }
}
