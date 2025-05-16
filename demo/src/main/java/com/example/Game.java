package com.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

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

        // Create layout that combines score panel and game canvas
        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        // Create score panel
        HBox scorePanel = new HBox();
        scorePanel.setPrefHeight(30); // Set height for score panel
        scorePanel.setMinHeight(30);
        scorePanel.setAlignment(Pos.CENTER_LEFT);
        scorePanel.setPadding(new Insets(5, 10, 5, 10));
        scorePanel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        // Create score label
        Label scoreLabel = new Label("SCORE: 0");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scorePanel.getChildren().add(scoreLabel);

        // Add some spacing between labels
        scorePanel.setSpacing(30);

        // Create lives label
        Label livesLabel = new Label("LIVES: 3");
        livesLabel.setTextFill(Color.WHITE);
        livesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scorePanel.getChildren().add(livesLabel);

        // Create game canvas container
        Group canvasContainer = new Group();
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        canvasContainer.getChildren().add(canvas);

        // Add both components to main layout
        root.getChildren().addAll(scorePanel, canvasContainer);

        // Create scene with the root layout
        Scene scene = new Scene(root, canvasWidth, Math.max(canvasHeight + 30, 30));

        // Show the scene
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        IController controller = new Controller(map.getPacman(), map.getRedGhost());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPressed1(event);
                controller.keyPressed2(event);
            }
        });
        GameLives gameLives = new GameLives();
        GameScore gameScore = new GameScore();
        IDraw draw = new Draw(map, canvas);
        IUpdate update = new Update(map, gameScore, gameLives);
        IUpdateImages updateImages = new UpdateImages(map);
        

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw.drawAllBlocks();
                update.updateGame(map);
                updateImages.updateAllImages();
                
                // Update both displays separately
                scoreLabel.setText("SCORE: " + gameScore.getScore());
                livesLabel.setText("LIVES: " + gameLives.getLives());
            }
        };

        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
