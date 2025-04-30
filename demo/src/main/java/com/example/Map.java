package com.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Map implements IMap {

    String[] tileMap;
    int rowCount;
    int columCount;
    int tileSize = 32; // each tile is 16 pixels wide
    GraphicsContext gc;
    
    @Override
    public void loadmap(Canvas canvas) {
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
            "XB X     P     X BX",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
        };

        rowCount = 21; // Gameboard is 21 rows
        columCount = 19; // Gameboard is 19 columns
        gc = canvas.getGraphicsContext2D(); // Initialize the GraphicsContext from the Canvas

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

        // Draw the map
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columCount; col++) {
                char tile = tileMap[row].charAt(col);
                switch (tile) {
                    case 'X':
                        //System.out.println("Drawing wall at: " + col + ", " + row);
                        // Draw wall
                        gc.drawImage(wallImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'P':
                        //System.out.println("Drawing Pacman at: " + col + ", " + row);
                        // Draw Pacman
                        gc.drawImage(pacmanImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        
                        break;
                    case 'r':
                        //System.out.println("Drawing red ghost at: " + col + ", " + row);
                        // Draw red ghost
                        gc.drawImage(redGhostImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'b':
                        //System.out.println("Drawing blue ghost at: " + col + ", " + row);
                        // Draw blue ghost
                        gc.drawImage(blueGhostImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'p':
                        //System.out.println("Drawing pink ghost at: " + col + ", " + row);
                        // Draw pink ghost
                        gc.drawImage(pinkGhostImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'o':
                        //System.out.println("Drawing orange ghost at: " + col + ", " + row);
                        // Draw orange ghost
                        gc.drawImage(orangeGhostImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case ' ':
                        //System.out.println("Drawing empty space at: " + col + ", " + row);
                        // Draw empty space
                        gc.drawImage(smallFoodImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'B':
                        //System.out.println("Drawing big food at: " + col + ", " + row);
                        // Draw big food
                        gc.drawImage(bigFoodImage, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    case 'D':
                        //System.out.println("Drawing door at: " + col + ", " + row);
                        // Draw door
                        gc.drawImage(doorClosed, col * tileSize, row * tileSize, tileSize, tileSize);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
