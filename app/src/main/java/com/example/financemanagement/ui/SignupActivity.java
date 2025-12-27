package com.example.financemanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.financemanagement.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText etName, etEmail, etPassword;
    private Button btnSignup;
    private TextView tvLogin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        
        etName = findViewById(R.id.et_name_up);
        etEmail = findViewById(R.id.et_email_up);
        etPassword = findViewById(R.id.et_password_up);
        btnSignup = findViewById(R.id.btn_signup);
        tvLogin = findViewById(R.id.tv_goto_login);
        progressBar = findViewById(R.id.signup_progress_bar);

        btnSignup.setOnClickListener(v -> signup());
        tvLogin.setOnClickListener(v -> finish());
    }

    private void signup() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        btnSignup.setEnabled(false);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Update profile with name
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();
                        mAuth.getCurrentUser().updateProfile(profileUpdates);
                        
                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        finishAffinity();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        btnSignup.setEnabled(true);
                        Toast.makeText(SignupActivity.this, "Signup failed: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
