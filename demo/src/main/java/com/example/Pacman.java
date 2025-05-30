package com.example;

import javafx.scene.image.Image;

public class Pacman extends MoveableBlock {
    private boolean alive = true;

    /**
     * Constructor for Pacman that initializes the block with an image and position.
     * 
     * @param image The image representing Pacman.
     * @param x The x-coordinate of Pacman.
     * @param y The y-coordinate of Pacman.
     */
    public Pacman(Image image, int x, int y) {
        super(image, x, y);
    }
    
    /**
     * Getter that tells if Pacman is alive or not.
     * 
     * @return If Pacman is alive or not.
     */
    public boolean isAlive() {
        return alive;
    }
    
    /**
     * Sets the alive state of Pacman.
     * 
     * @param livingState The new alive state of Pacman.
     */
    public void setAlive(boolean livingState) {
        this.alive = livingState;
    }
}
