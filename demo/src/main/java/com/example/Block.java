package com.example;
import javafx.scene.image.Image;

public abstract class Block implements Collideable, Updateable, Drawable {

    enum direction {
        UP, DOWN, LEFT, RIGHT, NONE
    }

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double speed;
    protected Image image;
    protected direction dir;

    public Block(double x, double y, double width, double height, double speed, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.image = image;
        this.dir = direction.NONE;
    }

    public boolean isColliding(Block other) {
        return this.x < other.x + other.width && this.x + this.width > other.x &&
               this.y < other.y + other.height && this.y + this.height > other.y;
    }

    public void draw() {
        
    }
    
    public void update() {
        // Implement update logic based on direction and speed
        
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

}