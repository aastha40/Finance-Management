package com.example.financemanagement.service;

import com.example.financemanagement.data.jdbc.DatabaseConnection;
import com.example.financemanagement.data.jdbc.JdbcAccountDao;
import com.example.financemanagement.data.jdbc.JdbcTransactionDao;

import java.sql.Connection;
import java.sql.SQLException;

public class FinanceService {

    /**
     * Records a new transaction and updates the account balance atomically using JDBC transactions.
     */
    public void logTransactionAtomic(String transId, String accountId, String type, double amount, String currency, long timestamp, String note) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            // Start transaction
            conn.setAutoCommit(false);

            JdbcTransactionDao transDao = new JdbcTransactionDao(conn);
            JdbcAccountDao accountDao = new JdbcAccountDao(conn);

            // 1. Insert Transaction Record
            transDao.insertTransaction(transId, accountId, type, amount, currency, timestamp, note);

            // 2. Update Account Balance
            accountDao.updateBalance(accountId, amount, type);

            // Commit if both succeed
            conn.commit();
            System.out.println("Transaction logged and balance updated successfully.");

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.err.println("Transaction failed. Rolling back...");
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Financial Calculation Logic: Example of calculating total net worth
     */
    public double calculateNetWorth() {
        // Implementation would call DAO to get all account balances and sum them
        return 0.0; 
    }
}
