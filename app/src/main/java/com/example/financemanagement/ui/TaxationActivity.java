package com.example.financemanagement.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.financemanagement.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;
import java.util.Locale;

public class TaxationActivity extends AppCompatActivity {

    private TextInputEditText etAnnualIncome;
    private TextView tvTaxAmount;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etAnnualIncome = findViewById(R.id.etAnnualIncome);
        tvTaxAmount = findViewById(R.id.tvTaxAmount);
        btnCalculate = findViewById(R.id.btnCalculateTax);

        btnCalculate.setOnClickListener(v -> calculateTax());
    }

    private void calculateTax() {
        String incomeStr = etAnnualIncome.getText().toString();
        if (incomeStr.isEmpty()) {
            Toast.makeText(this, "Please enter income", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double income = Double.parseDouble(incomeStr);
            double tax = 0;

            // Latest Indian Income Tax Rules (New Regime FY 2024-25 / AY 2025-26)
            // Rebate under Sec 87A: No tax if total income does not exceed ₹7,00,000
            if (income <= 700000) {
                tax = 0;
            } else {
                // Slabs:
                // 0 - 3L: Nil
                // 3L - 6L: 5%
                // 6L - 9L: 10%
                // 9L - 12L: 15%
                // 12L - 15L: 20%
                // Above 15L: 30%

                if (income > 1500000) {
                    tax += (income - 1500000) * 0.30;
                    income = 1500000;
                }
                if (income > 1200000) {
                    tax += (income - 1200000) * 0.20;
                    income = 1200000;
                }
                if (income > 900000) {
                    tax += (income - 900000) * 0.15;
                    income = 900000;
                }
                if (income > 600000) {
                    tax += (income - 600000) * 0.10;
                    income = 600000;
                }
                if (income > 300000) {
                    tax += (income - 300000) * 0.05;
                }
            }

            // Add 4% Health & Education Cess
            double totalTax = tax + (tax * 0.04);

            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
            tvTaxAmount.setText(format.format(totalTax));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid income amount", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
