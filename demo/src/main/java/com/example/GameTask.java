package com.example;

public class GameTask implements IGameTask {
    private final String name;
    private final long startTime;
    private final GameTask task;

    public GameTask(String name, long startTime, GameTask task) {
        this.name = name;
        this.startTime = startTime;
        this.task = task;
    }

    @Override
    public boolean run() {
        return task.run();
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