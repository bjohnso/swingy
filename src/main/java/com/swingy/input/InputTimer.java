package com.swingy.input;

import java.util.Scanner;
import java.util.concurrent.Callable;

public class InputTimer implements Callable{

    private boolean running;

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
        int i = 1000000000;
        while(i-- >= 0) {
        }
        stop();
        return null;
    }

}
