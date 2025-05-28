package com.example;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class GameState{

    public enum mode {
        NOTSTARTEDYET,
        PLAYING,
        GAME_OVER,
        WIN
    }

    private mode gameState;
    private final AnimationTimer gameLoop;
    private final IGameLives gameLives;
    private final IGameScore gameScore;
    private final IMap map;
    private final Label gameOverText;
    private final Label restartText;
    private final Label winText;
    private final Label startText;
    private final Label nextLevelText;
    private final IController controller;
    private final Scene scene;

    public GameState(AnimationTimer gameLoop, IGameLives gameLives, IGameScore gameScore, IMap map, 
                Label gameOverText, Label restartText, Label winText, Label startText, Label nextLevelText,
                ImageView logoImageView, IController controller, Scene scene) {

        this.map = map;
        this.gameState = mode.NOTSTARTEDYET;
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
        
            if (gameState == mode.NOTSTARTEDYET
                && event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN
                || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                startText.setVisible(false);
                logoImageView.setVisible(false);
                controller.keyPressed(event);
                gameState = mode.PLAYING;
                setupGameControls(); // Setup regular game controls
                map.resetAllGhosts();
                gameLoop.start();
            }
        });
    }

    public mode getGameState() {
        return this.gameState;
    }

    public void updateGameState() {
        if (gameLives.getLives() < 0) {
            gameState = mode.GAME_OVER;
        } else if (map.getPelletsLeft() == 0) {
            gameState = mode.WIN;
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
        if (gameState == mode.PLAYING) {
            // Already handled by the gameLoop
        } else if (gameState == mode.GAME_OVER) {
            gameOver();
        } else if (gameState == mode.WIN) {
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

        gameState = mode.NOTSTARTEDYET;

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
            gameState = mode.PLAYING;
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
        map.resetMap();
        winText.setVisible(false);
        nextLevelText.setVisible(false);
        
        gameState = mode.NOTSTARTEDYET;

        gameLoop.start();
        // Restore original controls with the updated controller
        scene.setOnKeyPressed(event -> {
            controller.keyPressed(event);
            gameState = mode.PLAYING;
        });
    }
    
}
