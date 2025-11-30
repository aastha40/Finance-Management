package com.example.financemanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.financemanagement.R;
import com.example.financemanagement.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private TextView totalBalanceValue;
    private Button viewAccountsButton;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        totalBalanceValue = findViewById(R.id.total_balance_value);
        viewAccountsButton = findViewById(R.id.view_accounts_button);
        fab = findViewById(R.id.fab);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getTotalBalance().observe(this, balance -> {
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
            totalBalanceValue.setText(format.format(balance));
        });

        viewAccountsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AccountsActivity.class);
            startActivity(intent);
        });

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
            startActivity(intent);
        });
    }
}
