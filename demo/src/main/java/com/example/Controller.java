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
                pacman.setBufferDirection(pacman.bufferDirection.UP);
                break;
            case DOWN:
                pacman.setBufferDirection(pacman.bufferDirection.DOWN);
                break;
            case LEFT:
                pacman.setBufferDirection(pacman.bufferDirection.LEFT);
                break;
            case RIGHT:
                pacman.setBufferDirection(pacman.bufferDirection.RIGHT);
                break;
            default:
                break;
        }
    }
}
