package com.example;

public class Revive {
    private final IMap map;

    Revive(IMap map) {
        this.map = map;
    }

    public void tryReviveGhost(Ghost ghost) {
        if (ghost.getState() == Ghost.states.EATEN) {
            ghost.setState(Ghost.states.CHASE);
            ghost.setEaten(false);
        }
    }
}
