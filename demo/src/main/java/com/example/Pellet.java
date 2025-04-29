package com.example;

import javafx.scene.image.Image;

public class Pellet extends Block {
    public Pellet(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed, new Image(Pellet.class.getResource("/com/example/images/smallFood.png").toExternalForm()));
    }
}
