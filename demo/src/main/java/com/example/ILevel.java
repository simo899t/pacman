package com.example;

/**
 * The ILevel interface defines the contract for managing the game level
 */
public interface ILevel {
    int getLevel();
    void incrementLevel();
    void resetLevel();
}
