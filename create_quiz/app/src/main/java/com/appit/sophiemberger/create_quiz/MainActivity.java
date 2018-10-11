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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionInput = findViewById(R.id.qEditText);
        questionInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String question = questionInput.getText().toString();
            }
        });


        correctAnswerInput = findViewById(R.id.caEditText);
        correctAnswerInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String correctAnswer = correctAnswerInput.getText().toString();
            }
        });


        wrongAnswer1Input = findViewById(R.id.wa1EditText);
        wrongAnswer1Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wrongAnswer1 = wrongAnswer1Input.getText().toString();
            }
        });


        wrongAnswer2Input = findViewById(R.id.wa2EditText);
        wrongAnswer2Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wrongAnswer2 = wrongAnswer2Input.getText().toString();
            }
        });


        wrongAnswer3Input = findViewById(R.id.wa3EditText);
        wrongAnswer3Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String wrongAnswer3 = wrongAnswer3Input.getText().toString();
            }
        });
    }


    //Opens next question screen
    public void nextQuestion(View view) {
        Intent nextQuestionScreen = new Intent(this, MainActivity.class);
        startActivity(nextQuestionScreen);

    }


}
