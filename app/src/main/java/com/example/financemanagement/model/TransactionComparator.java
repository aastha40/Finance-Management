package com.example.financemanagement.model;

import java.util.Comparator;

public class TransactionComparator<T extends Transaction> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return o1.getDateTime().compareTo(o2.getDateTime());
    }
}
