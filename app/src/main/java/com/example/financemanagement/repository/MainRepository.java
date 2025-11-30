package com.example.financemanagement.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.financemanagement.model.Account;

import java.util.List;

public class MainRepository {

    private final AccountRepository accountRepository;

    public MainRepository() {
        this.accountRepository = new AccountRepository();
    }

    public LiveData<Double> getTotalBalance() {
        MediatorLiveData<Double> totalBalance = new MediatorLiveData<>();

        totalBalance.addSource(accountRepository.getAccounts(), accounts -> {
            double sum = 0;
            if (accounts != null) {
                for (Account account : accounts) {
                    sum += account.getBalance();
                }
            }
            totalBalance.setValue(sum);
        });

        return totalBalance;
    }
}
