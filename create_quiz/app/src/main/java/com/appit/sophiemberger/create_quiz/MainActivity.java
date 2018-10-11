package com.appit.sophiemberger.create_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Fields
    private EditText questionInput;
    private EditText correctAnswerInput;
    private EditText wrongAnswer1Input;
    private EditText wrongAnswer2Input;
    private EditText wrongAnswer3Input;

    String question;
    String correctAnswer;
    String wrongAnswer1;
    String wrongAnswer2;
    String wrongAnswer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionInput = findViewById(R.id.qEditText);
        questionInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                question = questionInput.getText().toString();
            }
        });


        correctAnswerInput = findViewById(R.id.caEditText);
        correctAnswerInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                correctAnswer = correctAnswerInput.getText().toString();
            }
        });


        wrongAnswer1Input = findViewById(R.id.wa1EditText);
        wrongAnswer1Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                wrongAnswer1 = wrongAnswer1Input.getText().toString();
            }
        });


        wrongAnswer2Input = findViewById(R.id.wa2EditText);
        wrongAnswer2Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                wrongAnswer2 = wrongAnswer2Input.getText().toString();
            }
        });


        wrongAnswer3Input = findViewById(R.id.wa3EditText);
        wrongAnswer3Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                wrongAnswer3 = wrongAnswer3Input.getText().toString();
            }
        });


    }


    //Opens next question screen and instantiates a QuizQuestion object
    //QuizQuestion is added to QuizQuestion queue
    public void nextQuestion(View view) {
        Intent nextQuestionScreen = new Intent(this, MainActivity.class);
        startActivity(nextQuestionScreen);
        QuizQuestion newQuestion = new QuizQuestion(question, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3);
        QuizQuestions.quizQuestions.add(newQuestion);

    }


}
