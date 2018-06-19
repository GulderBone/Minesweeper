package com.example.kirag.minesweeper2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class HighScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        configureExitButton();

        try {
            printHighScores(Menu.highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureExitButton() {
        Button exitButton = findViewById(R.id.highScoresExitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void printHighScores(String[][] highScoreTable) throws IOException {

        TextView name1 = findViewById(R.id.firstNameTextView);
        TextView name2 = findViewById(R.id.secondNickTextView);
        TextView name3 = findViewById(R.id.thirdNickTextView);
        TextView name4 = findViewById(R.id.fourthNickTextView);
        TextView name5 = findViewById(R.id.fifthNickTextView);
        TextView name6 = findViewById(R.id.sixthNickTextView);
        TextView name7 = findViewById(R.id.seventhNickTextView);
        TextView name8 = findViewById(R.id.eighthNickTextView);
        TextView name9 = findViewById(R.id.ninethNickTextView);
        TextView name10 = findViewById(R.id.tenthNickTextView);

        name1.setText(highScoreTable[0][0]);
        name2.setText(highScoreTable[0][1]);
        name3.setText(highScoreTable[0][2]);
        name4.setText(highScoreTable[0][3]);
        name5.setText(highScoreTable[0][4]);
        name6.setText(highScoreTable[0][5]);
        name7.setText(highScoreTable[0][6]);
        name8.setText(highScoreTable[0][7]);
        name9.setText(highScoreTable[0][8]);
        name10.setText(highScoreTable[0][9]);

        TextView mode1 = findViewById(R.id.firstModeTextView);
        TextView mode2 = findViewById(R.id.secondModeTextView);
        TextView mode3 = findViewById(R.id.thirdModeTextView);
        TextView mode4 = findViewById(R.id.fourthModeTextView);
        TextView mode5 = findViewById(R.id.fifthModeTextView);
        TextView mode6 = findViewById(R.id.sixthModeTextView);
        TextView mode7 = findViewById(R.id.seventhModeTextView);
        TextView mode8 = findViewById(R.id.eighthModeTextView);
        TextView mode9 = findViewById(R.id.ninethModeTextView);
        TextView mode10 = findViewById(R.id.tenthModeTextView);

        mode1.setText(highScoreTable[1][0]);
        mode2.setText(highScoreTable[1][1]);
        mode3.setText(highScoreTable[1][2]);
        mode4.setText(highScoreTable[1][3]);
        mode5.setText(highScoreTable[1][4]);
        mode6.setText(highScoreTable[1][5]);
        mode7.setText(highScoreTable[1][6]);
        mode8.setText(highScoreTable[1][7]);
        mode9.setText(highScoreTable[1][8]);
        mode10.setText(highScoreTable[1][9]);

        TextView time1 = findViewById(R.id.firstTimetextView);
        TextView time2 = findViewById(R.id.secondTimetextView);
        TextView time3 = findViewById(R.id.thirdTimetextView);
        TextView time4 = findViewById(R.id.fourthTimetextView);
        TextView time5 = findViewById(R.id.fifthTimetextView);
        TextView time6 = findViewById(R.id.sixthTimetextView);
        TextView time7 = findViewById(R.id.seventhTimetextView);
        TextView time8 = findViewById(R.id.eightTimetextView);
        TextView time9 = findViewById(R.id.ninthTimetextView);
        TextView time10 = findViewById(R.id.tenthTimetextView);

        time1.setText(highScoreTable[2][0]);
        time2.setText(highScoreTable[2][1]);
        time3.setText(highScoreTable[2][2]);
        time4.setText(highScoreTable[2][3]);
        time5.setText(highScoreTable[2][4]);
        time6.setText(highScoreTable[2][5]);
        time7.setText(highScoreTable[2][6]);
        time8.setText(highScoreTable[2][7]);
        time9.setText(highScoreTable[2][8]);
        time10.setText(highScoreTable[2][9]);
    }
}
