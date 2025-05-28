package com.example;

import java.util.ArrayList;

import com.example.Ghost.states;

import javafx.scene.image.Image;

public class BlockFactory {
    static Image pacmanImage = new Image(BlockFactory.class.getResource("/com/example/images/pacman.png").toExternalForm());
    static Image wallImage = new Image(BlockFactory.class.getResource("/com/example/images/wall.png").toExternalForm());
    static Image doorClosed = new Image(BlockFactory.class.getResource("/com/example/images/doorClosed.png").toExternalForm());
    static Image redGhostImage = new Image(BlockFactory.class.getResource("/com/example/images/redGhostRight.png").toExternalForm());
    static Image blueGhostImage = new Image(BlockFactory.class.getResource("/com/example/images/blueGhostRight.png").toExternalForm());
    static Image pinkGhostImage = new Image(BlockFactory.class.getResource("/com/example/images/pinkGhostRight.png").toExternalForm());
    static Image orangeGhostImage = new Image(BlockFactory.class.getResource("/com/example/images/orangeGhostRight.png").toExternalForm());
    static Image smallFoodImage = new Image(BlockFactory.class.getResource("/com/example/images/smallFood.png").toExternalForm());
    static Image bigFoodImage = new Image(BlockFactory.class.getResource("/com/example/images/bigFood.png").toExternalForm());
  
        public BlockFactory() {
        }

        public static Block createPellet(int x, int y) {
            Pellet pellet = new Pellet(smallFoodImage, x, y);
            pellet.setType(BlockType.PELLET);
            pellet.setEaten(false);
            
            return pellet;
        }

        public static Block createGhostHome(int x, int y) {
            Block ghostHome = new Block(null, x, y);
            ghostHome.setType(BlockType.GHOSTHOME);
            return ghostHome;
        }

        public static Block createDoor(int x, int y) {
            Block door = new Block(doorClosed, x, y);
            door.setType(BlockType.DOOR);
            return door;
        }

        public static Block createBigPellet(int x, int y) {
            Pellet bigPellet = new Pellet(bigFoodImage, x, y);
            bigPellet.setType(BlockType.PELLET);
            bigPellet.setEaten(false);
            return bigPellet;
        }

        public static Block createEatenPellet(int x, int y) {
            Pellet eatenPellet = new Pellet(null, x, y);
            eatenPellet.setType(BlockType.PELLET);
            eatenPellet.setEaten(true);
            return eatenPellet;
        }

        static Block createWall(int x, int y) {
            Block wall = new Block(wallImage, x, y);
            wall.setType(BlockType.WALL);
            return wall;
        }

        public static Pacman createPacman(int x, int y) {
            Pacman pacman = new Pacman(pacmanImage, x, y);
            pacman.setType(BlockType.PACMAN);
            return pacman;
        }

        public static Block createRedGhost(int x, int y) {
            Ghost redGhost = new Ghost(redGhostImage, x, y, Ghost.color.RED);
            redGhost.setType(BlockType.GHOST);
            redGhost.setState(Ghost.states.CHASE);
            return redGhost;
        }

        public static Block createBlueGhost(int x, int y) {
            Ghost blueGhost = new Ghost(blueGhostImage, x, y, Ghost.color.BLUE);
            blueGhost.setType(BlockType.GHOST);
            blueGhost.setState(Ghost.states.STILL);
            return blueGhost;
        }

        public static Block createPinkGhost(int x, int y) {
            Ghost pinkGhost = new Ghost(pinkGhostImage, x, y, Ghost.color.PINK);
            pinkGhost.setType(BlockType.GHOST);
            pinkGhost.setState(Ghost.states.STILL);
            return pinkGhost;
        }

        public static Block createOrangeGhost(int x, int y) {
            Ghost orangeGhost = new Ghost(orangeGhostImage, x, y, Ghost.color.ORANGE);
            orangeGhost.setType(BlockType.GHOST);
            orangeGhost.setState(Ghost.states.STILL);
            return orangeGhost;
        }

        public static Block createTeleporterBlock(int x, int y) {
            Block teleporter = new Block(null, x, y);
            teleporter.setType(BlockType.TELEPORTER);
            return teleporter;
        }

}
