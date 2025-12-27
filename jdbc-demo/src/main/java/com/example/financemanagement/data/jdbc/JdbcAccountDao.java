package com.example.financemanagement.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcAccountDao {

    private final Connection connection;

    public JdbcAccountDao(Connection connection) {
        this.connection = connection;
    }

    public void createAccountTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS accounts (id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), type VARCHAR(255), currency VARCHAR(255), balance DOUBLE, createdAt TIMESTAMP);";
        try (var stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void updateBalance(String accountId, double amount, String type) throws SQLException {
        String sql;
        if ("income".equalsIgnoreCase(type)) {
            sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        } else {
            sql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        }
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountId);
            int affected = pstmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Account not found: " + accountId);
            }
        }
    }
}
