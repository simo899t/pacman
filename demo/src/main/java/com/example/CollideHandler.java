package com.example;

public class CollideHandler implements ICollideHandler {
    private final IKillEntity killEntity;
    private final IEater eater;
    private final GameTimer gameTimer;
    private final Revive revive;

    public CollideHandler(GameTimer gameTimer, Revive revive, IEater eater, IKillEntity killEntity) {
        this.killEntity = killEntity;
        this.eater = eater;
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
                "OpenTheDoor", currentTime, 1000L, () -> {
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
