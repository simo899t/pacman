package com.example;

/**
 * IGameScore interface defines methods for managing the players score in the game.
 */
public interface IGameScore {
    public int getScore();
    public void addScore(int score);
    public void resetScore();
}
