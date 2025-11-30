package com.example.financemanagement.repository;

import com.example.financemanagement.model.Transaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class TransactionRepository {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    public void addTransaction(Transaction transaction) {
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            // If user is not logged in, do not attempt to add the transaction.
            return;
        }

        String userId = currentUser.getUid();
        db.collection("users").document(userId).collection("transactions")
                .document(transaction.getId()).set(transaction);
    }
}
