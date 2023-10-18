package com.example.quizz_down;

public class Question {
    public  String questions[] = {
            "La France à gagner 3 fois la coupe du monde ?",
            "Qui est le GOAT du football ?",

    };

    public String choices[][] = {
            {"Oui", "Non"},
            {"CR7", "Zidane"}

    };

    public String correctAnswer[] = {
            "Non",
            "Zidane",

    };

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }



}
