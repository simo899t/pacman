package com.example;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Map implements IMap {
    private Pacman pacman;
    private Ghost redGhost;
    private Ghost blueGhost;
    private Ghost pinkGhost;
    private Ghost orangeGhost;
    private Block ghostHome;
    private int tileSize = 32; // each tile is 32 pixels wide
    private int pelletCount = 0;
    private int pelletsLeft;

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

    int rowCount = map.length; // Gameboard is 21 rows
    int columnCount = map[0].length(); // Gameboard is 19 columns
    long currentTime = System.currentTimeMillis();

    private final GameTimer gameTimer;

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
        
        // Load images
        Image pacmanImage = new Image(getClass().getResource("/com/example/images/pacman.png").toExternalForm());
        Image wallImage = new Image(getClass().getResource("/com/example/images/wall.png").toExternalForm());
        Image doorClosed = new Image(getClass().getResource("/com/example/images/doorClosed.png").toExternalForm());
        Image redGhostImage = new Image(getClass().getResource("/com/example/images/redGhostRight.png").toExternalForm());
        Image blueGhostImage = new Image(getClass().getResource("/com/example/images/blueGhostRight.png").toExternalForm());
        Image pinkGhostImage = new Image(getClass().getResource("/com/example/images/pinkGhostRight.png").toExternalForm());
        Image orangeGhostImage = new Image(getClass().getResource("/com/example/images/orangeGhostRight.png").toExternalForm());
        Image smallFoodImage = new Image(getClass().getResource("/com/example/images/smallFood.png").toExternalForm());
        Image bigFoodImage = new Image(getClass().getResource("/com/example/images/bigFood.png").toExternalForm());


        // Draw the pellet and door map 
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                char tile = tileMap[row].charAt(col);
                switch (tile) {
                    case ' ':
                        Pellet pellet = new Pellet(smallFoodImage, col * tileSize, row * tileSize);
                        pellet.setType(BlockType.PELLET);
                        allBlocks.add(pellet);
                        addPellet();
                        break;
                    case 'D':
                        Door door = new Door(doorClosed, col * tileSize, row * tileSize);
                        door.setType(BlockType.DOOR);
                        allBlocks.add(door);
                        break;
                    case 'B':
                        Pellet bigPellet = new Pellet(bigFoodImage, col * tileSize, row * tileSize);
                        bigPellet.setType(BlockType.BIGPELLET);
                        allBlocks.add(bigPellet);
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
                        Block wall = new Block(wallImage, col * tileSize, row * tileSize);
                        wall.setType(BlockType.WALL);
                        allBlocks.add(wall);
                        break;
                    case 'P':
                        pacman = new Pacman(pacmanImage, col * tileSize, row * tileSize);
                        pacman.setType(BlockType.PACMAN);
                        allBlocks.add(pacman);
                        Pellet eatenPellet = new Pellet(null, col * tileSize, row * tileSize);
                        eatenPellet.setType(BlockType.PELLET);
                        eatenPellet.setEaten(true);
                        allBlocks.add(eatenPellet);
                        break;
                    case 'r':
                        redGhost = new Ghost(redGhostImage, col * tileSize, row * tileSize, Ghost.color.RED);
                        redGhost.setType(BlockType.GHOST);
                        redGhost.setState(Ghost.states.CHASE);
                        allBlocks.add(redGhost);
                        ghostHome = new Block(null, col * tileSize, row * tileSize);
                        ghostHome.setType(BlockType.GHOSTHOME);
                        allBlocks.add(ghostHome);
                        
                        Pellet eatenPelletBehindRed = new Pellet(null, col * tileSize, row * tileSize);
                        eatenPelletBehindRed.setType(BlockType.PELLET);
                        eatenPelletBehindRed.setEaten(true);
                        allBlocks.add(eatenPelletBehindRed);
                        break;
                    case 'b':
                        blueGhost = new Ghost(blueGhostImage, col * tileSize, row * tileSize, Ghost.color.BLUE);
                        blueGhost.setType(BlockType.GHOST);
                        blueGhost.setState(Ghost.states.STILL);
                        allBlocks.add(blueGhost);
                        
                        gameTimer.addFunctionToList(
                            GameTimer.atTimeRunFunction(
                                "blueGhostChaseTimer", currentTime, 12000L, () -> {
                                    blueGhost.setState(Ghost.states.CHASE);
                                }
                            )
                        );
                        
                        Pellet eatenPelletBehindBlue = new Pellet(null, col * tileSize, row * tileSize);
                        eatenPelletBehindBlue.setType(BlockType.PELLET);
                        eatenPelletBehindBlue.setEaten(true);
                        allBlocks.add(eatenPelletBehindBlue);
                        break;
                    case 'p':
                        pinkGhost = new Ghost(pinkGhostImage, col * tileSize, row * tileSize, Ghost.color.PINK);
                        pinkGhost.setType(BlockType.GHOST);
                        pinkGhost.setState(Ghost.states.STILL);
                        allBlocks.add(pinkGhost);

                        gameTimer.addFunctionToList(
                            GameTimer.atTimeRunFunction(
                                "pinkGhostChaseTimer", currentTime, 8000L, () -> {
                                    pinkGhost.setState(Ghost.states.CHASE);
                                }
                            )
                        );
                        
                        Pellet eatenPelletBehindPink = new Pellet(null, col * tileSize, row * tileSize);
                        eatenPelletBehindPink.setType(BlockType.PELLET);
                        eatenPelletBehindPink.setEaten(true);
                        allBlocks.add(eatenPelletBehindPink);
                        break;
                    case 'o':
                        orangeGhost = new Ghost(orangeGhostImage, col * tileSize, row * tileSize, Ghost.color.ORANGE);
                        orangeGhost.setType(BlockType.GHOST);
                        orangeGhost.setState(Ghost.states.STILL);
                        allBlocks.add(orangeGhost);

                        gameTimer.addFunctionToList(
                            GameTimer.atTimeRunFunction(
                                "OrangeGhostChaseTimer", currentTime, 15000L, () -> {
                                    orangeGhost.setState(Ghost.states.CHASE);
                                }
                            )
                        );
                        
                        Pellet eatenPelletBehindOrange = new Pellet(null, col * tileSize, row * tileSize);
                        eatenPelletBehindOrange.setType(BlockType.PELLET);
                        eatenPelletBehindOrange.setEaten(true);
                        allBlocks.add(eatenPelletBehindOrange);
                        break;
                    case 'O':
                        Block teleporter = new Block(null, col * tileSize, row * tileSize);
                        teleporter.setType(BlockType.TELEPORTER);
                        allBlocks.add(teleporter);
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
            
            if (block instanceof Ghost) {
                Ghost ghost = (Ghost) block;
                switch (ghost.getColor()) {
                    case Ghost.color.RED:
                        ghost.setState(Ghost.states.CHASE);
                        break;
                    case Ghost.color.BLUE:
                        ghost.setState(Ghost.states.STILL);
                        gameTimer.addFunctionToList(
                            GameTimer.atTimeRunFunction(
                                "blueGhostChaseTimer", currentTime, 12000L, () -> {
                                    blueGhost.setState(Ghost.states.CHASE);
                                }
                            )
                        );
                        break;
                    case Ghost.color.PINK:
                        ghost.setState(Ghost.states.STILL);
                        gameTimer.addFunctionToList(
                            GameTimer.atTimeRunFunction(
                                "pinkGhostChaseTimer", currentTime, 8000L, () -> {
                                    pinkGhost.setState(Ghost.states.CHASE);
                                }
                            )
                        );
                        break;
                    case Ghost.color.ORANGE:
                        ghost.setState(Ghost.states.STILL);
                        gameTimer.addFunctionToList(
                            GameTimer.atTimeRunFunction(
                                "OrangeGhostChaseTimer", currentTime, 15000L, () -> {
                                    orangeGhost.setState(Ghost.states.CHASE);
                                }
                            )
                        );
                        break;
                    default:
                        ghost.setState(Ghost.states.STILL);
                        break;
                }
                
                ghost.setPos(ghost.getStartX(), ghost.getStartY());
            }
            
            if (block instanceof Pacman) {
                Pacman pacman = (Pacman) block;
                pacman.setPos(pacman.getStartX(), pacman.getStartY());
            }
        }
        resetPelletsLeft();
    }

    private ArrayList<Block> allBlocks;

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
}
