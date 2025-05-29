package com.example;

import com.example.Ghost.states;
import com.example.MoveableBlock.directions;

import javafx.scene.image.Image;

public class KillEntity implements IKillEntity {

    private final Image blueGhostImage = new Image(getClass().getResource("/com/example/images/blueGhostRight.png").toExternalForm());
    private final Image pinkGhostImage = new Image(getClass().getResource("/com/example/images/pinkGhostRight.png").toExternalForm());
    private final Image orangeGhostImage = new Image(getClass().getResource("/com/example/images/orangeGhostRight.png").toExternalForm());

    private final IMap map;
    private final IGameLives lives;
    private final Pacman pacman;
    private final Ghost blueGhost;
    private final Ghost pinkGhost;
    private final Ghost orangeGhost;
    private final GameTimer ghostResetTimer;

    public KillEntity(IMap map, IGameLives lives, GameTimer ghostResetTimer) {
        this.map = map;
        this.lives = lives;
        this.ghostResetTimer = ghostResetTimer;
        this.pacman = map.getPacman();
        this.blueGhost = map.getBlueGhost();
        this.pinkGhost = map.getPinkGhost();
        this.orangeGhost = map.getOrangeGhost();
    }
        
    @Override
    public void killPlayer() {
        lives.removeLife();
        ghostResetTimer.gameTimerReset();
        
        pacman.setAlive(false);
        pacman.setDirection(directions.NONE);
        pacman.setBufferDirection(directions.NONE);
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
