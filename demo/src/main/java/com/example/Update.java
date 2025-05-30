package com.example;

import com.example.Block.BlockType;
import com.example.MoveableBlock.directions;

public class Update implements IUpdate {
    private final IMap map;
    private final Move move;
    private final int tileSize;
    private final Collision collision;
    private final ICollideHandler collideHandler;

    /**
     * Constructor for Update that initializes the map, move handler, collision checker, and collide handler.
     * 
     * @param map The map containing all blocks to update.
     * @param collideHandler The handler for collision events.
     * @param collision The collision checker to determine block collisions.
     */
    public Update(IMap map, ICollideHandler collideHandler, Collision collision) {
        this.map = map;
        this.tileSize = map.getTileSize(); 
        this.move = new Move();
        this.collision = collision;
        this.collideHandler = collideHandler;
  
    }

    /**
     * Updates the state of all moveable blocks in the game.
     * This includes handling collisions, movement, and direction changes for Pacman and Ghosts.
     * It ensures that only moveable blocks are processed, and their interactions with other blocks are handled appropriately.
     * This method should be called within a game loop to refresh the movement and interactions of entities.
     * 
     * @param map The map containing all blocks to update.
     */
    public void updateGame(IMap map) {
        for (Block block : map.getAllBlocks()) {
            if (block instanceof MoveableBlock) {
                if (block.getType() == BlockType.GHOST || block.getType() == BlockType.PACMAN) {
                    updateEntity((MoveableBlock) block);
                }
            }
        }
    }

    /**
     * Updates the state of a specific moveable block (Pacman or Ghost).
     * This method handles collisions, direction changes, and movement logic.
     * 
     * @param entity The moveable block to update.
     */
    @Override
    public void updateEntity(MoveableBlock entity) {
        directions bufferDirection = entity.getBufferDirection();
        directions currentDirection = entity.getDirection();
        BlockType entityType = entity.getType();
        BlockType blockType;

        // If the entity wants to turn around 180, it should be able to do so.
        boolean isReverse = (currentDirection == directions.LEFT && bufferDirection == directions.RIGHT) ||
                            (currentDirection == directions.RIGHT && bufferDirection == directions.LEFT) ||
                            (currentDirection == directions.UP && bufferDirection == directions.DOWN) ||
                            (currentDirection == directions.DOWN && bufferDirection == directions.UP);

        
        for (Block block : map.getAllBlocks()) {
            blockType = block.getType();
            if (!collision.checkCollision(entity, block)) {
                continue;
            }

            if (entityType == BlockType.PACMAN) {
                switch (blockType) {
                    case PELLET:
                        collideHandler.pelletCollision((Pellet) block);
                        break;
                    case BIGPELLET:
                        collideHandler.bigPelletCollision((BigPellet) block);
                        break;
                    case GHOST:
                        collideHandler.ghostCollision((Ghost) block);
                        break;
                    case TELEPORTER:
                        // Teleport Pacman to the next teleporter block.
                        for (Block otherTeleporter : map.getAllBlocks()) {
                            if (otherTeleporter.getType() == BlockType.TELEPORTER && otherTeleporter != block) {
                                Block nextToTeleporter = nextBlock(otherTeleporter, entity.getDirection());
                                if (nextToTeleporter != null) {
                                    entity.setPos(nextToTeleporter.getX(), nextToTeleporter.getY());
                                    break;
                                }
                            }
                        }
                    default:
                        break;
                }
            }
            
            if (entityType == BlockType.GHOST) {
                switch (blockType) {
                    case DOOR:
                        collideHandler.doorCollision((Door) block);
                        break;
                    case GHOSTHOME:
                        collideHandler.homeCollision((Ghost) entity);
                        break;
                    case TELEPORTER: 
                        // Teleport Ghost to the next teleporter block.
                        for (Block otherTeleporter : map.getAllBlocks()) {
                            if (otherTeleporter.getType() == BlockType.TELEPORTER && otherTeleporter != block) {
                                Block nextToTeleporter = nextBlock(otherTeleporter, entity.getDirection());
                                if (nextToTeleporter != null) {
                                    entity.setPos(nextToTeleporter.getX(), nextToTeleporter.getY());
                                    break;
                                }
                            }
                        }
                    default:
                        break;    
                }
            }


            if (entity.getType() == BlockType.PACMAN) {
                if (isReverse) {
                    Block reverseNextBlock = nextBlock(entity, bufferDirection);
                    if (reverseNextBlock == null) {
                        entity.setDirection(bufferDirection);
                    }
                    else if (reverseNextBlock.getType() != BlockType.WALL) {
                        entity.setDirection(bufferDirection);
                    }
                }
            }

            // Add this check right before movement processing
            if (entityType == BlockType.PACMAN && !((Pacman) entity).isAlive()) {
                return; // Skip movement if Pacman was killed during collision
            } else if (entity.getType() == BlockType.GHOST && ((Ghost) entity).getState() == Ghost.states.STILL) {
                // If the ghost is eaten, it should not move
                entity.setDirection(directions.NONE);
                continue; // Skip further processing for this ghost
            }

            // Handle buffer direction changes
            if (canITurn(entity, bufferDirection)) {
                Block bufferedNextBlock = nextBlock(entity, bufferDirection);
                whatToDoBuffer(entity, bufferedNextBlock, currentDirection, bufferDirection);
            }

            // Handle movement
            Block moveNextBlock = nextBlock(entity, entity.getDirection());
            if (moveNextBlock == null) {
                move.move(entity);
            } else if (
                (entity.getType() == BlockType.GHOST && moveNextBlock.getType() != BlockType.WALL) ||
                (entity.getType() == BlockType.PACMAN && moveNextBlock.getType() != BlockType.WALL && moveNextBlock.getType() != BlockType.DOOR)
            ) {
                move.move(entity);
            } else {
                entity.setDirection(directions.NONE);
            }
        }
    }

