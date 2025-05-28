package com.example;

import com.example.Ghost.states;
import com.example.MoveableBlock.direction;

import javafx.scene.image.Image;

public class KillEntity implements IKillEntity {

    private final Image blueGhostImage = new Image(getClass().getResource("/com/example/images/blueGhostRight.png").toExternalForm());
    private final Image pinkGhostImage = new Image(getClass().getResource("/com/example/images/pinkGhostRight.png").toExternalForm());
    private final Image orangeGhostImage = new Image(getClass().getResource("/com/example/images/orangeGhostRight.png").toExternalForm());

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
        ghostResetTimer.gameTimerReset();
        
        pacman.setAlive(false);
        pacman.setDirection(direction.NONE);
        pacman.setBufferDirection(direction.NONE);
        pacman.setPos(pacman.getStartX(), pacman.getStartY());
        
        map.resetAllGhosts();
        
        pinkGhost.setImage(pinkGhostImage);
        blueGhost.setImage(blueGhostImage);
        orangeGhost.setImage(orangeGhostImage);
    }

    @Override
    public void killGhost(Ghost ghost) {
        ghost.setState(states.EATEN);
        ghost.setEaten(true);
    }
}
