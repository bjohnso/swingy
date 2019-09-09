package com.swingy.input;

import java.util.Scanner;
import java.util.concurrent.Callable;

public class InputTimer implements Callable{

    @Override
    public Object call() throws Exception {
        Thread.sleep(100);
        return null;
    }

}
