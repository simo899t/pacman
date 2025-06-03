package com.example;

import java.util.ArrayList;
import java.util.Random;

import com.example.MoveableBlock.directions;

public class RandomWalk {
    private final IGrid grid;
    private final Random random = new Random();

    /**
     * Constructor for RandomWalk that initializes the grid.
     * 
     * @param grid The grid to perform random walk on.
     */
    public RandomWalk(IGrid grid) {
        this.grid = grid;
    }

    /**
     * Returns a random valid direction for the ghost.
     * 
     * @param ghost The ghost to perform the random walk for.
     * @return A random direction from the valid neighbors of the ghost's current position.
     */
    public directions search(Ghost ghost) {
        // Convert pixel coordinates to grid coordinates
        int col = grid.toCol(ghost.getX());
        int row = grid.toRow(ghost.getY());
        
        Node currentNode = grid.getNode(col, row);
        if (currentNode == null) {
            return directions.NONE; // Safety check
        }
        
        Node[] neighbours = currentNode.getNeighbours();
        
        // Create a list of valid (non-null) neighbors
        ArrayList<Integer> validIndices = new ArrayList<>();
        for (int i = 0; i < neighbours.length; i++) {
            if (neighbours[i] != null) {
                validIndices.add(i);
            }
        }
        
        // Choose a random valid neighbor
        int randomIndex = validIndices.get(random.nextInt(validIndices.size()));
        
        // UPDATED BUFFER DIRECTION LOGIC:
        // Always set a buffer direction when the ghost is not moving
        if (ghost.getDirection() == directions.NONE) {
            int bufferIndex = validIndices.get(random.nextInt(validIndices.size()));
            ghost.setBufferDirection(indexToDirection(bufferIndex));
        } 
        // 20% chance to set a random buffer direction even when moving
        else if (random.nextDouble() < 0.50) {
            int bufferIndex = validIndices.get(random.nextInt(validIndices.size()));
            ghost.setBufferDirection(indexToDirection(bufferIndex));
        }
        
        return indexToDirection(randomIndex);
    }
    
    // Helper method to convert index to direction
    private directions indexToDirection(int index) {
        switch (index) {
            case 0: return directions.UP;
            case 1: return directions.DOWN;
            case 2: return directions.LEFT;
            case 3: return directions.RIGHT;
            default: return directions.NONE;
        }
    }
}

