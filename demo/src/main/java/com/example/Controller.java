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
                System.out.println("setDirection UP");
                break;
            case DOWN:
                pacman.setDirection(pacman.direction.DOWN);
                System.out.println("setDirection DOWN");
                break;
            case LEFT:
                pacman.setDirection(pacman.direction.LEFT);
                System.out.println("setDirection LEFT");
                break;
            case RIGHT:
                pacman.setDirection(pacman.direction.RIGHT);
                System.out.println("setDirection RIGHT");
                break;
            default:
                break;
        }
    }
}
