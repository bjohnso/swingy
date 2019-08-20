package com.swingy;

import com.swingy.audio.MusicPlayer;
import com.swingy.database.SwingyDB;
import com.swingy.handlers.ThreadPool;
import com.swingy.view.Swingy;

import java.sql.SQLException;

import static com.swingy.database.SwingyDB.swingyDB;

public class Main {

    public static ThreadPool pool;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        swingyDB.createDB();

        pool = new ThreadPool(3);
        Swingy swingy = new Swingy();
        MusicPlayer musicPlayer = new MusicPlayer("Battle");

        pool.runTask(musicPlayer);
        pool.runTask(swingy);
    }
}
