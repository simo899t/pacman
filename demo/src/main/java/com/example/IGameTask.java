package com.example;

/**
 * IGameTask interface defines a task that can be run in the game.
 */
public interface IGameTask {
    boolean runFunction(); // return true if done, false if should stay in list
    String getName();
    long getStartTime();
}