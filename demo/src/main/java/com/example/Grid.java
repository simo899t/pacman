package com.example;

import java.util.ArrayList;

public class Grid implements IGrid {
    private final IMap map;
    private final Node[][] grid;
    private final int cols;
    private final int rows;
    private final int tileSize;
    private Node currentNode;

    public Grid(IMap map) {
        this.map = map;
        this.cols  = map.getCols();
        this.rows  = map.getRows();
        this.tileSize = map.getTileSize();
        this.grid  = new Node[cols][rows];
        makeGrid();
    }

    public void makeGrid() {
        String[] gridMap = map.getMap();  
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (gridMap[row].charAt(col) != 'X') {
                    grid[col][row] = new Node(col * tileSize, row * tileSize);
                    if (gridMap[row].charAt(col) == 'P') {
                        currentNode = grid[col][row];
                    }
                }
            }
        }
        
        // Second pass: Connect all nodes properly
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[col][row] != null) {
                    connectNodeNeighbors(grid[col][row], col, row);
                }
            }
        }
    }

    @Override
    public void connectNodeNeighbors(Node node, int col, int row) {
        // Check all four directions
        if (getNode(col, row - 1) != null) {
            node.neighbourgs[0] = getNode(col, row - 1); // UP
        } else {
            node.neighbourgs[0] = null;
        }
        
        if (getNode(col, row + 1) != null) {
            node.neighbourgs[1] = getNode(col, row + 1); // DOWN
        } else {
            node.neighbourgs[1] = null;
        }
        
        if (getNode(col - 1, row) != null) {
            node.neighbourgs[2] = getNode(col - 1, row); // LEFT
        } else {
            node.neighbourgs[2] = null;
        }
        
        if (getNode(col + 1, row) != null) {
            node.neighbourgs[3] = getNode(col + 1, row); // RIGHT
        } else {
            node.neighbourgs[3] = null;
        }
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public ArrayList<Node> getAllNodes() {
        ArrayList<Node> allNodes = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[col][row] != null) {
                    allNodes.add(grid[col][row]);
                }
            }
        }
        return allNodes;
    }

    /**
     * 
     */
    public void printGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[col][row] != null) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    /** lookup the block at tile‐coords (col,row), or null */
    public Node getNode(int col, int row) {
        if (col < 0 || col >= cols || row < 0 || row >= rows) {
            return null;
        }
        return grid[col][row];
    }

    /**
     * convert pixel X to grid‐column
     * @param x
     * @return
     */
    @Override
    public int toCol(int x) {
        return (int) x / map.getTileSize(); 
    }
    
    /**
     * convert pixel Y to grid‐row
     * @param y
     * @return
     */
    @Override
    public int toRow(int y) {
        return (int) y / map.getTileSize();
    }
}

   