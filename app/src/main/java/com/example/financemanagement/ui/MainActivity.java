package com.example.financemanagement.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.financemanagement.R;
import com.example.financemanagement.viewmodel.MainActivityViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private TextView totalBalanceValue, tvUserName, tvUserEmail;
    private MaterialCardView cardAccounts, cardCalculators, cardTaxation;
    private ExtendedFloatingActionButton fab;
    private PieChart pieChart;
    private View profileSection;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        totalBalanceValue = findViewById(R.id.total_balance_value);
        tvUserName = findViewById(R.id.user_name);
        tvUserEmail = findViewById(R.id.user_email);
        profileSection = findViewById(R.id.profile_section);
        cardAccounts = findViewById(R.id.card_accounts);
        cardCalculators = findViewById(R.id.card_calculators);
        cardTaxation = findViewById(R.id.card_taxation);
        fab = findViewById(R.id.fab);
        pieChart = findViewById(R.id.financial_pie_chart);

        if (user != null) {
            tvUserName.setText(user.getDisplayName() != null ? user.getDisplayName() : "User");
            tvUserEmail.setText(user.getEmail());
        }

        profileSection.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));

        setupPieChart();

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getTotalBalance().observe(this, balance -> {
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
            totalBalanceValue.setText(format.format(balance != null ? balance : 0.0));
            updatePieChart(balance != null ? balance.floatValue() : 0f);
        });

        cardAccounts.setOnClickListener(v -> startActivity(new Intent(this, AccountsActivity.class)));
        cardCalculators.setOnClickListener(v -> startActivity(new Intent(this, CalculatorActivity.class)));
        cardTaxation.setOnClickListener(v -> startActivity(new Intent(this, TaxationActivity.class)));
        
        fab.setOnClickListener(v -> startActivity(new Intent(this, AddTransactionActivity.class)));
    }

    private void setupPieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setCenterText("Portfolio");
        pieChart.setCenterTextSize(18f);
    }

    private void updatePieChart(float balance) {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(balance * 0.6f, "Savings"));
        entries.add(new PieEntry(balance * 0.3f, "Investments"));
        entries.add(new PieEntry(balance * 0.1f, "Cash"));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        
        int[] appleColors = {
            Color.parseColor("#0071E3"),
            Color.parseColor("#34C759"),
            Color.parseColor("#FF9500"),
            Color.parseColor("#AF52DE")
        };
        dataSet.setColors(appleColors);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);
        pieChart.invalidate();
    }
}
