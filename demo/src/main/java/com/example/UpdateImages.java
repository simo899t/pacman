package com.example;

import com.example.MoveableBlock.direction;

import javafx.scene.image.Image;

public class UpdateImages implements IUpdateImages {
    private IMap map;

    public UpdateImages(IMap map) {
        this.map = map;
        updateAllImages();
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

    @Override
    public void updateAllImages() {
        for (Block block : map.getAllBlocks()) {
            updateImage(block);
        }
    }

    public void updateImage(Block block) {
        switch (block.getType()) {
            case PACMAN:
                switch (((MoveableBlock) block).getDirection()) {
                    case UP:
                        block.setImage(pacmanImageUp);
                        break;
                    case DOWN:
                        block.setImage(pacmanImageDown);
                        break;
                    case LEFT:
                        block.setImage(pacmanImageLeft);
                        break;
                    case RIGHT:
                        block.setImage(pacmanImageRight);
                    break;
                    default:
                        block.setImage(pacmanImage);
                        break;
                }
        }
    }

    @Override
    public void animateAllBlocks() {
        for (Block block : map.getAllBlocks()) {
            animateBlock(block);
        }
    }

    public void animateBlock(Block entity) {
        switch (entity.getType()) {
            case PACMAN:
                if (((MoveableBlock) entity).getAnimationImage() == 0 && ((MoveableBlock) entity).getDirection() != direction.NONE) {
                    ((MoveableBlock) entity).setImage(pacmanImage); 
                break;
                }
            default:
                    break;
        }
    }
}

