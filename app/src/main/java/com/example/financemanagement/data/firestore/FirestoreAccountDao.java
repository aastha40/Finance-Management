package com.example.financemanagement.data.firestore;

import com.example.financemanagement.model.Account;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreAccountDao {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void addAccount(Account account) {
        db.collection("accounts").document(account.getId()).set(account);
    }
}
