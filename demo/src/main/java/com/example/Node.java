package com.example;

public class Node {
    int x;
    int y;
    Node[] neighbourgs = new Node[4]; // up, down, left, right

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node[] getNeighbourgs() {
        return neighbourgs;
    }
    
    public double getDistance(Node other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}