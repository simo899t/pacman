package com.example;

public class Collision {

    private int margin = 15;
    private IMap map;

    public Collision(IMap map) {
        this.map = map;
    }

    public boolean checkCollision(MoveableBlock entity, Block block) {
        if (entity.getX() < block.getX() + (map.getTileSize() - margin) &&
            entity.getX() + (map.getTileSize() - margin) > block.getX() &&
            entity.getY() < block.getY() + (map.getTileSize() - margin) &&
            entity.getY() + (map.getTileSize() - margin) > block.getY()) {
        return true;
        } else {
            return false;
        }
    }
}
