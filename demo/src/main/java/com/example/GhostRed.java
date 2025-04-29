package com.example;

import javafx.scene.image.Image;

public class GhostRed extends Block {
    public GhostRed(double x, double y, double width, double height, double speed, Image image) {
        super(x, y, width, height, speed, new Image(GhostRed.class.getResource("/com/example/images/redghost.png").toExternalForm()));
    }
}
