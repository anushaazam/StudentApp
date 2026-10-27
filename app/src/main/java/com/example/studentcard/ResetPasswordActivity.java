package com.example.studentcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        TextView tvEmail = findViewById(R.id.tvResetEmail);
        EditText etNew = findViewById(R.id.etNewPassword);
        EditText etConfirm = findViewById(R.id.etConfirmNew);
        Button btnSave = findViewById(R.id.btnSavePassword);

        // read the email sent by the Forgot screen
        String email = getIntent().getStringExtra("email");
        tvEmail.setText(email);

        btnSave.setOnClickListener(v -> {
            String p1 = etNew.getText().toString();
            String p2 = etConfirm.getText().toString();

            if (p1.length() < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            } else if (!p1.equals(p2)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor =
                        getSharedPreferences("student_card", MODE_PRIVATE).edit();
                editor.putString("pass", p1);
                editor.apply();

                Toast.makeText(this, "Password updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}