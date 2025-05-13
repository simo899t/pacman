package com.example;

import com.example.MoveableBlock.direction;

public class Update implements IUpdate {
    IMap map;
    IMove move;
    ICollide collide = new Collide(map);
    int tileSize;

    public void Update(IMap map) {
        this.tileSize = map.getTileSize();
        this.move = new Move();
        this.map = map;
        for (Block block : map.getAllBlocks()) {
            if (block instanceof MoveableBlock) {
                if (block.getType() == BlockType.PACMAN) {
                    updateEntity((MoveableBlock) block);
                }
            }
        }
    }

    @Override
    public void updateEntity(MoveableBlock entity) {
        direction bufferDirection = entity.getBufferDirection();
        direction currentDirection = entity.getDirection();
        Block bufferedNextBlock = nextBlock(entity, bufferDirection);
        Block currentNextBlock = nextBlock(entity, currentDirection);

        whatToDCurrent(entity, currentNextBlock, currentDirection, bufferDirection);

        if (bufferedNextBlock == null) {            
            move.move(entity);
            return;
        }

        if (canITurn(entity, bufferDirection)) {
            whatToDoBuffer(entity, bufferedNextBlock, currentDirection, bufferDirection);
        }
        move.move(entity);
    }

    @Override
    public Block nextBlock(MoveableBlock entity, direction direction) {
        switch (direction) {
            case UP:
                return map.getBlock(entity.getX(), entity.getY() - map.getTileSize());
            case DOWN:
                return map.getBlock(entity.getX(), entity.getY() + map.getTileSize());
            case LEFT:
                return map.getBlock(entity.getX() - map.getTileSize(), entity.getY());
            case RIGHT:
                return map.getBlock(entity.getX() + map.getTileSize(), entity.getY());
            default:
                return null;
        }
    }

    public void newBufferDirection(MoveableBlock entity, direction bufferDirection) {
        if (bufferDirection == entity.getDirection()) {
            return;
        }
        else {
            entity.setDirection(bufferDirection);
        }
    }
    
    public boolean canITurn(MoveableBlock entity, direction bufferDirection) {
        switch (entity.getDirection()) {
            case UP:
                if (bufferDirection == direction.DOWN || entity.getX() % tileSize == 0) {
                    return true;
                }
                break;
            case DOWN:
                if (bufferDirection == direction.UP || entity.getX() % tileSize == 0) {
                    return true;
                }
                break;
            case LEFT:
                if (bufferDirection == direction.RIGHT || entity.getY() % tileSize == 0) {
                    return true;
                }
            break;
            case RIGHT:
                if (bufferDirection == direction.LEFT || entity.getY() % tileSize == 0) {
                    return true;
                }
                break;
            case NONE:
                if (entity.getY() % tileSize == 0) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    public void whatToDoBuffer(MoveableBlock entity, Block bufferedNextBlock, direction currentDirection, direction bufferDirection) {
        switch (bufferedNextBlock.getType()) {
            case WALL:
                if (bufferDirection == currentDirection) {
                    entity.setDirection(direction.NONE);
                }
                break;
            case GHOST:
                if (entity.getType() == BlockType.PACMAN) {
                    // ghostCollision(nextblock);
                }
                entity.setDirection(bufferDirection);
                break;
            case PELLET:
                if (entity.getType() == BlockType.PACMAN) {
                    // pelletCollision(nextblock);
                }
                entity.setDirection(bufferDirection);
                break;
            case BIGPELLET:
                if (entity.getType() == BlockType.PACMAN) {
                    // bigPelletCollision(nextblock);
                }
                entity.setDirection(bufferDirection);
                break;
            case DOOR:
                if (entity.getType() == BlockType.GHOST) {
                }
                if (entity.getType() == BlockType.PACMAN) {
                    entity.setDirection(direction.NONE);
                }
                break;
            default:
                entity.setDirection(bufferDirection);
                break;        
        }
    }

    public void whatToDCurrent(MoveableBlock entity, Block currentNextBlock, direction currentDirection, direction bufferDirection) {
        switch (currentNextBlock.getType()) {
            case null:
                entity.setDirection(bufferDirection);
                return;

            case WALL:
                if (bufferDirection == currentDirection) {
                    entity.setDirection(direction.NONE);
                }
                break;
            case GHOST:
                if (entity.getType() == BlockType.PACMAN) {
                    // ghostCollision(nextblock);
                }
                entity.setDirection(bufferDirection);
                break;
            case PELLET:
                if (entity.getType() == BlockType.PACMAN) {
                    // pelletCollision(nextblock);
                }
                entity.setDirection(bufferDirection);
                break;
            case BIGPELLET:
                if (entity.getType() == BlockType.PACMAN) {
                    // bigPelletCollision(nextblock);
                }
                entity.setDirection(bufferDirection);
                break;
            case DOOR:
                if (entity.getType() == BlockType.GHOST) {
                }
                if (entity.getType() == BlockType.PACMAN) {
                    entity.setDirection(direction.NONE);
                }
                break;
            default:
                entity.setDirection(bufferDirection);
                break;
        }
    }
}
