package com.example;

import java.util.ArrayList;

public interface IMap {
    public ArrayList<Block> getAllBlocks();
    // public ArrayList<MoveableBlock> getMoveableBlocks();
    public Pacman getPacman();
    public int getTileSize();
    public int getCols();
    public int getRows();
    public String[] getMap();
    public Block getBlock(int x, int y);
}
