package com.example.quizapp.model;

import android.text.Html;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Question {
    @SerializedName("question")
    private String question;

    @SerializedName("correct_answer")
    private String correctAnswer;

    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;

    public String getQuestion() {
        return processText(question);
    }

    public String getCorrectAnswer() {
        return processText(correctAnswer);
    }

    public List<String> getIncorrectAnswers() {
        return processTextList(incorrectAnswers);
    }

    public List<String> getAllAnswers() {
        List<String> allAnswers = new ArrayList<>();
        allAnswers.add(getCorrectAnswer());
        allAnswers.addAll(getIncorrectAnswers());
        return allAnswers;
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
        return processedText.replace("\"", "");
    }

    // Méthode pour traiter la liste de texte
    private List<String> processTextList(List<String> inputList) {
        List<String> processedList = new ArrayList<>();
        if (inputList != null) {
            for (String text : inputList) {
                processedList.add(processText(text));
            }
        }
        return processedList;
    }
}
