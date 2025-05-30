package com.example;

import javafx.scene.image.Image;

public class Pacman extends MoveableBlock {
    private boolean alive = true;

    public Pacman(Image image, int x, int y) {
        super(image, x, y);
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public void setAlive(boolean livingState) {
        this.alive = livingState;
    }
}
