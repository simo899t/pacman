package com.example;


public class Eater {
    IMap map;
    Block pellet;
    GameScore score;

    
    public Eater(IMap map) {
        this.map = map;
        this.score = new GameScore();
    }

    private boolean tryEat(Pellet pellet, int points) {
        if (pellet.isEaten())
            return false;
        
        pellet.setEaten(true);
        pellet.setImage(null);
        score.addScore(points);
        System.out.println("Pellet eaten! Score: " + score.getScore());
        return true;
    }

    public void eatPellet(Pellet pellet) {
        tryEat(pellet, 10); 
    }

    public void eatBigPellet(Pellet pellet) {
        if (!tryEat(pellet, 100)) 
            return;
        // set the state of the ghosts to frightened"
    }
}
