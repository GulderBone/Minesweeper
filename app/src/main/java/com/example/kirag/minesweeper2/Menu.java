package com.example.kirag.minesweeper2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Menu extends AppCompatActivity {
    public static String [][] highScores = new String[3][10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // loading high scores to the global table
        try {
            // I'm using this functions only when i did something wrong and lost my high scores
            //fillHighScores();
            //saveScores();
            loadScores();
        } catch (IOException e) {
            e.printStackTrace();
        }

        configureLevelButtons();
        configureHighScoresButton();
        configureExitButton();
    }

    private void configureLevelButtons() {
        Button easyButton = findViewById(R.id.easyButton);
        Button mediumButton = findViewById(R.id.mediumButton);
        Button hardButton = findViewById(R.id.hardButton);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.BOMB_NUMBER = 10;
                GameEngine.WIDTH = 10;
                GameEngine.HEIGHT = 10;
                GameEngine.GAMELOST = false;
                startActivity(new Intent(Menu.this, Game.class));
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.BOMB_NUMBER = 32;
                GameEngine.WIDTH = 14;
                GameEngine.HEIGHT = 16;
                GameEngine.GAMELOST = false;
                startActivity(new Intent(Menu.this, Game.class));
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.BOMB_NUMBER = 100;
                GameEngine.WIDTH = 20;
                GameEngine.HEIGHT = 24;
                GameEngine.GAMELOST = false;
                startActivity(new Intent(Menu.this, Game.class));
            }
        });
    }

    private void configureHighScoresButton() {
        Button highScoresButton = findViewById(R.id.highScoresButton);
        highScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try { // trying to save
                    saveScores();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(Menu.this, HighScores.class));
            }
        });
    }

    private void configureExitButton() {
        Button exitButton = findViewById(R.id.menuExitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    saveScores();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                finish();
            }
        });
    }

    private void saveScores() throws IOException {
        File path = getFilesDir();
        File file = new File(path, "scores.txt");
        String line;

        FileOutputStream stream = new FileOutputStream(file);
        try {
            for(int i = 0; i < 9; i++) {
                line = highScores[0][i] + " " + highScores[1][i] + " " + highScores[2][i] + " ";
                stream.write(line.getBytes());
            }
            line = highScores[0][9] + " " + highScores[1][9] + " " + highScores[2][9];
            stream.write(line.getBytes());
        } finally {
            stream.close();
        }
    }

    private void loadScores() throws IOException {
        File path = getFilesDir();
        File file = new File(path, "scores.txt");

        int length = (int) file.length();
        byte[] bytes = new byte[length];

        FileInputStream in = new FileInputStream(file);
        try {
            in.read(bytes);
        } finally {
            in.close();
        }

        String contents = new String(bytes);

        String[] parts = contents.split(" ");
        int partCount = 0;

        for(int i = 0; i < 10; i++) {
            highScores[0][i] = parts[partCount];
            highScores[1][i] = parts[partCount + 1];
            highScores[2][i] = parts[partCount + 2];

            partCount = partCount + 3;
        }
    }
    // I'm using this only when I want to restore highscores
    private void fillHighScores() {
            for(int i = 0; i < 10; i++) {
                highScores[0][i] = "GulderBone";
                highScores[1][i] = "Easy";
                highScores[2][i] = 999 + "";
            }
    }
}
