package com.example;

import javafx.scene.image.Image;

public class Powerup extends Block{
    public Powerup(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed, new Image(Powerup.class.getResource("/com/example/images/bigFood.png").toExternalForm()));
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
