package com.example;

import java.util.ArrayList;

public interface IGrid {
    public Node getNode(int x, int y);
    public void surroundingNodes(Node node);
    public ArrayList<Node> getAllNodes();


}
