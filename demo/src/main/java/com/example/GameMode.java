package com.example;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class GameMode{

    public enum mode {
        NOTSTARTEDYET,
        PLAYING,
        GAME_OVER,
        WIN
    }

    private mode gameMode;
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
    private final Pacman pacman;

    public GameMode(AnimationTimer gameLoop, IGameLives gameLives, IGameScore gameScore, IMap map, 
                Label gameOverText, Label restartText, Label winText, Label startText, Label nextLevelText,
                ImageView logoImageView, IController controller, Scene scene, Pacman pacman) {

        this.map = map;
        this.gameMode = mode.NOTSTARTEDYET;
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
        this.pacman = pacman;

        // Set up initial key press detection for game start
        scene.setOnKeyPressed(event -> {
            if (gameMode == mode.NOTSTARTEDYET
                && (event.getCode() == KeyCode.UP
                    || event.getCode() == KeyCode.DOWN
                    || event.getCode() == KeyCode.LEFT
                    || event.getCode() == KeyCode.RIGHT)) {
                startText.setVisible(false);
                logoImageView.setVisible(false);
                controller.keyPressed(event);
                gameMode = mode.PLAYING;
                setupGameControls(); // Setup regular game controls
                map.resetAllGhosts();
                gameLoop.start();
            }
        });
    }

    public mode getGameState() {
        return this.gameMode;
    }

    public void setGameState(mode newState) {
        this.gameMode = newState;
    }

    public void updateGameState() {
        if (gameLives.getLives() < 0) {
            gameMode = mode.GAME_OVER;
        } else if (map.getPelletsLeft() == 0) {
            gameMode = mode.WIN;
        } else if (!pacman.isAlive()) {
            gameMode = mode.NOTSTARTEDYET;            
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
        System.out.println("Game mode set to " + gameMode);

        if (gameMode == mode.PLAYING) {
            // Already handled by the gameLoop
        } else if (gameMode == mode.GAME_OVER) {
            gameOver();
        } else if (gameMode == mode.WIN) {
            gameWin();
        } else if (gameMode == mode.NOTSTARTEDYET) {
            gameLoop.stop();
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.UP
                    || event.getCode() == KeyCode.DOWN
                    || event.getCode() == KeyCode.LEFT
                    || event.getCode() == KeyCode.RIGHT) {

                    if (gameMode == mode.NOTSTARTEDYET) {
                        gameMode = mode.PLAYING;
                        controller.keyPressed(event);
                        gameLoop.start();
                        map.resetAllGhosts();
                        setupGameControls();
                        pacman.setAlive(true);
                    }
                }
            });
        }
        // NOTSTARTEDYET is handled by the initial key handler
    }

    private void gameOver() {
        // Stop the game loop
        gameLoop.stop();

        // Show game over text and restart text
        gameOverText.setVisible(true);
        restartText.setVisible(true);

        gameMode = mode.NOTSTARTEDYET;

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
            gameMode = mode.PLAYING;
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
        
        gameMode = mode.NOTSTARTEDYET;

        gameLoop.start();
        // Restore original controls with the updated controller
        scene.setOnKeyPressed(event -> {
            controller.keyPressed(event);
            gameMode = mode.PLAYING;
        });
    }
    
}
