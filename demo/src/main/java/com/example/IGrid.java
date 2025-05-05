package com.example;

public interface IGrid {
    public void setBlock(MoveableBlock block, int x, int y);
    public MoveableBlock getBlock(int x, int y);
    public MoveableBlock nextBlock(MoveableBlock b);


}
