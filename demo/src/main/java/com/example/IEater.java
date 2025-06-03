package com.example;

/**
 * IEater interface defines differet methods for handling the eating actions of blocks in the game.
 */
public interface IEater {
    public void eat(Pellet pellet);
    public void eat(BigPellet pellet);
    public void eat(Ghost ghost);
}
