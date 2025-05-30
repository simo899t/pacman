package com.example;

import java.util.ArrayList;

/**
 * IGrid interface defines methods for managing a grid of nodes and its neighbors in the game.
 */
public interface IGrid {
    public Node getNode(int x, int y);
    public void connectNodeNeighbors(Node node, int col, int row);
    public ArrayList<Node> getAllNodes();
    public int toCol(int x);
    public int toRow(int y);


}
