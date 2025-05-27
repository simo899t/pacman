package com.example;

import javafx.scene.canvas.Canvas;

public class Draw implements IDraw {
    private final IMap map;
    private final Canvas canvas;

    public Draw(IMap map, Canvas canvas) {
        this.canvas = canvas;
        this.map = map;
    }

    @Override
    public void drawAllBlocks() {
        // Clear the canvas and fill it with black
        canvas.getGraphicsContext2D().setFill(javafx.scene.paint.Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw all blocks against the black background
        for (Block block : map.getAllBlocks()) {
            draw(block);
        }
    }

    private void draw(Block block) {
        canvas.getGraphicsContext2D().drawImage(block.getImage(), block.getX(), block.getY(), map.getTileSize(), map.getTileSize());
    }
    
}
