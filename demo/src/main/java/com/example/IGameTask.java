package com.example;

public interface IGameTask {
    boolean run(); // return true if done, false if should stay in list
    String getName();
    long getStartTime();
}