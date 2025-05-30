package com.example;

import javafx.scene.image.Image;

public class Ghost extends MoveableBlock implements IEatableBehavior {

    //helperclass for eatable behavior
    private final IEatableBehavior eatableBehavior = new EatableBehavior();
    private states state;
    private final color color;

    // Enum to represent the different states of the ghost
    public enum states {
        STILL,
        CHASE,
        FRIGHTENED,
        EATEN
    }

    // Enum to represent the different colors of the ghost
    public enum color {
        BLUE,
        PINK,
        ORANGE,
        RED
    }

    /**
     * Constructor for Ghost
     * @param image
     * @param x
     * @param y
     * @param colour color of the ghost
     */
    public Ghost(Image image, int x, int y, color colour) {
        super(image, x, y);
        this.state = states.STILL; // Initial state is STILL so the ghost does not move before the game starts
        this.color = colour;
    }

    /**
     * get the state of the ghost
     * @return state
     */
    public states getState() {
        return state;
    }

    /**
     * set the state of the ghost
     * @param state state to set
     */
    public void setState(states state) {
        this.state = state;
        if (state == states.EATEN) {
            this.setPos(this.getX() - this.getX()%2 , this.getY() - this.getY()%2);
            setStepSize(2);
        } else setStepSize(1); // måske en ny klasse
    }


    // eatableBehavior getters and setters
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

    public color getColor() {
        return color;
    }

    /**
     * Resets the ghost's state to STILL and schedules a transition to CHASE state after a specified time.
     * @param time The time in milliseconds after which the ghost will transition to CHASE state.
     * @param gameTimer The game timer used to schedule the state change.
     */
    public void resetState(Long time, GameTimer gameTimer) {
        this.setState(states.STILL);

        String ghostName = this.color.toString() + "GhostResetFunction";
        long currentTime = System.currentTimeMillis();
        gameTimer.addFunctionToList(
            GameTimer.atTimeRunFunction(
                ghostName, currentTime, time, () -> {
                    this.setState(Ghost.states.CHASE);
                }
            )
        );
    }


}
