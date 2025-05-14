package com.example;

import javafx.scene.input.KeyEvent;

public class Controller implements IController {
    private Pacman pacman;
    private Ghost redGhost;
    private Ghost blueGhost;
    private Ghost pinkGhost;
    private Ghost orangeGhost;

    public Controller(Pacman pacman, Ghost redGhost) {
        this.pacman = pacman;
        this.redGhost = redGhost;
    }

    public void keyPressed1(KeyEvent key) {
        //System.out.println("KeyEvent: " + keyCode);
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

    public void keyPressed2(KeyEvent key) {
        //System.out.println("KeyEvent: " + keyCode);
        switch (key.getCode()) {
            case W:
                redGhost.setBufferDirection(redGhost.bufferDirection.UP);
                break;
            case S:
                redGhost.setBufferDirection(redGhost.bufferDirection.DOWN);
                break;
            case A:
                redGhost.setBufferDirection(redGhost.bufferDirection.LEFT);
                break;
            case D:
                redGhost.setBufferDirection(redGhost.bufferDirection.RIGHT);
                break;
            default:
                break;
        }
    }
}
