package com.example;

public class GameLives implements IGameLives{
    private int lives;

    /**
     * Constructor for GameLives class.
     * Initializes the number of lives to 2 (3 because we count 0 lives as a life).
     */
    public GameLives() {
        this.lives = 2;
    }

    // getters and setters for the number of lives.
    public int getLives() {
        return lives;
    }

    // Rather than using a setter, we provide methods to modify lives, by just decreasing it.
    public void removeLife() {
        if (lives >= 0) {
            lives = lives -1;
        }
    }

    /**
     * Resets the number of lives to 2 (3 because we count 0 lives as a life).
     */
    public void resetLives() {
        this.lives = 2;
    }
}