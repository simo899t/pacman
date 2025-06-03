package com.example;

/**
 * IUpdate interface defines methods for updating the game state and entities.
 * It includes methods for updating the direction of individual entities, and determining the next block based on direction.
 */
public interface IUpdate {
    public void updateGame(IMap map);
    public void updateEntity(MoveableBlock entity);
}
