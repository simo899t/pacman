package com.example;

import javafx.scene.image.Image;

public class Powerup extends Block{
    public Powerup(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed, new Image(Powerup.class.getResource("/com/example/images/bigFood.png").toExternalForm()));
    }
}
