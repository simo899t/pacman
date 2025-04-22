package com.example;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


public class Game extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Pacman Game");
        stage.setResizable(false);

        Group root = new Group(imageView);
        Scene scene = new Scene(root, 800, 600, Color.BLACK);

        stage.setScene(scene);

        stage.show();
    }




    ImageView imageView = new ImageView("file:demo/src/main/resources/com/example/tileset.png");
    
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

}
