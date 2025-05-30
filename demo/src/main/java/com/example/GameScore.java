package com.example;

public class GameScore implements IGameScore{
    private int score;

    /**
     * Constructor for GameScore class.
     * Initializes the score to 0.
     */
    public GameScore() {
        this.score = 0;
    }

    // getters and setters for the score.
    public int getScore() {
        return score;
    }

    // Adds the given score to the current score.
    public void addScore(int score) {
        this.score += score;
    }

    // resets the score to 0.
    public void resetScore() {
        this.score = 0;
    }
}
