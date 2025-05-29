package com.example;

import com.example.MoveableBlock.directions;

public interface IUpdate {
    public void updateGame(IMap map);
    public void updateEntity(MoveableBlock entity);
    public Block nextBlock(Block block, directions bufferDirection);
}
