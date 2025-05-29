package com.example;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameTimer {  
    // Interface for tasks that can report completion
    private List<IGameTask> functionList = new ArrayList<>();


    public void addFunctionToList(IGameTask function) {
        functionList.removeIf(f -> f.getName().equals(function.getName()));
        functionList.add(function);
    }

    public void runFunctionList(List<IGameTask> functionList) {
        List<IGameTask> copy = new ArrayList<>(functionList);
        Iterator<IGameTask> iterator = copy.iterator();
        while (iterator.hasNext()) {
            IGameTask function = iterator.next();
            if (function.runFunction()) {
                this.functionList.remove(function); // Remove from the original list
            }
        }
    }

    public List<IGameTask> getFunctionList() {
        return functionList;
    }

    public void gameTimerReset(){
        functionList = new ArrayList<>();
    }

    public static IGameTask atTimeRunFunction(String name, long startTime, long wantedDuration, Runnable function) {
        return new GameTask(name, startTime, wantedDuration, function);
    }
}

