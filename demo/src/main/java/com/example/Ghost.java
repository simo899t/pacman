package com.example;

import javafx.scene.image.Image;

public class Ghost extends MoveableBlock implements Eatable {

    private final Eatable eatableBehavior = new EatableBehavior(200);

    public enum states {
        STILL,
        CHASE,
        FRIGHTENED,
        EATEN
    }
    private states state;

    public enum color {
        BLUE,
        PINK,
        ORANGE,
        RED
    }
    private final color color;

    public Ghost(Image image, int x, int y, color colour) {
        super(image, x, y);
        this.state = states.STILL;
        this.color = colour;
    }

    public states getState() {
        return state;
    }

    public void setState(states state) {
        this.state = state;
    }

    @Override
    public boolean isEaten() {
        return eatableBehavior.isEaten();
    }

    @Override
    public void setEaten(boolean eaten) {
        eatableBehavior.setEaten(eaten);
        if (eaten) {
            setState(states.EATEN);
        } else {
            setState(states.STILL);
        }
    }

    @Override
    public int getPoints() {
        return eatableBehavior.getPoints();
    }

    public color getColor() {
        return color;
    }


}
