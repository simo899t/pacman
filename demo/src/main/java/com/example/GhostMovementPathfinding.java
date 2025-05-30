package com.example;

import com.example.Block.BlockType;
import com.example.MoveableBlock.directions;

public class GhostMovementPathfinding {
    private final IMap map;
    private final RandomWalk randomWalk;
    private final BFS bfs;

    /**
     * Constructor for GhostMovementPathfinding class.
     * Initializes the pathfinding algorithms with the provided map and grid.
     *
     * @param map  The game map containing all blocks and entities.
     * @param grid The grid used for pathfinding calculations.
     */
    GhostMovementPathfinding(IMap map, IGrid grid) {
        this.map = map;
        this.randomWalk = new RandomWalk(grid); // Initialize randomwalk algorithm here after map is set
        this.bfs = new BFS(grid); // Initialize breadth first seach here after map is set
    }

    /**
     * Calls the directGhost method for all ghosts in the map.
     */
    public void directAllGhosts() {
        Pacman pacman = map.getPacman();
        Block ghostHome = map.getGhostHome();

        for (Block ghost : map.getAllBlocks()) {
            if (ghost.getType() == BlockType.GHOST) {
                directGhost(ghost, pacman, ghostHome);
            }
        }
    }

    /**
     * Directs the ghost based on its state and color.
     * Uses both BFS and RandomWalk algorithms to determine the direction.
     *
     * @param entity     The ghost entity to be directed.
     * @param pacman     The Pacman entity in the game. (target sometimes)
     * @param ghostHome  The home block for ghosts. (used when ghost is eaten)
     */
    private void directGhost(Block entity, Pacman pacman, Block ghostHome) {
        Ghost ghost = (Ghost) entity;
        switch (ghost.getState()) {
            case CHASE:
                BFSDirectGhost(ghost, pacman);
                break;
            case FRIGHTENED:
                RandomWalkDirectGhost(ghost);
                break;
            case EATEN:
                BFSDirectGhost(ghost, ghostHome);
                break;
            default:
                break;
        }
    }

    /**
     * Uses BFS to find the direction towards Pacman for the ghost.
     * If the ghost is not aligned with the tile size, it does not change direction.
     *
     * @param ghost  The ghost entity to be directed.
     * @param target The target block (Pacman).
     */
    private void BFSDirectGhost(Ghost ghost, Block target) {
        if (ghost.getX() % map.getTileSize() == 0 && ghost.getY() % map.getTileSize() == 0) {
            ghost.setBufferDirection(bfs.search(ghost, target));
        }
    }

    /**
     * Uses RandomWalk to find a random direction for the ghost.
     * If the ghost is not aligned with the tile size, it does not change direction.
     *
     * @param ghost The ghost entity to be directed.
     */
    private void RandomWalkDirectGhost(Ghost ghost) {
        if (ghost.getX() % map.getTileSize() == 0 && ghost.getY() % map.getTileSize() == 0) {
            directions newDirection = randomWalk.search(ghost);
            ghost.setDirection(newDirection);
        }
    }




    
}
