package com.example;

import javafx.scene.image.Image;

public class MoveableBlock extends Block {
    enum direction {
        UP, DOWN, LEFT, RIGHT, NONE
    }

    protected int stepSize = 2;
    protected direction direction;
    protected direction bufferDirection;

    public MoveableBlock(Image image, int x, int y, String type) {
        super(image, x, y, type);
        this.direction = direction.NONE;
        this.bufferDirection = direction.NONE;
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        if (stepSize < 1) {
            this.stepSize = 1;
            return;
        }
        this.stepSize = stepSize;
    }

    public direction getDirection() {
        return direction;
    }

    public void setDirection(direction direction) {
        this.direction = direction;
    }
}
