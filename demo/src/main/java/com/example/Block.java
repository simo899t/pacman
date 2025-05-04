package com.example;
import javafx.scene.image.Image;

public class Block {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Image image;

    public Block(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
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
    
    public Image getImage() {
        return image;
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }
}