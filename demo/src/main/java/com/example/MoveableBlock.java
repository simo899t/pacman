package com.example;

import javafx.scene.image.Image;

public class MoveableBlock extends Block {
    private int stepSize = 2;
    private directions direction = directions.NONE;
    private directions bufferDirection = directions.NONE;
    private final int startX;
    private final int startY;
    
    /**
     * Enum representing the possible movement directions for a MoveableBlock.
     */
    public enum directions {
        UP, DOWN, LEFT, RIGHT, NONE
    }
    
    /**
     * Constructor for MoveableBlock that initializes the block with an image and position.
     * 
     * @param image The image representing the moveable block.
     * @param x The x-coordinate of the moveable block.
     * @param y The y-coordinate of the moveable block.
     */
    public MoveableBlock(Image image, int x, int y) {
        super(image, x, y);
        this.startX = x;
        this.startY = y;
    }

    /**
     * Gets the current step size for the moveable block.
     * 
     * @return The current step size of the moveable block.
     */
    public int getStepSize() {
        return stepSize;
    }

    /**
     * Sets the step size for the moveable block. If the provided step size is less than 1, it defaults to 1.
     * 
     * @param stepSize The new step size for the moveable block.
     */
    public void setStepSize(int stepSize) {
        if (stepSize < 1) {
            this.stepSize = 1;
            return;
        }
        this.stepSize = stepSize;
    }

    /**
     * Getter for the direction of the moveable block.
     * 
     * @return The current direction of the moveable block.
     */
    public directions getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the moveable block.
     * 
     * @param direction The new direction for the moveable block.
     */
    public void setDirection(directions direction) {
        this.direction = direction;
    }
    
    /**
     * Gets the buffer direction of the moveable block.
     * 
     * @return The current buffer direction of the moveable block.
     */
    public directions getBufferDirection() {
        return bufferDirection;
    }

    /**
     * Sets the buffer direction of the moveable block.
     * 
     * @param bufferDirection The new buffer direction for the moveable block.
     */
    public void setBufferDirection(directions bufferDirection) {
        this.bufferDirection = bufferDirection;
    }

    // Getters for the starting position of the moveable block.
    public int getStartX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }
    
}
