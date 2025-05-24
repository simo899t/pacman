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
    public int getPelletsLeft();
    public void decreasePelletsLeft();
    public void addPellet();
    public void resetPelletsLeft();
    public void resetMap();
    public String[] getMap();
    public Block getBlock(int x, int y);
}
