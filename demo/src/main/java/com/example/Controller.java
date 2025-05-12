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
                pacman.setBufferDirection(pacman.bufferDirection.UP);
                System.out.println("setBufferDirection UP");
                break;
            case DOWN:
                pacman.setBufferDirection(pacman.bufferDirection.DOWN);
                System.out.println("setBufferDirection DOWN");
                break;
            case LEFT:
                pacman.setBufferDirection(pacman.bufferDirection.LEFT);
                System.out.println("setBufferDirection LEFT");
                break;
            case RIGHT:
                pacman.setBufferDirection(pacman.bufferDirection.RIGHT);
                System.out.println("setBufferDirection RIGHT");
                break;
            default:
                break;
        }
    }
}
