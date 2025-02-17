package ru.petrov.test.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class GameSettings {

    @JsonProperty("width")
    private int width;
    @JsonProperty("height")
    private int height;
    @JsonProperty("mines_count")
    private int minesCount;

    public GameSettings(){}

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMinesCount(int minesCount) {
        this.minesCount = minesCount;
    }

    public void setWidth(int width) {
        this.width = width;
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
