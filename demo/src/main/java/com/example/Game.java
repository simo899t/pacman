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
import javafx.scene.input.KeyEvent;


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

        // Create an instance of Map and load the map onto the Canvas
        IMap map = new Map();
        map.loadmap(canvas);

        Pacman pacman = new Pacman();
        Controller controller = new Controller(pacman);
        

        // show the scene
        stage.setScene(scene);
        stage.centerOnScreen(); // Center the stage on the screen
        stage.show();

        


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPressed(event);
            }
        });

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update the game state
                //pacman.update();;
                //pacman.draw(canvas);
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
