package com.example.financemanagement.data.firestore;

import com.example.financemanagement.model.Transaction;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreTransactionDao {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void addTransaction(Transaction transaction) {
        db.collection("transactions").document(transaction.getId()).set(transaction);
    }
}
