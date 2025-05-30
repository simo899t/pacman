package com.example;

/**
 * IKillEntity interface defines methods for killing entities in the game.
 */
public interface IKillEntity {
    public void killPlayer();
    public void killGhost(Ghost ghost);
}
