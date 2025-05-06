package com.example;

public interface IGrid {
    public void setBlock(Block block, int x, int y);
    public Block getBlock(int x, int y);
    public Block nextBlock(MoveableBlock b);


}
