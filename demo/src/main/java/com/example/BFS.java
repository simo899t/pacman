package com.example;

import java.util.ArrayList;

import com.example.MoveableBlock.direction;
import com.example.Node.neighborDirection;

public class BFS {
    private final IGrid grid;

    public BFS(IGrid grid) {
        this.grid = grid;
    }

    public direction search(Ghost ghost, Block target) {
        Node startNode = grid.getNode(grid.toCol(ghost.getX()), grid.toRow(ghost.getY()));
        Node targetNode = grid.getNode(grid.toCol(target.getX()), grid.toRow(target.getY()));
        
        // Reset all nodes for a clean search
        for (Node node : grid.getAllNodes()) {
            node.setSeen(false);
            node.setNeighborsNodeDirection(null);
        }
        
        ArrayList<Node> queue = new ArrayList<>();
        ArrayList<Node> seenNodes = new ArrayList<>();
        boolean found = false;
        
        // Mark start node as seen
        startNode.setSeen(true);
        seenNodes.add(startNode);
        
        // Add initial neighbors with directions
        Node[] startneighbors = startNode.getNeighbourgs();
        for (int i = 0; i < startneighbors.length; i++) {
            if (startneighbors[i] != null) {
                // Check if this neighbor is already Pacman
                if (startneighbors[i] == targetNode) {
                    // Found Pacman at startnode neighbors!
                    switch (i) {
                        case 0: return direction.UP;
                        case 1: return direction.DOWN;
                        case 2: return direction.LEFT;
                        case 3: return direction.RIGHT;
                        default: return direction.NONE;
                    }
                }
                
                queue.add(startneighbors[i]);
                startneighbors[i].setSeen(true);
                seenNodes.add(startneighbors[i]);
                
                // Set direction based on index
                switch (i) {
                    case 0: startneighbors[i].setNeighborsNodeDirection(neighborDirection.UP); break;
                    case 1: startneighbors[i].setNeighborsNodeDirection(neighborDirection.DOWN); break;
                    case 2: startneighbors[i].setNeighborsNodeDirection(neighborDirection.LEFT); break;
                    case 3: startneighbors[i].setNeighborsNodeDirection(neighborDirection.RIGHT); break;
                }
            }
        }
        
        // Process queue until empty or found
        while (!queue.isEmpty() && !found) {
            Node current = queue.remove(0);
            
            // Check all neighbors of current node
            Node[] neighbors = current.getNeighbourgs();
            for (int i = 0; i < neighbors.length; i++) {
                Node neighbor = neighbors[i];
                if (neighbor != null && !neighbor.isSeen()) {
                    neighbor.setSeen(true);
                    seenNodes.add(neighbor);
                    
                    // Inherit direction from current node
                    neighbor.setNeighborsNodeDirection(current.getNeighborsNodeDirection());
                    
                    // Check if this is Pacman
                    if (neighbor == targetNode) {
                        found = true;
                        return directionFromNeighborDirection(current.getNeighborsNodeDirection());
                    }
                    
                    // Add to queue for processing
                    queue.add(neighbor);
                }
            }
        }
        
        // If we get here, no path exists (should never happen in a proper maze)
        System.out.println("WARNING: No path found to Pacman!");
        return direction.NONE;
    }

    private direction directionFromNeighborDirection(neighborDirection nd) {
        switch (nd) {
            case UP: return direction.UP;
            case DOWN: return direction.DOWN;
            case LEFT: return direction.LEFT;
            case RIGHT: return direction.RIGHT;
            default: return direction.NONE;
        }
    }

    public boolean isPacman(Node node, Node targetNode) {
        return node.equals(targetNode);
    }

    public ArrayList<Node> validNeibours(Node node, ArrayList<Node> seenNodes) {
        System.out.println("checking valid neighbours");
        Node[] neighbours = node.getNeighbourgs();
        System.out.println("Neighbours: " + neighbours.length);
        for (Node n : neighbours) {
            if (n != null) {
                System.out.println(n.isSeen());
            }
        }
        ArrayList<Node> validNeibours = new ArrayList<>();
        for (int i = 0; i < neighbours.length; i++) {
            if (neighbours[i] != null && !neighbours[i].isSeen()) {
                neighbours[i].setSeen(true);
                neighbours[i].setNeighborsNodeDirection(node.getNeighborsNodeDirection());
                seenNodes.add(neighbours[i]);
                validNeibours.add(neighbours[i]);
            }
        }
        System.out.println("Valid neighbours: " + validNeibours.size());
        return validNeibours;
    }
}
