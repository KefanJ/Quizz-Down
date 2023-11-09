package com.example.quizz_down;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;

public class MainActivity extends AppCompatActivity  {

  TextView tv_login, tv_password;
  Button btn_connexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_login = findViewById(R.id.tv_login);
        tv_password = findViewById(R.id.tv_password);
        btn_connexion = findViewById(R.id.btn_connexion);

        btn_connexion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),QuestionActivity.class);
                startActivity(in);
            }
        });



    }


}