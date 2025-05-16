package com.example;

public class Eater {
    IMap map;
    Block pellet;
    GameScore score;
    GameLives lives;

    
    public Eater(IMap map, GameScore score, GameLives lives) {
        this.map = map;
        this.score = score;
        this.lives = lives;
    }

    private boolean tryEat(Pellet pellet, int points) {
        if (pellet.isEaten())
            return false;
        
        pellet.setEaten(true);
        pellet.setImage(null);
        score.addScore(points);
        // System.out.println("Pellet eaten! Score: " + score.getScore());
        return true;
    }

    public void eatPellet(Pellet pellet) {
        tryEat(pellet, 10); 
    }

    public void eatBigPellet(Pellet pellet) {
        if (!tryEat(pellet, 20)) 
            return;
        // set the state of the ghosts to frightened"
    }

    public void eatFruit(Pellet fruit) {
        if (!tryEat(fruit, 20)) 
            return;
        // set the state of the ghosts to frightened"
    }
    public void eatGhost(Pellet ghost) {
        if (!tryEat(ghost, 200)) 
            return;
        // set the state of the ghosts to frightened"
    }
}
