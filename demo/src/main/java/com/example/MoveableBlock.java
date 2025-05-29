package com.example;

import javafx.scene.image.Image;

public class MoveableBlock extends Block {
    public enum directions {
        UP, DOWN, LEFT, RIGHT, NONE
    }

    private int stepSize = 2;
    private directions direction = directions.NONE;
    private directions bufferDirection = directions.NONE;
    private int startX;
    private int startY;

    public MoveableBlock(Image image, int x, int y) {
        super(image, x, y);
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

    public directions getDirection() {
        return direction;
    }

    public void setDirection(directions direction) {
        this.direction = direction;
    }
    
    public directions getBufferDirection() {
        return bufferDirection;
    }

    public void setBufferDirection(directions bufferDirection) {
        this.bufferDirection = bufferDirection;
    }

    public int getStartX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }
    
}
