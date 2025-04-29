package com.example;

public class Teleport implements ITeleport {
    @Override
    public void teleport(Block entity, int x, int y) {
        entity.setPos(x, y);
    }
}
    
