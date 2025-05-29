package com.example;

import com.example.Block.BlockType;
import javafx.scene.image.Image;

public class Eater implements IEater {
    private final IMap map;
    private final IGameScore score;
    private final GameTimer gameTimer;
    private final long howLongGhostFrightened = 5000L;

    public Eater(IMap map, IGameScore score, GameTimer gameTimer) {
        this.map = map;
        this.score = score;
        this.gameTimer = gameTimer;
    }

    private boolean tryEat(Pellet pellet, int points) {
        if (pellet.isEaten())
            return false;
        
        pellet.setEaten(true);
        pellet.setImage(null);
        map.decreasePelletsLeft();
        score.addScore(points);
        return true;
    }

    public void eatPellet(Pellet pellet) {
        tryEat(pellet, pellet.getPoints());
    }

    public void eatBigPellet(Pellet pellet) {
        if (!tryEat(pellet, pellet.getPoints())) {
            return;
        }

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
    public void eatGhost(Pellet ghost) {
        tryEat(ghost, ghost.getPoints());
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
