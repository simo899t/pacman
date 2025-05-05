package com.example;

public class Collide implements ICollide {
    IMap map = new Map();

    public Collide() {
        
    }

    public boolean isColliding(Block block1, Block block2) {
        if (block1.getX() < block2.getX() + map.getTileSize() &&
                block1.getX() + map.getTileSize() > block2.getX() &&
                block1.getY() < block2.getY() + map.getTileSize() &&
                block1.getY() + map.getTileSize() > block2.getY()) {
            return true;
        } else {
            return false;
        }
    }
}
