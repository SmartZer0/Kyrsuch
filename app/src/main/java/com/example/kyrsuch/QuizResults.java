package com.example.kyrsuch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class QuizResults extends AppCompatActivity {

    Button endup;
    TextView result;
    Integer value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_results);

        endup = findViewById(R.id.endup);
        result = findViewById(R.id.result);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getInt("result");
        }
        result.setText("Ваш результат: " + value.toString() + "/10");
        endup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizResults.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}