package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class themechoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        // Ajoutez le code pour afficher le choix des thèmes
        // Vous pouvez utiliser des boutons ou d'autres éléments d'interface pour permettre à l'utilisateur de choisir le thème.

        // Une fois que l'utilisateur a fait son choix, dirigez-le vers themeActivity avec le thème sélectionné
        String selectedTheme = "Theme 1"; // Remplacez par le thème réel sélectionné
        startQuiz(selectedTheme);
    }

    private void startQuiz(String theme) {
        // Vous pouvez passer le thème sélectionné à l'activité suivante (themeActivity)
        Intent intent = new Intent(themechoiceActivity.this, themeActivity.class);
        intent.putExtra("Theme", theme);
        startActivity(intent);
        finish();
    }
}
