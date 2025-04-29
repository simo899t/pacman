package com.example;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class Game extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pacman Game");
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);

        
        stage.setScene(scene);
        stage.centerOnScreen(); // Center the stage on the screen
        stage.show();
    }
    
    //load music
    //load sound effects

    //public play() {
    //    
    //}

    public void keyPressed(KeyEvent e) {
        //System.out.println("KeyEvent: " + keyCode);
        switch (e.getCode()) {
            case UP:
                // Move up
                break;
            case DOWN:
                // Move down
                break;
            case LEFT:
                // Move left
                break;
            case RIGHT:
                // Move right
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
