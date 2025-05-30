package com.example;
import javafx.scene.image.Image;


public class Block {
    private int x;
    private int y;
    private BlockType type;
    private Image image;

    /**
     * Enum representing the different all types of blocks in the game.
     */
    public enum BlockType {
        PACMAN,
        WALL,
        GHOST,
        BIGPELLET,
        PELLET,
        DOOR,
        TELEPORTER,
        GHOSTHOME,
        EMPTY
    }

    /**
     * Constructor for Block.
     *
     * @param image The image representing the block.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     */
    public Block(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    /**
     * Getters and Setters for Block properties.
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
       
    public void setImage(Image image) {
        this.image = image;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }
    
}