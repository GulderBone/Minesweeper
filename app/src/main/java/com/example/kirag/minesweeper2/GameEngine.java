package com.example.kirag.minesweeper2;

import android.content.Context;
import android.widget.Toast;

public class GameEngine {
    private static GameEngine instance;

    public static int BOMB_NUMBER = 100;
    public static int WIDTH = 20;
    public static int HEIGHT = 24;
    public static boolean GAMELOST = false;
    public static boolean GAMEWON = false;

    // modes
    // hard 20 24 120
    // medium 14 16 36
    // easy 10 10 10

    private Context context;

    private Cell[][] minesweeperGrid = new Cell[WIDTH][HEIGHT];

    public static GameEngine getInstance() {
        if(instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    private GameEngine() {
    }

    public void createGrid(Context context) {
        this.context = context;

        // create the grid and store it
        int[][] generatedGrid = Generator.generate(BOMB_NUMBER, WIDTH, HEIGHT);
        setGrid(context, generatedGrid);
    }

    private void setGrid(final Context context, final int[][] grid) {
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                if(minesweeperGrid[x][y] == null) {
                    minesweeperGrid[x][y] = new Cell(context, x, y);
                }
                minesweeperGrid[x][y].setValue(grid[x][y]);
                minesweeperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return minesweeperGrid[x][y];
    }

    public Cell getCellAt(int x, int y) {
        return minesweeperGrid[x][y];
    }

    public void click(int x, int y) {

        if(x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x, y).isClicked() && !getCellAt(x, y).isFlagged() && !GAMELOST) {
            getCellAt(x, y).setClicked();

            if(getCellAt(x, y).getValue() == 0) {
                for(int xt = -1; xt <= 1; xt++) {
                    for(int yt = -1; yt <= 1; yt++) {
                        if(xt != yt) {
                            click(x + xt, y + yt);
                        }
                    }
                }
            }

            if(getCellAt(x, y).isBomb()) {
                onGameLost();
            }
        }
        checkEnd();
    }

    private boolean checkEnd() {
        int bombNotFound = BOMB_NUMBER;
        int notRevealed = WIDTH * HEIGHT;

        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                if(getCellAt(x, y).isRevealed() || getCellAt(x, y).isFlagged()) {
                    notRevealed--;
                }

                if(getCellAt(x, y).isFlagged() && getCellAt(x, y).isBomb()) {
                    bombNotFound--;
                }
            }
        }

        if((bombNotFound == 0 && notRevealed == 0) || (bombNotFound == notRevealed)) {
            Toast.makeText(context, "Game won", Toast.LENGTH_SHORT).show();
            int time = Game.count;

            GAMEWON = true;
            saveScore(time);
        }
        return false;
    }

    public void flag(int x, int y) {
        if(!getCellAt(x, y).isRevealed() && !GAMELOST) {
            boolean isFlagged = getCellAt(x, y).isFlagged();
            getCellAt(x, y).setFlagged(!isFlagged);
            getCellAt(x, y).invalidate();
        }
        checkEnd();
    }

    private void onGameLost() {
        GAMELOST = true;
        Toast.makeText(context, "Game lost", Toast.LENGTH_SHORT).show();

        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                getCellAt(x, y).setRevealed();
            }
        }
    }

    private void saveScore(int time) {
        int mode = 0;
        String modeString = "Easy";
        int highScoreMode = 0;
        int highScoreTime = 999;

        if (BOMB_NUMBER == 10) {
            mode = 0;
        }
        else if (BOMB_NUMBER == 32) {
            mode = 1;
        }
        else if (BOMB_NUMBER == 100) {
            mode = 2;
        }

        if (mode == 0) {
            modeString = "Easy";
        }
        else if (mode == 1) {
            modeString = "Medium";
        }
        else if (mode == 2) {
            modeString = "Hard";
        }

        for(int i = 0; i < 10; i++) {
            highScoreTime = Integer.parseInt(Menu.highScores[2][i]);
            if(Menu.highScores[1][i].equals("Easy")) {
                highScoreMode = 0;
            }
            else if(Menu.highScores[1][i].equals("Medium")) {
                highScoreMode = 1;
            }
            else if(Menu.highScores[1][i].equals("Hard")) {
                highScoreMode = 2;
            }

            if(mode > highScoreMode) {
                for(int j = 8; j >= i; j--) {
                    Menu.highScores[0][j + 1] = Menu.highScores[0][j];
                    Menu.highScores[1][j + 1] = Menu.highScores[1][j];
                    Menu.highScores[2][j + 1] = Menu.highScores[2][j];
                }
                Menu.highScores[1][i] = modeString;
                Menu.highScores[2][i] = time + "";
                i = 10;
            }
            else if(mode == highScoreMode) {
                if(time < highScoreTime) {
                    for(int j = 8; j >= i; j--) {
                        Menu.highScores[0][j + 1] = Menu.highScores[0][j];
                        Menu.highScores[1][j + 1] = Menu.highScores[1][j];
                        Menu.highScores[2][j + 1] = Menu.highScores[2][j];
                    }
                    Menu.highScores[1][i] = modeString;
                    Menu.highScores[2][i] = time + "";
                    i = 10;
                }
            }
        }
    }
}