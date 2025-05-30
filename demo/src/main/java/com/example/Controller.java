package com.example;

import javafx.scene.input.KeyEvent;

public class Controller implements IController {
    private final Pacman pacman;

    /**
     * Constructor for the Controller class.
     * @param pacman The Pacman instance that this controller will control.
     */
    public Controller(Pacman pacman) {
        this.pacman = pacman;
    }

    /**
     * Handles key press events to change the direction of Pacman.
     * @param key The KeyEvent that contains the key pressed information.
     */
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
