package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameTimer {
    // Interface for tasks that can report completion
    public interface GameTask {
        boolean run(); // return true if done, false if should stay in list
    }

    public List<GameTask> functionList = new ArrayList<>();

    public void addFunctionToList(GameTask function) {
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

    // Example usage for delayed execution
    public static GameTask atTimeRunFunction(long startTime, long wantedDuration, Runnable function) {
        return new GameTask() {
            @Override
            public boolean run() {
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime >= wantedDuration) {
                    function.run();
                    return true; // Done, remove from list
                }
                return false; // Not done, keep in list
            }
        };
    }
}

