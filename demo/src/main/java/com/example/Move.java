package com.example;

public class Move implements IMove {

    @Override
    public void move(MoveableBlock entity) {
        System.out.println(entity.getDirection() + " move");
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
