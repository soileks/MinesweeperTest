package ru.petrov.test.model;


public class TurnRequest {
    private String game_id;
    private int col;
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

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public int getRow() {
        return row;
    }
}
