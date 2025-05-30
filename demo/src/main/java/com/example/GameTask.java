package com.example;

public class GameTask implements IGameTask {
    private final String name;
    private final long startTime;
    private final long wantedDuration;
    private final Runnable function;

    /**
     * Constructor for GameTask that initializes the task with a name, start time, wanted duration, and a function to execute.
     * 
     * @param name The name of the task.
     * @param startTime The time when the task was created (in milliseconds).
     * @param wantedDuration The duration after which the task should be executed (in milliseconds).
     * @param function The function to execute when the task is due.
     */
    public GameTask(String name, long startTime, long wantedDuration, Runnable function) {
        this.name = name;
        this.startTime = startTime;
        this.wantedDuration = wantedDuration;
        this.function = function;
    }

    /**
     * Executes the function if the current time exceeds the start time plus the wanted duration.
     * 
     * @return true if the function was executed and the task is done, false otherwise.
     */
    @Override
    public boolean runFunction() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - startTime >= wantedDuration) {
            function.run();
            return true; // Done, remove from list
        }
        return false; // Not done, keep in list
    }

    /**
     * Gets the name of the task.
     * 
     * @return The name of the task.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the start time of the task eg. when it was created.
     * 
     * @return The start time of the task in milliseconds.
     */
    @Override
    public long getStartTime() {
        return startTime;
    }
}