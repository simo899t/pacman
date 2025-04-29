package com.example;
import javafx.scene.image.Image;

public class Pacman extends Block {

    public Pacman(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed, new Image(Pacman.class.getResource("/com/example/images/pacman.png").toExternalForm()));
    }
}
