package com.example;

/**
 * IEatableBehavior interface defines the contract for setting and getting
 * the eaten state and points of an entity in the game.
 */
public interface IEatableBehavior {
    boolean isEaten();
    void setEaten(boolean eaten);
    void setPoints(int points);
    int getPoints();
}
