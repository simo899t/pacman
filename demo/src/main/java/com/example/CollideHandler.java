package com.example;

public class CollideHandler implements ICollideHandler {
    private final IKillEntity killEntity;
    private final IEater eater;
    private final GameTimer gameTimer;
    private final Revive revive;

    public CollideHandler(IMap map, IGameScore score, IGameLives lives, GameTimer gameTimer, App game, UpdateImages updateImages, Revive revive) {
        // 1) bind the map first
        // 2) now you can safely pass it into KillEntity
        this.killEntity = new KillEntity(map, score, lives);
        this.eater = new Eater(map, score, lives, gameTimer, game, updateImages);
        this.gameTimer = gameTimer;
        this.revive = revive;
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
    public void homeCollision(Ghost ghost) {
        revive.reviveGhost(ghost);
    }

    @Override
    public void doorCollision(Door door) {
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
    }


    @Override
    public void teleporterCollision(Pacman pacman) {
    }

    
    

    
    
}
