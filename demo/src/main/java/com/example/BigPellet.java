package com.example;

import javafx.scene.image.Image;

public class BigPellet extends Block implements IEatableBehavior {
    private final IEatableBehavior eatableBehavior = new EatableBehavior();

    public BigPellet(Image image, int x, int y) {
        super(image, x, y);
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
    public void setPoints(int points) {
        eatableBehavior.setPoints(points);
    }

    @Override
    public int getPoints() {
        return eatableBehavior.getPoints();
    }
}