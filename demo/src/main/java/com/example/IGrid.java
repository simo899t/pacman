package com.example;

import java.util.ArrayList;

public interface IGrid {
    public Node getNode(int x, int y);
    public void connectNodeNeighbors(Node node, int col, int row);
    public ArrayList<Node> getAllNodes();
    public int toCol(int x);
    public int toRow(int y);


}
