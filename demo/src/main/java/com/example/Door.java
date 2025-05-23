package com.example;

import javafx.scene.image.Image;

public class Door extends Block{

    public Door(Image image, int x, int y) {
        super(image, x, y);
    }

    public void openDoor() {
        setImage(new Image(getClass().getResource("/com/example/images/doorOpen.png").toExternalForm()));
    }

    public void closeDoor() {
        setImage(new Image(getClass().getResource("/com/example/images/doorClosed.png").toExternalForm()));
    }

    
}
