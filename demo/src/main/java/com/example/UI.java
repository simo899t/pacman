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
    private Label livesLabel;
    private Label startText;
    private Label gameOverText;
    private Label restartText;
    private Label winText;
    private Label nextLevelText;
    private ImageView logoImageView;
    private final HBox scorePanel;
    private final VBox root;
    private final int canvasWidth;
    private final int canvasHeight;
    private final Group canvasContainer;

    public UI(Group canvasContainer, int canvasWidth, int canvasHeight) {
        this.root = new VBox();
        this.scorePanel = new HBox();
        this.canvasContainer = canvasContainer;
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
        loadPanels();
        loadLogo();
        loadAlltext();
    }

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
    public void setScoreLabelText(String text) {
        if (scoreLabel != null) {
            scoreLabel.setText(text);
        }
    }
    public void setLivesLabelText(String text) {
        if (livesLabel != null) {
            livesLabel.setText(text);
        }        
    }
    public ImageView getLogoImageView() {
        return logoImageView;
    }


    public void loadPanels() {
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
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
        livesLabel = new Label("LIVES: 2");
        livesLabel.setTextFill(Color.WHITE);
        livesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scorePanel.getChildren().add(livesLabel);
    }

    public void loadLogo() {
        Image logoImage = new Image(getClass().getResource("/com/example/images/logo.png").toExternalForm());
        logoImageView = new ImageView(logoImage);
        
        // Set image size (adjust as needed)
        logoImageView.setFitWidth(400);
        logoImageView.setPreserveRatio(true);
        
        // Position the image above the start text
        logoImageView.setX((canvasWidth - 400) / 2);  // Center horizontally
        logoImageView.setY(canvasHeight / 4);         // Position in top half
        
        logoImageView.setVisible(true);  // Make visible at start
        canvasContainer.getChildren().addAll(logoImageView);
    }

    public void loadAlltext() {
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
        gameOverText.setLayoutX((canvasWidth - 280) / 2); // Adjusted width calculation
        gameOverText.setLayoutY((canvasHeight / 2) - 60); // Position above center

        // Create a restart text (also initially hidden)
        restartText = new Label("Press ENTER to restart");
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
        nextLevelText = new Label("Press any button to go to continue");
        nextLevelText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nextLevelText.setTextFill(Color.YELLOW);
        nextLevelText.setVisible(false);

        // Position the next level text below under the win text
        nextLevelText.setLayoutX((canvasWidth - 300) / 2); // Adjusted width calculation
        nextLevelText.setLayoutY((canvasHeight / 2) + 10); // Position below center

        // add all text labels to the canvas container
        canvasContainer.getChildren().addAll(gameOverText, restartText, winText, startText, nextLevelText);
    }

    public Scene setScene() {
        root.getChildren().addAll(scorePanel, canvasContainer);
        Scene scene = new Scene(root, canvasWidth, Math.max(canvasHeight + 30, 30));
        return scene;
    }
}
