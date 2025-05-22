package com.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import com.example.MoveableBlock.direction;
import com.example.Node.neighborDirection;

public class BFS {
    IMap map;
    IGrid grid;
    

    public BFS(IMap map, IGrid grid) {
        this.map = map;
        this.grid = grid;
    }

    public direction search(Ghost ghost) {
        Node startNode = grid.getNode(grid.toCol(ghost.getX()), grid.toRow(ghost.getY()));
        Node targetNode = grid.getNode(grid.toCol(map.getPacman().getX()), grid.toRow(map.getPacman().getY()));
        ArrayList<Node> possibleNodes = new ArrayList<>();
        boolean found = false;

        Node[] path = startNode.getNeighbourgs();
        for (int i = 0; i < path.length; i++) {
            if (path[i] != null) {
                possibleNodes.add(path[i]);
                switch (i) {
                    case 0:
                        path[i].setNeighborsNodeDirection(neighborDirection.UP);
                        break;
                    case 1:
                        path[i].setNeighborsNodeDirection(neighborDirection.DOWN);
                        break;
                    case 2:
                        path[i].setNeighborsNodeDirection(neighborDirection.LEFT);
                        break;
                    case 3:
                        path[i].setNeighborsNodeDirection(neighborDirection.RIGHT);
                        break;
                    default:
                        break;
                }
            }
        }

        int index = 0;
        Node directionNode = null;
        while (!found && index < possibleNodes.size()) {
            Node node = possibleNodes.get(index);
            if (isPacman(node, targetNode)) {
                found = true;
                directionNode = node;
                break;
            } 
            else {
                ArrayList<Node> validNeibours = validNeibours(node);
                for (Node validNode : validNeibours) {
                    possibleNodes.add(validNode);
                }
            }
            index++;
        }
        
        if (directionNode != null) {
            switch (directionNode.getNeighborsNodeDirection()) {
                case UP:
                    return direction.LEFT;
                case DOWN:
                    return direction.RIGHT;
                case LEFT:
                    return direction.UP;
                case RIGHT:
                    return direction.DOWN;
                default:
                    break;
            }
        }
        return null;
    }

    public boolean isPacman(Node node, Node targetNode) {
        return node.equals(targetNode);
    }

    public ArrayList<Node> validNeibours(Node node) {
        Node[] neighbours = node.getNeighbourgs();
        ArrayList<Node> validNeibours = new ArrayList<>();
        for (int i = 0; i < neighbours.length; i++) {
            if (neighbours[i] != null && !neighbours[i].isSeen()) {
                neighbours[i].setSeen(true);
                validNeibours.add(neighbours[i]);
                validNeibours.get(i).setNeighborsNodeDirection(node.getNeighborsNodeDirection());
            }
        }
        return validNeibours;
    }
}
