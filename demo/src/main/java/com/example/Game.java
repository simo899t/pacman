package com.example;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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

        // Create a Canvas
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        // Create an instance of Map and load the map onto the Canvas
        IMap map = new Map();
        map.loadmap(canvas);

        // Add the Canvas to the root group
        root.getChildren().add(canvas);

        stage.setScene(scene);
        stage.centerOnScreen(); // Center the stage on the screen
        stage.show();

        System.out.println("Game started now looping");
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
