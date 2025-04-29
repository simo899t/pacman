package com.example;

import javafx.scene.image.Image;

public class GhostPink extends Block {
    public GhostPink(double x, double y, double width, double height, double speed, Image image) {
        super(x, y, width, height, speed, new Image(GhostPink.class.getResource("/com/example/images/pinkghost.png").toExternalForm()));
    }
}
