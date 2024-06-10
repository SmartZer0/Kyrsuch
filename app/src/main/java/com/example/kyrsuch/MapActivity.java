package com.example.kyrsuch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.kyrsuch.models.His;
import com.example.kyrsuch.models.Info;
import com.example.kyrsuch.models.Math;
import com.example.kyrsuch.models.Sport;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;
import java.util.Set;

public class MapActivity extends AppCompatActivity {
    Button back, addQuest, exit;
    TextView name, email;
    ConstraintLayout root;

    String email2;

    // Множество email-адресов пользователей, которые могут добавлять вопросы
    private final Set<String> allowedEmails = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        root = findViewById(R.id.root);

        addQuest = (Button) findViewById(R.id.addQuest);
        addQuest.setVisibility(View.GONE);  // Скрыть кнопку по умолчанию

        // Добавление разрешенных email-адресов
        allowedEmails.add("admin@gmail.com");
        allowedEmails.add("Vit@gmail.com");
        allowedEmails.add("Kol@gmail.com");
        allowedEmails.add("Vitaly@gmail.com");
        allowedEmails.add("cherryllz@yandex.ru");

        // Кнопка возвращения на предыдущую страницу
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Кнопка выхода из аккаунта
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogOutWindow();
            }
        });

        // Установление имени и почты пользователя в профиле
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = db.child("Users").child(uid);
        uidRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    email2 = snapshot.child("email").getValue(String.class);
                    String name2 = snapshot.child("name").getValue(String.class);
                    email.setText("Адрес почты: " + email2);
                    // Проверка, если email пользователя в списке
                    if (allowedEmails.contains(email2)) {
                        addQuest.setVisibility(View.VISIBLE);
                        addQuest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showAddQuestWindow();
                            }
                        });
                    }
                    name.setText("Имя: " + name2);
                }
            }
        });
    }


    //Выход из аккаунта
    private void showLogOutWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Выход");
        dialog.setMessage("Вы уверены что хотите выйти?");

        LayoutInflater inflater = LayoutInflater.from(this);
        View log_out_window = inflater.inflate(R.layout.log_out, null);
        dialog.setView(log_out_window);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Выйти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Intent ex = new Intent(MapActivity.this, LoginActivity.class);
                startActivity(ex);
            }
        });

        dialog.show();
    }

    //Добавление вопросов в банк
    private void showAddQuestWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Добавить вопрос");
        dialog.setMessage("Введите все данные для вопроса");

        LayoutInflater inflater = LayoutInflater.from(this);
        View adding_window = inflater.inflate(R.layout.adding, null);
        dialog.setView(adding_window);

        final EditText lesson = adding_window.findViewById(R.id.lesson);
        final EditText quest = adding_window.findViewById(R.id.quest);
        final EditText corrAns = adding_window.findViewById(R.id.corrAns);
        final EditText incor1 = adding_window.findViewById(R.id.incor1);
        final EditText incor2 = adding_window.findViewById(R.id.incor2);
        final EditText incor3 = adding_window.findViewById(R.id.incor3);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                //Проверка вопроса
                if(TextUtils.isEmpty(quest.getText().toString())){
                    Snackbar.make(root, "Введите вопрос", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                //Проверка правильного ответа
                if(TextUtils.isEmpty(corrAns.getText().toString())){
                    Snackbar.make(root, "Введите правильный ответ", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                //Проверка неправильного ответа
                if(TextUtils.isEmpty(incor1.getText().toString())){
                    Snackbar.make(root, "Введите неправильный ответ", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(incor2.getText().toString())){
                    Snackbar.make(root, "Введите неправильный ответ", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(incor3.getText().toString())){
                    Snackbar.make(root, "Введите неправильный ответ", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                //Регистрация вопроса
                String math = "Математика";
                String info = "Информатика";
                String sport = "Физкультура";
                String his = "История";
                lesson.getText().toString();

                //Математика
                if (lesson.getText().toString().equals(math)){
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference myRef  = db.getReference("Math");
                    String ques = quest.getText().toString();
                    String corr = corrAns.getText().toString();
                    String icor1 = incor1.getText().toString();
                    String icor2 = incor2.getText().toString();
                    String icor3 = incor3.getText().toString();
                    Math newMath = new Math(ques, corr, icor1, icor2, icor3);
                    myRef.push().setValue(newMath);
                }
                //Информатика
                if (lesson.getText().toString().equals(info)){
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference myRef  = db.getReference("Info");
                    String ques = quest.getText().toString();
                    String corr = corrAns.getText().toString();
                    String icor1 = incor1.getText().toString();
                    String icor2 = incor2.getText().toString();
                    String icor3 = incor3.getText().toString();
                    Info newInfo = new Info(ques, corr, icor1, icor2, icor3);
                    myRef.push().setValue(newInfo);
                }
                //Физкультура
                if (lesson.getText().toString().equals(sport)){
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference myRef  = db.getReference("Sport");
                    String ques = quest.getText().toString();
                    String corr = corrAns.getText().toString();
                    String icor1 = incor1.getText().toString();
                    String icor2 = incor2.getText().toString();
                    String icor3 = incor3.getText().toString();
                    Sport newSport = new Sport(ques, corr, icor1, icor2, icor3);
                    myRef.push().setValue(newSport);
                }
                //История
                if (lesson.getText().toString().equals(his)){
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference myRef  = db.getReference("His");
                    String ques = quest.getText().toString();
                    String corr = corrAns.getText().toString();
                    String icor1 = incor1.getText().toString();
                    String icor2 = incor2.getText().toString();
                    String icor3 = incor3.getText().toString();
                    His newHis = new His(ques, corr, icor1, icor2, icor3);
                    myRef.push().setValue(newHis);
                }
            }
        });
        dialog.show();
    }
}
