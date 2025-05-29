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
                pacman.setBufferDirection(MoveableBlock.direction.UP);
                break;
            case DOWN:
                pacman.setBufferDirection(MoveableBlock.direction.DOWN);
                break;
            case LEFT:
                pacman.setBufferDirection(MoveableBlock.direction.LEFT);
                break;
            case RIGHT:
                pacman.setBufferDirection(MoveableBlock.direction.RIGHT);
                break;
            default:
                break;
        }
    }
}
