package com.example.financemanagement;

import com.example.financemanagement.data.jdbc.JdbcAccountDao;
import com.example.financemanagement.data.jdbc.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            JdbcAccountDao accountDao = new JdbcAccountDao(connection);
            accountDao.createAccountTable();
            System.out.println("Database and tables created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
