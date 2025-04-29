package com.example;
import javafx.scene.image.Image;

public abstract class Block {

    enum direction {
        UP, DOWN, LEFT, RIGHT, NONE
    }

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double stepSize;
    protected Image image;
    protected direction direction;

    public Block(double x, double y, double width, double height, double speed, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.stepSize = stepSize;
        this.image = image;
        this.direction = direction.NONE;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getStepSize() {
        return stepSize;
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void setDirection(direction direction) {
        this.direction = direction;
    }

}