package com.example;

import java.util.ArrayList;

public interface IMap {
    public ArrayList<Block> getAllBlocks();
    public Pacman getPacman();
    public Ghost getRedGhost();
    public Ghost getBlueGhost();
    public Ghost getPinkGhost();
    public Ghost getOrangeGhost();
    public Block getGhostHome();
    public int getTileSize();
    public int getCols();
    public int getRows();
    public int getPelletCount();
    public void removePellet();
    public void addPellet();
    public void resetPelletCount();
    public void resetMap();
    public String[] getMap();
    public Block getBlock(int x, int y);
    public void removeBlock(Block block);
}
