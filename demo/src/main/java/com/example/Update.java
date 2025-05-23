package com.example;

import com.example.MoveableBlock.direction;

public class Update implements IUpdate {
    private final IMap map;
    private final Move move;
    private final int tileSize;
    private final Collision collision;
    private final CollideHandler collideHandler;
    private final GameTimer gameTimer;
    private final UpdateImages updateImages;

    public Update(IMap map, GameScore score, GameLives lives, GameTimer gameTimer, Game game, UpdateImages updateImages, Revive revive) {
        this.map = map;
        this.tileSize = map.getTileSize(); 
        this.move = new Move();
        this.collision = new Collision(map);
        this.gameTimer = gameTimer;
        this.updateImages = updateImages;
        this.collideHandler = new CollideHandler(map, score, lives, gameTimer, game, updateImages, revive);
  
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
        BlockType entityType = entity.getType();
        BlockType blockType;

        boolean isReverse = (currentDirection == direction.LEFT && bufferDirection == direction.RIGHT) ||
                            (currentDirection == direction.RIGHT && bufferDirection == direction.LEFT) ||
                            (currentDirection == direction.UP && bufferDirection == direction.DOWN) ||
                            (currentDirection == direction.DOWN && bufferDirection == direction.UP);

        
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
                    case FRUIT:
                        // collideHandler.fruitCollision(entity, block);
                        break;
                    case TELEPORTER: // this could be a method by itself, but need nextblock
                        for (Block otherTeleporter : map.getAllBlocks()) {
                            if (otherTeleporter.getType() == BlockType.TELEPORTER && otherTeleporter != block) {
                                Block nextToTeleporter = nextBlock(otherTeleporter, entity.getDirection());
                                if (nextToTeleporter != null) {
                                    entity.setPos(nextToTeleporter.getX(), nextToTeleporter.getY());
                                    // System.out.println("Pacman teleported to another teleporter");
                                    break;
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
            
            if (entityType == BlockType.GHOST) {
                switch (blockType) {
                    case DOOR:
                        collideHandler.doorCollision(block);
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
                                    // System.out.println("Ghost teleported to another teleporter");
                                    break;
                                }
                            }
                        }
                        break;
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

        if (canITurn(entity, bufferDirection)) {
            Block bufferedNextBlock = nextBlock(entity, bufferDirection);
            whatToDoBuffer(entity, bufferedNextBlock, currentDirection, bufferDirection);
        }

        // mby place this in the collide handler???
        Block moveNextBlock = nextBlock(entity, entity.getDirection());
        if (moveNextBlock == null) {
            move.move(entity);
        } else if (
            (entity.getType() == BlockType.GHOST && moveNextBlock.getType() != BlockType.WALL) ||
            (entity.getType() == BlockType.PACMAN && moveNextBlock.getType() != BlockType.WALL && moveNextBlock.getType() != BlockType.DOOR)
        ) {
            move.move(entity);
        } else {
            entity.setDirection(direction.NONE);
        }
    }
    }


    @Override
    public Block nextBlock(Block block, direction direction) {
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
