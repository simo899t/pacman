package com.example;

import javafx.scene.image.Image;

public class Ghost extends MoveableBlock {

    public enum states {
        STILL,
        CHASE,
        FRIGHTENED,
        EATEN
    }

    private states state;


    public Ghost(Image image, int x, int y) {
        super(image, x, y);
        this.state = states.STILL;

    }

    public states getState() {
        return state;
    }

    public void setState(states state) {
        this.state = state;
    }
}
