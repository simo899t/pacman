package com.example;

import com.example.Block.BlockType;
import com.example.MoveableBlock.directions;

public class Update implements IUpdate {
    private final IMap map;
    private final Move move;
    private final int tileSize;
    private final Collision collision;
    private final ICollideHandler collideHandler;

    public Update(IMap map, ICollideHandler collideHandler, Collision collision) {
        this.map = map;
        this.tileSize = map.getTileSize(); 
        this.move = new Move();
        this.collision = collision;
        this.collideHandler = collideHandler;
  
    }

    public void updateGame(IMap map) {
        for (Block block : map.getAllBlocks()) {
            if (block instanceof MoveableBlock) {
                if (block.getType() == BlockType.GHOST || block.getType() == BlockType.PACMAN) {
                    updateEntity((MoveableBlock) block);
                }
            }
        }
    }

    @Override
    public void updateEntity(MoveableBlock entity) {
        directions bufferDirection = entity.getBufferDirection();
        directions currentDirection = entity.getDirection();
        BlockType entityType = entity.getType();
        BlockType blockType;

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
                        collideHandler.bigPelletCollision((Pellet) block);
                        break;
                    case GHOST:
                        collideHandler.ghostCollision((Ghost) block);
                        break;
                    case TELEPORTER: // this could be a method by itself, but need nextblock
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
                    case TELEPORTER: // this also could be a method by itself, but need nextblock
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

    public void newBufferDirection(MoveableBlock entity, directions bufferDirection) {
        if (bufferDirection == entity.getDirection()) {
            return;
        }
        else {
            entity.setDirection(bufferDirection);
        }
    }
    
    public boolean canITurn(MoveableBlock entity, directions bufferDirection) {
        return entity.getX() % tileSize == 0 && entity.getY() % tileSize == 0;
    }

    public void whatToDoBuffer(MoveableBlock entity, Block bufferedNextBlock, directions currentDirection, directions bufferDirection) {
        if (bufferedNextBlock == null) {
            return; // No next block to check
        }
        if (entity.getType() == BlockType.GHOST) {
            // Ghosts can turn into anything except walls
            if (bufferedNextBlock.getType() != BlockType.WALL) {
                entity.setDirection(bufferDirection);
                return;
            }
        } else {
            // Pacman can't turn into walls or doors
            if (bufferedNextBlock.getType() != BlockType.WALL && bufferedNextBlock.getType() != BlockType.DOOR) {
                entity.setDirection(bufferDirection);
                return;
            }
    }
}

    public void whatToDCurrent(MoveableBlock entity, Block currentNextBlock, directions currentDirection, directions bufferDirection) {
    
        switch (currentNextBlock.getType()) {
            case WALL:
                if (bufferDirection == currentDirection) {
                    entity.setDirection(directions.NONE);
                }
                break;
            case DOOR:
                if (entity.getType() == BlockType.GHOST) {
                }
                if (entity.getType() == BlockType.PACMAN) {
                    entity.setDirection(directions.NONE);
                }
                break;
            default:
                break;
        }
    }
}
