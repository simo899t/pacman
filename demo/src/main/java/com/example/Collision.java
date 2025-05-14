package com.example;

public class Collision {

    private IMap map;

    public Collision(IMap map) {
        this.map = map;
    }

    public boolean checkCollision(MoveableBlock entity, Block block) {
        if (entity.getX() < block.getX() + map.getTileSize() &&
            entity.getX() + map.getTileSize() > block.getX() &&
            entity.getY() < block.getY() + map.getTileSize() &&
            entity.getY() + map.getTileSize() > block.getY()) {
        return true;
        } else {
            return false;
        }
    }
}
