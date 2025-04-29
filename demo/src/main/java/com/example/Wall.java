package com.example;

import javafx.scene.image.Image;

public class Wall extends Block {

    public Wall(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed, new Image(Wall.class.getResource("/com/example/images/wall.png").toExternalForm()));
    }
}
