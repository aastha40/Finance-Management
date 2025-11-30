package com.example.financemanagement.data.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTransactionDao {

    private final Connection connection;

    public JdbcTransactionDao(Connection connection) {
        this.connection = connection;
    }

    public void createTransactionTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS transactions (id VARCHAR(255) PRIMARY KEY, accountId VARCHAR(255), categoryId VARCHAR(255), type VARCHAR(255), amount DOUBLE, currency VARCHAR(255), dateTime DATETIME, note TEXT, tags TEXT, isRecurring BOOLEAN, recurrenceRule TEXT);";
        try (var stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
}
