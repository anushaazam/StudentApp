package com.example.studentcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EditText etEmail = findViewById(R.id.etForgotEmail);
        Button btnVerify = findViewById(R.id.btnVerify);
        Button btnBack = findViewById(R.id.btnForgotBack);

        btnVerify.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            SharedPreferences prefs = getSharedPreferences("student_card", MODE_PRIVATE);
            String savedEmail = prefs.getString("email", "");

            if (!savedEmail.isEmpty() && email.equalsIgnoreCase(savedEmail)) {
                // send the email to the Reset screen (Intent with extra data)
                Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "No account found with this email", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}