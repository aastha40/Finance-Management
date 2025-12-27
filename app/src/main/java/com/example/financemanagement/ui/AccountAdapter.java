package com.example.financemanagement.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financemanagement.R;
import com.example.financemanagement.model.Account;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<Account> accounts;

    public AccountAdapter(List<Account> accounts) {
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = accounts.get(position);
        holder.name.setText(account.getName());
        holder.type.setText(account.getType() != null ? account.getType() : "Account");
        
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        holder.balance.setText(format.format(account.getBalance()));
    }

    @Override
    public int getItemCount() {
        return accounts != null ? accounts.size() : 0;
    }

    static class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        TextView balance;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.account_name);
            type = itemView.findViewById(R.id.account_type);
            balance = itemView.findViewById(R.id.account_balance);
        }
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        notifyDataSetChanged();
    }
}
