package com.example;
import javafx.scene.image.Image;

enum direction {
    UP, DOWN, LEFT, RIGHT, NONE
}

public class Block {

    int defaultVelocity = 5;
    int slowVelocity = 2;
    int fastVelocity = 10;
    int stopVelocity = 0;
    int tileSize = 16;

    int x;
    int y;
    int width;
    int height;
    int velocity = defaultVelocity;
    direction currentDirection;
    Image image;

    private Block(BlockBuilder builder) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.currentDirection = currentDirection;
        this.image = image;
    }

    public class BlockBuilder {
        private int x;
        private int y;
        private int width = tileSize;
        private int height = tileSize;
        private direction currentDirection;
        private Image image;
    
        public BlockBuilder setX(int x) {
            this.x = x;
            return this;
        }
        public BlockBuilder setY(int y) {
            this.y = y;
            return this;
        }
        public BlockBuilder setWidth(int width) {
            this.width = width;
            return this;
        }
        public BlockBuilder setHeight(int height) {
            this.height = height;
            return this;
        }
        public BlockBuilder setCurrentDirection(direction currentDirection) {
            this.currentDirection = currentDirection;
            return this;
        }
        public BlockBuilder setImage(Image image) {
            this.image = image;
            return this;
        }
        public Block build() {
            return new Block(this);
        }
    }

    Block pacman = new Block.BlockBuilder()
            .setX(0)
            .setY(0)
            .setImage(new Image("file:src/main/resources/com/example/pacman.png"))
            .build();
    
    Block wall = new Block.BlockBuilder()
            .setX(0)
            .setY(0)
            .setImage(new Image("file:src/main/resources/com/example/wall.png"))
            .build();
    
    Block redGhost = new Block.BlockBuilder()
            .setX(0)
            .setY(0)
            .setImage(new Image("file:src/main/resources/com/example/redGhost.png"))
            .build();

    Block blueGhost = new Block.BlockBuilder()
            .setX(0)
            .setY(0)
            .setImage(new Image("file:src/main/resources/com/example/blueGhost.png"))
            .build();
    
    Block pinkGhost = new Block.BlockBuilder()
            .setX(0)
            .setY(0)
            .setImage(new Image("file:src/main/resources/com/example/pinkGhost.png"))
            .build();

    Block orangeGhost = new Block.BlockBuilder()
            .setX(0)
            .setY(0)
            .setImage(new Image("file:src/main/resources/com/example/orangeGhost.png"))
            .build();
    
    Block smallFood = new Block.BlockBuilder()
            .setX(0)
            .setY(0)
            .setImage(new Image("file:src/main/resources/com/example/smallFood.png"))
            .build();

    Block bigFood = new Block.BlockBuilder()
            .setX(0)
            .setY(0)
            .setImage(new Image("file:src/main/resources/com/example/bigFood.png"))
            .build();
    


}

