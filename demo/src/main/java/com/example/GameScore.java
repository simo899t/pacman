package com.example;

public class GameScore {
    private int score;

    public GameScore() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void resetScore() {
        this.score = 0;
    }
}
