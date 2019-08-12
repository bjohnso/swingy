package com.swingy;

import com.swingy.view.Swingy;

public class Main {

    public static void main(String[] args) {
        /*ThreadPool pool = new ThreadPool(3);
        BattleState battleGame = new BattleState();
        MusicPlayer player = new MusicPlayer("Battle");
        pool.runTask(player);
        pool.runTask(battleGame);
        pool.runTask(BattleEngine.getBattleEngine());*/

        Swingy swingy = new Swingy();
    }
}
