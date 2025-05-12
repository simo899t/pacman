package com.example;

public class Collide implements ICollide{
    
    IMap map;

    public Collide(IMap map) {
        this.map = map;
    }

    @Override
    public boolean isColliding(Block block1, Block block2, IMap map) {
        return block1.getX() < block2.getX() + map.getTileSize() &&
               block1.getX() + map.getTileSize() > block2.getX() &&
               block1.getY() < block2.getY() + map.getTileSize() &&
               block1.getY() + map.getTileSize() > block2.getY();
    }
}
