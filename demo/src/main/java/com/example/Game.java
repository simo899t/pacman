package com.example;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Game extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pacman Game");
        stage.setResizable(false);

        // Define the dimensions of the game
        int tileSize = 32;
        int rowCount = 21;
        int columCount = 19;
        int canvasWidth = columCount * tileSize;
        int canvasHeight = rowCount * tileSize;

        Group root = new Group();
        Scene scene = new Scene(root, canvasWidth, canvasHeight, Color.BLACK);
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);

        // show the scene
        stage.setScene(scene);
        stage.centerOnScreen(); // Center the stage on the screen
        stage.show();

        
        IMap map = new Map();
     
        IController controller = new Controller(map.getPacman());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPressed(event);
            }
        });
       
        IDraw draw = new Draw(map, canvas);
        IUpdateGamePositions update = new UpdateGamePositions(map);
        
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw.drawAllBlocks();
                update.updateGamePositions();
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
