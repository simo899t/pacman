package com.example;

import com.example.MoveableBlock.direction;

public class GhostMovementPathfinding {
    public IMap map;
    public IGrid grid;
    public Revive revive;
    public Ghost redGhost;
    public Ghost blueGhost;
    public Ghost pinkGhost;
    public Ghost orangeGhost;
    public RandomWalk randomWalk;
    public BFS bfs;
    public BadSeach badSearch;

    GhostMovementPathfinding(IMap map, IGrid grid) {
        this.map = map;
        this.grid = grid;
        this.randomWalk = new RandomWalk(map, grid); // Initialize here after map is set
        this.bfs = new BFS(map, grid); // Initialize here after map is set
        this.badSearch = new BadSeach(grid); // Initialize here after map is set
        this.redGhost = map.getRedGhost();
        this.blueGhost = map.getBlueGhost();
        this.pinkGhost = map.getPinkGhost();
        this.orangeGhost = map.getOrangeGhost();
    }

    public void directAllGhosts() {
        Pacman pacman = map.getPacman();
        Block ghostHome = map.getGhostHome();

        for (Block ghost : map.getAllBlocks()) {
            if (ghost.getType() == BlockType.GHOST) {
                directGhost(ghost, pacman, ghostHome);
            }
        }
    }

    private void directGhost(Block entity, Pacman pacman, Block ghostHome) {
        Ghost ghost = (Ghost) entity;
        if (ghost.getState() == Ghost.states.CHASE) {
            if (ghost.getColor() == Ghost.color.RED || ghost.getColor() == Ghost.color.ORANGE) {
                BFSDirectGhost(ghost, pacman);
            } else if (ghost.getColor() == Ghost.color.BLUE || ghost.getColor() == Ghost.color.PINK) {
                RandomWalkDirectGhost(ghost);
            }
        } else if (ghost.getState() == Ghost.states.FRIGHTENED) {
            RandomWalkDirectGhost(ghost);
        } else if (ghost.getState() == Ghost.states.EATEN) {
            BFSDirectGhost(ghost, ghostHome);
        }
    }

    private void BFSDirectGhost(Ghost ghost, Block target) {
        if (ghost.getX() % map.getTileSize() == 0 && ghost.getY() % map.getTileSize() == 0) {
            ghost.setBufferDirection(bfs.search(ghost, target));
        }
    }

    private void RandomWalkDirectGhost(Ghost ghost) {
        if (ghost.getX() % map.getTileSize() == 0 && ghost.getY() % map.getTileSize() == 0) {
            direction newDirection = randomWalk.seach(ghost);
            ghost.setDirection(newDirection);
        }
    }

    private void badSearchDirectGhost(Ghost ghost, Block target) {
        if (ghost.getX() % map.getTileSize() == 0 && ghost.getY() % map.getTileSize() == 0) {
            ghost.setBufferDirection(badSearch.search(ghost, target));
        }
    }



    
}
