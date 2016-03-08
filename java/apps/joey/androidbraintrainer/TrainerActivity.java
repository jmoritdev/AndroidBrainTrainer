package apps.joey.androidbraintrainer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainerActivity extends AppCompatActivity {

    TextView timerText;
    TextView scoreText;
    TextView sumText;
    List<Button> buttonList;
    int correctAnswer;

    int score;
    int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        timerText = (TextView) findViewById(R.id.timerText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        sumText = (TextView) findViewById(R.id.sumText);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        buttonList = new ArrayList<Button>();
        buttonList.add((Button) findViewById(R.id.button1));
        buttonList.add((Button) findViewById(R.id.button2));
        buttonList.add((Button) findViewById(R.id.button3));
        buttonList.add((Button) findViewById(R.id.button4));

        totalQuestions = 0;
        score = 0;
        scoreText.setText(score+"/"+totalQuestions);

        startTimer();
        generateSum();
    }

    public void startTimer(){
        CountDownTimer timer = new CountDownTimer(30 * 1000+100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(millisUntilFinished/1000+"");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        }.start();
    }

    public void generateSum(){
        Random randomnizer = new Random();
        int number1 = randomnizer.nextInt(100)+1;
        int number2 = randomnizer.nextInt(100)+1;
        sumText.setText(number1 + " + " + number2);
        correctAnswer = number1 + number2;

        buttonList.get(randomnizer.nextInt(4)).setText(correctAnswer+"");

        int maxNumber = correctAnswer + 20;
        int minNumber = correctAnswer - 20;
        for(Button b : buttonList){
            if(!b.getText().equals(""+correctAnswer)){
                //generates a random number that is 20 higher or lower than the correct answer
                //so if correctAnswer equals 100, this will generate a number between 80 and 120 
                b.setText( ""+(randomnizer.nextInt( maxNumber - minNumber + 1 ) + minNumber) );
            }
        }
    }

    public void checkAnswer(View view){
        Button button = (Button) view;
        totalQuestions++;
        if(button.getText().equals(""+correctAnswer)){
            score++;
        }
        scoreText.setText(score+"/"+totalQuestions);
        generateSum();
    }

}
