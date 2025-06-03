package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UI {
    private Label scoreLabel;
    private Label levelLabel;
    private Label startText;
    private Label gameOverText;
    private Label restartText;
    private Label winText;
    private Label nextLevelText;
    private ImageView lifeImageView;
    private ImageView logoImageView;
    private final HBox scorePanel;
    private final HBox livesPanel;
    private final HBox levelPanel;
    private final VBox root;
    private final int canvasWidth;
    private final int canvasHeight;
    private final Group canvasContainer;

    /**
     * Constructor for UI that initializes the UI components.
     * 
     * @param canvasContainer The container for the canvas where the game is displayed.
     * @param canvasWidth The width of the canvas.
     * @param canvasHeight The height of the canvas.
     */
    public UI(Group canvasContainer, int canvasWidth, int canvasHeight) {
        this.root = new VBox();
        this.scorePanel = new HBox();
        this.livesPanel = new HBox();
        this.levelPanel = new HBox();
        this.canvasContainer = canvasContainer;
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
        loadPanels();
        loadImages();
        loadAlltext();
        
        // Hide game panels initially
        scorePanel.setVisible(false);
        livesPanel.setVisible(false);
        levelPanel.setVisible(false);
    }

    // Getters for UI components
    public Label getStartText() {
        return startText;
    }
    public Label getGameOverText() {
        return gameOverText;
    }
    public Label getRestartText() {
        return restartText;
    }
    public Label getWinText() {
        return winText;
    }
    public Label getNextLevelText() {
        return nextLevelText;
    }
    public ImageView getLogoImageView() {
        return logoImageView;
    }

    /**
     * Sets the text of the score label.
     * This method updates the score label with the provided text.
     * 
     * @param text The text to set for the score label.
     */
    public void setScoreLabelText(String text) {
        if (scoreLabel != null) {
            scoreLabel.setText(text);
        }
    }  

    public void setLevelLabelText(String text) {
        if (levelLabel != null) {
            levelLabel.setText(text);
        }
    } 

    /**
     * Updates the lives panel with the current number of lives left.
     * This method clears the existing lives panel and repopulates it with new life icons.
     * 
     * @param livesLeft The number of lives left to display.
     */
    public void updateLives(int livesLeft) {
        // Clear any existing content in livesPanel
        livesPanel.getChildren().clear();
        
        // Add new life icons to livesPanel
        for (int i = 0; i < livesLeft; i++) { 
            Image lifeImage = new Image(getClass().getResource("/com/example/images/life.png").toExternalForm());
            ImageView lifeIcon = new ImageView(lifeImage);
            lifeIcon.setFitWidth(30); // Slightly smaller to fit better
            lifeIcon.setFitHeight(30);
            
            // Add to livesPanel
            livesPanel.getChildren().add(lifeIcon);
        }
    }

    /**
     * Loads the panels and sets their properties.
     * This method initializes the score panel and sets its background and alignment.
     */
    private void loadPanels() {
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        // Configure main score panel to span full width
        scorePanel.setPrefHeight(30);
        scorePanel.setMinHeight(30);
        scorePanel.setPrefWidth(canvasWidth); // Make it full width
        scorePanel.setPadding(new Insets(5, 10, 5, 10));
        scorePanel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        
        // Create a 3-section layout with proper spacing
        HBox leftSection = new HBox(); // For score
        HBox centerSection = new HBox(); // For lives
        HBox rightSection = new HBox(); // For level
        
        // Configure sections
        leftSection.setAlignment(Pos.CENTER_LEFT);
        leftSection.setPrefWidth(canvasWidth / 3);
        
        centerSection.setAlignment(Pos.CENTER);
        centerSection.setPrefWidth(canvasWidth / 3);
        
        rightSection.setAlignment(Pos.CENTER_RIGHT);
        rightSection.setPrefWidth(canvasWidth / 3);
        
        // Create score label in left section
        scoreLabel = new Label("SCORE: 0");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        leftSection.getChildren().add(scoreLabel);
        
        // Lives will be added to center section in updateLives()
        livesPanel.setAlignment(Pos.CENTER);
        centerSection.getChildren().add(livesPanel);
        
        // Create level label in right section
        levelLabel = new Label("LEVEL: 1");
        levelLabel.setTextFill(Color.WHITE);
        levelLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        rightSection.getChildren().add(levelLabel);
        
        // Add all three sections to the score panel
        scorePanel.getChildren().addAll(leftSection, centerSection, rightSection);
    }

    /**
     * Loads the images used in the UI.
     * This method initializes the logo image and positions it above the start text.
     */
    private void loadImages() {
        Image logoImage = new Image(getClass().getResource("/com/example/images/logo.png").toExternalForm());
        logoImageView = new ImageView(logoImage);
        
        // Set image size (adjust as needed)
        logoImageView.setFitWidth(400);
        logoImageView.setPreserveRatio(true);
        
        // Position the image above the start text
        logoImageView.setX((canvasWidth - 400) / 2);  // Center horizontally
        logoImageView.setY(canvasHeight / 10);         // Position in top half
        logoImageView.setVisible(true);  // Make visible at start

        Image lifeImage = new Image(getClass().getResource("/com/example/images/life.png").toExternalForm());
        lifeImageView = new ImageView(lifeImage);
        lifeImageView.setFitWidth(50);
        lifeImageView.setFitHeight(50);
        canvasContainer.getChildren().addAll(logoImageView);
    }

    /**
     * Loads all text labels used in the UI.
     * This method initializes the start text, game over text, restart text, win text, and next level text.
     * It positions them appropriately within the canvas container.
     */
    private void loadAlltext() {
        startText = new Label("Press Arrow Key To Start");
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
        gameOverText.setLayoutX((canvasWidth - 280) / 2); // Adjusted width calculation
        gameOverText.setLayoutY((canvasHeight / 2) - 60); // Position above center

        // Create a restart text (also initially hidden)
        restartText = new Label("Press ENTER To Restart");
        restartText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        restartText.setTextFill(Color.YELLOW);
        restartText.setVisible(false);

        // Position the restart text below the game over text
        restartText.setLayoutX((canvasWidth - 200) / 2); // Adjusted width calculation
        restartText.setLayoutY((canvasHeight / 2) + 10); // Position below center

        // Create a win text (also initially hidden)
        winText = new Label("YOU WIN!");
        winText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        winText.setTextFill(Color.YELLOW);
        winText.setVisible(false);

        // Center the win text in the canvas
        winText.setLayoutX((canvasWidth - 220) / 2); // Adjusted width calculation for win text
        winText.setLayoutY((canvasHeight / 2) - 50); // Position above center

        // Create a next level text (also initially hidden)
        nextLevelText = new Label("Press Any Button To Continue");
        nextLevelText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nextLevelText.setTextFill(Color.YELLOW);
        nextLevelText.setVisible(false);

        // Position the next level text below under the win text
        nextLevelText.setLayoutX((canvasWidth - 300) / 2); // Adjusted width calculation
        nextLevelText.setLayoutY((canvasHeight / 2) + 10); // Position below center

        // add all text labels to the canvas container
        canvasContainer.getChildren().addAll(gameOverText, restartText, winText, startText, nextLevelText);
    }

    /**
     * Sets the scene for the UI.
     * This method adds the score panel and canvas container to the root layout and returns a new Scene.
     * 
     * @return A new Scene containing the UI components.
     */
    public Scene setScene() {
        root.getChildren().addAll(scorePanel, canvasContainer);
        Scene scene = new Scene(root, canvasWidth, Math.max(canvasHeight + 30, 30));
        return scene;
    }

    // Add this method to the UI class
    public void setGamePanelsVisible(boolean visible) {
        scorePanel.setVisible(visible);
        livesPanel.setVisible(visible);
        levelPanel.setVisible(visible);
    }
}
