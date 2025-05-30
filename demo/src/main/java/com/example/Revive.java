package com.example;

public class Revive {

    /**
     * Attempts to revive a ghost if it is in the EATEN state.
     * 
     * @param ghost The ghost to be revived.
     */
    public void tryReviveGhost(Ghost ghost) {
        if (ghost.getState() == Ghost.states.EATEN) {
            ghost.setState(Ghost.states.CHASE);
            ghost.setEaten(false);
        }
    }
}
