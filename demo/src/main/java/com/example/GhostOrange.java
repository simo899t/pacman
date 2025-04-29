package com.example;

import javafx.scene.image.Image;

public class GhostOrange extends Block {
    public GhostOrange(double x, double y, double width, double height, double speed, Image image) {
        super(x, y, width, height, speed, new Image(GhostOrange.class.getResource("/com/example/images/orangeGhostRight.png").toExternalForm()));
    }
}
