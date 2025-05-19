package com.example;

public class GameLives {
    private int lives;

    public GameLives() {
        this.lives = 3;
    }

    public int getLives() {
        return lives;
    }

    public void removeLife() {
        if (lives > 0) {
            lives = lives -1;
        }
    }

    public void addLife() {
        if (lives < 3) {
            lives++;
        }
    }

    public void resetLives() {
        this.lives = 3;
    }
}
