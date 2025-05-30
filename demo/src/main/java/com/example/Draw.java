package com.example;

import javafx.scene.canvas.Canvas;

public class Draw implements IDraw {
    private final IMap map;
    private final Canvas canvas;

    /**
     * Constructor for the Draw Class.
     * @param map The map where all game ellements are stored.
     * @param canvas The canvas where the game elements will be drawn.
     */
    public Draw(IMap map, Canvas canvas) {
        this.canvas = canvas;
        this.map = map;
    }

    /**
     * Draws all blocks on the canvas against a black background.
     * Usigng draw(Block block) to draw each block.
     */
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

    /**
     * Draws a single block on the canvas.
     * @param block
     */
    private void draw(Block block) {
        canvas.getGraphicsContext2D().drawImage(block.getImage(), block.getX(), block.getY(), map.getTileSize(), map.getTileSize());
    }
    
}
