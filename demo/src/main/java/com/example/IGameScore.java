package com.example;

public interface IGameScore {
    public GameScore getGameScore();
    public int getScore();
    public void addScore(int score);
    public void resetScore();
}
