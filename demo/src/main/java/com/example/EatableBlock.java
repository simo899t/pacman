package com.example;

import javafx.scene.image.Image;

public class EatableBlock extends Block {
    private int scoreValue;
    private boolean eaten;

    public EatableBlock(Image image, int x, int y, int scoreValue) {
        super(image, x, y);
        this.scoreValue = scoreValue;
        this.eaten = false;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public boolean isEaten() {
        return eaten;
    }
}