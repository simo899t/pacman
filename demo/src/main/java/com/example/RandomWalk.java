package com.example;

import com.example.MoveableBlock.direction;
import java.util.ArrayList;
import java.util.Random;

public class RandomWalk {
    Ghost ghost;
    IGrid grid;
    IMap map;
    int tileSize;
    Random random = new Random();

    public RandomWalk(IMap map, IGrid grid) {
        this.grid = grid;
        this.map = map;
        this.tileSize = map.getTileSize();
    }

    public direction seach(Ghost ghost) {
        // Convert pixel coordinates to grid coordinates
        int col = ghost.getX() / tileSize;
        int row = ghost.getY() / tileSize;
        
        Node currentNode = grid.getNode(col, row);
        if (currentNode == null) {
            return direction.NONE; // Safety check
        }
        
        Node[] neighbours = currentNode.getNeighbourgs();
        
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
        if (ghost.getDirection() == direction.NONE) {
            int bufferIndex = validIndices.get(random.nextInt(validIndices.size()));
            ghost.setBufferDirection(indexToDirection(bufferIndex));
        } 
        // 20% chance to set a random buffer direction even when moving
        else if (random.nextDouble() < 0.50) {
            int bufferIndex = validIndices.get(random.nextInt(validIndices.size()));
            ghost.setBufferDirection(indexToDirection(bufferIndex));
            System.out.println("Random buffer direction set: " + indexToDirection(bufferIndex));
        }
        
        return indexToDirection(randomIndex);
    }
    
    // Helper method to convert index to direction
    private direction indexToDirection(int index) {
        switch (index) {
            case 0: return direction.UP;
            case 1: return direction.DOWN;
            case 2: return direction.LEFT;
            case 3: return direction.RIGHT;
            default: return direction.NONE;
        }
    }
}

