package com.swingy;

import com.swingy.audio.MusicPlayer;
import com.swingy.game.BattleEngine;
import com.swingy.handlers.ThreadPool;
import com.swingy.heroes.Hero;
import com.swingy.view.BattleGame;
import com.swingy.view.Swingy;

public class Main {

    public static void main(String[] args) {
        /*ThreadPool pool = new ThreadPool(3);
        BattleGame battleGame = new BattleGame();
        MusicPlayer player = new MusicPlayer("Battle");
        pool.runTask(player);
        pool.runTask(battleGame);
        pool.runTask(BattleEngine.getBattleEngine());*/

        Swingy swingy = new Swingy();
    }
}
