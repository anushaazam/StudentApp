package com.example.studentcard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etName = findViewById(R.id.etName);
        EditText etReg = findViewById(R.id.etReg);
        Spinner spClass = findViewById(R.id.spClass);
        EditText etCnic = findViewById(R.id.etCnic);
        EditText etEmail = findViewById(R.id.etRegEmail);
        EditText etPass = findViewById(R.id.etRegPass);
        EditText etConfirm = findViewById(R.id.etRegConfirm);
        RadioGroup rgGender = findViewById(R.id.rgGender);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvBackLogin = findViewById(R.id.tvBackLogin);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String reg = etReg.getText().toString().trim();
            String cls = spClass.getSelectedItem().toString();
            String cnic = etCnic.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPass.getText().toString();
            String confirm = etConfirm.getText().toString();

            if (name.isEmpty() || reg.isEmpty() || cnic.isEmpty() || email.isEmpty()
                    || pass.isEmpty() || rgGender.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (cnic.length() != 13) {
                Toast.makeText(this, "CNIC must be 13 digits", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show();
            } else if (pass.length() < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                // show CNIC like 12345-1234567-1
                String cnicFormatted = cnic.substring(0, 5) + "-" + cnic.substring(5, 12)
                        + "-" + cnic.substring(12);

                // save everything on the phone
                SharedPreferences.Editor editor =
                        getSharedPreferences("student_card", MODE_PRIVATE).edit();
                editor.putString("name", name);
                editor.putString("reg", reg);
                editor.putString("class", cls);
                editor.putString("cnic", cnicFormatted);
                editor.putString("email", email);
                editor.putString("pass", pass);
                editor.apply();

                Toast.makeText(this, "Registered! Please login", Toast.LENGTH_SHORT).show();
                finish(); // go back to Login
            }
        });

        tvBackLogin.setOnClickListener(v -> finish());
    }
}