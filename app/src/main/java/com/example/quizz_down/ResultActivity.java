package com.example.quizz_down;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView tv_wrong, tv_correct, tv_result;
    Button btn_restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv_wrong = findViewById(R.id.tv_wrong);
        tv_correct = findViewById(R.id.tv_correct);
        tv_result = findViewById(R.id.tv_result);
        btn_restart =  findViewById(R.id.btn_restart);

        StringBuffer sbCorrect = new StringBuffer();
        sbCorrect.append("Réponse correcte : " + QuestionActivity.correct +"\n");

        StringBuffer sbWrong = new StringBuffer();
        sbWrong.append("Réponse incorrecte : " + QuestionActivity.wrong +"\n");

        StringBuffer sbResult = new StringBuffer();
        sbResult.append("Résultat final : " + QuestionActivity.marks +"\n");

        tv_correct.setText(sbCorrect);
        tv_wrong.setText(sbWrong);
        tv_result.setText(sbResult);

        QuestionActivity.correct=0;
        QuestionActivity.wrong=0;

        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),QuestionActivity.class);
                startActivity(in);
            }
        });


    }
}