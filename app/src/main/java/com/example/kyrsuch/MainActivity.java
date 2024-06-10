package com.example.kyrsuch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private String selected = "";
    ImageView profile, info;
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        root = findViewById(R.id.main);
        profile = findViewById(R.id.Prof);
        info = findViewById(R.id.Info);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProf = new Intent(MainActivity.this, MapActivity.class);
                startActivity(toProf);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProf = new Intent(MainActivity.this, InformationActivity.class);
                startActivity(toProf);
            }
        });

        final LinearLayout math = findViewById(R.id.MathLayout);
        final LinearLayout inf = findViewById(R.id.InfLayout);
        final LinearLayout his = findViewById(R.id.HisLayout);
        final LinearLayout sport = findViewById(R.id.SportLayout);

        final Button start = findViewById(R.id.startQuizBtn);

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "math";
                math.setBackgroundResource(R.drawable.round_back_white_stroke10);
                inf.setBackgroundResource(R.drawable.round_back_white10);
                his.setBackgroundResource(R.drawable.round_back_white10);
                sport.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "inf";
                inf.setBackgroundResource(R.drawable.round_back_white_stroke10);
                math.setBackgroundResource(R.drawable.round_back_white10);
                his.setBackgroundResource(R.drawable.round_back_white10);
                sport.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "his";
                his.setBackgroundResource(R.drawable.round_back_white_stroke10);
                inf.setBackgroundResource(R.drawable.round_back_white10);
                math.setBackgroundResource(R.drawable.round_back_white10);
                sport.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "sport";
                sport.setBackgroundResource(R.drawable.round_back_white_stroke10);
                inf.setBackgroundResource(R.drawable.round_back_white10);
                his.setBackgroundResource(R.drawable.round_back_white10);
                math.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected.isEmpty()) {
                    Snackbar.make(root, "Выберите предмет!", Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("selected", selected);
                    startActivity(intent);
                    finish();
                }
            }
        });
        scheduleMotivationalNotification();
    }

    private void scheduleMotivationalNotification() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendMotivationalNotification();
            }
        }, 60000); // 60000 ms = 1 minute
    }

    private void sendMotivationalNotification() {
        String message = MotivationalMessages.getRandomMessage();
        NotificationHelper.sendTestNotification(this, "Мотивация", message);
    }
}
