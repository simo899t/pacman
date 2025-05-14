package com.example;

public class CollideHandler implements ICollideHandler {
    private final IMap map;
    private final IKillEntity killEntity;

    public CollideHandler(IMap map) {
        // 1) bind the map first
        this.map = map;
        // 2) now you can safely pass it into KillEntity
        this.killEntity = new KillEntity(map);
    }

    @Override
    public void ghostCollision(Pacman pacman, Ghost ghost) {
        System.err.println("OMG COLLISION HAPPENED!!!!");
        switch (ghost.getState()) {
            case CHASE:
                killEntity.killPlayer();
                break;
            case FRIGHTENED:
                killEntity.killGhost(ghost);
                break;
            case EATEN:
                break;
        }
    }

    @Override
    public void pelletCollision(MoveableBlock entity, Block pellet) {
        // eat(entity);
        // gameScore.addScore(10);
    }

    @Override
    public void bigPelletCollision(MoveableBlock entity, Block pellet) {
        // eat(entity);
        // gameScore.addScore(10);
        
    }

    @Override
    public void fruitCollision(MoveableBlock entity, Block pellet) {
        // eat(entity);
        // gameLives.addLife();
        
    }

    
    
}
