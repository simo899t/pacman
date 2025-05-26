package com.example;

import com.example.Ghost.states;
import com.example.MoveableBlock.direction;

import javafx.scene.image.Image;

public class KillEntity implements IKillEntity {

    Image blueGhostImage = new Image(getClass().getResource("/com/example/images/blueGhostRight.png").toExternalForm());
    Image pinkGhostImage = new Image(getClass().getResource("/com/example/images/pinkGhostRight.png").toExternalForm());
    Image orangeGhostImage = new Image(getClass().getResource("/com/example/images/orangeGhostRight.png").toExternalForm());

    private final IMap map;
    private final IGameScore score;
    private final IGameLives lives;
    private final Pacman pacman;
    private final Ghost redGhost;
    private final Ghost blueGhost;
    private final Ghost pinkGhost;
    private final Ghost orangeGhost;
    private final GameTimer ghostResetTimer;

    public KillEntity(IMap map, IGameScore score, IGameLives lives, GameTimer ghostResetTimer) {
        this.map = map;
        this.score = score;
        this.lives = lives;
        this.ghostResetTimer = ghostResetTimer;
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

        pinkGhost.resetState(8000L, ghostResetTimer);
        blueGhost.resetState(12000L, ghostResetTimer);
        orangeGhost.resetState(15000L, ghostResetTimer);
        
        pinkGhost.setImage(pinkGhostImage);
        blueGhost.setImage(blueGhostImage);
        orangeGhost.setImage(orangeGhostImage);
    }

    @Override
    public void killGhost(Ghost ghost) {
        ghost.setState(states.EATEN);

    }
}
