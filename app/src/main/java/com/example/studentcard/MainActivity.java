package com.example.studentcard;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tap "Student Card" -> open ProfileActivity (this is an Intent)
        findViewById(R.id.cardProfile).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ProfileActivity.class)));

        // The other tiles just show a message for now
        View.OnClickListener soon = v ->
                Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
        findViewById(R.id.cardCourses).setOnClickListener(soon);
        findViewById(R.id.cardAttendance).setOnClickListener(soon);
        findViewById(R.id.cardResults).setOnClickListener(soon);
    }
}