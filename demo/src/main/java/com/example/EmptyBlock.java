package com.example;

public class EmptyBlock extends Block {

    /**
     * Constructor for EmptyBlock.
     * This block represents an empty space in the game grid.
     * It does not have an image associated with it. (therefore image = null)
     *
     * @param x The x-coordinate of the empty block.
     * @param y The y-coordinate of the empty block.
     */
    public EmptyBlock(int x, int y) {
        super(null, x, y);
        this.setType(BlockType.EMPTY);
    }

    
}
