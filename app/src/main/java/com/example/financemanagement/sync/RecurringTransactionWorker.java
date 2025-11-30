package com.example.financemanagement.sync;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RecurringTransactionWorker extends Worker {

    public RecurringTransactionWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Logic to create recurring transactions
        System.out.println("RecurringTransactionWorker is running.");
        return Result.success();
    }
}
