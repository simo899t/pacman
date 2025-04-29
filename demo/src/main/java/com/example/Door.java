package com.example;

import javafx.scene.image.Image;

public class Door extends Block {

    public Door(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed, new Image(Door.class.getResource("/com/example/images/door.png").toExternalForm()));
    }
}
