package com.example;

import javafx.scene.image.Image;

public class MoveableBlock extends Block {
    public enum direction {
        UP, DOWN, LEFT, RIGHT, NONE
    }

    private int stepSize = 2;
    private direction direction;
    private direction bufferDirection;
    private int startX;
    private int startY;

    public MoveableBlock(Image image, int x, int y) {
        super(image, x, y);
        this.direction = direction.NONE;
        this.bufferDirection = direction.NONE;
        this.startX = x;
        this.startY = y;
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
    
    public direction getBufferDirection() {
        return bufferDirection;
    }

    public void setBufferDirection(direction bufferDirection) {
        this.bufferDirection = bufferDirection;
    }

    public int getStartX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }
    
}
