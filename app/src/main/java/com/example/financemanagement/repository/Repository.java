package com.example.financemanagement.repository;

import java.util.List;

public interface Repository<T> {
    void add(T item);
    void update(T item);
    void delete(T item);
    T getById(String id);
    List<T> getAll();
}
