package com.example;

public class EmptyBlock extends Block {

    public EmptyBlock(int x, int y) {
        super(null, x, y);
        this.setType(BlockType.EMPTY);
    }

    
}
