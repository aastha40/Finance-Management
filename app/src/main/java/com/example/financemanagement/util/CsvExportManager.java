package com.example.financemanagement.util;

import com.example.financemanagement.model.Transaction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class CsvExportManager {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final ReentrantLock lock = new ReentrantLock();

    public void exportTransactionsToCsv(List<Transaction> transactions) {
        executorService.submit(() -> {
            lock.lock();
            try {
                // Simulate a long-running CSV export process
                Thread.sleep(2000);
                System.out.println("Exporting transactions to CSV...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
    }
}
