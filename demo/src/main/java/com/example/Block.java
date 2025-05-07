package com.example;
import javafx.scene.image.Image;

public class Block {
    protected double x;
    protected double y;
    protected String type;
    protected Image image;

    public Block(Image image, double x, double y, String type) {
        this.type = type;
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
    
    public Image getImage() {
        return image;
    }   

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String getType() {
        return type;
    }
}