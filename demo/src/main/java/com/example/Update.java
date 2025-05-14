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
            if (bufferedNextBlock != null && bufferedNextBlock.getType() != BlockType.WALL) {
                entity.setDirection(bufferDirection);
            }
        }

        Block moveNextBlock = nextBlock(entity, entity.getDirection());
        if (moveNextBlock == null){
            move.move(entity);
        } else if (moveNextBlock.getType() != BlockType.WALL) {
            move.move(entity);
        } else {
            entity.setDirection(direction.NONE);
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
        if (!canITurn(entity, bufferDirection)) return;

        switch (bufferedNextBlock.getType()) {
            case WALL:
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
