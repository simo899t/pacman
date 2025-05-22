package com.example;


public class Node {

    public enum neighborDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    int x;
    int y;
    Node[] neighbourgs = new Node[4]; // up, down, left, right
    boolean isSeen;
    neighborDirection neighborsNodeDirection;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.isSeen = false;
        this.neighborsNodeDirection = null; // Default direction
    }

    public Node[] getNeighbourgs() {
        return neighbourgs;
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

    public void setNeighborsNodeDirection(neighborDirection direction) {
        this.neighborsNodeDirection = direction;
    }

    public neighborDirection getNeighborsNodeDirection() {
        return neighborsNodeDirection;
    }
}