package com.example;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameTimer {  
    // Interface for tasks that can report completion
    public List<IGameTask> functionList = new ArrayList<>();

    public void addFunctionToList(IGameTask function) {
        // Remove any existing task with the same name
        functionList.removeIf(f -> f.getName().equals(function.getName()));
        functionList.add(function);
    }

    public void runFunctionList(List<IGameTask> functionList) {
        List<IGameTask> copy = new ArrayList<>(functionList);
        Iterator<IGameTask> iterator = copy.iterator();
        while (iterator.hasNext()) {
            IGameTask function = iterator.next();
            if (function.run()) {
                this.functionList.remove(function); // Remove from the original list
            }
        }
    }

    public void gameTimerReset(){
        functionList = new ArrayList<>();
    }

    // Example usage for delayed execution
    public static IGameTask atTimeRunFunction(String name, long startTime, long wantedDuration, Runnable function) {
        return new IGameTask() {
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
        };
    }
}

