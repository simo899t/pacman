package com.example;

public class CollideHandler implements ICollideHandler {
    private final IMap map;
    private final IKillEntity killEntity;
    private final Eater eater;
    private final GameTimer gameTimer;
    private final Game game;
    private final UpdateImages updateImages;

    public CollideHandler(IMap map, GameScore score, GameLives lives, GameTimer gameTimer, Game game, UpdateImages updateImages) {
        // 1) bind the map first
        this.map = map;
        // 2) now you can safely pass it into KillEntity
        this.killEntity = new KillEntity(map, score, lives);
        this.eater = new Eater(map, score, lives);
        this.gameTimer = gameTimer;
        this.game = game;
        this.updateImages = updateImages;
    }

    @Override
    public void ghostCollision(Ghost ghost) {
        switch (ghost.getState()) {
            case CHASE:
                killEntity.killPlayer();
                break;
            case FRIGHTENED:
                killEntity.killGhost(ghost);
                break;
            case EATEN:
                break;
            default:
                break;
        }
    }

    @Override
    public void doorCollision(Block door) {
        door.openDoor();

        long currentTime = System.currentTimeMillis();
        gameTimer.addFunctionToList(
            GameTimer.atTimeRunFunction(
                currentTime, 1000L, () -> {
                    door.closeDoor();
                }
            )
        );

    }

    @Override
    public void pelletCollision(Pellet pellet) {
        eater.eatPellet(pellet);
    }

    @Override
    public void bigPelletCollision(Pellet pellet) {
        eater.eatBigPellet(pellet);

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
    }


    @Override
    public void teleporterCollision(Pacman pacman) {
        // TODO Auto-generated method stub
        // eat(entity);
        // gameLives.addLife();
        
    }

    
    

    
    
}
