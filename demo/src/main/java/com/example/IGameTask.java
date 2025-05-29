package com.example;

public interface IGameTask {
    boolean runFunction(); // return true if done, false if should stay in list
    String getName();
    long getStartTime();
}