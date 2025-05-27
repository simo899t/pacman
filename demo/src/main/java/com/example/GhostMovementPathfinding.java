package com.example;

import com.example.MoveableBlock.direction;

public class GhostMovementPathfinding {
    private final IMap map;
    private final IGrid grid;
    private final Ghost redGhost;
    private final Ghost blueGhost;
    private final Ghost pinkGhost;
    private final Ghost orangeGhost;
    private final RandomWalk randomWalk;
    private final BFS bfs;

    GhostMovementPathfinding(IMap map, IGrid grid) {
        this.map = map;
        this.grid = grid;
        this.randomWalk = new RandomWalk(map, grid); // Initialize here after map is set
        this.bfs = new BFS(grid); // Initialize here after map is set
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
        if (null != ghost.getState()) switch (ghost.getState()) {
            case CHASE:
                if (ghost.getColor() == Ghost.color.RED || ghost.getColor() == Ghost.color.ORANGE) {
                    BFSDirectGhost(ghost, pacman);
                } else if (ghost.getColor() == Ghost.color.BLUE || ghost.getColor() == Ghost.color.PINK) {
                    BFSDirectGhost(ghost, pacman);
                    //RandomWalkDirectGhost(ghost);
                }   break;
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




    
}
