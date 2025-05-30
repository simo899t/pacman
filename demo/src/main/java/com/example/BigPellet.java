package com.example;

import javafx.scene.image.Image;

public class BigPellet extends Pellet {

    // BigPellet inherits from Pellet, which implements IEatableBehavior

    // Constructor for BigPellet
    public BigPellet(Image image, int x, int y) {
        super(image, x, y);
    }
}