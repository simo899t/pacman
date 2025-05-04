package com.example;

public class Grid {
    private final IMap map;
    private final Block[][] grid;
    private final int cols;
    private final int rows;
    private final int tileSize;

    public Grid(Map map) {
        this.map = map;
        this.cols  = map.getCols();
        this.rows  = map.getRows();
        this.tileSize = map.getTileSize();
        this.grid  = new Block[cols][rows];
    }

    /** place a block into the grid at tile‐coords (col,row) */
    public void setBlock(int col, int row, Block b) {
        if (col < 0 || col >= cols || row < 0 || row >= rows) {
            return;
        }

        grid[col][row] = b;
    }

    /** lookup the block at tile‐coords (col,row), or null */
    public Block getBlock(int col, int row) {
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
    public int toCol(int x) {
        return x / map.getTileSize(); 
    }
    
    /**
     * convert pixel Y to grid‐row
     * @param y
     * @return
     */
    public int toRow(int y) {
        return y / map.getTileSize();
    }
}
