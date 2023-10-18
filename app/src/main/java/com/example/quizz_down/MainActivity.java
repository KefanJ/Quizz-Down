package com.example.quizz_down;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    Button btn_choice_1,btn_choice_2,btn_next ;
    TextView tv_question;
    private Question question = new Question();
    int flags = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_choice_1 = findViewById(R.id.btn_choice_1);
        btn_choice_2 = findViewById(R.id.btn_choice_2);
        btn_next = findViewById(R.id.btn_next);
        tv_question = findViewById(R.id.tv_question);

        tv_question.setText(question.questions[flags]);
        btn_choice_1.setText(question.getchoice1(flags));
        btn_choice_2.setText(question.getchoice2(flags));

        btn_next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                tv_question.setText(question.questions[flags]);
                btn_choice_1.setText(question.getchoice1(flags));
                btn_choice_2.setText(question.getchoice2(flags));
                flags++;
            }
        });
    }


}