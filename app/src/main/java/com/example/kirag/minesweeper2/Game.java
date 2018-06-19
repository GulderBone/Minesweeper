package com.example.kirag.minesweeper2;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // I don't know how 'context' works
        Log.e("Game", "onCreate");
        GameEngine.getInstance().createGrid(this);
        context = this;

        timer.start();
        configureRestartButton();
        configureExitButton();
    }

    private void configureRestartButton() {
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().createGrid(context);
                timer.cancel();
                count = 0;
                timer.start();
            }
        });
    }

    private void configureExitButton() {
        Button exitButton = findViewById(R.id.gameExitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                count = 0;
                finish();
            }
        });
    }

    int totalSeconds = 999;
    int intervalSeconds = 1;
    static int count = 0;

    CountDownTimer timer = new CountDownTimer(totalSeconds * 1000, intervalSeconds * 1000) {

        public void onTick(long millisUntilFinished) {
            TextView counterTextView = findViewById(R.id.counterTextView);

            if(GameEngine.GAMEWON || GameEngine.GAMELOST) {
                timer.cancel();
                GameEngine.GAMEWON = false;
                GameEngine.GAMELOST = false;
            }

            count++;
            counterTextView.setText(count + "");
        }

        public void onFinish() {
            count = 0;
        }

    };
}
