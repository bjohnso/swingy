package com.swingy;

import com.swingy.audio.MusicPlayer;
import com.swingy.handlers.ThreadPool;
import com.swingy.game.Swingy;

import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        boolean gui;
        if (args.length == 0)
            gui = false;
        else if (args[0].equalsIgnoreCase("-gui"))
            gui = true;
        else
            gui = false;

        Swingy swingy = new Swingy(gui);
        MusicPlayer musicPlayer = new MusicPlayer("Battle");

        ExecutorService swingyThread = Executors.newSingleThreadExecutor();
        ExecutorService musicThread = Executors.newSingleThreadExecutor();

        swingyThread.execute(swingy);
        musicThread.execute(musicPlayer);
    }
}
