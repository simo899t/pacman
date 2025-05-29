package com.example;

import com.example.Block.BlockType;
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

        public static Block createPellet(int x, int y, int points) {
            Pellet pellet = new Pellet(smallFoodImage, x, y);
            pellet.setType(BlockType.PELLET);
            pellet.setEaten(false);
            pellet.setPoints(points);
            return pellet;
        }

        public static GhostHome createGhostHome(int x, int y) {
            GhostHome ghostHome = new GhostHome(x, y);
            ghostHome.setType(BlockType.GHOSTHOME);
            return ghostHome;
        }

        public static Door createDoor(int x, int y) {
            Door door = new Door(doorClosed, x, y);
            door.setType(BlockType.DOOR);
            return door;
        }

        public static Pellet createBigPellet(int x, int y, int points) {
            Pellet bigPellet = new Pellet(bigFoodImage, x, y);
            bigPellet.setType(BlockType.BIGPELLET);
            bigPellet.setEaten(false);
            bigPellet.setPoints(points);
            return bigPellet;
        }

        public static EmptyBlock createEmptyBlock(int x, int y) {
            EmptyBlock eatenPellet = new EmptyBlock(x, y);
            eatenPellet.setType(BlockType.EMPTY);
            return eatenPellet;
        }

        static Wall createWall(int x, int y) {
            Wall wall = new Wall(wallImage, x, y);
            wall.setType(BlockType.WALL);
            return wall;
        }

        public static Pacman createPacman(int x, int y) {
            Pacman pacman = new Pacman(pacmanImage, x, y);
            pacman.setType(BlockType.PACMAN);
            return pacman;
        }

        public static Ghost createRedGhost(int x, int y, int points) {
            Ghost redGhost = new Ghost(redGhostImage, x, y, Ghost.color.RED);
            redGhost.setType(BlockType.GHOST);
            redGhost.setState(Ghost.states.CHASE);
            redGhost.setPoints(points);
            return redGhost;
        }

        public static Ghost createBlueGhost(int x, int y, int points) {
            Ghost blueGhost = new Ghost(blueGhostImage, x, y, Ghost.color.BLUE);
            blueGhost.setType(BlockType.GHOST);
            blueGhost.setState(Ghost.states.STILL);
            blueGhost.setPoints(points);
            return blueGhost;
        }

        public static Ghost createPinkGhost(int x, int y, int points) {
            Ghost pinkGhost = new Ghost(pinkGhostImage, x, y, Ghost.color.PINK);
            pinkGhost.setType(BlockType.GHOST);
            pinkGhost.setState(Ghost.states.STILL);
            pinkGhost.setPoints(points);
            return pinkGhost;
        }

        public static Ghost createOrangeGhost(int x, int y, int points) {
            Ghost orangeGhost = new Ghost(orangeGhostImage, x, y, Ghost.color.ORANGE);
            orangeGhost.setType(BlockType.GHOST);
            orangeGhost.setState(Ghost.states.STILL);
            orangeGhost.setPoints(points);
            return orangeGhost;
        }

        public static Teleporter createTeleporterBlock(int x, int y) {
            Teleporter teleporter = new Teleporter(x, y);
            teleporter.setType(BlockType.TELEPORTER);
            return teleporter;
        }

}
