package com.example;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Map implements IMap {

    String[] map = new String[] {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "XBXX XXX X XXX XXBX",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X   X   X    X",
        "XXXX XXX X XXX XXXX",
        "OOOX X   r   X XOOO",
        "XXXX X XXDXX X XXXX",
        "O      XbpoX      O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
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
    int columCount = map[0].length(); // Gameboard is 19 columns

    public Map() {
        loadAllBlocks(map);
    }

    public String[] getMap() {
        return map;
    }

    private Pacman pacman;
    public Pacman getPacman() {
        return pacman;
    }

    private int tileSize = 32; // each tile is 16 pixels wide
    public int getTileSize() {
        return tileSize;
    }
    public int getCols() {
        return columCount;
    }
    public int getRows() {
        return rowCount;
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

        allBlocks = new ArrayList<>(new ArrayList<>());
        //moveableBlocks = new ArrayList<>();

        // Draw the map
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columCount; col++) {
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
                        //moveableBlocks.add(pacman);
                        break;
                    case 'r':
                        Ghost redGhost = new Ghost(redGhostImage, col * tileSize, row * tileSize);
                        redGhost.setType(BlockType.GHOST);
                        allBlocks.add(redGhost);
                        //moveableBlocks.add(redGhost);
                        break;
                    case 'b':
                        Ghost blueGhost = new Ghost(blueGhostImage, col * tileSize, row * tileSize);
                        blueGhost.setType(BlockType.GHOST);
                        allBlocks.add(blueGhost);
                        //moveableBlocks.add(blueGhost);
                        break;
                    case 'p':
                        Ghost pinkGhost = new Ghost(pinkGhostImage, col * tileSize, row * tileSize);
                        pinkGhost.setType(BlockType.GHOST);
                        allBlocks.add(pinkGhost);
                        //moveableBlocks.add(pinkGhost);
                        break;
                    case 'o':
                        Ghost orangeGhost = new Ghost(orangeGhostImage, col * tileSize, row * tileSize);
                        orangeGhost.setType(BlockType.GHOST);
                        allBlocks.add(orangeGhost);
                        //moveableBlocks.add(orangeGhost);
                        break;
                    case ' ':
                        Block pellet = new Block(smallFoodImage, col * tileSize, row * tileSize);
                        pellet.setType(BlockType.PELLET);
                        allBlocks.add(pellet);
                        break;
                    case 'B':
                        Block bigPellet = new Block(bigFoodImage, col * tileSize, row * tileSize);
                        bigPellet.setType(BlockType.BIGPELLET);
                        allBlocks.add(bigPellet);
                        break;
                    case 'D':
                        Block door = new Block(doorClosed, col * tileSize, row * tileSize);
                        door.setType(BlockType.DOOR);
                        allBlocks.add(door);
                        break;
                    default:
                        break;
                }
            }

        }
    }

    private ArrayList<Block> allBlocks;
    public ArrayList<Block> getAllBlocks() {
        return allBlocks;
    }

    private ArrayList<MoveableBlock> moveableBlocks;
    public ArrayList<MoveableBlock> getMoveableBlocks() {
        return moveableBlocks;
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
