package com.example;

import javafx.scene.input.KeyEvent;

public class Controller implements IController {
    private final Pacman pacman;

    public Controller(Pacman pacman) {
        this.pacman = pacman;
    }
    public void keyPressed(KeyEvent key) {
        switch (key.getCode()) {
            case UP:
                pacman.setBufferDirection(MoveableBlock.directions.UP);
                break;
            case DOWN:
                pacman.setBufferDirection(MoveableBlock.directions.DOWN);
                break;
            case LEFT:
                pacman.setBufferDirection(MoveableBlock.directions.LEFT);
                break;
            case RIGHT:
                pacman.setBufferDirection(MoveableBlock.directions.RIGHT);
                break;
            default:
                break;
        }
    }
}
