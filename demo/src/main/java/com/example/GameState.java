package com.example;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;

public class GameState{

    public enum State {
        NOTSTARTEDYET,
        PLAYING,
        GAME_OVER,
        WIN
    }

    private State gameState;
    private AnimationTimer gameLoop;
    private IGameLives gameLives;
    private IGameScore gameScore;
    private IMap map;
    private Label gameOverText;
    private Label restartText;
    private Label winText;
    private Label startText;
    private Label nextLevelText;
    private IController controller;
    private Scene scene;

    public GameState(AnimationTimer gameLoop, IGameLives gameLives, IGameScore gameScore, IMap map, 
                Label gameOverText, Label restartText, Label winText, Label startText, Label nextLevelText,
                ImageView logoImageView, IController controller, Scene scene) {

        this.map = map;
        this.gameState = State.NOTSTARTEDYET;
        this.gameLoop = gameLoop;
        this.gameLives = gameLives;
        this.gameScore = gameScore;
        this.gameOverText = gameOverText;
        this.restartText = restartText;
        this.winText = winText;
        this.startText = startText;
        this.nextLevelText = nextLevelText;
        this.controller = controller;
        this.scene = scene;
        this.gameOverText.setVisible(false);
        this.restartText.setVisible(false);
        this.winText.setVisible(false);
        this.winText.setVisible(false);
        
        // Set up initial key press detection for game start
        scene.setOnKeyPressed(event -> {
            if (gameState == State.NOTSTARTEDYET) {
                startText.setVisible(false);
                logoImageView.setVisible(false);
                controller.keyPressed(event);
                gameState = State.PLAYING;
                setupGameControls(); // Setup regular game controls
                gameLoop.start();
            }
        });
    }

    public void updateGameState() {
        if (gameLives.getLives() < 0) {
            gameState = State.GAME_OVER;
        } else if (map.getPelletsLeft() == 0) {
            gameState = State.WIN;
        }
        checkGameState();
    }

    private void setupGameControls() {
        // Set up game controls with the controller
        scene.setOnKeyPressed(event -> {
            controller.keyPressed(event);
        });
    }

    private void checkGameState() {
        if (gameState == State.PLAYING) {
            // Already handled by the gameLoop
        } else if (gameState == State.GAME_OVER) {
            gameOver();
        } else if (gameState == State.WIN) {
            gameWin();
        }
        // NOTSTARTEDYET is handled by the initial key handler
    }

    private void gameOver() {
        // Stop the game loop
        gameLoop.stop();

        // Show game over text and restart text
        gameOverText.setVisible(true);
        restartText.setVisible(true);

        gameState = State.NOTSTARTEDYET;

        // Set up event handler for restarting the game
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                resetGame();
                
            }
        });
    }

    private void resetGame() {
        // Reset game state
        gameLives.resetLives();
        gameScore.resetScore();
        map.resetMap();
        
        // Hide game over text
        gameOverText.setVisible(false);
        restartText.setVisible(false);
        startText.setVisible(false);
        
        // Start a new game loop
        gameLoop.start();
        
        // Restore original controls with the updated controller
        scene.setOnKeyPressed(event -> {
            controller.keyPressed(event);
            gameState = State.PLAYING;
        });
    }

    private void gameWin() {
        // Stop the game loop
        gameLoop.stop();

        // Show win text and restart text
        winText.setVisible(true);
        nextLevelText.setVisible(true);

        // Set up event handler for restarting the game
        scene.setOnKeyPressed(event -> {
            nextLevel();
        });
    }

    private void nextLevel() {
        // Reset game state
        gameLives.resetLives();
        map.resetMap();

        System.out.println();
        winText.setVisible(false);
        nextLevelText.setVisible(false);
        
        gameState = State.NOTSTARTEDYET;

        gameLoop.start();
        // Restore original controls with the updated controller
        scene.setOnKeyPressed(event -> {
            controller.keyPressed(event);
            
            gameState = State.PLAYING;
        });
    }
    
}
