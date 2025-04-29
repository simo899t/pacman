package com.example;

import javafx.scene.image.Image;

public class Ghost extends Block {
    public Ghost(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed, new Image(Ghost.class.getResource("/com/example/images/ghost.png").toExternalForm()));
    }

    @Override
    public void update() {
        // Implement Pacman's specific update logic
        super.update();
    }

    @Override
    public void draw() {
        // Implement Pacman's specific drawing logic
        super.draw();
    }

    @Override
    public void collide(Collideable other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collide'");
    }
}
