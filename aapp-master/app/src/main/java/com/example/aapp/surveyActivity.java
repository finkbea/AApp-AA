package com.example.aapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class surveyActivity extends Activity implements View.OnClickListener{

    TextView tv_question;
    Button btn_one, btn_two, btn_three, btn_four,submitButton;
    EditText textInput;
    Button[] buttons = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introsurvey);
        tv_question = (TextView)findViewById(R.id.tv_question);
        btn_one = (Button)findViewById(R.id.btn_one);
        btn_two = (Button)findViewById(R.id.btn_two);
        btn_three = (Button)findViewById(R.id.btn_three);
        btn_four = (Button)findViewById(R.id.btn_four);
        submitButton = (Button)findViewById(R.id.submitButton);
        textInput = (EditText)findViewById(R.id.UserInput);
        NextQuestion(0);
        buttons[0] = (Button)findViewById(R.id.btn_one);
        buttons[1] = (Button)findViewById(R.id.btn_two);
        buttons[2] = (Button)findViewById(R.id.btn_three);
        buttons[3] = (Button)findViewById(R.id.btn_four);
    }

    int count = 0;

    private Questionnaire question = new Questionnaire();

    private String answer;
    private int questionLength = question.questions.length;
    public String[] allAnswers = new String[questionLength];


    private void buttonCheck(){
        int i = 0;
        while(buttons[i] != null){
            String current = buttons[i].getText().toString();
            if( current.equals("*EMPTY*") ){
                buttons[i].setVisibility(View.INVISIBLE);

            }
            else{
                buttons[i].setVisibility(View.VISIBLE);

            }
            i++;
        }
    }

    private void NextQuestion(int num){
        tv_question.setText(question.getQuestion(num));
        btn_one.setText(question.getchoice1(num));
        btn_two.setText(question.getchoice2(num));
        btn_three.setText(question.getchoice3(num));
        btn_four.setText(question.getchoice4(num));
        textInput.setText("");
        textInput.clearFocus();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                //get button one answer
                answer = question.getchoice1(count);

                break;
            case R.id.btn_two:
                //get button two answer
                answer = question.getchoice2(count);

                break;
            case R.id.btn_three:
                //get button three answer
                answer = question.getchoice3(count);

                break;
            case R.id.btn_four:
                //get button four answer
                answer = question.getchoice4(count);
                break;

            case R.id.submitButton:
                answer = String.valueOf(textInput.getText());

                break;


        }
        //change the question
        allAnswers[count] = answer;
        count++;
        if (count < questionLength){
            NextQuestion(count);
          //  buttonCheck();
        }
        else {
            launchMain();
        }


    }
    public String[] getAllAnswers(){
        return allAnswers;
    }

    public void launchMain() {
        //d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
