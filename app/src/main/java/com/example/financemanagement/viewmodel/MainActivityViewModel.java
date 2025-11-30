package com.example.financemanagement.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.financemanagement.repository.MainRepository;

public class MainActivityViewModel extends ViewModel {

    private final MainRepository mainRepository;

    public MainActivityViewModel() {
        this.mainRepository = new MainRepository();
    }

    public LiveData<Double> getTotalBalance() {
        return mainRepository.getTotalBalance();
    }
}
