package com.example;

public class GameLives {
    int lives;

    public GameLives(int maxLives) {
        this.lives = maxLives;
    }

    public int getLives() {
        return lives;
    }

    public void removeLife() {
        if (lives > 0) {
            lives=-1;
        }
    }

    public void addLife() {
        lives++;
    }

    public void resetLives() {
        this.lives = 3;
    }
}
