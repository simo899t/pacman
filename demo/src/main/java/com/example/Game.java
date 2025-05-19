package com.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
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

    private GameLives gameLives;
    private GameScore gameScore;
    private IMap map;
    private IController controller;
    private IDraw draw;
    private IUpdate update;
    private IUpdateImages updateImages;
    private AnimationTimer gameLoop;
    private Label scoreLabel;
    private Label livesLabel;
    private Label gameOverText;
    private Label restartText;
    private Label winText;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pacman Game");
        stage.setResizable(false);

        // Load the map
        map = new Map();

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
        scoreLabel = new Label("SCORE: 0");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scorePanel.getChildren().add(scoreLabel);

        // Add some spacing between labels
        scorePanel.setSpacing(30);

        // Create lives label
        livesLabel = new Label("LIVES: 3");
        livesLabel.setTextFill(Color.WHITE);
        livesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scorePanel.getChildren().add(livesLabel);

        // Create game canvas container
        Group canvasContainer = new Group();
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        canvasContainer.getChildren().add(canvas);

        // Create a game over text (initially hidden)
        gameOverText = new Label("GAME OVER");
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        gameOverText.setTextFill(Color.YELLOW);
        gameOverText.setVisible(false);

        // Center the gameover text in the canvas
        gameOverText.setLayoutX((canvasWidth - 260) / 2); // Adjusted width calculation
        gameOverText.setLayoutY((canvasHeight / 2) - 50); // Position above center

        // Create a restart text (also initially hidden)
        restartText = new Label("Press ENTER to restart");
        restartText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        restartText.setTextFill(Color.YELLOW);
        restartText.setVisible(false);

        // Position the restart text below the game over text
        restartText.setLayoutX((canvasWidth - 200) / 2); // Adjusted width calculation
        restartText.setLayoutY((canvasHeight / 2) + 10); // Position below center
        // Create a win text (initially hidden)
        winText = new Label("YOU WIN!");
        winText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        winText.setTextFill(Color.YELLOW);
        winText.setVisible(false);

        // Center the win text in the canvas
        winText.setLayoutX((canvasWidth - 220) / 2); // Adjusted width calculation for win text
        winText.setLayoutY((canvasHeight / 2) - 50); // Position above center

        canvasContainer.getChildren().addAll(gameOverText, restartText, winText);

        // Add both components to main layout
        root.getChildren().addAll(scorePanel, canvasContainer);

        // Create scene with the root layout
        scene = new Scene(root, canvasWidth, Math.max(canvasHeight + 30, 30));

        // Show the scene
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        controller = new Controller(map.getPacman(), map.getRedGhost());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPressed1(event);
                controller.keyPressed2(event);
            }
        });
        gameLives = new GameLives();
        gameScore = new GameScore();
        draw = new Draw(map, canvas);
        update = new Update(map, gameScore, gameLives);
        updateImages = new UpdateImages(map);

        // The game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw.drawAllBlocks();
                update.updateGame(map);
                updateImages.updateAllImages();

                // Update both displays separately
                scoreLabel.setText("SCORE: " + gameScore.getScore());
                livesLabel.setText("LIVES: " + gameLives.getLives());

                if (gameLives.getLives() <= 0) {
                    // Show game over text
                    gameOver();
                }

                if (map.getPelletCount() == 0) {
                    // Show game over text
                    gameWin();
                }
            }
        };

        gameLoop.start();
    }

    private void resetGame() {
        // Reset game state
        gameLives.resetLives();
        gameScore.resetScore();
        map.resetMap();
        
        // Update controller to use the new Pacman instance
        controller = new Controller(map.getPacman(), map.getRedGhost());
        
        // Hide game over text
        gameOverText.setVisible(false);
        restartText.setVisible(false);
        
        // Start a new game loop
        gameLoop.start();
        
        // Restore original controls with the updated controller
        scene.setOnKeyPressed(event -> {
            controller.keyPressed1(event);
            controller.keyPressed2(event);
        });
    }

    private void gameOver() {
        // Stop the game loop
        gameLoop.stop();

        // Show game over text and restart text
        gameOverText.setVisible(true);
        restartText.setVisible(true);

        // Set up event handler for restarting the game
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                resetGame();
            }
        });
    }
    private void gameWin() {
        // Stop the game loop
        gameLoop.stop();

        // Show win text and restart text
        winText.setVisible(true);
        restartText.setVisible(true);

        // Set up event handler for restarting the game
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                winText.setVisible(false);
                restartText.setVisible(false);
                resetGame();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
