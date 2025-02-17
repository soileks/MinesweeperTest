package ru.petrov.test.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TurnRequest {
    @JsonProperty("game_id")
    private String gameId;
    @JsonProperty("col")
    private int col;
    @JsonProperty("row")
    private int row;

    public TurnRequest(){}

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getRow() {
        return row;
    }
}
