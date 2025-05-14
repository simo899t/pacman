package com.example;

public class CollideHandler implements ICollideHandler {

    IMap map;

    public CollideHandler(IMap map) {
        this.map = map;
    }
    IKillEntity killEntity = new KillEntity(map);

    @Override
    public void ghostCollision(Ghost ghost, Pacman pacman) {
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
