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
    private final ILevel level;
    private final IMap map;
    private final Label gameOverText;
    private final Label restartText;
    private final Label winText;
    private final Label startText;
    private final Label nextLevelText;
    private final ImageView logoImageView;
    private final IController controller;
    private final Scene scene;
    private final Pacman pacman;

    public GameMode(AnimationTimer gameLoop, IGameLives gameLives, IGameScore gameScore, ILevel level, IMap map, IController controller, Scene scene, UI ui) {
        this.map = map;
        this.gameMode = mode.NOTSTARTEDYET;     // Initial game state
        this.gameLoop = gameLoop;
        this.gameLives = gameLives;
        this.gameScore = gameScore;
        this.level = level;
        this.gameOverText = ui.getGameOverText();
        this.restartText = ui.getRestartText();
        this.winText = ui.getWinText();
        this.startText = ui.getStartText();
        this.nextLevelText = ui.getNextLevelText();
        this.logoImageView = ui.getLogoImageView();
        this.controller = controller;
        this.scene = scene;
        
        this.gameOverText.setVisible(false); // Hide gameover and win text initially
        this.restartText.setVisible(false);
        this.winText.setVisible(false);
        this.winText.setVisible(false);
        this.pacman = map.getPacman();

        // Set up initial key press detection for game start
        scene.setOnKeyPressed(event -> {
            if (gameMode == mode.NOTSTARTEDYET
                && (event.getCode() == KeyCode.UP
                    || event.getCode() == KeyCode.DOWN
                    || event.getCode() == KeyCode.LEFT
                    || event.getCode() == KeyCode.RIGHT)) {
                ui.setGamePanelsVisible(true);
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

    //getter and setter for gameMode
    public mode getGameState() {
        return this.gameMode;
    }

    public void setGameState(mode newState) {
        this.gameMode = newState;
    }

    /**
     * Updates the game state based on the current conditions.
     * This method checks if the game is over, if the player has won, or if Pacman is dead.
     * It updates the gameMode accordingly and calls checkGameState to handle the state changes.
     */
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

    /**
     * Sets up the game controls for the player.
     * This method binds the key press events to the controller's keyPressed method,
     * allowing the player to control Pacman using the arrow keys.
     */
    private void setupGameControls() {
        // Set up game controls with the controller
        scene.setOnKeyPressed(event -> {
            controller.keyPressed(event);
        });
    }

    /**
     * Checks the current game state and performs actions based on it.
     * This method handles the different game modes: PLAYING, GAME_OVER, WIN, and NOTSTARTEDYET.
     * It updates the game loop and sets up key event handlers as needed.
     */
    private void checkGameState() {
        switch (gameMode) {
            case GAME_OVER:
                gameOver();
                break;
            case WIN:
                gameWin();
                break;
            case NOTSTARTEDYET:
                // ensures that the game loop is stopped when the game is not started yet
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
            default:
                break;
        }
    }

    /**
     * Handles the game over state.
     * This method stops the game loop, displays the game over text and restart text,
     * and sets up an event handler to restart the game when the Enter key is pressed.
     */
    private void gameOver() {
        // Stop the game loop
        gameLoop.stop();

        // Show game over text and restart text
        gameOverText.setVisible(true);
        restartText.setVisible(true);

        // ensure that the game loop is stopped when the game is not started yet
        gameMode = mode.NOTSTARTEDYET;

        // Set up event handler for restarting the game
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                resetGame();
                
            }
        });
    }

    /**
     * Resets the game state to start a new game.
     * This method resets lives, score, and map, hides the game over text,
     * and starts a new game loop. It also restores the original controls.
     */
    private void resetGame() {
        // Reset game state
        gameLives.resetLives();
        gameScore.resetScore();
        level.resetLevel();
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

    /**
     * Handles the game win state.
     * This method stops the game loop, displays the win text and next level text,
     * and sets up an event handler to proceed to the next level when a key is pressed.
     */
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

    /**
     * Proceeds to the next level by resetting the game state.
     * This method resets the map, hides the win text and next level text,
     * and starts a new game loop. It also restores the original controls.
     */
    private void nextLevel() {
        // Reset game state
        map.resetMap();
        winText.setVisible(false);
        nextLevelText.setVisible(false);
        level.incrementLevel();
        
        // ensure that the game loop is stopped when the game is not started yet
        gameMode = mode.NOTSTARTEDYET;

        gameLoop.start();
        // Restore original controls with the updated controller
        scene.setOnKeyPressed(event -> {
            controller.keyPressed(event);
            gameMode = mode.PLAYING;
        });
    }
    
}
