package com.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    private ICollideHandler collideHandler;
    private Collision collision;
    private IEater eater;
    private IKillEntity killEntity;
    
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
        gameTimer = new GameTimer();
        map = new Map(gameTimer);
        IGrid grid = new Grid(map);

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
        UI ui = new UI(canvasContainer, canvasWidth, canvasHeight);
        scene = ui.setScene();
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
        updateImages = new UpdateImages(map, gameTimer);
        revive = new Revive(map);
        eater = new Eater(map, gameScore, gameLives, gameTimer, null, updateImages);
        killEntity = new KillEntity(map, gameScore, gameLives, gameTimer);
        collision = new Collision(map);

        collideHandler = new CollideHandler(map, gameScore, gameLives, gameTimer, this, updateImages, revive, eater, killEntity);
        update = new Update(map, gameScore, gameLives, gameTimer, this, updateImages, revive, collideHandler, collision);
        

        // Initialize gameLoop BEFORE creating GameState
        gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                long startOfLoopTime = System.currentTimeMillis();
                
                draw.drawAllBlocks();
                ghostMovementPathfinding.directAllGhosts();
                update.updateGame(map);
                updateImages.updateAllImages();
                gameState.updateGameState();
                ui.setScoreLabelText("SCORE: " + gameScore.getScore());
                ui.setLivesLabelText("LIVES: " + gameLives.getLives());
                

                if (gameState.getGameState() == GameState.State.PLAYING) {
                    gameTimer.runFunctionList(gameTimer.functionList);
                }

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
        
        // Create GameState instance
        gameState = new GameState(gameLoop, gameLives, gameScore, map, ui.getGameOverText(), ui.getRestartText(), 
                                  ui.getWinText(), ui.getStartText(), ui.getNextLevelText(), ui.getLogoImageView(), controller, scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
