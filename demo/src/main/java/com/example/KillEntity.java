package com.example;

import com.example.Ghost.states;

public class KillEntity implements IKillEntity {

    GameLives gameLives;
    GameScore gameScore;

    IMap map;
    Pacman pacman;
    Ghost redGhost;
    Ghost blueGhost;
    Ghost pinkGhost;
    Ghost orangeGhost;

    public KillEntity(IMap map) {
        this.map = map;
        this.pacman = map.getPacman();
        this.redGhost = map.getRedGhost();
        this.blueGhost = map.getBlueGhost();
        this.pinkGhost = map.getPinkGhost();
        this.orangeGhost = map.getOrangeGhost();
        this.gameLives = new GameLives();
        this.gameScore = new GameScore();
    }
        
    @Override
    public void killPlayer() {
        pacman.setPos(pacman.getStartX(), pacman.getStartY());
        redGhost.setPos(redGhost.getStartX(), redGhost.getStartY());
        blueGhost.setPos(blueGhost.getStartX(), blueGhost.getStartY());
        pinkGhost.setPos(pinkGhost.getStartX(), pinkGhost.getStartY());
        orangeGhost.setPos(orangeGhost.getStartX(), orangeGhost.getStartY());
        gameLives.removeLife();
    }

    @Override
    public void killGhost(Ghost ghost) {
        ghost.setState(states.EATEN);
        gameScore.addScore(200);
    }
}
