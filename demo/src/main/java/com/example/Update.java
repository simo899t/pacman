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
        



        if (canITurn(entity, bufferDirection)) {
            Block currentNextBlock = nextBlock(entity, currentDirection);
            whatToDCurrent(entity, currentNextBlock, currentDirection, bufferDirection);
            Block bufferedNextBlock = nextBlock(entity, bufferDirection);
            whatToDoBuffer(entity, bufferedNextBlock, currentDirection, bufferDirection);
            whatToDCurrent(entity, currentNextBlock, currentDirection, bufferDirection);
            for (Block block : map.getAllBlocks()) {
                if (collide.isColliding(entity, block, map)) {
                    if (block.getType() == BlockType.WALL) {
                        System.out.println("collision");
                    }   
                }
            }
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
                return map.getBlock(entity.getX(), entity.getY());
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
                if (entity.getX() % tileSize == 0 && entity.getY() % tileSize == 0) {
                    return true;
                }
                break;
            case DOWN:
                if (entity.getX() % tileSize == 0 && entity.getY() % tileSize == 0) {
                    return true;
                }
                break;
            case LEFT:
                if (entity.getY() % tileSize == 0 && entity.getX() % tileSize == 0) {
                    return true;
                }
            break;
            case RIGHT:
                if (entity.getY() % tileSize == 0 && entity.getX() % tileSize == 0) {
                    return true;
                }
                break;
            case NONE:
                    return true;
            default:
                return false;
        }
        return false;
    }

    public void whatToDoBuffer(MoveableBlock entity, Block bufferedNextBlock, direction currentDirection, direction bufferDirection) {
        switch (bufferedNextBlock.getType()) {
            case WALL:
                break;
            case DOOR:
                break;
            default:
                entity.setDirection(bufferDirection);
                break;        
        }
    }

    public void whatToDCurrent(MoveableBlock entity, Block currentNextBlock, direction currentDirection, direction bufferDirection) {
    
        switch (currentNextBlock.getType()) {
            case WALL:
                if (bufferDirection == currentDirection) {
                    entity.setDirection(direction.NONE);
                }
                break;
            case GHOST:
                if (entity.getType() == BlockType.PACMAN) {
                    // ghostCollision(nextblock);
                }

                break;
            case PELLET:
                if (entity.getType() == BlockType.PACMAN) {
                    // pelletCollision(nextblock);
                }
                // System.out.println("pellet collision");
                break;
            case BIGPELLET:
                if (entity.getType() == BlockType.PACMAN) {
                    // bigPelletCollision(nextblock);
                }
                break;
            case DOOR:
                if (entity.getType() == BlockType.GHOST) {
                }
                if (entity.getType() == BlockType.PACMAN) {
                    entity.setDirection(direction.NONE);
                }
                break;
            default:
                break;
        }
    }
}
