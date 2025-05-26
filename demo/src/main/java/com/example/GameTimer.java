package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameTimer {
    // Interface for tasks that can report completion
    public interface GameTask {
        boolean run(); // return true if done, false if should stay in list
        String getName();
        long getStartTime();
    }

    public static class NamedGameTask implements GameTask {
        private final String name;
        private final long startTime;
        private final GameTask task;

        public NamedGameTask(String name, long startTime, GameTask task) {
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

    public List<GameTask> functionList = new ArrayList<>();

    public void addFunctionToList(GameTask function) {
        // Remove any existing task with the same name
        functionList.removeIf(f -> f.getName().equals(function.getName()));
        functionList.add(function);
    }

    public void runFunctionList(List<GameTask> functionList) {
        List<GameTask> copy = new ArrayList<>(functionList);
        Iterator<GameTask> iterator = copy.iterator();
        while (iterator.hasNext()) {
            GameTask function = iterator.next();
            if (function.run()) {
                this.functionList.remove(function); // Remove from the original list
            }
        }
    }

    public void gameTimerReset(){
        functionList = new ArrayList<>();
    }

    // Example usage for delayed execution
    public static GameTask atTimeRunFunction(String name, long startTime, long wantedDuration, Runnable function) {
        return new NamedGameTask(name, startTime, new GameTask() {
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
        });
    }
}

