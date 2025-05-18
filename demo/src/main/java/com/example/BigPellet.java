package com.example;

import javafx.scene.image.Image;

public class BigPellet extends Block implements Eatable {
    private final Eatable eatableBehavior = new EatableBehavior(50);

    public BigPellet(Image image, int x, int y) {
        super(image, x, y);
        boolean eaten = false;
    }

    @Override
    public boolean isEaten() {
        return eatableBehavior.isEaten();
    }
    
    @Override
    public void setEaten(boolean eaten) {
        eatableBehavior.setEaten(eaten);
    }

    @Override
    public int getPoints() {
        return eatableBehavior.getPoints();
    }
}