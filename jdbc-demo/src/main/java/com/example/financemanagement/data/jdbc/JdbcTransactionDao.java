package com.example.financemanagement.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcTransactionDao {

    private final Connection connection;

    public JdbcTransactionDao(Connection connection) {
        this.connection = connection;
    }

    public void createTransactionTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS transactions (id VARCHAR(255) PRIMARY KEY, accountId VARCHAR(255), categoryId VARCHAR(255), type VARCHAR(255), amount DOUBLE, currency VARCHAR(255), dateTime TIMESTAMP, note TEXT, tags TEXT, isRecurring BOOLEAN, recurrenceRule TEXT);";
        try (var stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insertTransaction(String id, String accountId, String type, double amount, String currency, long timestamp, String note) throws SQLException {
        String sql = "INSERT INTO transactions (id, accountId, type, amount, currency, dateTime, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, accountId);
            pstmt.setString(3, type);
            pstmt.setDouble(4, amount);
            pstmt.setString(5, currency);
            pstmt.setTimestamp(6, new Timestamp(timestamp));
            pstmt.setString(7, note);
            pstmt.executeUpdate();
        }
    }
}
