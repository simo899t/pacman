package com.example;

import com.example.Block.BlockType;
import javafx.scene.image.Image;

public class BlockFactory {


    // Static initial images for different blocks
    private static Image pacmanImage = new Image(BlockFactory.class.getResource("/com/example/images/pacman.png").toExternalForm());
    private static Image wallImage = new Image(BlockFactory.class.getResource("/com/example/images/wall.png").toExternalForm());
    private static Image doorClosed = new Image(BlockFactory.class.getResource("/com/example/images/doorClosed.png").toExternalForm());
    private static Image redGhostImage = new Image(BlockFactory.class.getResource("/com/example/images/redGhostRight.png").toExternalForm());
    private static Image blueGhostImage = new Image(BlockFactory.class.getResource("/com/example/images/blueGhostRight.png").toExternalForm());
    private static Image pinkGhostImage = new Image(BlockFactory.class.getResource("/com/example/images/pinkGhostRight.png").toExternalForm());
    private static Image orangeGhostImage = new Image(BlockFactory.class.getResource("/com/example/images/orangeGhostRight.png").toExternalForm());
    private static Image smallFoodImage = new Image(BlockFactory.class.getResource("/com/example/images/smallFood.png").toExternalForm());
    private static Image bigFoodImage = new Image(BlockFactory.class.getResource("/com/example/images/bigFood.png").toExternalForm());

        /**
         * Factory method for crating a Pellet block.
         * @param x
         * @param y
         * @param points
         * @return Pellet
         */
        public static Pellet createPellet(int x, int y, int points) {
            Pellet pellet = new Pellet(smallFoodImage, x, y);
            pellet.setType(BlockType.PELLET);
            pellet.setEaten(false);
            pellet.setPoints(points);
            return pellet;
        }

        /**
         * Factory method for creating a GhostHome block.
         * @param x
         * @param y
         * @return GhostHome
         */
        public static GhostHome createGhostHome(int x, int y) {
            GhostHome ghostHome = new GhostHome(x, y);
            ghostHome.setType(BlockType.GHOSTHOME);
            return ghostHome;
        }

        /**
         * Factory method for creating a Door block.
         * @param x
         * @param y
         * @return Door
         */
        public static Door createDoor(int x, int y) {
            Door door = new Door(doorClosed, x, y);
            door.setType(BlockType.DOOR);
            return door;
        }

        /**
         * Factory method for creating a Big Pellet block.
         * @param x
         * @param y
         * @param points
         * @return BigPellet
         */
        public static BigPellet createBigPellet(int x, int y, int points) {
            BigPellet bigPellet = new BigPellet(bigFoodImage, x, y);
            bigPellet.setType(BlockType.BIGPELLET);
            bigPellet.setEaten(false);
            bigPellet.setPoints(points);
            return bigPellet;
        }

        /**
         * Factory method for creating a EmptyBlock.
         * @param x
         * @param y
         * @return EmptyBlock
         */
        public static EmptyBlock createEmptyBlock(int x, int y) {
            EmptyBlock eatenPellet = new EmptyBlock(x, y);
            eatenPellet.setType(BlockType.EMPTY);
            return eatenPellet;
        }

        /**
         * Factory method for creating a Wall block
         * @param x
         * @param y
         * @return Wall
         */
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
