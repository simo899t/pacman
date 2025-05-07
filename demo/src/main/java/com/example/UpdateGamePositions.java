package com.example;

public class UpdateGamePositions implements IUpdateGamePositions {

    private IMap map;
    public UpdateGamePositions(IMap map) {
        this.map = map;
    }

    @Override
    public void updateGamePositions() {
        for (Block block : map.getAllBlocks()) {
            if (block instanceof MoveableBlock) {
                MoveableBlock moveableBlock = (MoveableBlock) block;
                move(moveableBlock);
            }
        }
    }

    public void move(MoveableBlock entity) {
        switch (entity.getDirection()) {
            case UP:
                entity.setPos(entity.getX(), entity.getY() - entity.getStepSize());
                
                // System.out.println(entity.getX() + " " + entity.getY());
                break;
            case DOWN:
                entity.setPos(entity.getX(), entity.getY() + entity.getStepSize());
                // System.out.println(entity.getX() + " " + entity.getY());
                break;
            case LEFT:
                entity.setPos(entity.getX() - entity.getStepSize(), entity.getY());
                // System.out.println(entity.getX() + " " + entity.getY());
                break;
            case RIGHT:
                entity.setPos(entity.getX() + entity.getStepSize(), entity.getY());
                // System.out.println(entity.getX() + " " + entity.getY());
                break;
            default:
                break;
        }
    }
    
}
