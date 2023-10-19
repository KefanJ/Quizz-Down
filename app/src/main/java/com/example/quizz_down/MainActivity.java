package com.example.quizz_down;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;

public class MainActivity extends AppCompatActivity  {

    Button btn_choice_1,btn_choice_2,btn_next ;
    TextView tv_question;
    private Question question = new Question();

    // le flags commence à 1 et non 0 pour éviter que la question 1 s'afffiche encore quand on appuie sur next
    int flags = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_choice_1 = findViewById(R.id.btn_choice_1);
        btn_choice_2 = findViewById(R.id.btn_choice_2);
        btn_next = findViewById(R.id.btn_next);
        tv_question = findViewById(R.id.tv_question);

        // on met -1 pour que lorsque l'application se lance c'est la première question qui s'affiche et non la 2e
        tv_question.setText(question.questions[flags-1]);
        btn_choice_1.setText(question.getchoice1(flags-1));
        btn_choice_2.setText(question.getchoice2(flags-1));

        btn_next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                tv_question.setText(question.questions[flags]);
                btn_choice_1.setText(question.getchoice1(flags));
                btn_choice_2.setText(question.getchoice2(flags));
            }
        });

    }


}