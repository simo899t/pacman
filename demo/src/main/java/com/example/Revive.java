package com.example;

public class Revive {
    Revive() {
    }

    public void tryReviveGhost(Ghost ghost) {
        if (ghost.getState() == Ghost.states.EATEN) {
            ghost.setState(Ghost.states.CHASE);
            ghost.setEaten(false);
        }
    }
}
