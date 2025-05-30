package com.example;

import java.util.ArrayList;

public class Grid implements IGrid {
    private final IMap map;
    private final Node[][] grid;
    private final int cols;
    private final int rows;
    private final int tileSize;
    private Node currentNode;

    /**
     * Constructor for the Grid class.
     * Initializes the grid based on the provided map.
     * 
     * @param map The map to create the grid from.
     */
    public Grid(IMap map) {
        this.map = map;
        this.cols  = map.getCols(); // Get the number of columns from the map
        this.rows  = map.getRows(); // Get the number of rows from the map
        this.tileSize = map.getTileSize(); // Get the tile size from the map
        this.grid  = new Node[cols][rows]; // Initialize the grid with the number of columns and rows
        makeGrid();
    }

    /**
     * Creates the grid based on the map.
     * Initializes nodes for each tile and connects them to their neighbors.
     */
    private void makeGrid() {
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
        
        // Second pass: Connect all nodes to their neighbors with the connectNodeNeighbors method
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[col][row] != null) {
                    connectNodeNeighbors(grid[col][row], col, row);
                }
            }
        }
    }

    /**
     * Connects the neighbors of a given node based on its position in the grid.
     * 
     * @param node The node to connect neighbors for.
     * @param col  The column index of the node.
     * @param row  The row index of the node.
     */
    @Override
    public void connectNodeNeighbors(Node node, int col, int row) {
        // Check all four directions
        if (getNode(col, row - 1) != null) {
            node.setNeighbours(getNode(col, row - 1),0); // UP
        } else {
            node.setNeighbours(null, 0);
        }
        
        if (getNode(col, row + 1) != null) {
            node.setNeighbours(getNode(col, row + 1), 1);
        } else {
            node.setNeighbours(null, 1);
        }
        
        if (getNode(col - 1, row) != null) {
            node.setNeighbours(getNode(col - 1, row), 2);
        } else {
            node.setNeighbours(null, 2);
        }
        
        if (getNode(col + 1, row) != null) {
            node.setNeighbours(getNode(col + 1, row), 3);
        } else {
            node.setNeighbours(null, 3);
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

   