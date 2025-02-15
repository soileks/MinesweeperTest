package ru.petrov.test.model;


public class GameSettings {

    private int height;

    private int width;

    private int mines_count;

    public GameSettings(){}

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMines_count(int mines_count) {
        this.mines_count = mines_count;
    }

    public void setWidth(int width) {
        this.width = width;
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
