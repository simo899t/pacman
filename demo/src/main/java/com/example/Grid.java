package com.example;

public class Grid implements IGrid {
    private final IMap map;
    private final Block[][] grid;
    private final int cols;
    private final int rows;
    private final int tileSize;

    public Grid(IMap map) {
        this.map = map;
        this.cols  = map.getCols();
        this.rows  = map.getRows();
        this.tileSize = map.getTileSize();
        this.grid  = new Block[cols][rows];
        makeGrid();
    }

    public void makeGrid() {
        for (Block block : map.getAllBlocks()) {
            int col = toCol(block.getX());
            int row = toRow(block.getY());
            grid[col][row] = block;
        }
    }

    /** place a block into the grid at tile‐coords (col,row) */
    public void setBlock(Block b, int col, int row) {
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

    public Block nextBlock(MoveableBlock b) {
        int col = toCol(b.getX());
        int row = toRow(b.getY());
        switch (b.getDirection()) {
            case UP:
                System.out.println(getBlock(col, row).getX());
                return getBlock(col, row - 1);
            case DOWN:
                System.out.println("down");
                return getBlock(col, row + 1);
            case LEFT:
                System.out.println("left");
                return getBlock(col - 1, row);
            case RIGHT:
                System.out.println("right");
                return getBlock(col + 1, row);      
            default:
                System.out.println("No direction set");
                return null;
        }
        
        
    }
}

    
