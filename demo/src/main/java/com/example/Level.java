package com.example;

public class Level implements ILevel {
    private int level;

    /**
     * Constructor for Level class.
     * Initializes the level with default value of 1.
     */
    public Level() {
        this.level = 1;
    }

    /**
     * Gets the current level.
     *
     * @return The current level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * increase level by 1.
     *
     * @param level The new level to set.
     */
    public void incrementLevel() {
        this.level++;
    }

    /**
     * resets the level to 1.
     */
    public void resetLevel() {
        this.level = 1;
    }
}