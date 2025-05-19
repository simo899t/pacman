package com.example;

public class CollideHandler implements ICollideHandler {
    private final IMap map;
    private final IKillEntity killEntity;
    private final Eater eater;

    public CollideHandler(IMap map, GameScore score, GameLives lives) {
        // 1) bind the map first
        this.map = map;
        // 2) now you can safely pass it into KillEntity
        this.killEntity = new KillEntity(map, score, lives);
        this.eater = new Eater(map, score, lives);
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
            ghost.setState(Ghost.states.FRIGHTENED);
        }
}
    }

    @Override
    public void fruitCollision(Fruit pellet) {
        // eat(entity);
        // gameLives.addLife();
        
    }

    @Override
    public void teleporterCollision(Pacman pacman) {
        // TODO Auto-generated method stub
        // eat(entity);
        // gameLives.addLife();
        
    }

    
    

    
    
}
