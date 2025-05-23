package com.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {

    private IGameLives gameLives;
    private IGameScore gameScore;
    private Revive revive;
    private IMap map;
    private IController controller;
    private IDraw draw; 
    private IUpdate update;
    private UpdateImages updateImages;
    private GameState gameState;
    private AnimationTimer gameLoop;
    private Label scoreLabel;
    private Label livesLabel;
    private Label startText;
    private Label gameOverText;
    private Label restartText;
    private Label winText;
    private Scene scene;
    private GameTimer gameTimer;
    private ImageView logoImageView;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pac-Man Game");
        stage.setResizable(false);

        // Load the map
        map = new Map();
        IGrid grid = new Grid(map);

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

        // Create a restart text (also initially hidden)
        startText = new Label("Press any button to start");
        startText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        startText.setTextFill(Color.YELLOW);
        startText.setVisible(true);

        // Position the restart text below the game over text
        startText.setLayoutX((canvasWidth - 350) / 2); // Adjusted width calculation
        startText.setLayoutY((canvasHeight / 2)); // Position below center

        // Create a game over text (initially hidden)
        gameOverText = new Label("GAME OVER");
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        gameOverText.setTextFill(Color.YELLOW);
        gameOverText.setVisible(false);

        // Center the gameover text in the canvas
        gameOverText.setLayoutX((canvasWidth-280) / 2); // Adjusted width calculation
        gameOverText.setLayoutY((canvasHeight / 2) -60); // Position above center

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

        // Create start screen image
        Image logoImage = new Image(getClass().getResource("/com/example/images/logo.png").toExternalForm());
        logoImageView = new ImageView(logoImage);
        
        // Set image size (adjust as needed)
        logoImageView.setFitWidth(400);
        logoImageView.setPreserveRatio(true);
        
        // Position the image above the start text
        logoImageView.setX((canvasWidth - 400) / 2);  // Center horizontally
        logoImageView.setY(canvasHeight / 4);         // Position in top half
        
        logoImageView.setVisible(true);  // Make visible at start

        canvasContainer.getChildren().addAll(gameOverText, restartText, winText, startText, logoImageView);

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
                controller.keyPressed(event);
            }
        });

        GhostMovementPathfinding ghostMovementPathfinding = new GhostMovementPathfinding(map, grid);
        gameLives = new GameLives();
        gameScore = new GameScore();
        draw = new Draw(map, canvas);
        gameTimer = new GameTimer();
        updateImages = new UpdateImages(map);
        revive = new Revive(map);
        update = new Update(map, gameScore, gameLives, gameTimer, this, updateImages, revive);
        
        // Initialize gameLoop BEFORE creating GameState
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw.drawAllBlocks();
                ghostMovementPathfinding.directAllGhosts();
                update.updateGame(map);
                updateImages.updateAllImages();
                gameState.updateGameState();
                scoreLabel.setText("SCORE: " + gameScore.getScore());
                livesLabel.setText("LIVES: " + gameLives.getLives());
                 
                gameTimer.runFunctionList(gameTimer.functionList);
            }
        };
        
        // Create GameState instance
        gameState = new GameState(gameLoop, gameLives, gameScore, map, gameOverText, restartText, winText, startText, logoImageView, controller, scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
