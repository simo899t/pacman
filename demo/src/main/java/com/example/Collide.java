package com.example;

public class Collide implements ICollide {

    public boolean isColliding(Block block1, Block block2) {
        if (block1.getX() < block2.getX() + block2.getWidth() &&
                block1.getX() + block1.getWidth() > block2.getX() &&
                block1.getY() < block2.getY() + block2.getHeight() &&
                block1.getY() + block1.getHeight() > block2.getY()) {
            return true;
        } else {
            return false;
        }
    }
}
