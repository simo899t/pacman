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
        map.removePellet();
        score.addScore(points);
        // System.out.println("Pellet eaten! Score: " + score.getScore());
        return true;
    }

    public void eatPellet(Pellet pellet) {
        tryEat(pellet, pellet.getPoints());
    }

    public void eatBigPellet(Pellet pellet) {
        if (!tryEat(pellet, pellet.getPoints())) 
            return;
        // set the state of the ghosts to frightened"
    }

    public void eatFruit(Pellet fruit) {
        if (!tryEat(fruit, fruit.getPoints())) 
            return;
        // set the state of the ghosts to frightened"
    }
    public void eatGhost(Pellet ghost) {
        if (!tryEat(ghost, ghost.getPoints())) 
            return;
        // set the state of the ghosts to frightened"
    }
}
