package com.example;

import com.example.MoveableBlock.direction;

public interface IUpdate {
    public void updateGame(IMap map);
    public void updateEntity(MoveableBlock entity);
    public Block nextBlock(MoveableBlock entity, direction bufferDirection);
}
