package com.swingy.console;

import com.swingy.Main;
import com.swingy.states.State;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Console implements PropertyChangeListener {

    public static Console console;
    private String userInput;

    static { console = new Console(); }

    public String getUserInput(){
        return this.userInput;
    }

    private Console() {
    }

    public void tick(){
        userInput = null;
    }

    public void userSelection(State state){
        switch (state.getName()){
            case "menu":
                menuOptions();
                break ;
            case "character-new":
                characterCreationOptions();
                break;
            case "character-load":
                characterSelectionOptions();
                break;
            case "settings":
                settings();
                break;
        }
        ConsoleInputListener consoleInputListener = new ConsoleInputListener();
        consoleInputListener.addPropertyChangeListener(this);
        Main.pool.runTask(consoleInputListener);
    }

    public void menuOptions(){
        System.out.print("1. NEW GAME\n2. LOAD GAME\n3. SETTINGS\n4. EXIT");
    }

    public void characterCreationOptions(){
        System.out.println("1. NEXT\n2. PLAY\n3. BACK");
    }

    public void characterSelectionOptions(){
        System.out.println("1. NEXT\n2. DELETE\n3. PLAY\n4. BACK");
    }

    public void settings(){
        System.out.println("1. RESET DATABASE\n2. BACK");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("EVENT FIRED");
        if (evt.getPropertyName().equalsIgnoreCase("UserInput"))
            userInput = (String)evt.getNewValue();
    }
}
