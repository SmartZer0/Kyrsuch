package com.example.kyrsuch;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.kyrsuch.models.His;
import com.example.kyrsuch.models.Info;
import com.example.kyrsuch.models.Math;
import com.example.kyrsuch.models.QuestionsList;
import com.example.kyrsuch.models.Sport;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private TextView questions;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextBtn;

    List<Math> randomQuest;
    List<His> randomQuest1;
    List<Sport> randomQuest2;
    List<Info> randomQuest3;
    private Timer timer;
    private int second = 0;
    private int totalTimeInMins = 15;

    private final List<QuestionsList> questionsList = new ArrayList<>();
    private int currentQuest = 1;
    private int numOfCorr = 0;
    private String selectedOption = "";
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_math);

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView topicName = findViewById(R.id.topicName);


        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        root = findViewById(R.id.root2);

        String math = "math";
        String inf = "inf";
        String his = "his";
        String sport = "sport";

        nextBtn = findViewById(R.id.nextBtn);

        final String getSelected = getIntent().getStringExtra("selected");
        if (getSelected.equals(math)){
            topicName.setText("Математика");
        }else if (getSelected.equals(his)){
            topicName.setText("История");
        }else if (getSelected.equals(sport)){
            topicName.setText("Физкультура");
        }else if (getSelected.equals(inf)){
            topicName.setText("Информатика");
        }
        startTimer(timer);


        FirebaseDatabase db= FirebaseDatabase.getInstance();


        //Кнопка к следующему вопросу
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuest == 10){
                    Intent intent = new Intent(QuizActivity.this, QuizResults.class);
                    intent.putExtra("result", numOfCorr);
                    startActivity(intent);
                    finish();
                }

                if (selectedOption.isEmpty()){
                    Snackbar.make(root, "Выберите вариант ответа!", Snackbar.LENGTH_SHORT).show();
                } else {
                    if (getSelected.equals(math)){
                        changeQuest();
                        questions.setText((currentQuest) + "/" + 10);
                    }else if (getSelected.equals(his)){
                        changeQuest1();
                        questions.setText((currentQuest) + "/" + 10);
                    }else if (getSelected.equals(inf)){
                        changeQuest3();
                        questions.setText((currentQuest) + "/" + 10);
                    }else if (getSelected.equals(sport)){
                        changeQuest2();
                        questions.setText((currentQuest) + "/" + 10);
                    }

                }
            }
        });


        //Математика
        if (getSelected.equals(math)){
            DatabaseReference myRef = db.getReference("Math");
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<Math> mathQuest = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Math math = snapshot.getValue(Math.class);
                        mathQuest.add(math);
                    }

                    Collections.shuffle(mathQuest);
                    randomQuest = mathQuest.subList(0, 11);

                    Math quest = randomQuest.get(currentQuest);
                    question.setText(quest.getText());
                    if (quest != null){
                        List<String> answers = new ArrayList<>(Arrays.asList(quest.getCorrect(),quest.getIncor1(), quest.getIncor2(), quest.getIncor3()));
                        Collections.shuffle(answers);
                        option1.setText(answers.get(0));
                        option2.setText(answers.get(1));
                        option3.setText(answers.get(2));
                        option4.setText(answers.get(3));

                        optionAns(quest.getCorrect());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar.make(root, "Непредвиденная ошибка!", Snackbar.LENGTH_SHORT).show();
                }
            });
        }

        //История
        if (getSelected.equals(his)){
            DatabaseReference myRef = db.getReference("His");
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<His> hisQuest = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        His his = snapshot.getValue(His.class);
                        hisQuest.add(his);
                    }

                    Collections.shuffle(hisQuest);
                    randomQuest1 = hisQuest.subList(0, 11);

                    His quest = randomQuest1.get(currentQuest);
                    question.setText(quest.getText());
                    if (quest != null){
                        List<String> answers = new ArrayList<>(Arrays.asList(quest.getCorrect(),quest.getIncor1(), quest.getIncor2(), quest.getIncor3()));
                        Collections.shuffle(answers);
                        option1.setText(answers.get(0));
                        option2.setText(answers.get(1));
                        option3.setText(answers.get(2));
                        option4.setText(answers.get(3));

                        optionAns(quest.getCorrect());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar.make(root, "Непредвиденная ошибка!", Snackbar.LENGTH_SHORT).show();
                }
            });
        }

        //Информатика
        if (getSelected.equals(inf)){
            DatabaseReference myRef = db.getReference("Info");
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<Info> infoQuest = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Info info = snapshot.getValue(Info.class);
                        infoQuest.add(info);
                    }

                    Collections.shuffle(infoQuest);
                    randomQuest3 = infoQuest.subList(0, 11);

                    Info quest = randomQuest3.get(currentQuest);
                    question.setText(quest.getText());
                    if (quest != null){
                        List<String> answers = new ArrayList<>(Arrays.asList(quest.getCorrect(),quest.getIncor1(), quest.getIncor2(), quest.getIncor3()));
                        Collections.shuffle(answers);
                        option1.setText(answers.get(0));
                        option2.setText(answers.get(1));
                        option3.setText(answers.get(2));
                        option4.setText(answers.get(3));

                        optionAns(quest.getCorrect());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar.make(root, "Непредвиденная ошибка!", Snackbar.LENGTH_SHORT).show();
                }
            });
        }

        //Физкультура
        if (getSelected.equals(sport)){
            DatabaseReference myRef = db.getReference("Sport");
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<Sport> sportQuest = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Sport sport = snapshot.getValue(Sport.class);
                        sportQuest.add(sport);
                    }

                    Collections.shuffle(sportQuest);
                    randomQuest2 = sportQuest.subList(0, 11);

                    Sport quest = randomQuest2.get(currentQuest);
                    question.setText(quest.getText());
                    if (quest != null){
                        List<String> answers = new ArrayList<>(Arrays.asList(quest.getCorrect(),quest.getIncor1(), quest.getIncor2(), quest.getIncor3()));
                        Collections.shuffle(answers);
                        option1.setText(answers.get(0));
                        option2.setText(answers.get(1));
                        option3.setText(answers.get(2));
                        option4.setText(answers.get(3));

                        optionAns(quest.getCorrect());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar.make(root, "Непредвиденная ошибка!", Snackbar.LENGTH_SHORT).show();
                }
            });
        }

        //Кнопка назад
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });


    }


    //Таймер
    private void startTimer (TextView timerTextView){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (second == 0){
                    totalTimeInMins --;
                    second = 59;
                } else if (second == 0 && totalTimeInMins == 0) {
                    timer.purge();
                    timer.cancel();

                    Snackbar.make(root, "Время вышло!", Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(QuizActivity.this, QuizResults.class);
                    intent.putExtra("correct", getCorrect());
                    startActivity(intent);
                    finish();
                } else {
                    second --;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(second);

                        if (finalMinutes.length() == 1){
                            finalMinutes = "0" + finalMinutes;
                        }
                        if (finalSeconds.length() == 1){
                            finalSeconds = "0"+ finalSeconds;
                        }
                        timerTextView.setText(finalMinutes + ":" + finalSeconds);

                    }
                });

            }
        }, 1000, 1000);
    }

    //Получение количества правильных ответов
    private int getCorrect(){

        int correct = 0;

        for (int i = 0; i < questionsList.size(); i++) {
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();

            if (getUserSelectedAnswer.equals(getAnswer)) {
                correct ++;
            }
        }

        return correct;

    }

    //Кнопка назад
    private void back(){
        timer.purge();
        timer.cancel();
        startActivity(new Intent(QuizActivity.this, MainActivity.class));
        finish();
    }

    //Правильный и неправильный ответ
    private void revealAnswer(String Correct){
        if(option1.getText().toString().equals(Correct)){
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        } else if (option2.getText().toString().equals(Correct)) {
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        }else if (option3.getText().toString().equals(Correct)) {
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        }else if (option4.getText().toString().equals(Correct)) {
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }

    }
    private void optionAns (String Corrects){
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()){
                    selectedOption = option1.getText().toString();
                    if (selectedOption.equals(Corrects)){
                        numOfCorr ++;
                    }
                    option1.setBackgroundResource(R.drawable.round_back_red20);
                    option1.setTextColor(Color.WHITE);
                    revealAnswer(Corrects);
                }
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()){
                    selectedOption = option2.getText().toString();
                    if (selectedOption.equals(Corrects)){
                        numOfCorr ++;
                    }
                    option2.setBackgroundResource(R.drawable.round_back_red20);
                    option2.setTextColor(Color.WHITE);
                    revealAnswer(Corrects);
                }
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()){
                    selectedOption = option3.getText().toString();
                    if (selectedOption.equals(Corrects)){
                        numOfCorr ++;
                    }
                    option3.setBackgroundResource(R.drawable.round_back_red20);
                    option3.setTextColor(Color.WHITE);
                    revealAnswer(Corrects);
                }
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption.isEmpty()){
                    selectedOption = option4.getText().toString();
                    if (selectedOption.equals(Corrects)){
                        numOfCorr ++;
                    }
                    option4.setBackgroundResource(R.drawable.round_back_red20);
                    option4.setTextColor(Color.WHITE);
                    revealAnswer(Corrects);
                }
            }
        });
    }


    //Кнопка перехода к следующему вопросу
    private void changeQuest(){
        currentQuest++;

        if ((currentQuest + 1)== 11){
            nextBtn.setText("Закончить");
        }
        if (currentQuest <= 10) {
            Math quest = randomQuest.get(currentQuest);
            question.setText(quest.getText());
            if (quest != null){
                List<String> answers = new ArrayList<>(Arrays.asList(quest.getCorrect(),quest.getIncor1(), quest.getIncor2(), quest.getIncor3()));
                Collections.shuffle(answers);
                option1.setText(answers.get(0));
                option2.setText(answers.get(1));
                option3.setText(answers.get(2));
                option4.setText(answers.get(3));

                optionAns(quest.getCorrect());

            }

            selectedOption = "";
            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#1F6BB8"));
        }
    }
    private void changeQuest1(){
        currentQuest++;

        if ((currentQuest + 1)== 11){
            nextBtn.setText("Закончить");
        }
        if (currentQuest <= 10) {
            His quest = randomQuest1.get(currentQuest);
            question.setText(quest.getText());
            if (quest != null){
                List<String> answers = new ArrayList<>(Arrays.asList(quest.getCorrect(),quest.getIncor1(), quest.getIncor2(), quest.getIncor3()));
                Collections.shuffle(answers);
                option1.setText(answers.get(0));
                option2.setText(answers.get(1));
                option3.setText(answers.get(2));
                option4.setText(answers.get(3));

                optionAns(quest.getCorrect());

            }

            selectedOption = "";
            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#1F6BB8"));
        }
    }
    private void changeQuest2(){
        currentQuest++;

        if ((currentQuest + 1)== 11){
            nextBtn.setText("Закончить");
        }
        if (currentQuest <= 10) {
            Sport quest = randomQuest2.get(currentQuest);
            question.setText(quest.getText());
            if (quest != null){
                List<String> answers = new ArrayList<>(Arrays.asList(quest.getCorrect(),quest.getIncor1(), quest.getIncor2(), quest.getIncor3()));
                Collections.shuffle(answers);
                option1.setText(answers.get(0));
                option2.setText(answers.get(1));
                option3.setText(answers.get(2));
                option4.setText(answers.get(3));

                optionAns(quest.getCorrect());

            }

            selectedOption = "";
            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#1F6BB8"));
        }
    }
    private void changeQuest3(){

        if ((currentQuest + 1)== 11){
            nextBtn.setText("Закончить");
        }

        currentQuest++;

        if (currentQuest <= 10) {
            Info quest = randomQuest3.get(currentQuest);
            question.setText(quest.getText());
            if (quest != null){
                List<String> answers = new ArrayList<>(Arrays.asList(quest.getCorrect(),quest.getIncor1(), quest.getIncor2(), quest.getIncor3()));
                Collections.shuffle(answers);
                option1.setText(answers.get(0));
                option2.setText(answers.get(1));
                option3.setText(answers.get(2));
                option4.setText(answers.get(3));

                optionAns(quest.getCorrect());

            }

            selectedOption = "";
            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#1F6BB8"));
        }
    }
}