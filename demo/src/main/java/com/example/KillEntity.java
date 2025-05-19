package com.example;

import com.example.Ghost.states;
import com.example.MoveableBlock.direction;

public class KillEntity implements IKillEntity {

    private final IMap map;
    private final GameScore score;
    private final GameLives lives;
    private final Pacman pacman;
    private final Ghost redGhost;
    private final Ghost blueGhost;
    private final Ghost pinkGhost;
    private final Ghost orangeGhost;

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
        lives.removeLife();
        
        pacman.setDirection(direction.NONE);
        pacman.setBufferDirection(direction.NONE);
        redGhost.setDirection(direction.NONE);
        redGhost.setBufferDirection(direction.NONE);
        blueGhost.setDirection(direction.NONE);
        blueGhost.setBufferDirection(direction.NONE);
        pinkGhost.setDirection(direction.NONE);
        pinkGhost.setBufferDirection(direction.NONE);
        orangeGhost.setDirection(direction.NONE);
        orangeGhost.setBufferDirection(direction.NONE);

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
