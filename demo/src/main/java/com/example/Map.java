package com.example;

import java.util.ArrayList;
import com.example.Block.BlockType;
import javafx.scene.image.Image;

public class Map implements IMap {
    private Pacman pacman;
    private Ghost redGhost;
    private Ghost blueGhost;
    private Ghost pinkGhost;
    private Ghost orangeGhost;
    private Block ghostHome; // Home for the ghosts (where they respawn)
    private final int tileSize = 32; // each tile is 32 pixels wide
    private int pelletCount = 0; // start number of pellets
    private int pelletsLeft;     // number of pellets left to eat
    private final GameTimer gameTimer;
    private final ArrayList<Block> allBlocks;

        // for map creation, note the following
        // 'X' = Wall
        // ' ' = Pellet
        // 'D' = Door
        // 'B' = Big Pellet
        // 'P' = Pacman
        // 'r' = Red Ghost
        // 'b' = Blue Ghost
        // 'p' = Pink Ghost
        // 'o' = Orange Ghost
        // '.' = Empty Block
        // 'O' = Teleporter Block

    // Map 1
    String[] map = new String[] {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "XBXX XXX X XXX XXBX",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X   X   X    X",
        "XXXX XXX X XXX XXXX",
        "...X X   r   X X...",
        "XXXX X XXDXX X XXXX",
        "O      XbpoX      O",
        "XXXX X XXXXX X XXXX",
        "...X X       X X...",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "XB X     P     X BX",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX"
    };
    
    
    // map 2
    // String[] map = new String[] {
    //     "..................XXXXXXXXXXXXXXXXXXX",
    //     "..................X                 X",
    //     "..................X XXXXXXXOXXXXXXX X",
    //     "..................X                 X",
    //     "XXXXXXXXXXXXXXXXXXXXX XXXXXXXXXXX XXX",
    //     "X        X                          X",
    //     "XPXXXXXX X XXOXXXBXBXXXXXX X XXXXXXrX",
    //     "X                          X        X",
    //     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    // };


    // map 3
    // String[] map = new String[] {
    //     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
    //     "X        X        P     X       X   X",
    //     "X XXXXXX X XXXXXX X XXX X XXXXX X X X",
    //     "X                 X                 X",
    //     "X XX X XXXXX X XX X XX X XXXXX X XX X",
    //     "X    X   X   X         X   X   X    X",
    //     "XXXX XXX X XXX XXXDXXX XXX X XXX XXXX",
    //     "...X X       X Xp...rX         X X...",
    //     "XXXX X XXDXX X X.....X X XXDXX X XXXX",
    //     "O            X Xb...oX X            O",
    //     "XXXX X XXXXX X XXXXXXX X XXXXX X XXXX",
    //     "...X X       X         X       X X...",
    //     "XXXX X XXXXX X XXXXXXX X XXXXX X XXXX",
    //     "X        X        X        X        X",
    //     "X XX XXX X XXX XX X XX XXX X XXX XX X",
    //     "X    XXX   XXX         XXX   XXX    X",
    //     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
    // };

    private final int pelletValue = 10; // Points for each pellet
    private final int bigPelletValue = 50; // Points for each big pellet
    private final int ghostValue = 200; // Points for eating a ghost

    int rowCount = map.length; // Gameboard is 21 rows
    int columnCount = map[0].length(); // Gameboard is 19 columns

    /**
     * Constructor for the Map class.
     * Initializes the map, game elements, and loads all blocks.
     * @param gameTimer The GameTimer instance to manage game timing.
     */
    public Map(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
        allBlocks = new ArrayList<>();
        loadAllBlocks(map);
    }

    /**
     * Returns the map as a String array.
     * Each String in the array represents a row in the map.
     * @return The map as a String array.
     */
    public String[] getMap() {
        return map;
    }

    // Getters for the game elements
    @Override
    public Pacman getPacman() {
        return pacman;
    }
    @Override
    public Ghost getRedGhost() {
        return redGhost;
    }
    @Override
    public Ghost getBlueGhost() {
        return blueGhost;
    }
    @Override
    public Ghost getPinkGhost() {
        return pinkGhost;
    }
    @Override
    public Ghost getOrangeGhost() {
        return orangeGhost;
    }
    @Override
    public Block getGhostHome() {
        return ghostHome;
    }
    @Override
    public int getTileSize() {
        return tileSize;
    }
    @Override
    public int getCols() {
        return columnCount;
    }
    @Override
    public int getRows() {
        return rowCount;
    }

    // methods for pellet management
    @Override
    public int getPelletsLeft() {
        return pelletsLeft;
    }
    @Override
    public void decreasePelletsLeft() {
        this.pelletsLeft = pelletsLeft - 1;
    }
    private void addPellet() {
        this.pelletCount = pelletCount + 1;
    }
    private void resetPelletsLeft() {
        this.pelletsLeft = pelletCount;
    }

