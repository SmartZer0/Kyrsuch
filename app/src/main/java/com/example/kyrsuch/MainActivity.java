package com.example.kyrsuch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String selectedTopic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout math = findViewById(R.id.mathLayout);
        final LinearLayout history = findViewById(R.id.historyLayout);
        final LinearLayout it = findViewById(R.id.itLayout);
        final LinearLayout sport = findViewById(R.id.sportLayout);

        final Button startQuizBtn = findViewById(R.id.startQuizBtn);

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "math";
                math.setBackgroundResource(R.drawable.round_back_white_stroke10);
                history.setBackgroundResource(R.drawable.round_back_white10);
                it.setBackgroundResource(R.drawable.round_back_white10);
                sport.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "history";
                history.setBackgroundResource(R.drawable.round_back_white_stroke10);
                math.setBackgroundResource(R.drawable.round_back_white10);
                it.setBackgroundResource(R.drawable.round_back_white10);
                sport.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "it";
                it.setBackgroundResource(R.drawable.round_back_white_stroke10);
                history.setBackgroundResource(R.drawable.round_back_white10);
                math.setBackgroundResource(R.drawable.round_back_white10);
                sport.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "sport";
                sport.setBackgroundResource(R.drawable.round_back_white_stroke10);
                history.setBackgroundResource(R.drawable.round_back_white10);
                it.setBackgroundResource(R.drawable.round_back_white10);
                math.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        startQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedTopic.isEmpty()){
                    Toast.makeText(MainActivity.this,"Выбертье категорию", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("selectedTopic", selectedTopic);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}