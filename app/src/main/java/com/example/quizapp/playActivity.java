package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.api.TriviaApi;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.TriviaResponse;
import com.example.quizapp.api.ApiUrlManager;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class playActivity extends AppCompatActivity {

    private TextView cptQuestion, textQuestion;
    private Button btnChoose1, btnChoose2, btnChoose3, btnChoose4, btnNext;
    private int currentQuestion = 0;
    private int scorePlayer = 0;
    private boolean isClickBtn = false;
    private String valueChoose = "";
    private Button btnClick;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        cptQuestion = findViewById(R.id.cpt_question);
        textQuestion = findViewById(R.id.text_question);
        btnChoose1 = findViewById(R.id.btn_choose1);
        btnChoose2 = findViewById(R.id.btn_choose2);
        btnChoose3 = findViewById(R.id.btn_choose3);
        btnChoose4 = findViewById(R.id.btn_choose4);
        btnNext = findViewById(R.id.btn_next);

        findViewById(R.id.image_back).setOnClickListener(a -> finish());

        btnNext.setOnClickListener(view -> {
            if (isClickBtn) {
                isClickBtn = false;

                if (!valueChoose.equals(questionList.get(currentQuestion).getCorrectAnswer())) {
                    Toast.makeText(playActivity.this, "Erreur", Toast.LENGTH_LONG).show();
                    btnClick.setBackgroundResource(R.drawable.background_btn_erreur);
                } else {
                    Toast.makeText(playActivity.this, "Correct", Toast.LENGTH_LONG).show();
                    btnClick.setBackgroundResource(R.drawable.background_btn_correct);
                    scorePlayer++;
                }

                new Handler().postDelayed(() -> {
                    if (currentQuestion < questionList.size() - 1) {
                        currentQuestion++;
                        remplirData();
                        valueChoose = "";
                        btnChoose1.setBackgroundResource(R.drawable.background_btn_choose);
                        btnChoose2.setBackgroundResource(R.drawable.background_btn_choose);
                        btnChoose3.setBackgroundResource(R.drawable.background_btn_choose);
                        btnChoose4.setBackgroundResource(R.drawable.background_btn_choose);
                    } else {
                        Intent intent = new Intent(playActivity.this, ResulteActivity.class);
                        intent.putExtra("Result", scorePlayer);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);

            } else {
                Toast.makeText(playActivity.this, "Vous devez en choisir un", Toast.LENGTH_LONG).show();
            }
        });

        // Récupérer le thème sélectionné depuis l'intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("Theme")) {
            String theme = intent.getStringExtra("Theme");
            Log.d("playActivity", "Theme selected: " + theme);

            // Utiliser la classe ApiUrlManager pour obtenir la catégorie spécifique du thème
            int category = ApiUrlManager.getCategoryForTheme(theme);

            // Appel à l'API pour récupérer les questions
            fetchQuestions(category);
        }
    }

    private void fetchQuestions(int category) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")  // Assurez-vous que l'URL se termine par "/"
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TriviaApi triviaApi = retrofit.create(TriviaApi.class);
        Call<TriviaResponse> call = triviaApi.getTriviaQuestions(5, category); // Utilisez les valeurs appropriées pour amount et category

        call.enqueue(new Callback<TriviaResponse>() {
            @Override
            public void onResponse(Call<TriviaResponse> call, Response<TriviaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TriviaResponse triviaResponse = response.body();
                    questionList = triviaResponse.getResults();

                    if (questionList != null && questionList.size() > 0) {
                        remplirData();
                    } else {
                        // Gérer le cas où la liste de questions est vide
                        Toast.makeText(playActivity.this, "La liste de questions est vide", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Gérer l'échec de la requête
                    Toast.makeText(playActivity.this, "Erreur lors de la récupération des questions", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TriviaResponse> call, Throwable t) {
                // Gérer l'échec de la requête
                Toast.makeText(playActivity.this, "Erreur réseau", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void remplirData() {
        cptQuestion.setText((currentQuestion + 1) + "/" + questionList.size());

        // Traiter le texte de la question avant de l'afficher
        String questionText = processText(questionList.get(currentQuestion).getQuestion());
        textQuestion.setText(questionText);

        List<String> allAnswers = questionList.get(currentQuestion).getAllAnswers();
        List<String> shuffledAnswers = shuffleAnswers(allAnswers);

        // Afficher les réponses sur les boutons
        btnChoose1.setText(processText(shuffledAnswers.get(0)));
        btnChoose2.setText(processText(shuffledAnswers.get(1)));
        btnChoose3.setText(processText(shuffledAnswers.get(2)));
        btnChoose4.setText(processText(shuffledAnswers.get(3)));
    }

    // Mélanger les réponses de manière aléatoire
    private List<String> shuffleAnswers(List<String> answers) {
        List<String> shuffledAnswers = new ArrayList<>(answers);
        Collections.shuffle(shuffledAnswers);
        return shuffledAnswers;
    }

    // Méthode pour traiter le texte (encodage, remplacement, etc.)
    private String processText(String inputText) {
        // 1. Encodage et décodage
        String decodedText = "";
        try {
            decodedText = URLDecoder.decode(inputText, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 2. Traitement des balises HTML et entités HTML
        String processedText = Html.fromHtml(decodedText, Html.FROM_HTML_MODE_LEGACY).toString();

        // 3. Remplacement de caractères indésirables (ajuste selon tes besoins)
        String cleanedText = processedText.replace("\"", "");

        return cleanedText;
    }

    public void ClickChoose(View view) {
        btnClick = (Button) view;

        if (isClickBtn) {
            btnChoose1.setBackgroundResource(R.drawable.background_btn_choose);
            btnChoose2.setBackgroundResource(R.drawable.background_btn_choose);
            btnChoose3.setBackgroundResource(R.drawable.background_btn_choose);
            btnChoose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();
    }

    private void chooseBtn() {
        btnClick.setBackgroundResource(R.drawable.background_btn_choose_color);
        isClickBtn = true;
        valueChoose = btnClick.getText().toString();
    }
}
