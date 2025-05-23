package com.example;

public class Revive {
    IMap map;

    Revive(IMap map) {
        this.map = map;
    }

    public void reviveGhost(Ghost ghost) {
        System.out.println(ghost.getX() + " " + ghost.getY());
        System.out.println(map.getGhostHome().getX() + " " + map.getGhostHome().getY());
        if (ghost.getState() == Ghost.states.EATEN) {
            System.out.println("Ghost is revived");
            ghost.setState(Ghost.states.CHASE);
        }
    }
}
