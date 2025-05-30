package com.example;

import javafx.scene.image.Image;

public class Wall extends Block {

    /**
     * Constructor for Wall that initializes the block with an image and position.
     * 
     * @param image The image representing the wall.
     * @param x The x-coordinate of the wall.
     * @param y The y-coordinate of the wall.
     */
    public Wall(Image image, int x, int y) {
        super(image, x, y);
    }
}
