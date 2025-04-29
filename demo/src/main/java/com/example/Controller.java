package com.example;

import javafx.scene.input.KeyEvent;

public class Controller implements IController {
    private Pacman pacman;

    public Controller(Pacman pacman) {
        this.pacman = pacman;
    }

    public void keyPressed(KeyEvent e) {
        //System.out.println("KeyEvent: " + keyCode);
        switch (e.getCode()) {
            case UP:
                pacman.setDirection(pacman.direction.UP);
                break;
            case DOWN:
                pacman.setDirection(pacman.direction.DOWN);
                break;
            case LEFT:
                pacman.setDirection(pacman.direction.LEFT);
                break;
            case RIGHT:
                pacman.setDirection(pacman.direction.RIGHT);
                break;
            default:
                break;
        }
    }
}
