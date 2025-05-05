package com.example;

public class GameScore {
    int score;
    int lives;

    public GameScore() {
        this.score = 0;
        this.lives = 3;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void removeLife() {
        if (lives > 0) {
            lives--;
        }
    }
}
