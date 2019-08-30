package com.swingy.console;

import com.swingy.Main;
import com.swingy.states.State;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Console {

    public static Console console;

    private ConsoleInputListener consoleInputListener;
    private ExecutorService executorService;
    private Future<String> future;

    static { console = new Console(); }

    private Console() {
        consoleInputListener = new ConsoleInputListener();
        executorService = Executors.newSingleThreadExecutor();
        future = executorService.submit(consoleInputListener);
    }

    public String tick() throws ExecutionException, InterruptedException {
        String toReturn = null;
        if (future.isDone()){
            toReturn = future.get();
            future = executorService.submit(consoleInputListener);
        }
        return toReturn;
    }

    public void userSelection(State state){
        switch (state.getName()){
            case "menu":
                menuOptions();
                break;
            case "character-new":
                characterCreationOptions();
                break;
            case "character-load":
                characterSelectionOptions();
                break;
            case "settings":
                settings();
                break;
            case "map":
                map();
                break;
            case "battle":
                break;
        }
    }

    public void menuOptions(){
        System.out.println("\nSWINGY\n1. NEW GAME\n2. LOAD GAME\n3. SETTINGS\n4. EXIT");
    }

    public void characterCreationOptions(){
        System.out.println("\nSELECT YOUR FIGHTER\n1. NEXT\n2. PLAY\n3. BACK");
    }

    public void characterSelectionOptions(){ System.out.println("\nLOAD FIGHTER\n1. NEXT\n2. DELETE\n3. PLAY\n4. BACK"); }

    public void settings(){
        System.out.println("\nSETTINGS\n1. INCREASE MUSIC VOLUME\n2. DECREASE MUSIC VOLUME\n3. RESET DATABASE\n4. BACK");
    }

    public void map() {
        System.out.println("\n1. UP\n2. DOWN\n3. LEFT\n4. RIGHT\n5. QUIT");
    }

}
