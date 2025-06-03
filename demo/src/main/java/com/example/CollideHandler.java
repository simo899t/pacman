package com.example;

import com.example.Block.BlockType;
import com.example.MoveableBlock.directions;

public class CollideHandler implements ICollideHandler {
    private final IKillEntity killEntity;
    private final IEater eater;
    private final GameTimer gameTimer;
    private final Revive revive;
    private final IMap map;

    /**
     * Constructor for CollideHandler
     *
     * @param gameTimer The game timer to manage timed events
     * @param revive    The revive handler for ghost revival logic
     * @param eater     The eater handler for eating logic
     * @param killEntity The entity responsible for killing players and ghosts
     */
    public CollideHandler(GameTimer gameTimer, Revive revive, IEater eater, IKillEntity killEntity, IMap map) {
        this.killEntity = killEntity;
        this.eater = eater;
        this.gameTimer = gameTimer;
        this.revive = revive;
        this.map = map;
    }

    @Override
    public void collision(Block entity) {
        if (entity.getType() == BlockType.GHOST) {
            collision((Ghost) entity);
        } else if (entity.getType() == BlockType.GHOSTHOME) {
            homeCollision((Ghost) entity);
        } else if (entity.getType() == BlockType.DOOR) {
            collision((Door) entity);
        } else if (entity.getType() == BlockType.PELLET) {
            collision((Pellet) entity);
        } else if (entity.getType() == BlockType.BIGPELLET) {
            collision((BigPellet) entity);
        } else if (entity.getType() == BlockType.TELEPORTER) {
            for (Block otherTeleporter : map.getAllBlocks()) {
                            if (otherTeleporter.getType() == BlockType.TELEPORTER && otherTeleporter != entity) {
                                Block nextToTeleporter = nextBlock(otherTeleporter, ((MoveableBlock) entity).getDirection());
                                if (nextToTeleporter != null) {
                                    entity.setPos(nextToTeleporter.getX(), nextToTeleporter.getY());
                                    break;
                                }
                            }
                        }
        }
    }

    private void collision(Ghost ghost) {
        if (ghost.getState() == Ghost.states.FRIGHTENED) {
            killEntity.killGhost(ghost); // The ghost is killed by the player
            eater.eat(ghost); // The player eats the ghost (points awarded)
        } else if (ghost.getState() == Ghost.states.CHASE) {
            // If the ghost is in chase state, the player loses
            killEntity.killPlayer(); // The player is killed by the ghost
        }
    }

    /**
     * Handles the collision between the player and a home.
     * @param ghost The ghost that collided with the home
     */
    @Override
    public void homeCollision(Ghost ghost) {
        revive.tryReviveGhost(ghost); // Attempt to revive the ghost
    }

    /**
     * Handles the collision between the player and a door.
     * @param door The door that the enitity collided with
     */
    private void collision(Door door) {
        door.openDoor();

        // Schedule the door to close after 1 second
        long currentTime = System.currentTimeMillis();
        gameTimer.addFunctionToList(
            GameTimer.atTimeRunFunction(
                "OpenTheDoor", currentTime, 1000L, () -> {
                    door.closeDoor();
                }
            )
        );

    }

    /**
     * Handles the collision between the player and a pellet.
     * @param pellet The pellet that the player collided with
     */
    private void collision(Pellet pellet) {
        eater.eat(pellet); // The player eats the pellet (points awarded)
    }

    /**
     * Handles the collision between the player and a big pellet.
     * @param pellet The big pellet that the player collided with
     */
    private void collision(BigPellet bigPellet) {
        eater.eat(bigPellet); // The player eats the big pellet (points awarded and ghosts frightened)
    }  
    
    public Block nextBlock(Block block, directions direction) {
        switch (direction) {
            case UP:
                return map.getBlock(block.getX(), block.getY() - map.getTileSize());
            case DOWN:
                return map.getBlock(block.getX(), block.getY() + map.getTileSize());
            case LEFT:
                return map.getBlock(block.getX() - map.getTileSize(), block.getY());
            case RIGHT:
                return map.getBlock(block.getX() + map.getTileSize(), block.getY());
            default:
                return map.getBlock(block.getX(), block.getY());
        }
    }
}