    /**
     * Loads all blocks from the map into the allBlocks list.
     * @param map
     */
    private void loadAllBlocks(String[] map) {
        String[] tileMap = map;

        // Count pellets first so they are drawn before the Pacman and ghosts
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                char tile = tileMap[row].charAt(col);
                switch (tile) {
                    case ' ':
                        allBlocks.add(BlockFactory.createPellet(col * tileSize, row * tileSize, pelletValue));
                        addPellet();
                        break;
                    case 'D':
                        allBlocks.add(BlockFactory.createDoor(col * tileSize, row * tileSize));
                        break;
                    case 'B':
                        allBlocks.add(BlockFactory.createBigPellet(col * tileSize, row * tileSize, bigPelletValue));
                        addPellet();
                        break;
                    case '.':
                        allBlocks.add(BlockFactory.createEmptyBlock(col * tileSize, row * tileSize));
                        break;
                    default:
                        break;
                }
            }
        }
        pelletsLeft = pelletCount; // Initialize pellets left after counting all pellets
        // load the rest of the blocks
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                char tile = tileMap[row].charAt(col);
                switch (tile) {
                    case 'X':
                        allBlocks.add(BlockFactory.createWall(col * tileSize, row * tileSize));
                        break;
                    case 'P':
                        pacman = BlockFactory.createPacman(col * tileSize, row * tileSize);
                        allBlocks.add(pacman);
                        allBlocks.add(BlockFactory.createEmptyBlock(col * tileSize, row * tileSize));

                        break;
                    case 'r':
                        redGhost = BlockFactory.createRedGhost(col * tileSize, row * tileSize, ghostValue);
                        allBlocks.add(redGhost);
                        ghostHome = BlockFactory.createGhostHome(col * tileSize, row * tileSize);
                        allBlocks.add(ghostHome);
                        allBlocks.add(BlockFactory.createEmptyBlock(col * tileSize, row * tileSize));
                        break;
                    case 'b':
                        blueGhost = BlockFactory.createBlueGhost(col * tileSize, row * tileSize, ghostValue);
                        allBlocks.add(blueGhost);
                        allBlocks.add(BlockFactory.createEmptyBlock(col * tileSize, row * tileSize));
                        break;
                    case 'p':
                        pinkGhost = BlockFactory.createPinkGhost(col * tileSize, row * tileSize, ghostValue);
                        allBlocks.add(pinkGhost);
                        allBlocks.add(BlockFactory.createEmptyBlock(col * tileSize, row * tileSize));
                        break;
                    case 'o':
                        orangeGhost = BlockFactory.createOrangeGhost(col * tileSize, row * tileSize, ghostValue);
                        allBlocks.add(orangeGhost);
                        allBlocks.add(BlockFactory.createEmptyBlock(col * tileSize, row * tileSize));
                        break;
                    case 'O':
                        allBlocks.add(BlockFactory.createTeleporterBlock(col * tileSize, row * tileSize));
                    default:
                        break;
                }
            }

        }
    }

    public void resetMap() {
        // Reload the pellet images
        Image smallFoodImage = new Image(getClass().getResource("/com/example/images/smallFood.png").toExternalForm());
        Image bigFoodImage = new Image(getClass().getResource("/com/example/images/bigFood.png").toExternalForm());

        for (Block block : allBlocks) {
            if (block instanceof Pellet) {
                Pellet pellet = (Pellet) block;

                // Reset pellet eaten state
                pellet.setEaten(false);

                // Set the correct image based on pellet type
                if (block.getType() == BlockType.PELLET) {
                    pellet.setImage(smallFoodImage);
                } else if (block.getType() == BlockType.BIGPELLET) {
                    pellet.setImage(bigFoodImage);
                }
            }

            // Reset all blocks to their initial positions (StartX, StartY)
            resetAllGhosts();

            // Reset the Pacman position
            if (block instanceof Pacman) {
                Pacman pacman = (Pacman) block;
                pacman.setPos(pacman.getStartX(), pacman.getStartY());
            }
        }
        // set pellets left to the initial pellet count (PelletCount)
        resetPelletsLeft();
    }

    /**
     * Returns a list of all blocks in the map.
     * @return An ArrayList containing all blocks in the map.
     */
    public ArrayList<Block> getAllBlocks() {
        return allBlocks;
    }

    /**
     * Returns a block at the specified coordinates.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @return The block at the specified coordinates, or null if no block exists there.
     */
    public Block getBlock(int x, int y) {
        for (Block block : allBlocks) {
            if (block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        return null;
    }

    /**
     * Resets all ghosts to their starting positions and states.
     * This method is called when the player loses a life or when the game is reset.
     */
    public void resetAllGhosts() {
        for (Block block : allBlocks) {
            if (block.getType() == BlockType.GHOST) {
                Ghost ghost = (Ghost) block;
                ghost.setPos(ghost.getStartX(), ghost.getStartY());
                switch (ghost.getColor()) {
                    case RED:
                        ghost.setState(Ghost.states.CHASE);
                        break;
                    case BLUE:
                        ghost.setState(Ghost.states.STILL);
                        blueGhost.resetState(12000L, gameTimer);
                        break;
                    case PINK:
                    ghost.setState(Ghost.states.STILL);
                        pinkGhost.resetState(8000L, gameTimer);
                        break;
                    case ORANGE:
                        ghost.setState(Ghost.states.STILL);
                        orangeGhost.resetState(15000L, gameTimer);
                        break;
                    default:
                }
            }
        }
    }
}
