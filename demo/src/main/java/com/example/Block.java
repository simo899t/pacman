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
    protected double speed;
}