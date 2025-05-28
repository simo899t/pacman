package com.example;

import javafx.scene.image.Image;

public class Ghost extends MoveableBlock implements Eatable {

    private final Eatable eatableBehavior = new EatableBehavior(200);
    private states state;
    private final color color;

    public enum states {
        STILL,
        CHASE,
        FRIGHTENED,
        EATEN
    }

    public enum color {
        BLUE,
        PINK,
        ORANGE,
        RED
    }

    public Ghost(Image image, int x, int y, color colour) {
        super(image, x, y);
        this.state = states.STILL;
        this.color = colour;
    }

    public states getState() {
        return state;
    }

    /*
     * set the state of the ghost
     */
    public void setState(states state) {
        this.state = state;
        if (state == states.EATEN) {
            this.setPos(this.getX() - this.getX()%2 , this.getY() - this.getY()%2);
            setStepSize(2);
        } else setStepSize(1);
    }

    @Override
    public boolean isEaten() {
        return eatableBehavior.isEaten();
    }

    @Override
    public void setEaten(boolean eaten) {
        eatableBehavior.setEaten(eaten);
        if (eaten) {
            setState(states.EATEN);
        } else {
            setState(states.STILL);
        }
    }

    @Override
    public int getPoints() {
        return eatableBehavior.getPoints();
    }

    public color getColor() {
        return color;
    }

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
