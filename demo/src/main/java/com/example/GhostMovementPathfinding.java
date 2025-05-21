package com.example;

import com.example.MoveableBlock.direction;

public class GhostMovementPathfinding {
    public IMap map;
    public IGrid grid;
    public Ghost redGhost;
    public Ghost blueGhost;
    public Ghost pinkGhost;
    public Ghost orangeGhost;
    public RandomWalk randomWalk;

    GhostMovementPathfinding(IMap map, IGrid grid) {
        this.map = map;
        this.grid = grid;
        this.randomWalk = new RandomWalk(map, grid); // Initialize here after map is set
        this.redGhost = map.getRedGhost();
        this.blueGhost = map.getBlueGhost();
        this.pinkGhost = map.getPinkGhost();
        this.orangeGhost = map.getOrangeGhost();
    }

    public void directAllGhosts() {
        if (redGhost.getState() == Ghost.states.EATEN) {
            //BFSDirectGhost(redGhost);
        } else if (redGhost.getState() == Ghost.states.CHASE) {
            RandomWalkDirectGhost(redGhost);
        }
        if (blueGhost.getState() == Ghost.states.EATEN && blueGhost.isHome() != true) {
            //BFSDirectGhost(blueGhost);
        } else if (blueGhost.getState() == Ghost.states.CHASE && blueGhost.isHome() == false) {
            RandomWalkDirectGhost(blueGhost);
        }
        if (pinkGhost.getState() == Ghost.states.EATEN && pinkGhost.isHome() != true) {
            //BFSDirectGhost(pinkGhost);
        } else if (pinkGhost.getState() == Ghost.states.CHASE && pinkGhost.isHome() == false) {
            RandomWalkDirectGhost(pinkGhost);
        }
        if (orangeGhost.getState() == Ghost.states.EATEN && orangeGhost.isHome() != true) {
            //BFSDirectGhost(orangeGhost);
        } else if (orangeGhost.getState() == Ghost.states.CHASE && orangeGhost.isHome() == false) {
            RandomWalkDirectGhost(orangeGhost);
        }
    }

    public void BFSDirectGhost(Ghost ghost) {
        if (ghost.getDirection() == direction.NONE) {
            //ghost.direction = bfs.seach(ghost);
        }
    }

    public void RandomWalkDirectGhost(Ghost ghost) {
        // Change this to always set direction at grid intersections
        if (ghost.getX() % map.getTileSize() == 0 && ghost.getY() % map.getTileSize() == 0) {
            direction newDirection = randomWalk.seach(ghost);
            ghost.setDirection(newDirection);
            System.out.println("Setting ghost direction to: " + newDirection);
        }
    }
}
