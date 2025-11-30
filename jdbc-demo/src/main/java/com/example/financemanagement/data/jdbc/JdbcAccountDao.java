package com.example.financemanagement.data.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcAccountDao {

    private final Connection connection;

    public JdbcAccountDao(Connection connection) {
        this.connection = connection;
    }

    public void createAccountTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS accounts (id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), type VARCHAR(255), currency VARCHAR(255), balance DOUBLE, createdAt DATETIME);";
        try (var stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
}
