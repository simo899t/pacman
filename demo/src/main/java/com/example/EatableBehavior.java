package com.example;

public class EatableBehavior implements IEatableBehavior {
    private boolean eaten;
    private int points;

    public EatableBehavior() {
        this.eaten = false;
        this.points = 0;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
