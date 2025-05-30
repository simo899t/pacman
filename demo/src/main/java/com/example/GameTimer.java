package com.example;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameTimer {  
    private List<IGameTask> functionList = new ArrayList<>();

    /**
     * Adds a function to the list of functions to be executed.
     * If a function with the same name already exists, it will be replaced.
     *
     * @param function The function to add to the list.
     */
    public void addFunctionToList(IGameTask function) {
        functionList.removeIf(f -> f.getName().equals(function.getName()));
        functionList.add(function);
    }

    /**
     * Runs all functions in the provided list that are due to be executed.
     * Functions that are executed will be removed from the original functionList.
     *
     * @param functionList The list of functions to run.
     */
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

    /**
     * Getter for the function list that contains all scheduled functions.
     * 
     * @return The list of functions that are currently scheduled to be executed.
     */
    public List<IGameTask> getFunctionList() {
        return functionList;
    }

    /**
     * Resets the game timer by clearing the function list.
     * This is useful for starting a new game or resetting the timer due to deaths.
     */
    public void gameTimerReset(){
        functionList = new ArrayList<>();
    }

    /**
     * Creates a new game task that will run a function at a specified time.
     *
     * @param name The name of the task.
     * @param startTime The time when the task was created (in milliseconds).
     * @param wantedDuration The duration after which the task should be executed (in milliseconds).
     * @param function The function to execute when the task is due.
     * @return A new IGameTask instance that can be added to the function list.
     */
    public static IGameTask atTimeRunFunction(String name, long startTime, long wantedDuration, Runnable function) {
        return new GameTask(name, startTime, wantedDuration, function);
    }
}

