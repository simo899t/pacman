package com.example;

/**
 * ICollideHandler interface defines methods for handling collisions
 * between game entities such as ghosts, pellets, and doors.
 */
public interface ICollideHandler{
    public void ghostCollision(Ghost ghost);
    public void pelletCollision(Pellet pellet);
    public void bigPelletCollision(BigPellet pellet);
    public void doorCollision(Door door);
    public void homeCollision(Ghost ghost);
}