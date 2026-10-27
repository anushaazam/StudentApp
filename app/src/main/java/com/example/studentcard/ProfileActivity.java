package com.example.studentcard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // read what Register saved
        SharedPreferences prefs = getSharedPreferences("student_card", MODE_PRIVATE);

        ((TextView) findViewById(R.id.tvName)).setText(": " + prefs.getString("name", "-"));
        ((TextView) findViewById(R.id.tvClass)).setText(": " + prefs.getString("class", "-"));
        ((TextView) findViewById(R.id.tvRegNo)).setText(": " + prefs.getString("reg", "-"));
        ((TextView) findViewById(R.id.tvCnic)).setText(": " + prefs.getString("cnic", "-"));
    }
}