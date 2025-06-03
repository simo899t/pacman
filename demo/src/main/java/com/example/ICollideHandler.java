package com.example;

import com.example.MoveableBlock.directions;

/**
 * ICollideHandler interface defines methods for handling collisions
 * between game entities such as ghosts, pellets, and doors.
 */
public interface ICollideHandler {
    void collision(Block block);
    void homeCollision(Ghost ghost);
    Block nextBlock(Block block, directions direction);
}