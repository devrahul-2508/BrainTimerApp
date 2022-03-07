package com.example.braintimerapp;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Intent intent=getIntent();
    TextView sumTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    CountDownTimer countDownTimer;
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView=findViewById(R.id.sumTextView);
        resultTextView=findViewById(R.id.resultTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        timerTextView=findViewById(R.id.timerTextView);
        newQuestion();

    }
    public void newQuestion()
    {

        Random rand=new Random();
        int a=rand.nextInt(11);
        int b=rand.nextInt(11);
        int operand=rand.nextInt(3);
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                if(operand==0)
                {
                    sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
                    answers.add(a+b);
                }
                else if(operand==1)
                {
                    sumTextView.setText(Integer.toString(a)+"-"+Integer.toString(b));
                    answers.add(a-b);
                }
                else if(operand==2)
                {
                    sumTextView.setText(Integer.toString(a)+"*"+Integer.toString(b));
                    answers.add(a*b);
                }
            }

            else{
                int wrongAnswer=rand.nextInt(101);
                while(wrongAnswer==a+b)
                {
                    wrongAnswer=rand.nextInt(101);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
        countDownTimer=new CountDownTimer(6000,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                    newQuestion();
                    numberOfQuestions++;
                    scoreTextView.setText(Integer.toString(score)+"/"+ Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        resultTextView.setVisibility(View.VISIBLE);
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!!");
            score++;
        } else {
            resultTextView.setText("Wrong Answer");
        }
        countDownTimer.cancel();
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+ Integer.toString(numberOfQuestions));
        newQuestion();
    }
}