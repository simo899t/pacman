package com.example;

import java.util.Currency;

import com.example.MoveableBlock.direction;

public class PacmanMove {
    IGrid grid;
    Pacman pacman;
    IUpdateGamePositions updateGamePositions;

    public void pacmanMove(Node node) {
        switch (pacman.getBufferDirection()) {
            case UP:
                checkNewDirection(direction.DOWN);
                canITurn(node,0);
                break;
            case DOWN:
                checkNewDirection(direction.UP);
                canITurn(node, node.getNeighbourgs()[1]);
                break;
            case LEFT:
                checkNewDirection(direction.RIGHT);
                
                if (canITurn(node, node.getNeighbourgs()[2])) {
                    pacman.setDirection(pacman.getBufferDirection());
                }
                break;
            case RIGHT:
                checkNewDirection(direction.LEFT);
                canITurn(node, node.getNeighbourgs()[3]);
                break;
            default:
                break;
        }
        updateGamePositions.updateGamePositions();
    }

    public void checkNewDirection(direction newDirection) {
        if (pacman.getDirection() == newDirection) {
            pacman.setDirection(pacman.getBufferDirection());
        }
        
    }

    public boolean checkIfCanMove(Node currentNode, Node neibourg) {
        for (Node node : currentNode.getNeighbourgs()) {
            if (neibourg.getX() == node.getX() && 
                neibourg.getY() == node.getY()) {
                return true;
            }
        }
    }

    public boolean canITurn(Node currentNode, int num) {
        for (Node node : currentNode.getNeighbourgs()) {
            if (pacman.getX() == node.getX() && 
                pacman.getY() == node.getY()) {
                    currentNode = node;
                    if (currentNode.getNeighbourgs()[num] != null) {
                        return true;
                    }
                    return false;
            }
        }
        if (pacman.getX() == currentNode.getX() && pacman.getY() == currentNode.getY() && currentNode.getNeighbourgs()[num] != null) {
            return true;
        }

        return false;
    }

}
