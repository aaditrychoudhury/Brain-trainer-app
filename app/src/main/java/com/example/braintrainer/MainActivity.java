package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{
    Button start_button;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button1 ;
    Button button2 ;
    Button button3 ;
    Button button4 ;
    Button playAgainButton;
    ConstraintLayout gameConstraintLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();

    }

    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        int incorrectAns;

        for (int i = 0; i<4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a + b);
            } else {
                incorrectAns = rand.nextInt(41);
                while (incorrectAns == a+b){
                    incorrectAns = rand.nextInt(41);
                }
                answers.add(incorrectAns);

            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
        resultTextView = (TextView) findViewById(R.id.resultTextview);
    }
    public  void  chooseAnswer(View view){
        if ( view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
           score++;
           resultTextView.setText("Correct");

        }else{
            resultTextView.setText("Incorrect");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions) );
        generateQuestion();
    }

    public  void start(View view){

        start_button.setVisibility(View.INVISIBLE);
        gameConstraintLayout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_button = (Button) findViewById(R.id.start_button);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        resultTextView = (TextView) findViewById(R.id.resultTextview);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        gameConstraintLayout = (ConstraintLayout) findViewById(R.id.gameConstraintLayout);




    }
}