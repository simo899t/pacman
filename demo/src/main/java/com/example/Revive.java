package com.example;

public class Revive {
    IMap map;

    Revive(IMap map) {
        this.map = map;
    }

    public void reviveGhost(Ghost ghost) {
        if (ghost.getState() == Ghost.states.EATEN) {
            ghost.setState(Ghost.states.CHASE);
        }
    }
}
