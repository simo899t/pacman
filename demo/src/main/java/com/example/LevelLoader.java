package com.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class LevelLoader implements IMap { //please change the name of the class to mapLoader (idk how to on new mac)

    String[] tileMap;
    int rowCount;
    int columCount;
    int tileSize = 16; // each tile is 16 pixels wide
    GraphicsContext gc;
    
    @Override
    public void loadmap() {

        tileMap = new String[] {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "XBXX XXX X xXX XXBX",
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
        "XF X     P     X FX",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX"
        };

        rowCount = 21; //Gameboard is 21 rows
        columCount = 19; //Gameboard is 19 colums
        Canvas canvas = new Canvas(columCount * tileSize, rowCount * tileSize); // Create a Canvas
        gc = canvas.getGraphicsContext2D(); // Initialize the GraphicsContext from the Canvas

        Image pacmanImage = new Image(getClass().getResource("/com/example/pacman.png").toExternalForm());
        Image pacmanImageUp = new Image(getClass().getResource("/com/example/pacmanUp.png").toExternalForm());
        Image pacmanImageDown = new Image(getClass().getResource("/com/example/pacmanDown.png").toExternalForm());
        Image pacmanImageLeft = new Image(getClass().getResource("/com/example/pacmanLeft.png").toExternalForm());
        Image pacmanImageRight = new Image(getClass().getResource("/com/example/pacmanRight.png").toExternalForm());
        
        Image wallImage = new Image(getClass().getResource("/com/example/wall.png").toExternalForm());
        Image doorClosed = new Image(getClass().getResource("/com/example/doorClosed.png").toExternalForm());
        Image doorOpen = new Image(getClass().getResource("/com/example/doorOpen.png").toExternalForm());
        Image redGhostImage = new Image(getClass().getResource("/com/example/redGhost.png").toExternalForm());
        Image blueGhostImage = new Image(getClass().getResource("/com/example/blueGhost.png").toExternalForm());
        Image pinkGhostImage = new Image(getClass().getResource("/com/example/pinkGhost.png").toExternalForm());
        Image orangeGhostImage = new Image(getClass().getResource("/com/example/orangeGhost.png").toExternalForm());
        Image smallFoodImage = new Image(getClass().getResource("/com/example/smallFood.png").toExternalForm());
        Image bigFoodImage = new Image(getClass().getResource("/com/example/bigFood.png").toExternalForm());


        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columCount; col++) {
                char tile = tileMap[row].charAt(col);
                // Draw the tile based on its type
                switch (tile) {
                    case 'X':
                        gc.drawImage(wallImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'P':
                        gc.drawImage(pacmanImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        
                        break;
                    case 'r':
                        gc.drawImage(redGhostImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'b':
                        gc.drawImage(blueGhostImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'p':
                        gc.drawImage(pinkGhostImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'o':
                        gc.drawImage(orangeGhostImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case ' ':
                        gc.drawImage(smallFoodImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'B':
                        gc.drawImage(bigFoodImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'D':
                        gc.drawImage(doorClosed, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}   
