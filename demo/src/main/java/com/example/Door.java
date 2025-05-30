package com.example;

import javafx.scene.image.Image;

public class Door extends Block{

    /**
     * Constructor for Door.
     * @param image The image representing the door.
     * @param x The x-coordinate of the door.
     * @param y The y-coordinate of the door.
     */
    public Door(Image image, int x, int y) {
        super(image, x, y);
    }

    /**
     * Opens the door by changing its image to the open door image.
     */
    public void openDoor() {
        setImage(new Image(getClass().getResource("/com/example/images/doorOpen.png").toExternalForm()));
    }

    /**
     * Closes the door by changing its image to the closed door image.
     */
    public void closeDoor() {
        setImage(new Image(getClass().getResource("/com/example/images/doorClosed.png").toExternalForm()));
    }

    
}
