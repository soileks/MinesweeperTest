package ru.petrov.test.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Random;

public class Game {
    @JsonProperty("game_id")
    private String gameId;
    @JsonProperty("width")
    private int width;
    @JsonProperty("height")
    private int height;
    @JsonProperty("mines_count")
    private int minesCount;
    @JsonProperty("completed")
    private boolean completed;
    private String[][] field;
    @JsonIgnore
    private boolean[][] bombs; // true, если в ячейке есть бомба
    @JsonIgnore
    private boolean[][] revealed; // открытые ячейки

    public Game(){}

    public Game(String gameId, int height, int width, int minesCount){
        this.gameId = gameId;
        this.height = height;
        this.width = width;
        this.minesCount = minesCount;
        completed = false;
        field = new String[height][width];
        bombs = new boolean[height][width];
        revealed = new boolean[height][width];

        initializeField();
    }
    private void initializeField() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = " ";
            }
        }

        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < minesCount) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);
            if (!bombs[row][col]) {
                bombs[row][col] = true;
                minesPlaced++;
            }
        }
    }


    public void revealCell(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width || revealed[row][col]) {
            return;
        }

        revealed[row][col] = true;

        if (bombs[row][col]) {
            //field[row][col] = "X";
            completed = true;
        } else {
            int bombsAround = countBombsAround(row, col);
            field[row][col] = String.valueOf(bombsAround); // Показываем количество бомб вокруг
            if (bombsAround == 0) {
                // Рекурсивно открываем соседние ячейки
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        revealCell(row + i, col + j);
                    }
                }
            }
        }
    }

    private int countBombsAround(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Пропускаем текущую ячейку
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width && bombs[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count;
    }
    public boolean isCompleted() { return completed; }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMines_count(int minesCount) {
        this.minesCount = minesCount;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setField(String[][] field) {
        this.field = field;
    }


    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public boolean[][] getBombs() { return bombs; }

    public boolean[][] getRevealed() { return revealed; }

    public String[][] getField() {
        return field;
    }

    public int getHeight() {
        return height;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public int getWidth() {
        return width;
    }
}
