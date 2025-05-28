package com.example;

public interface IEatableBehavior {
    boolean isEaten();
    void setEaten(boolean eaten);
    void setPoints(int points);
    int getPoints();
}
