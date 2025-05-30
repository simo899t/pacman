package com.example;

public class Move {

    /**
     * Moves the entity in the direction it is facing by its step size.
     * 
     * @param entity The MoveableBlock entity to be moved.
     */
    public void move(MoveableBlock entity) {
        switch (entity.getDirection()) {
            case UP:
                entity.setPos(entity.getX(), entity.getY() - entity.getStepSize());
                break;
            case DOWN:
                entity.setPos(entity.getX(), entity.getY() + entity.getStepSize());
                break;
            case LEFT:
                entity.setPos(entity.getX() - entity.getStepSize(), entity.getY());
                break;
            case RIGHT:
                entity.setPos(entity.getX() + entity.getStepSize(), entity.getY());
                break;
            default:
                break;
        }
    }

}
    

