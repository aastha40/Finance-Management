package com.example.financemanagement.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.financemanagement.model.Account;
import com.example.financemanagement.repository.AccountRepository;

import java.util.List;

public class AccountViewModel extends ViewModel {

    private final AccountRepository accountRepository;

    public AccountViewModel() {
        this.accountRepository = new AccountRepository();
    }

    public LiveData<List<Account>> getAccounts() {
        return accountRepository.getAccounts();
    }
}
