package com.example;

/**
 * IGameLives interface defines methods for managing the lives of a player in the game.
 */
public interface IGameLives {
    public int getLives();
    public void removeLife();
    public void resetLives();
}
