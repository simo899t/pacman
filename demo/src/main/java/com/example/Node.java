package com.example;


public class Node {
    private final int x;
    private final int y;
    private final Node[] neighbours = new Node[4]; // up, down, left, right
    private boolean isSeen;
    private neighbourDirection neighboursNodeDirection;

    /**
     * Enum representing the directions in which a neibouring node is.
     */
    public enum neighbourDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /**
     * Constructor for Node that initializes the position and sets default values.
     * 
     * @param x The x-coordinate of the node.
     * @param y The y-coordinate of the node.
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.isSeen = false;
        this.neighboursNodeDirection = null; // Default direction
    }

    /**
     * Gets the neighbours of the node.
     * 
     * @return An array of neighbouring nodes.
     */
    public Node[] getNeighbours() {
        return neighbours;
    }

    // Getters for x and y coordinates of Node.
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    /**
     * Sets a neighbour node at the specified index.
     * 
     * @param node The node to set as a neighbour.
     * @param iterator The index at which to set the neighbour (0: UP, 1: DOWN, 2: LEFT, 3: RIGHT).
     */
    public void setNeighbours(Node node, int iterator) {
        neighbours[iterator] = node;
    }
    
    /**
     * Sets the seen state of the node.
     * 
     * @param seen The new seen state of the node.
     */
    public void setSeen(boolean seen) {
        this.isSeen = seen;
    }

    /**
     * Gets the seen state of the node.
     * 
     * @return The seen state of the node.
     */
    public boolean isSeen() {
        return isSeen;
    }

    /**
     * Sets the direction of the neighbouring node based on the first neighbouring node direction.
     * 
     * @param direction The direction of the neighbouring node.
     */
    public void setNeighboursNodeDirection(neighbourDirection direction) {
        this.neighboursNodeDirection = direction;
    }

    /**
     * Gets the direction of the neighbouring node.
     * 
     * @return The direction of the neighbouring node.
     */
    public neighbourDirection getNeighboursNodeDirection() {
        return neighboursNodeDirection;
    }
}