    /**
     * Returns the next block in the specified direction from the given block.
     * 
     * @param block The current block.
     * @param direction The direction to move to the next block.
     * @return The next block in the specified direction, or the current block if no valid next block exists.
     */
    @Override
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

    /**
     * Sets a new buffer direction for the entity if it differs from the current direction.
     * 
     * @param entity The moveable block whose direction is to be updated.
     * @param bufferDirection The new direction to set as the buffer.
     */
    public void newBufferDirection(MoveableBlock entity, directions bufferDirection) {
        if (bufferDirection == entity.getDirection()) {
            return;
        }
        else {
            entity.setDirection(bufferDirection);
        }
    }
    
    /**
     * Checks if the entity can turn based on its current position and tile size.
     * Making sure that the entity is aligned with the grid before allowing a turn.
     * 
     * @param entity The moveable block to check.
     * @param bufferDirection The direction to check for turning.
     * @return true if the entity can turn, false otherwise.
     */
    public boolean canITurn(MoveableBlock entity, directions bufferDirection) {
        return entity.getX() % tileSize == 0 && entity.getY() % tileSize == 0;
    }

    /**
     * Determines what to do with the buffered next block based on the entity's type and direction.
     * Allowing ghosts to turn into any block except walls, and Pacman to turn into any block except walls or doors.
     * 
     * @param entity The moveable block whose direction is to be updated.
     * @param bufferedNextBlock The next block in the buffered direction.
     * @param currentDirection The current direction of the entity.
     * @param bufferDirection The buffered direction to check against.
     */
    public void whatToDoBuffer(MoveableBlock entity, Block bufferedNextBlock, directions currentDirection, directions bufferDirection) {
        if (bufferedNextBlock == null) {
            return; // No next block to check
        }
        if (entity.getType() == BlockType.GHOST) {
            // Ghosts can turn into anything except walls
            if (bufferedNextBlock.getType() != BlockType.WALL) {
                entity.setDirection(bufferDirection);
            }
        } else {
            // Pacman can't turn into walls or doors
            if (bufferedNextBlock.getType() != BlockType.WALL && bufferedNextBlock.getType() != BlockType.DOOR) {
                entity.setDirection(bufferDirection);
            }
        }
    }   

    /**
     * Determines what to do with the current next block based on the entity's type and direction.
     * This method handles collisions with walls and doors, ensuring that Pacman and Ghosts behave correctly.
     * 
     * @param entity The moveable block whose direction is to be updated.
     * @param currentNextBlock The next block in the current direction.
     * @param currentDirection The current direction of the entity.
     * @param bufferDirection The buffered direction to check against.
     */
    public void whatToDCurrent(MoveableBlock entity, Block currentNextBlock, directions currentDirection, directions bufferDirection) {
        switch (currentNextBlock.getType()) {
            case WALL:
                // No entity can pass through walls, so the direction is set to NONE.
                if (bufferDirection == currentDirection) {
                    entity.setDirection(directions.NONE);
                }
                break;
            case DOOR:
                // Pacman cannot pass through doors, so the direction is set to NONE.
                if (entity.getType() == BlockType.PACMAN) {
                    entity.setDirection(directions.NONE);
                }
                break;
            default:
                break;
        }
    }
}
