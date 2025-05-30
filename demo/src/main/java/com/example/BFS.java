package com.example;

import java.util.ArrayList;

import com.example.MoveableBlock.directions;
import com.example.Node.neighbourDirection;

public class BFS {
    private final IGrid grid;

    /**
     * Constructor for BFS that initializes the grid.
     * 
     * @param grid The grid to perform BFS on.
     */
    public BFS(IGrid grid) {
        this.grid = grid;
    }

    /**
     * Searches for the shortest path from the ghost to the target block using BFS.
     * 
     * @param ghost The ghost to search from.
     * @param target The target block (Pacman).
     * @return The direction to move towards Pacman, or NONE if no path exists.
     */
    public directions search(Ghost ghost, Block target) {
        Node startNode = grid.getNode(grid.toCol(ghost.getX()), grid.toRow(ghost.getY()));
        Node targetNode = grid.getNode(grid.toCol(target.getX()), grid.toRow(target.getY()));
        
        // Reset all nodes for a clean search
        for (Node node : grid.getAllNodes()) {
            node.setSeen(false);
            node.setNeighboursNodeDirection(null);
        }
        
        ArrayList<Node> queue = new ArrayList<>();
        ArrayList<Node> seenNodes = new ArrayList<>();
        boolean found = false;
        
        // Mark start node as seen
        startNode.setSeen(true);
        seenNodes.add(startNode);
        
        // Add initial neighbors with directions
        Node[] startneighbors = startNode.getNeighbours();
        for (int i = 0; i < startneighbors.length; i++) {
            if (startneighbors[i] != null) {
                // Check if this neighbor is already Pacman
                if (startneighbors[i].equals(targetNode)) {
                    // Found Pacman at startnode neighbors!
                     return switch (i) {
                        case 0 -> directions.UP;
                        case 1 -> directions.DOWN;
                        case 2 -> directions.LEFT;
                        case 3 -> directions.RIGHT;
                        default -> directions.NONE;
                    };
                }
                
                // Mark neighbor as seen and add to queue
                queue.add(startneighbors[i]);
                startneighbors[i].setSeen(true);
                seenNodes.add(startneighbors[i]);
                
                // Set direction based on index
                switch (i) {
                    case 0: startneighbors[i].setNeighboursNodeDirection(neighbourDirection.UP); break;
                    case 1: startneighbors[i].setNeighboursNodeDirection(neighbourDirection.DOWN); break;
                    case 2: startneighbors[i].setNeighboursNodeDirection(neighbourDirection.LEFT); break;
                    case 3: startneighbors[i].setNeighboursNodeDirection(neighbourDirection.RIGHT); break;
                }
            }
        }
        
        // Process queue until empty or found
        while (!queue.isEmpty() && !found) {
            Node current = queue.remove(0);
            
            // Check all neighbors of current node
            Node[] neighbors = current.getNeighbours();
            for (int i = 0; i < neighbors.length; i++) {
                Node neighbor = neighbors[i];
                if (neighbor != null && !neighbor.isSeen()) {
                    neighbor.setSeen(true);
                    seenNodes.add(neighbor);
                    
                    // Inherit direction from current node
                    neighbor.setNeighboursNodeDirection(current.getNeighboursNodeDirection());
                    
                    // Check if this is Pacman
                    if (neighbor.equals(targetNode)) {
                        found = true;
                        return directionFromNeighborDirection(current.getNeighboursNodeDirection());
                    }
                    
                    // Add to queue for processing
                    queue.add(neighbor);
                }
            }
        }
        
        // If BFS gets here, no path exists (should never happen in a proper maze)
        System.out.println("WARNING: No path found to Pacman!");
        return directions.NONE;
    }

    /**
     * Converts the neighbourDirection to the corresponding directions enum.
     * 
     * @param neighbourDirection The direction from the neighbor node.
     * @return The corresponding directions enum.
     */
    private directions directionFromNeighborDirection(neighbourDirection neighbourDirection) {
        return switch (neighbourDirection) {
            case UP -> directions.UP;
            case DOWN -> directions.DOWN;
            case LEFT -> directions.LEFT;
            case RIGHT -> directions.RIGHT;
            default -> directions.NONE;
        };
    }

}
