package com.example;

public class EatableBehavior implements IEatableBehavior {
    private boolean eaten;
    private int points;

    /**
     * Constructor for EatableBehavior class.
     * Initializes the eaten state to false and points to 0.
     */
    public EatableBehavior() {
        this.eaten = false;
        this.points = 0;
    }

    
    // getters and setters for the eaten state and points.
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
