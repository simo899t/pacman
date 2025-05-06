package com.example;

public class Grid implements IGrid {
    private final IMap map;
    private final Node[][] grid;
    private final int cols;
    private final int rows;
    private final int tileSize;

    public Grid(IMap map) {
        this.map = map;
        this.cols  = map.getCols();
        this.rows  = map.getRows();
        this.tileSize = map.getTileSize();
        this.grid  = new Node[cols][rows];
        makeGrid();
        printGrid();
    }

    public void makeGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (map.getMap()[row].charAt(col) != 'X') {
                    grid[col][row] = new Node(toCol(col), toRow(row));
                    surroundingNodes(getNode(col, row));
                }
            }
            
        }
    }

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
    private int toCol(double x) {
        return (int) x / map.getTileSize(); 
    }
    
    /**
     * convert pixel Y to grid‐row
     * @param y
     * @return
     */
    private int toRow(double y) {
        return (int) y / map.getTileSize();
    }

    /**
     * set surrounding nodes for a given node
     * @param node
     * @return
     */
    public void surroundingNodes(Node node) {
        int col = toCol(node.getX());
        int row = toRow(node.getY());

        node.neighbourgs[1] = null; // down
        node.neighbourgs[3] = null; // right        

        if (getNode(col, row - 1) != null) {
            node.neighbourgs[0] = getNode(col, row - 1);
            getNode(col, row - 1).neighbourgs[1] = node;
        } else {
            node.neighbourgs[0] = null;
        }
        if (getNode(col - 1, row) != null) {
            node.neighbourgs[2] = getNode(col - 1, row);
            getNode(col - 1, row).neighbourgs[3] = node;
        } else {
            node.neighbourgs[2] = null;
        } 
        
    }
}

    
