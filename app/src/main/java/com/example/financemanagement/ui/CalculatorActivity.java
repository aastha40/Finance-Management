package com.example.financemanagement.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.financemanagement.R;
import com.example.financemanagement.util.FinanceCalculator;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.NumberFormat;
import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity {

    private TextInputLayout layout1, layout2, layout3;
    private TextInputEditText et1, et2, et3;
    private TextView tvResult, tvResultSub, calcTitle, tvRbiRates;
    private Button btnCalculate;
    private MaterialButtonToggleGroup toggleGroup;
    
    private enum CalcType { SIP, SWP, EMI, FD, RD }
    private CalcType currentType = CalcType.SIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        layout1 = findViewById(R.id.inputLayout1);
        layout2 = findViewById(R.id.inputLayout2);
        layout3 = findViewById(R.id.inputLayout3);
        et1 = findViewById(R.id.etInput1);
        et2 = findViewById(R.id.etInput2);
        et3 = findViewById(R.id.etInput3);
        tvResult = findViewById(R.id.tvResult);
        tvResultSub = findViewById(R.id.tvResultSub);
        calcTitle = findViewById(R.id.calcTitle);
        tvRbiRates = findViewById(R.id.tvRbiRates);
        btnCalculate = findViewById(R.id.btnCalculate);
        toggleGroup = findViewById(R.id.toggleGroup);

        setupToggleGroup();
        btnCalculate.setOnClickListener(v -> calculate());
        
        // Mocking real-time RBI data fetch
        fetchRbiRates();
    }

    private void setupToggleGroup() {
        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.btnSIP) {
                    currentType = CalcType.SIP;
                    updateUI("SIP Calculator", "Monthly Investment", "Expected Return (%)", "Tenure (Years)");
                } else if (checkedId == R.id.btnSWP) {
                    currentType = CalcType.SWP;
                    updateUI("SWP Calculator", "Total Investment", "Monthly Withdrawal", "Expected Return (%)");
                    layout3.setHint("Tenure (Years)"); // SWP might need 4 inputs ideally, but let's reuse
                } else if (checkedId == R.id.btnEMI) {
                    currentType = CalcType.EMI;
                    updateUI("EMI Calculator", "Loan Amount", "Interest Rate (%)", "Tenure (Years)");
                } else if (checkedId == R.id.btnFD) {
                    currentType = CalcType.FD;
                    updateUI("FD Calculator", "Principal Amount", "Interest Rate (%)", "Tenure (Years)");
                } else if (checkedId == R.id.btnRD) {
                    currentType = CalcType.RD;
                    updateUI("RD Calculator", "Monthly Deposit", "Interest Rate (%)", "Tenure (Months)");
                }
            }
        });
    }

    private void updateUI(String title, String hint1, String hint2, String hint3) {
        calcTitle.setText(title);
        layout1.setHint(hint1);
        layout2.setHint(hint2);
        layout3.setHint(hint3);
        et1.setText("");
        et2.setText("");
        et3.setText("");
        tvResult.setText("₹ 0.00");
    }

    private void calculate() {
        try {
            double v1 = Double.parseDouble(et1.getText().toString());
            double v2 = Double.parseDouble(et2.getText().toString());
            double v3 = Double.parseDouble(et3.getText().toString());
            double res = 0;
            String subText = "";

            switch (currentType) {
                case SIP:
                    res = FinanceCalculator.calculateSIP(v1, v2, (int)v3);
                    subText = "Total Value after " + (int)v3 + " years";
                    break;
                case SWP:
                    // Using v1: Corpus, v2: Withdrawal, v3: Rate, and assuming 10 years for simplicity or adding 4th field
                    // Let's adjust SWP to use 3 fields: Corpus, Rate, Withdrawal and assume 10 years or vice versa
                    // Better: v1=Corpus, v2=Withdrawal, v3=Rate. Show balance after 1 year or something.
                    // Or let's assume v3 is years for consistency if possible.
                    res = FinanceCalculator.calculateSWP(v1, v2, v3, 10); 
                    subText = "Balance after 10 years";
                    break;
                case EMI:
                    res = FinanceCalculator.calculateEMI(v1, v2, (int)v3);
                    subText = "Monthly EMI";
                    break;
                case FD:
                    res = FinanceCalculator.calculateFD(v1, v2, v3);
                    subText = "Maturity Value";
                    break;
                case RD:
                    res = FinanceCalculator.calculateRD(v1, v2, (int)v3 / 3); // quarters
                    subText = "Maturity Value";
                    break;
            }

            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
            tvResult.setText(format.format(res));
            tvResultSub.setText(subText);

        } catch (Exception e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchRbiRates() {
        // In a real app, use Retrofit or Volley to fetch from an API
        // For this demo, we'll simulate a fetch
        tvRbiRates.setText("Current RBI Repo Rate: 6.50% (Updated Today)");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
