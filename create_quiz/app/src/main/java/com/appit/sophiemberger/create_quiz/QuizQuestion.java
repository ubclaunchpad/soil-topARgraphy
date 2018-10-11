package com.appit.sophiemberger.create_quiz;

import android.widget.EditText;

public class QuizQuestion {

    //fields
    private String question;
    private String correctAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String WrongAnswer3;


    //constructor
    public void quizQuestion(String q, String ca, String wa1, String wa2, String wa3) {
        this.question = q;
        this.correctAnswer = ca;
        this.wrongAnswer1 = wa1;
        this.wrongAnswer2 = wa2;
        this.WrongAnswer3 = wa3;
    }


    //setters
    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        WrongAnswer3 = wrongAnswer3;
    }


    //getters
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return WrongAnswer3;
    }

}
