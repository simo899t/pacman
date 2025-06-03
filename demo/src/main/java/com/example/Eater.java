package com.example;

import com.example.Block.BlockType;
import javafx.scene.image.Image;

public class Eater implements IEater {
    private final IMap map;
    private final IGameScore score;
    private final GameTimer gameTimer;
    private final long howLongGhostFrightened = 5000L;

    /**
     * Constructor for Eater.
     *
     * @param map The game map.
     * @param score The game score.
     * @param gameTimer The game timer.
     */
    public Eater(IMap map, IGameScore score, GameTimer gameTimer) {
        this.map = map;
        this.score = score;
        this.gameTimer = gameTimer;
    }

    /**
     * This method tries to eat a pellet. If the pellet is already eaten, it returns false.
     * If the pellet is successfully eaten, it updates the score and the pellesLeft
     * Then returns true.
     *
     * @param pellet The pellet to be eaten.
     * @param points The points awarded for eating the pellet.
     * @return true if the pellet was successfully eaten, false otherwise.
     */
    private boolean tryEat(Pellet pellet, int points) {
        if (pellet.isEaten()){
            return false;
        }
        pellet.setEaten(true);
        pellet.setImage(null);
        map.decreasePelletsLeft();
        score.addScore(points);
        return true;
    }

    /**
     * This method uses tryEat to eat a pellet.
     *
     * @param pellet The pellet to be eaten.
     */
    public void eat(Pellet pellet) {
        tryEat(pellet, pellet.getPoints());
    }

    /**
     * This method uses tryEat to eat a big pellet.
     * If the pellet is successfully eaten, it sets all ghosts to frightened state
     *
     * @param pellet The big pellet to be eaten.
     */
    public void eat(BigPellet bigPellet) {
        if (!tryEat((Pellet) bigPellet, ((Pellet) bigPellet).getPoints())) {
            return;
        }

        // If the pellet was successfully eaten, set all ghosts to frightened state
        for (Block block : map.getAllBlocks()) {
            if (block.getType() == BlockType.GHOST) {
                Ghost ghost = (Ghost) block;
                if (ghost.getState() != Ghost.states.EATEN && ghost.getState() != Ghost.states.STILL) {
                    ghost.setState(Ghost.states.FRIGHTENED);

                    Image whiteScaredGhost = new Image(getClass().getResource("/com/example/images/scaredGhostnegative.png").toExternalForm());
                    Image blueScaredGhost = new Image(getClass().getResource("/com/example/images/scaredGhost.png").toExternalForm());

                    long currentTime = System.currentTimeMillis();
                    ghost.setImage(blueScaredGhost);
                    String ghostBlinkTitle = ghost.getColor().toString() + "GhostFrightenedBlink";

                    
                    // Schedule the ghost to blink while frightened with functions in the game timer
                    // The ghost will switch 4 times, starting 2 seconds after it is frightened
                    gameTimer.addFunctionToList(
                        GameTimer.atTimeRunFunction(
                            ghostBlinkTitle+"Start", currentTime, howLongGhostFrightened-2000L, () -> {
                                if (ghost.getState() == Ghost.states.FRIGHTENED) ghost.setImage(whiteScaredGhost);
                            }
                        )
                    );

                    gameTimer.addFunctionToList(
                        GameTimer.atTimeRunFunction(
                            ghostBlinkTitle+"1", currentTime, howLongGhostFrightened-1500L, () -> {
                                if (ghost.getState() == Ghost.states.FRIGHTENED) ghost.setImage(blueScaredGhost);
                            }
                        )
                    );

                    gameTimer.addFunctionToList(
                        GameTimer.atTimeRunFunction(
                            ghostBlinkTitle+"2", currentTime, howLongGhostFrightened-1000L, () -> {
                                if (ghost.getState() == Ghost.states.FRIGHTENED) ghost.setImage(whiteScaredGhost);
                            }
                        )
                    );

                    gameTimer.addFunctionToList(
                        GameTimer.atTimeRunFunction(
                            ghostBlinkTitle+"3", currentTime, howLongGhostFrightened-500L, () -> {
                                if (ghost.getState() == Ghost.states.FRIGHTENED) ghost.setImage(blueScaredGhost);
                            }
                        )
                    );
                
                    // After the frightened time has passed, set all ghosts to chase state
                    gameTimer.addFunctionToList(
                        GameTimer.atTimeRunFunction(
                            "BigPelletEaten", currentTime, howLongGhostFrightened, () -> {        
                                setAllGhostsToChase();       
                            }
                        )
                    );
                }
                

            }
        }
    }


    /* 
     * This method is called when Pacman eats a ghost. It checks if the ghost is in the frightened state and if so, it sets it to eaten state.
     */
    @Override
    public void eat(Ghost ghost) {
        score.addScore(ghost.getPoints());
    }

    /*
     * This method sets all ghosts to chase state. It is called when the big pellet timer expires.
     */
    public void setAllGhostsToChase() {
        for (Block block : map.getAllBlocks()) {
            if (block instanceof Ghost) {
                Ghost ghost = (Ghost) block;
                if (ghost.getState() == Ghost.states.FRIGHTENED) {
                    ghost.setState(Ghost.states.CHASE);
                }
            }
        }
            
    }
}
