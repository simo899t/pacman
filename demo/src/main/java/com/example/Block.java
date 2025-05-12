package com.example;
import javafx.scene.image.Image;

enum BlockType {
    PACMAN,
    WALL,
    GHOST,
    BIGPELLET,
    PELLET,
    DOOR
}

public class Block {
    protected int x;
    protected int y;
    protected BlockType type;
    protected Image image;

    public Block(Image image, int x, int y) {
        this.type = type;
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

    public void getBlock() {

    }
}