package com.example.financemanagement.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.financemanagement.R;
import com.example.financemanagement.model.Transaction;
import com.example.financemanagement.repository.TransactionRepository;

import java.util.Date;
import java.util.UUID;

public class AddTransactionActivity extends AppCompatActivity {

    private EditText amountEditText;
    private EditText noteEditText;
    private Button saveButton;

    private TransactionRepository transactionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        transactionRepository = new TransactionRepository();

        amountEditText = findViewById(R.id.edit_text_amount);
        noteEditText = findViewById(R.id.edit_text_note);
        saveButton = findViewById(R.id.button_save_transaction);

        saveButton.setOnClickListener(v -> {
            saveTransaction();
        });
    }

    private void saveTransaction() {
        String amountString = amountEditText.getText().toString();
        String note = noteEditText.getText().toString();

        if (amountString.isEmpty()) {
            amountEditText.setError("Amount cannot be empty");
            return;
        }

        double amount = Double.parseDouble(amountString);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                null, // TODO: Add account selection
                null, // TODO: Add category selection
                "expense", // TODO: Add type selection
                amount,
                "INR",
                new Date(),
                note,
                null,
                false,
                null
        );

        transactionRepository.addTransaction(transaction);
        finish(); // Close the activity after saving
    }
}
