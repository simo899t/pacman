package com.example;


public class Node {
    private final int x;
    private final int y;
    private boolean isSeen;
    private neighborDirection neighborsNodeDirection;
    private Node[] neighbourgs = new Node[4]; // up, down, left, right

    public enum neighborDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.isSeen = false;
        this.neighborsNodeDirection = null; // Default direction
    }

    public Node[] getNeighbours() {
        return neighbourgs;
    }

    public void setNeighbours(Node node, int iterator) {
        neighbourgs[iterator] = node;
    }
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setSeen(boolean seen) {
        this.isSeen = seen;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setNeighboursNodeDirection(neighborDirection direction) {
        this.neighborsNodeDirection = direction;
    }

    public neighborDirection getNeighboursNodeDirection() {
        return neighborsNodeDirection;
    }
}