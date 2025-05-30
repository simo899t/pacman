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

    /**
     * Constructor for KillEntity.
     * @param map The game map.
     * @param lives The game lives. (needs to change when player dies)
     * @param ghostResetTimer The game timer for resetting ghosts.
     */
    public KillEntity(IMap map, IGameLives lives, GameTimer ghostResetTimer) {
        this.map = map;
        this.lives = lives;
        this.ghostResetTimer = ghostResetTimer;
        this.pacman = map.getPacman();
        this.blueGhost = map.getBlueGhost();
        this.pinkGhost = map.getPinkGhost();
        this.orangeGhost = map.getOrangeGhost();
    }
    
    /**
     * When the player dies, this method is called.
     * It resets the player's position, lives, position and alive state.
     * It also resets the ghosts' positions and images.
     */
    @Override
    public void killPlayer() {
        lives.removeLife();
        ghostResetTimer.gameTimerReset();
        
        pacman.setDirection(directions.NONE);
        pacman.setBufferDirection(directions.NONE);
        pacman.setPos(pacman.getStartX(), pacman.getStartY());

        map.resetAllGhosts();

        if (pinkGhost != null) 
            pinkGhost.setImage(pinkGhostImage);
        if (blueGhost != null)
            blueGhost.setImage(blueGhostImage);
        if (orangeGhost != null)
            orangeGhost.setImage(orangeGhostImage);

        pacman.setAlive(false);
    }   

    /**
     * This method is called when a ghost is killed by Pacman.
     * It sets the ghost's state to EATEN and marks it as eaten.
     * 
     * @param ghost The ghost
     */
    @Override
    public void killGhost(Ghost ghost) {
        ghost.setState(states.EATEN);
        ghost.setEaten(true);
    }
}
