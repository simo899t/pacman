package com.example;

public class EatableBehavior implements Eatable {
    private boolean eaten;
    private int points;

    public EatableBehavior(int points) {
        this.eaten = false;
        this.points = points;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public int getPoints() {
        return points;
    }
}
