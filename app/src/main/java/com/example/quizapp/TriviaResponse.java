package com.example.quizapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TriviaResponse {
    @SerializedName("results")
    private List<Question> results;

    // Ajoutez le getter nécessaire

    public List<Question> getResults() {
        return results;
    }
}
