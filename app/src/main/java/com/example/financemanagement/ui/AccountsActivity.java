package com.example.financemanagement.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financemanagement.R;
import com.example.financemanagement.viewmodel.AccountViewModel;

import java.util.ArrayList;

public class AccountsActivity extends AppCompatActivity {

    private AccountViewModel viewModel;
    private RecyclerView recyclerView;
    private AccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.accounts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AccountAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        viewModel.getAccounts().observe(this, accounts -> {
            adapter.setAccounts(accounts != null ? accounts : new ArrayList<>());
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
