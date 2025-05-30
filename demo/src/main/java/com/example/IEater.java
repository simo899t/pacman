package com.example;

/**
 * IEater interface defines differet methods for handling the eating actions of blocks in the game.
 */
public interface IEater {
    public void eatPellet(Pellet pellet);
    public void eatBigPellet(BigPellet pellet);
    public void eatGhost(Ghost ghost);
}
