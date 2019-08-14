package com.swingy;

import com.swingy.audio.MusicPlayer;
import com.swingy.handlers.ThreadPool;
import com.swingy.view.Swingy;

public class Main {

    public static ThreadPool pool;

    public static void main(String[] args) {

        pool = new ThreadPool(3);
        Swingy swingy = new Swingy();
        MusicPlayer musicPlayer = new MusicPlayer("Battle");

        pool.runTask(musicPlayer);
        pool.runTask(swingy);
    }
}
