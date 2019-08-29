package com.swingy.console;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Scanner;

public class ConsoleInputListener implements Runnable {

    private PropertyChangeSupport support;

    private boolean running;
    private Scanner scanner;
    private String userInput;

    public ConsoleInputListener(){
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    public void updateUserInput(String userInput){
        this.userInput = userInput;
        support.firePropertyChange("UserInput", "", userInput);
    }

    @Override
    public void run() {
        start();
        while(running) {
            scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                stop();
                updateUserInput(scanner.next());
            }
        }
    }

    private void start(){
        if (running)
            return;
        running = true;
        new Thread(this, "ConsoleInputListener-Thread").start();
    }

    public void stop(){
        if (!running)
            return;
        running = false;
    }
}
