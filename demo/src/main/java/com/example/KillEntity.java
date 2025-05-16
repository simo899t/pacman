package com.example;

import com.example.Ghost.states;
import com.example.MoveableBlock.direction;

public class KillEntity implements IKillEntity {

    IMap map;
    GameScore score;
    GameLives lives;
    Pacman pacman;
    Ghost redGhost;
    Ghost blueGhost;
    Ghost pinkGhost;
    Ghost orangeGhost;

    public KillEntity(IMap map, GameScore score, GameLives lives) {
        this.map = map;
        this.score = score;
        this.lives = lives;
        this.pacman = map.getPacman();
        this.redGhost = map.getRedGhost();
        this.blueGhost = map.getBlueGhost();
        this.pinkGhost = map.getPinkGhost();
        this.orangeGhost = map.getOrangeGhost();
    }
        
    @Override
    public void killPlayer() {
        pacman.setDirection(direction.NONE);
        redGhost.setDirection(direction.NONE);
        blueGhost.setDirection(direction.NONE);
        pinkGhost.setDirection(direction.NONE);
        orangeGhost.setDirection(direction.NONE);
        

        pacman.setPos(pacman.getStartX(), pacman.getStartY());
        redGhost.setPos(redGhost.getStartX(), redGhost.getStartY());
        blueGhost.setPos(blueGhost.getStartX(), blueGhost.getStartY());
        pinkGhost.setPos(pinkGhost.getStartX(), pinkGhost.getStartY());
        orangeGhost.setPos(orangeGhost.getStartX(), orangeGhost.getStartY());
    }

    @Override
    public void killGhost(Ghost ghost) {
        ghost.setState(states.EATEN);
    }
}
