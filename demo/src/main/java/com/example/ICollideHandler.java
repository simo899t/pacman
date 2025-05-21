package com.example;

public interface ICollideHandler{
    public void ghostCollision(Ghost ghost);
    public void pelletCollision(Pellet pellet);
    public void bigPelletCollision(Pellet pellet);
    public void doorCollision(Block door);
    public void teleporterCollision(Pacman pacman);
}