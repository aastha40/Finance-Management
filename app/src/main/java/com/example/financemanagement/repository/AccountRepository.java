package com.example.financemanagement.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.financemanagement.model.Account;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.List;

public class AccountRepository {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    public LiveData<List<Account>> getAccounts() {
        MutableLiveData<List<Account>> accounts = new MutableLiveData<>();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            // If user is not logged in, return an empty list.
            accounts.setValue(Collections.emptyList());
            return accounts;
        }

        String userId = currentUser.getUid();
        db.collection("users").document(userId).collection("accounts")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        accounts.setValue(Collections.emptyList());
                        return;
                    }

                    if (value != null) {
                        accounts.setValue(value.toObjects(Account.class));
                    }
                });

        return accounts;
    }
}
