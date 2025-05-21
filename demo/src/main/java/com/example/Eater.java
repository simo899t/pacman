package com.example;

public class Eater implements IEater {
    IMap map;
    Block pellet;
    GameScore score;
    GameLives lives;
    private final GameTimer gameTimer;
    private final Game game;
    private final UpdateImages updateImages;

    
    public Eater(IMap map, GameScore score, GameLives lives, GameTimer gameTimer, Game game, UpdateImages updateImages) {
        this.map = map;
        this.score = score;
        this.lives = lives;
        this.gameTimer = gameTimer;
        this.game = game;
        this.updateImages = updateImages;
    }

    private boolean tryEat(Pellet pellet, int points) {
        if (pellet.isEaten())
            return false;
        
        pellet.setEaten(true);
        pellet.setImage(null);
        map.removePellet();
        score.addScore(points);
        // System.out.println("Pellet eaten! Score: " + score.getScore());
        return true;
    }

    public void eatPellet(Pellet pellet) {
        tryEat(pellet, pellet.getPoints());
    }

    public void eatBigPellet(Pellet pellet) {
        if (!tryEat(pellet, pellet.getPoints())) 
            return;

        for (Block block : map.getAllBlocks()) {
            if (block instanceof Ghost) {
                Ghost ghost = (Ghost) block;
                if (ghost.getState() == Ghost.states.CHASE) {
                    ghost.setState(Ghost.states.FRIGHTENED);
                }
            }
        }

        long currentTime = System.currentTimeMillis();
        gameTimer.addFunctionToList(
            GameTimer.atTimeRunFunction(
                currentTime, 5000L, () -> {
                    for (Block block : map.getAllBlocks()) {
                        if (block instanceof Ghost) {
                            Ghost ghost = (Ghost) block;
                            game.setAllGhostsToChase();
                        }
                    }
                }
            )
        );
        // set the state of the ghosts to frightened"
    }


    public void eatGhost(Pellet ghost) {
        if (!tryEat(ghost, ghost.getPoints())) 
            return;
        // set the state of the ghosts to frightened"
    }
}
