package com.example;

public interface ICollideHandler{
    public void ghostCollision(Pacman pacman, Ghost ghost);
    public void pelletCollision(MoveableBlock entity, Block pellet);
    public void bigPelletCollision(MoveableBlock entity, Block pellet);
    public void fruitCollision(MoveableBlock entity, Block pellet);
}