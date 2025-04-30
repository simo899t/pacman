package com.example;

public class Update implements IUpdate {

    private Pacman pacman;
    private GhostRed ghostRed;
    private GhostBlue ghostBlue;
    private GhostPink ghostPink;
    private GhostOrange ghostOrange;

    @Override
    public void update() {
        move(pacman);
        move(ghostRed);
        move(ghostBlue);
        move(ghostPink);
        move(ghostOrange);
    }

    public void move(Block entity) {
        switch (entity.getDirection()) {
            case UP:
                entity.setPos(entity.getX(), entity.getY() + entity.getStepSize());
                System.out.println("Moving up");
                break;
            case DOWN:
                entity.setPos(entity.getX(), entity.getY() - entity.getStepSize());
                System.out.println("Moving down");
                break;
            case LEFT:
                entity.setPos(entity.getX() - entity.getStepSize(), entity.getY());
                System.out.println("Moving left");
                break;
            case RIGHT:
                entity.setPos(entity.getX() + entity.getStepSize(), entity.getY());
                System.out.println("Moving right");
                break;
            default:
                break;
        }
    }
    
}
