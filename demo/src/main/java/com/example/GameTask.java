package com.example;

public class GameTask implements IGameTask {
    private final String name;
    private final long startTime;
    private final long wantedDuration;
    private final Runnable function;

    public GameTask(String name, long startTime, long wantedDuration, Runnable function) {
        this.name = name;
        this.startTime = startTime;
        this.wantedDuration = wantedDuration;
        this.function = function;
    }

    @Override
    public boolean run() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - startTime >= wantedDuration) {
            function.run();
            return true; // Done, remove from list
        }
        return false; // Not done, keep in list
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }
}