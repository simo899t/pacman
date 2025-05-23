package com.example;

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
        System.out.println("Searching for pacman");
        Node startNode = grid.getNode(grid.toCol(ghost.getX()), grid.toRow(ghost.getY()));
        Node targetNode = grid.getNode(grid.toCol(map.getPacman().getX()), grid.toRow(map.getPacman().getY()));
        ArrayList<Node> possibleNodes = new ArrayList<>();
        ArrayList<Node> seenNodes = new ArrayList<>();
        boolean found = false;


        Node[] startneighbors = startNode.getNeighbourgs(); 
        System.out.println("Start node: " + startneighbors.length);
        for (int i = 0; i < startneighbors.length; i++) {
            if (startneighbors[i] != null && startneighbors[i] != targetNode) {
                possibleNodes.add(startneighbors[i]);
               
                //System.out.println(startneighbors[i]);
                //System.out.println(possibleNodes);
                switch (i) {
                    case 0:
                        startneighbors[i].setNeighborsNodeDirection(neighborDirection.UP);
                        break;
                    case 1:
                        startneighbors[i].setNeighborsNodeDirection(neighborDirection.DOWN);
                        break;
                    case 2:
                        startneighbors[i].setNeighborsNodeDirection(neighborDirection.LEFT);
                        break;
                    case 3:
                        startneighbors[i].setNeighborsNodeDirection(neighborDirection.RIGHT);
                        break;
                    default:
                        break;
                }
            }
        }

        
        Node directionNode = null; //We dont know which direction to go
        while (!found) { //While we havent found pacman
            if (possibleNodes.isEmpty()) { 
                break;
            }
            //System.out.println("Possible nodes: " + possibleNodes);
            Node workingnode = possibleNodes.get(0); //get the first node from the list
            ArrayList<Node> validNeibours = validNeibours(possibleNodes.get(0), seenNodes); //get all its valid neighbors, and set them to seen and their direction from startnode
                for (Node validNode : validNeibours) { //for each neighbor node, check if node contains pacman
                    if (isPacman(workingnode, targetNode)){
                        found = true;
                        System.out.println("Found pacman");
                        directionNode = workingnode; //set direction ghost should go
                        break;
                    }
                    else {
                        possibleNodes.add(validNode); //If not pacman, add them to the back of the queue
                    }
                }
                possibleNodes.remove(0); //remove worked node
            
            
        }

        for (Node node : seenNodes) { //for each node we have seen, set its direction from startnode
            node.setSeen(false);
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
        
        return direction.NONE;
    }

    public boolean isPacman(Node node, Node targetNode) {
        return node.equals(targetNode);
    }

    public ArrayList<Node> validNeibours(Node node, ArrayList<Node> seenNodes) {
        Node[] neighbours = node.getNeighbourgs();
        ArrayList<Node> validNeibours = new ArrayList<>();
        
        //System.out.println("NEIGHBOURS: " + Arrays.toString(neighbours));

        for (int i = 0; i < neighbours.length; i++) {
            //System.out.println("Checking neighbour: " + neighbours[i]);
            if (neighbours[i] != null) {
                //System.out.println("Neighbour is: " + neighbours[i].isSeen());
            }
            if (neighbours[i] != null && !neighbours[i].isSeen()) {
                neighbours[i].setSeen(true);
                seenNodes.add(neighbours[i]);
                //System.out.println("Setting neighbour: " + neighbours[i] + " to seen");
                validNeibours.add(neighbours[i]);
                //System.out.println("Adding neighbour: " + neighbours[i]);
                validNeibours.get(i).setNeighborsNodeDirection(node.getNeighborsNodeDirection());
            }
        }
        return validNeibours;
    }
}
