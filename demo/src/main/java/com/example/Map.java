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
    private Block ghostHome;
    private final int tileSize = 32; // each tile is 32 pixels wide
    private int pelletCount = 0;
    private int pelletsLeft;
    private final GameTimer gameTimer;
    private final ArrayList<Block> allBlocks;

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

    private final int pelletValue = 10; // Points for each pellet
    private final int bigPelletValue = 50; // Points for each big pellet
    private final int ghostValue = 200; // Points for eating a ghost

    int rowCount = map.length; // Gameboard is 21 rows
    int columnCount = map[0].length(); // Gameboard is 19 columns
    long currentTime = System.currentTimeMillis();

    public Map(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
        allBlocks = new ArrayList<>();
        loadAllBlocks(map);
    }

    public String[] getMap() {
        return map;
    }


    public Pacman getPacman() {
        return pacman;
    }

    public Ghost getRedGhost() {
        return redGhost;
    }

    public Ghost getBlueGhost() {
        return blueGhost;
    }

    public Ghost getPinkGhost() {
        return pinkGhost;
    }

    public Ghost getOrangeGhost() {
        return orangeGhost;
    }

    public Block getGhostHome() {
        return ghostHome;
    }

    public int getTileSize() {
        return tileSize;
    }
    public int getCols() {
        return columnCount;
    }
    public int getRows() {
        return rowCount;
    }

    public int getPelletsLeft() {
        return pelletsLeft;
    }

    public void decreasePelletsLeft() {
        this.pelletsLeft = pelletsLeft - 1;
    }

    public void addPellet() {
        this.pelletCount = pelletCount + 1;
    }

    public void resetPelletsLeft() {
        this.pelletsLeft = pelletCount;
    }

    private void loadAllBlocks(String[] map) {
        String[] tileMap = map;

        // Draw the pellet and door map 
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
                    default:
                        break;
                }
            }
        }
        pelletsLeft = pelletCount; // Initialize pellets left after counting all pellets
        // Draw other part of map
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
                pellet.setEaten(false);
                
                // Set the correct image based on pellet type
                if (block.getType() == BlockType.PELLET) {
                    pellet.setImage(smallFoodImage);
                } else if (block.getType() == BlockType.BIGPELLET) {
                    pellet.setImage(bigFoodImage);
                }
            }
            
            resetAllGhosts();
            
            
            if (block instanceof Pacman) {
                Pacman pacman = (Pacman) block;
                pacman.setPos(pacman.getStartX(), pacman.getStartY());
            }
        }
        resetPelletsLeft();
    }

    public ArrayList<Block> getAllBlocks() {
        return allBlocks;
    }

    public Block getBlock(int x, int y) {
        for (Block block : allBlocks) {
            if (block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        return null;
    }

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
