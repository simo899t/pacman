package com.example;

import javafx.scene.image.Image;

public class Fruit extends Block {
    private boolean eaten;

    public Fruit(Image image, int x, int y) {
        super(image, x, y);
        this.eaten = false;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }
}
