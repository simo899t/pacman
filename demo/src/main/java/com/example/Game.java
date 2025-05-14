package com.example;

import java.util.concurrent.atomic.AtomicInteger;
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

        // Load the map
        IMap map = new Map();

        // Define the dimensions of the game with current map
        int tileSize = map.getTileSize();
        int rowCount = map.getRows();
        int columCount = map.getCols();
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
     
        IController controller = new Controller(map.getPacman(), map.getRedGhost());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPressed1(event);
                controller.keyPressed2(event);
            }
        });
        
        
        final AtomicInteger animationTimer = new AtomicInteger();

        IDraw draw = new Draw(map, canvas);
        System.out.println("Map initialized: " + (map != null));
        IUpdate update = new Update(map);
        IUpdateImages updateImages = new UpdateImages(map);
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw.drawAllBlocks();
                update.updateGame(map);
                animationTimer.incrementAndGet(); // Increment the AtomicInteger
                if (animationTimer.get() == 10) {
                    animationTimer.set(0);
                    updateImages.animateAllBlocks();
                }
                updateImages.updateAllImages();
            }
        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
}
