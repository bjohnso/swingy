package com.swingy.console;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ConsoleInputListener implements Callable {

    private boolean running;
    private Scanner scanner;

    private void start(){
        if (running)
            return;
        running = true;
    }

    public void stop(){
        if (!running)
            return;
        running = false;
    }

    @Override
    public Object call() throws Exception {
        start();
        while(running) {
            scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                stop();
                return scanner.next();
            }
        }
        return null;
    }
}
