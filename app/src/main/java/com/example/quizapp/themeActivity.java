package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class themeActivity extends AppCompatActivity {

    Button btnTheme1, btnTheme2, btnTheme3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        btnTheme1 = findViewById(R.id.btn_theme1);
        btnTheme2 = findViewById(R.id.btn_theme2);
        btnTheme3 = findViewById(R.id.btn_theme3);

        btnTheme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz("THEME 1"); // Replace "Theme 1" with the actual theme name
            }
        });

        btnTheme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz("THEME 2"); // Replace "Theme 2" with the actual theme name
            }
        });

        btnTheme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz("THEME 3"); // Replace "Theme 3" with the actual theme name
            }
        });
    }

    private void startQuiz(String theme) {
        // You can pass the selected theme to the next activity (playActivity)
        Intent intent = new Intent(themeActivity.this, playActivity.class);
        intent.putExtra("Theme", theme);
        startActivity(intent);
        finish();
    }
}
