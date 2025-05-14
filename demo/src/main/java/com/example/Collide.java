package com.example;

public class Collide implements ICollide{

    Gamemode gameMode = new Gamemode();
    int mode = gameMode.getMode();
    
    GameLives gameLives = new GameLives();
    GameScore gameScore = new GameScore();
    

    @Override
    public void ghostCollision(MoveableBlock entity) {
       if (mode == 0) {
            killPlayer();
            gameLives.removeLife();

       }
       else if (mode == 1) {
            killGhost(entity);
            gameScore.addScore(5000000);
       }
    }

    @Override
    public void pelletCollision(MoveableBlock entity) {
        eatOn(entity);
        gameScore.addScore(10);
    }

    @Override
    public void bigPelletCollision(MoveableBlock entity) {
        eatOn(entity);
        gameMode.setMode(1);
        gameScore.addScore(10);
        
    }

    @Override
    public void fruitCollision(MoveableBlock entity) {
        eatOn(entity);
        gameLives.addLife();
        
    }

    
    
}
