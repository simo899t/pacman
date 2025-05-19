package com.example;

import com.example.Ghost.states;


public class Move {

    public void move(MoveableBlock entity) {
        if (entity.getType() == BlockType.GHOST) {
            Ghost ghost = (Ghost) entity;
            if (ghost.getState() == Ghost.states.EATEN && ghost.isHome() == true) {
                ghost.setState(states.CHASE);
            }
        }

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
    

