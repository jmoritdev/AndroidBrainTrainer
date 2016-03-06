package apps.joey.androidbraintrainer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int highScore;
    TextView highScoreText;
    TextView newHighText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        highScore = prefs.getInt("highScore", 0);

        highScoreText = (TextView) findViewById(R.id.highScoreText);
        newHighText = (TextView) findViewById(R.id.newHighText);

        int score = getIntent().getIntExtra("score", 0);
        if(score > highScore){
            prefs.edit().putInt("highScore", score).commit();
            highScoreText.setText("Highscore: " + score);
            newHighText.setText("NIEUWE HIGHSCORE!!!");
        } else {
            newHighText.setText("");
            highScoreText.setText("Highscore: " + highScore);
        }

    }

    public void startBrainTrainer(View view){

        Intent intent = new Intent(this, TrainerActivity.class);
        startActivity(intent);
    }
}
