package com.example;
import javafx.scene.image.Image;


public class Block {
    private int x;
    private int y;
    private BlockType type;
    private Image image;

    public enum BlockType {
        PACMAN,
        WALL,
        GHOST,
        BIGPELLET,
        PELLET,
        DOOR,
        FRUIT,
        TELEPORTER,
        GHOSTHOME,
        EMPTY
    }

    public Block(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

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