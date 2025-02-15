package ru.petrov.test.model;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Random;

public class Game {
    private String game_id;
    private int width;
    private int height;
    private int mines_count;
    private boolean completed;
    private String[][] field;
    @JsonIgnore
    private boolean[][] bombs; // true, если в ячейке есть бомба
    @JsonIgnore
    private boolean[][] revealed; // открытые ячейки

    public Game(){}

    public Game(String gameId, int height, int width, int mines_count){
        this.game_id=gameId;
        this.height=height;
        this.width=width;
        this.mines_count=mines_count;
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
                bombs[i][j]=false;
                revealed[i][j]=false;
            }
        }

        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mines_count) {
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
            if (bombsAround > 0) {
                field[row][col] = String.valueOf(bombsAround); // Показываем количество бомб вокруг
            } else {
                field[row][col] = "0"; // Нет бомб вокруг
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

    public void setMines_count(int mines_count) {
        this.mines_count = mines_count;
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


    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public boolean[][] getBombs() { return bombs; }

    public boolean[][] getRevealed() { return revealed; }

    public String[][] getField() {
        return field;
    }

    public int getHeight() {
        return height;
    }

    public int getMines_count() {
        return mines_count;
    }

    public int getWidth() {
        return width;
    }
}
