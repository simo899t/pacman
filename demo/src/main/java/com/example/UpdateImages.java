package com.example;

import com.example.Block.BlockType;

import javafx.scene.image.Image;

public class UpdateImages {
    private final IMap map;

    /**
     * Constructor for UpdateImages that initializes the map.
     * 
     * @param map The map containing all blocks to update images for.
     */
    public UpdateImages(IMap map) {
        this.map = map;
    }  

    // Initialization of images and animation variables.
    int pacmanAnimationStep = 0;
    int pacmanAnimationChangeCount = 0;
    int pacmanMouthSpeed = 8;
    Image pacmanImageRight = new Image(getClass().getResource("/com/example/images/pacmanRight.png").toExternalForm());
    Image pacmanImageLeft = new Image(getClass().getResource("/com/example/images/pacmanLeft.png").toExternalForm());
    Image pacmanImageUp = new Image(getClass().getResource("/com/example/images/pacmanUp.png").toExternalForm());
    Image pacmanImageDown = new Image(getClass().getResource("/com/example/images/pacmanDown.png").toExternalForm());
    Image pacmanImage = new Image(getClass().getResource("/com/example/images/pacman.png").toExternalForm());
    Image doorClosed = new Image(getClass().getResource("/com/example/images/doorClosed.png").toExternalForm());
    Image doorOpen = new Image(getClass().getResource("/com/example/images/doorOpen.png").toExternalForm());
    Image redGhostImageRight = new Image(getClass().getResource("/com/example/images/redGhostRight.png").toExternalForm());
    Image redGhostImageLeft = new Image(getClass().getResource("/com/example/images/redGhostLeft.png").toExternalForm());
    Image redGhostImageUp = new Image(getClass().getResource("/com/example/images/redGhostUp.png").toExternalForm());
    Image redGhostImageDown = new Image(getClass().getResource("/com/example/images/redGhostDown.png").toExternalForm());
    Image blueGhostImageRight = new Image(getClass().getResource("/com/example/images/blueGhostRight.png").toExternalForm());
    Image blueGhostImageLeft = new Image(getClass().getResource("/com/example/images/blueGhostLeft.png").toExternalForm());
    Image blueGhostImageUp = new Image(getClass().getResource("/com/example/images/blueGhostUp.png").toExternalForm());
    Image blueGhostImageDown = new Image(getClass().getResource("/com/example/images/blueGhostDown.png").toExternalForm());
    Image pinkGhostImageRight = new Image(getClass().getResource("/com/example/images/pinkGhostRight.png").toExternalForm());
    Image pinkGhostImageLeft = new Image(getClass().getResource("/com/example/images/pinkGhostLeft.png").toExternalForm());
    Image pinkGhostImageUp = new Image(getClass().getResource("/com/example/images/pinkGhostUp.png").toExternalForm());
    Image pinkGhostImageDown = new Image(getClass().getResource("/com/example/images/pinkGhostDown.png").toExternalForm());
    Image orangeGhostImageRight = new Image(getClass().getResource("/com/example/images/orangeGhostRight.png").toExternalForm());
    Image orangeGhostImageLeft = new Image(getClass().getResource("/com/example/images/orangeGhostLeft.png").toExternalForm());
    Image orangeGhostImageUp = new Image(getClass().getResource("/com/example/images/orangeGhostUp.png").toExternalForm());
    Image orangeGhostImageDown = new Image(getClass().getResource("/com/example/images/orangeGhostDown.png").toExternalForm());

    /**
     * Updates the images of all moveable blocks in the map.
     * This method should be called within a gameloop to refresh the images based on their current state.
     */
    public void updateAllImages() {
        for (Block block : map.getAllBlocks()) {
            if (block instanceof MoveableBlock) {
                updateImage((MoveableBlock) block);
            }
        }
    }


    /**
     * Updates the image of a specific moveable block based on its type and state.
     * 
     * @param entity The moveable block whose image is to be updated.
     */
    public void updateImage(MoveableBlock entity) {
        if (entity.getType() == BlockType.PACMAN) {
            if (pacmanAnimationStep == 0) {
                // Setting the direction based on pacman's current direction.
                switch (entity.getDirection()) {
                    case UP:
                        entity.setImage(pacmanImageUp);
                        break;
                    case DOWN:
                        entity.setImage(pacmanImageDown);
                        break;
                    case LEFT:
                        entity.setImage(pacmanImageLeft);
                        break;
                    case RIGHT:
                        entity.setImage(pacmanImageRight);
                        break;
                    default:
                        break;
                }
                // Doing the actual pacman animation, making it open and close its mouth.
                if (pacmanAnimationChangeCount >= pacmanMouthSpeed && ((Pacman) entity).isAlive()) {
                    // If it is here then mouth is open.
                    pacmanAnimationStep = 1;
                    pacmanAnimationChangeCount = 0;
                }
                pacmanAnimationChangeCount++;
                } else {
                    // If it is here then mouth is closed.
                    entity.setImage(pacmanImage);
                    if (pacmanAnimationChangeCount >= pacmanMouthSpeed) {
                        pacmanAnimationStep = 0;
                        pacmanAnimationChangeCount = 0;
                    }
                    pacmanAnimationChangeCount++;
            }
        } else if (entity.getType() == BlockType.GHOST) {
            if (entity instanceof Ghost) {
                Ghost ghost = (Ghost) entity;
                // The color of the ghost is determined by its type and used for the image path.
                String GhostColor = ghost.getColor().toString();
                String GhostImage = null;
                if (ghost.getState() == Ghost.states.CHASE) {
                    switch (entity.getDirection()){
                        case UP:
                            GhostImage = "/com/example/images/"+GhostColor+"GhostUp.png";
                            break;
                        case DOWN:
                            GhostImage = "/com/example/images/"+GhostColor+"GhostDown.png";
                            break;
                        case LEFT:
                            GhostImage = "/com/example/images/"+GhostColor+"GhostLeft.png";
                            break;
                        case RIGHT:
                            GhostImage = "/com/example/images/"+GhostColor+"GhostRight.png";
                            break;
                        default:
                            GhostImage = "/com/example/images/"+GhostColor+"GhostRight.png";
                            break;
                    }
                } else if (ghost.getState() == Ghost.states.EATEN) {
                    switch (entity.getDirection()){
                        case UP:
                            GhostImage = "/com/example/images/deadGhostUp.png";
                            break;
                        case DOWN:
                            GhostImage = "/com/example/images/deadGhostDown.png";
                            break;
                        case LEFT:
                            GhostImage = "/com/example/images/deadGhostLeft.png";
                            break;
                        case RIGHT:
                            GhostImage = "/com/example/images/deadGhostRight.png";
                            break;
                        default:
                            GhostImage = "/com/example/images/deadGhostRight.png";
                            break;
                    }
                } else if (ghost.getState() == Ghost.states.STILL) {
                    GhostImage = "/com/example/images/" + GhostColor + "GhostRight.png";
                }
                // If a GhostImage is determined, update the entity's image.
                if (GhostImage != null) {
                    Image updatedGhost = new Image(getClass().getResource(GhostImage).toExternalForm());
                    entity.setImage(updatedGhost);
                }
            }
        }
    }
}

