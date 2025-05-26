package com.example;

import javafx.scene.image.Image;

public class UpdateImages {
    private final IMap map;
    private GameTimer loadImageTimer;

    public UpdateImages(IMap map, GameTimer loadImageTimer) {
        this.loadImageTimer = loadImageTimer;
        this.map = map;
    }  

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

    
    public void updateAllImages() {
        for (Block block : map.getAllBlocks()) {
            if (block instanceof MoveableBlock) {
                updateImage((MoveableBlock) block);
            }
        }
    }

    public String blinkingGhost(MoveableBlock ghost) {

        String currentImage = "/com/example/images/scaredGhost.png";

        System.out.println(ghost.getImage());
        //if ghost.getImage() == 

        long currentTime = System.currentTimeMillis();
        long blinkTime = 1000L;
        
        loadImageTimer.addFunctionToList(
            GameTimer.atTimeRunFunction(
                "NextBlinkIn", currentTime, blinkTime, () -> {
                    blinkingGhost(ghost);
                }
            )
        );
        return currentImage;
    }

    // DO A GET IMAGE TO SPECIFIC IMAGE
    // Issue is constant recalling of the updateimage, which means the function is set to start all the time.
    // Timer start, timer done to make it work in 

    public void updateImage(MoveableBlock entity) {
        // int animationImage = block.getAnimationImage();
        // if (animationImage == 1) {
        //     animationImage = 1;
        switch (entity.getType()) {
            case PACMAN:
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
            case GHOST:
                if (entity instanceof Ghost) {
                    Ghost ghost = (Ghost) entity;
                    String GhostColor = ghost.getColor().toString();
                    String GhostImage = null;
                    if (ghost.getState() == Ghost.states.FRIGHTENED) {
                        // GhostImage = "/com/example/images/"+GhostColor+"GhostUp.png";
                        // done in eater right now.
                    } else if (ghost.getState() == Ghost.states.CHASE) {
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
                        GhostImage = "/com/example/images/"+GhostColor+"GhostRight.png";
                    }
                    if (GhostImage != null) {
                        Image updatedGhost = new Image(getClass().getResource(GhostImage).toExternalForm());
                        entity.setImage(updatedGhost);
                    }
                }
                

        }
    }


}

