package com.example;

public class Collision {

    private int margin = 15;
    private IMap map;

    /**
     * Constructor for Collision class.
     * 
     * @param map The map on which the collision detection will be performed.
    */
    public Collision(IMap map) {
        this.map = map;
    }

    /**
     * check if a MoveableBlock collides with a Block. (with a margin)
     * @param entity
     * @param block
     * @return boolean indicating whether a collision occurred
     */
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
