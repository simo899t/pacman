package com.example;

import com.example.MoveableBlock.direction;

public class BadSeach {
    
    IGrid grid;
    
    public BadSeach(IGrid grid) {
        this.grid = grid;
    }

    public direction search(Ghost ghost, Block target) {
        Node startNode = grid.getNode(grid.toCol(ghost.getX()), grid.toRow(ghost.getY()));
        Node targetNode = grid.getNode(grid.toCol(target.getX()), grid.toRow(target.getY()));
        
        int bestNeighborIndex = -1;
        double bestHeuristic = Double.MAX_VALUE;

        for (int i = 0; i < startNode.getNeighbourgs().length; i++) {
            Node neighbor = startNode.getNeighbourgs()[i];
            if (neighbor != null && bestHeuristic > heuristic(targetNode, neighbor)) {
                bestNeighborIndex = i;
                break;
            }
        }

        switch (bestNeighborIndex) {
            case 0: // UP
                return direction.UP;
            case 1: // DOWN
                return direction.DOWN;
            case 2: // LEFT
                return direction.LEFT;
            case 3: // RIGHT
                return direction.RIGHT;
            default:
            System.out.println("WARNING: No path found to Pacman!");
                return direction.NONE;
        }
        
    }

    public double heuristic(Node a, Node b) {
        // direct distance heuristic
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }
}
