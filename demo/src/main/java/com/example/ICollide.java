package com.example;

public interface ICollide{
    public void ghostCollision(MoveableBlock entity);
    public void pelletCollision(MoveableBlock entity);
    public void bigPelletCollision(MoveableBlock entity);
    public void fruitCollision(MoveableBlock entity);

}