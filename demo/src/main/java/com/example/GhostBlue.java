package com.example;

import javafx.scene.image.Image;

public class GhostBlue extends Block {
    public GhostBlue(double x, double y, double width, double height, double speed, Image image) {
        super(x, y, width, height, speed, new Image(GhostBlue.class.getResource("/com/example/images/redghost.png").toExternalForm()));
    }
}
