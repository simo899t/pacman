package com.example;


public class Node {
    private final int x;
    private final int y;
    private boolean isSeen;
    private neighbourDirection neighboursNodeDirection;
    private Node[] neighbours = new Node[4]; // up, down, left, right

    public enum neighbourDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.isSeen = false;
        this.neighboursNodeDirection = null; // Default direction
    }

    public Node[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Node node, int iterator) {
        neighbours[iterator] = node;
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

    public void setNeighboursNodeDirection(neighbourDirection direction) {
        this.neighboursNodeDirection = direction;
    }

    public neighbourDirection getNeighboursNodeDirection() {
        return neighboursNodeDirection;
    }
}