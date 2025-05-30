package com.example;

import java.util.ArrayList;

/**
 * The IMap interface defines the contract for a Pac-Man game map.
 * It provides access to all relevant things of the game environment,
 * including blocks (still blocks and moving entities) and map dimensions, and allows for state
 * changes such as pellet tracking and resetting the game state.
 */
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
    public void resetMap();
    public String[] getMap();
    public Block getBlock(int x, int y);
    public void resetAllGhosts();
}
