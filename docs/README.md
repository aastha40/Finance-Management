# Finance Management App

This document provides a summary of the project, including build instructions and a rubric-checklist mapping.

## Rubric Checklist

| Rubric Item | Marks | Implemented | Evidence |
|---|---|---|---|
| OOP Implementation | 10 | Yes | `app/src/main/java/com/example/financemanagement/model/Account.java`, `app/src/main/java/com/example/financemanagement/model/BankAccount.java`, `app/src/main/java/com/example/financemanagement/model/TransactionProcessor.java` |
| Collections & Generics | 6 | Yes | `app/src/main/java/com/example/financemanagement/repository/Repository.java`, `app/src/main/java/com/example/financemanagement/model/TransactionComparator.java` |
| Multithreading & Synchronization | 4 | Yes | `app/src/main/java/com/example/financemanagement/util/CsvExportManager.java`, `app/src/main/java/com/example/financemanagement/sync/RecurringTransactionWorker.java` |
| Classes for the database operations | 7 | Yes | `app/src/main/java/com/example/financemanagement/data/firestore/FirestoreTransactionDao.java`, `jdbc-demo/src/main/java/com/example/financemanagement/data/jdbc/JdbcAccountDao.java` |
| Database Connectivity (JDBC) | 3 | Yes | `jdbc-demo/src/main/java/com/example/financemanagement/data/jdbc/DatabaseConnection.java`, `jdbc-demo/src/main/java/com/example/financemanagement/Main.java` |
| Implement JDBC for database connectivity | 3 | Yes | `jdbc-demo/src/main/java/com/example/financemanagement/Main.java` |
