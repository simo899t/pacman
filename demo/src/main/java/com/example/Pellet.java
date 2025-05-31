package com.example;

import javafx.scene.image.Image;

public class Pellet extends Block implements IEatableBehavior {
    private IEatableBehavior eatableBehavior;

    /**
     * Constructor for Pellet that initializes the block with an image and position.
     * 
     * @param image The image representing the pellet.
     * @param x The x-coordinate of the pellet.
     * @param y The y-coordinate of the pellet.
     */
    public Pellet(Image image, int x, int y, IEatableBehavior eatableBehavior) {
        super(image, x, y);
        this.eatableBehavior = eatableBehavior;
    }

    /**
     * Getter that tells if a pellet is eaten or not.
     * 
     * @return If a pellet is eaten or not.
     */
    @Override
    public boolean isEaten() {
        return eatableBehavior.isEaten();
    }

    /**
     * Sets the eaten state of the pellet.
     */
    @Override
    public void setEaten(boolean eaten) {
        eatableBehavior.setEaten(eaten);
    }

    /**
     * Sets the points for the pellet.
     */
    @Override
    public void setPoints(int points) {
        eatableBehavior.setPoints(points);
    }

    /**
     * Gets the points for the pellet.
     * 
     * @return The points for the pellet.
     */
    @Override
    public int getPoints() {
        return eatableBehavior.getPoints();
    }
}
