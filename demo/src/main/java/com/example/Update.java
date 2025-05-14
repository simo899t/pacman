package com.example;

import com.example.MoveableBlock.direction;

public class Update implements IUpdate {
    IMap map;
    IMove move;
    int tileSize;

    Collision collision = new Collision(map);
    CollideHandler collideHandler = new CollideHandler(map);
    
    public Update(IMap map) {
        this.tileSize = map.getTileSize();
        this.move = new Move();
        this.map = map;
    }

    public void updateGame(IMap map) {
        for (Block block : map.getAllBlocks()) {
            if (block instanceof MoveableBlock) {
                if (block.getType() == BlockType.PACMAN || block.getType() == BlockType.GHOST) {
                    updateEntity((MoveableBlock) block);
                }
            }
        }
    }

    @Override
    public void updateEntity(MoveableBlock entity) {
        direction bufferDirection = entity.getBufferDirection();
        direction currentDirection = entity.getDirection();

        boolean isReverse = (currentDirection == direction.LEFT && bufferDirection == direction.RIGHT) ||
                            (currentDirection == direction.RIGHT && bufferDirection == direction.LEFT) ||
                            (currentDirection == direction.UP && bufferDirection == direction.DOWN) ||
                            (currentDirection == direction.DOWN && bufferDirection == direction.UP);

        if (isReverse) {
            Block reverseNextBlock = nextBlock(entity, bufferDirection);
            if (reverseNextBlock == null) {
                entity.setDirection(bufferDirection);
            }
            else if (reverseNextBlock.getType() != BlockType.WALL) {
                entity.setDirection(bufferDirection);
            }
        }

        if (canITurn(entity, bufferDirection)) {
            Block bufferedNextBlock = nextBlock(entity, bufferDirection);
            whatToDoBuffer(entity, bufferedNextBlock, currentDirection, bufferDirection);
        }
        
        Block moveNextBlock = nextBlock(entity, entity.getDirection());
        if (moveNextBlock == null){
            move.move(entity);
        } else if (moveNextBlock.getType() != BlockType.WALL) {
            move.move(entity);
        } else {
            entity.setDirection(direction.NONE);
        }

        for (Block block : map.getAllBlocks()) {
            switch (block.getType()) {
                case PELLET:
                    if (collision.checkCollision(entity, block)) {
                        collideHandler.pelletCollision(entity, block);
                    }
                    break;
                case PACMAN:
                    if (collision.checkCollision(entity, block)) {
                        collideHandler.ghostCollision((Ghost) entity, (Pacman) block);
                    }
                    break;
                case BIGPELLET:
                    if (collision.checkCollision(entity, block)) {
                        collideHandler.bigPelletCollision(entity, block);
                    }
                    break;
                default:
                    break;
            }

        }
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
        return entity.getX() % tileSize == 0 && entity.getY() % tileSize == 0;
    }

    public void whatToDoBuffer(MoveableBlock entity, Block bufferedNextBlock, direction currentDirection, direction bufferDirection) {
        if (bufferedNextBlock.getType() != BlockType.WALL && bufferedNextBlock.getType() != BlockType.DOOR) {
            entity.setDirection(bufferDirection);
            return;
        }
    }

    public void whatToDCurrent(MoveableBlock entity, Block currentNextBlock, direction currentDirection, direction bufferDirection) {
    
        switch (currentNextBlock.getType()) {
            case WALL:
                if (bufferDirection == currentDirection) {
                    entity.setDirection(direction.NONE);
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
