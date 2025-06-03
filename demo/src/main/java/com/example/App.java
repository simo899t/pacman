package com.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class App extends Application {
    private IGameLives gameLives;
    private IGameScore gameScore;
    private ILevel level;
    private Revive revive;
    private IMap map;
    private UI ui;
    private IGrid grid;
    private IController controller;
    private IDraw draw; 
    private IUpdate update;
    private GhostMovementPathfinding ghostMovementPathfinding;
    private UpdateImages updateImages;
    private GameMode gameMode;
    private AnimationTimer gameLoop;
    private ICollideHandler collideHandler;
    private Collision collision;
    private IEater eater;
    private IKillEntity killEntity;
    private Scene scene;
    private GameTimer gameTimer;


    /**
     * The main entry point for the JavaFX application.
     * Initializes the game components and starts the game loop.
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Pac-Man Game");
        stage.setResizable(false);

        // Load the map
        gameTimer = new GameTimer();
        map = new Map(gameTimer);
        grid = new Grid(map);

        // Define the dimensions of the game with current map
        int tileSize = map.getTileSize();
        int rowCount = map.getRows();
        int columCount = map.getCols();
        int canvasWidth = columCount * tileSize;
        int canvasHeight = rowCount * tileSize;
        
        // Create game canvas container
        Group canvasContainer = new Group();
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        canvasContainer.getChildren().add(canvas);

        // load all ui (panels, labels, images)
        ui = new UI(canvasContainer, canvasWidth, canvasHeight);
        scene = ui.setScene();
        // Show the scene
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        controller = new Controller(map.getPacman());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPressed(event);
            }
        });

        // Initialize game components
        level = new Level();
        gameLives = new GameLives();
        gameScore = new GameScore();
        ghostMovementPathfinding = new GhostMovementPathfinding(map, grid, level);
        draw = new Draw(map, canvas);
        updateImages = new UpdateImages(map);
        revive = new Revive();
        eater = new Eater(map, gameScore, gameTimer);
        killEntity = new KillEntity(map, gameLives, gameTimer);
        collision = new Collision(map);
        collideHandler = new CollideHandler(gameTimer, revive, eater, killEntity, map);
        update = new Update(map, collideHandler, collision);
        

        // Initialize gameLoop with the initial lives
        ui.updateLives(gameLives.getLives());
    

        gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                long startOfLoopTime = System.currentTimeMillis();
                
                // update ghost pathfinding
                ghostMovementPathfinding.directAllGhosts();

                // update pacman and ghost movement
                update.updateGame(map);

                // update game state
                gameMode.updateGameState();

                // draw all blocks
                draw.drawAllBlocks();

                // update images (& animation) and UI
                updateImages.updateAllImages();
                ui.setScoreLabelText("SCORE: " + gameScore.getScore());
                ui.setLevelLabelText("LEVEL: " + level.getLevel());
                ui.updateLives(gameLives.getLives());
                
                // play game-functions for ghost start, animation, etc.
                if (gameMode.getGameState() == GameMode.mode.PLAYING) {
                    gameTimer.runFunctionList(gameTimer.getFunctionList());
                }

                // make sure the game loop runs at a consistent frame rate
                long diff = System.currentTimeMillis() - startOfLoopTime;
                if (diff < 800 / 60) {
                    try {
                        Thread.sleep(800 / 60 - diff);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        // Create GameState instance (remove AnimationTimer from constructor)
        gameMode = new GameMode(gameLoop, gameLives, gameScore, level, map, controller, scene, ui);
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }

}
