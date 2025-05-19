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
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}