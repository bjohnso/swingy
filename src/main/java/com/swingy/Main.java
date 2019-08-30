package com.swingy;

import com.swingy.audio.MusicPlayer;
import com.swingy.handlers.ThreadPool;
import com.swingy.game.Swingy;

import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static ThreadPool pool;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /*if (args.length > 0){
            if (args[0] == "-Console") {
                pool = new ThreadPool(4);
                Swingy swingy = new Swingy();
            }

        }
        else {*/
            //pool = new ThreadPool(3);
            Swingy swingy = new Swingy();
            MusicPlayer musicPlayer = new MusicPlayer("Battle");

            ExecutorService swingyThread = Executors.newSingleThreadExecutor();
            ExecutorService musicThread = Executors.newSingleThreadExecutor();

            swingyThread.execute(swingy);
            musicThread.execute(musicPlayer);

            //pool.runTask(musicPlayer);
            //pool.runTask(swingy);
        //}
        /*FighterMetrics ninpo = new FighterMetrics("Asuna", "NINJA");
        FighterMetrics beast = new FighterMetrics("Rex", "DINO");
        FighterMetrics scourge = new FighterMetrics("Odesa", "ZOMBO");
        FighterMetrics mecha = new FighterMetrics("Optimus", "ROBO");
        int winCount = 0;
        int balancedWinCount = 0;
        double averagePowerDifference = 0;

        for (int i = 0; i < 10; i++) {
            BattleEngine battleEngine = new BattleEngine();
            battleEngine.setChallenger(ninpo);
            battleEngine.setDefender(beast);
            int j = 1;
            while (!battleEngine.end) {
                battleEngine.battle(j);
                j *= -1;
            }
            winCount += battleEngine.winCount;
            balancedWinCount += battleEngine.balancedWinCount;
            averagePowerDifference += (int)battleEngine.averagePowerDifference;
        }
        System.out.println("Ninpo vs Beast");
        System.out.printf("WIN COUNT : %d | BALANCED WIN COUNT : %d | DIFFERENCE IN POWER : %f\n\n", winCount, balancedWinCount, averagePowerDifference/winCount);
        winCount = 0;
        balancedWinCount = 0;
        averagePowerDifference = 0;


        for (int i = 0; i < 10; i++) {
            BattleEngine battleEngine = new BattleEngine();
            battleEngine.setChallenger(beast);
            battleEngine.setDefender(scourge);
            int j = 1;
            while (!battleEngine.end) {
                battleEngine.battle(j);
                j *= -1;
            }
            winCount += battleEngine.winCount;
            balancedWinCount += battleEngine.balancedWinCount;
            averagePowerDifference += (int)battleEngine.averagePowerDifference;
        }
        System.out.println("Beast vs Scourge");
        System.out.printf("WIN COUNT : %d | BALANCED WIN COUNT : %d | DIFFERENCE IN POWER : %f\n\n", winCount, balancedWinCount, averagePowerDifference/winCount);
        winCount = 0;
        balancedWinCount = 0;
        averagePowerDifference = 0;

        for (int i = 0; i < 10; i++) {
            BattleEngine battleEngine = new BattleEngine();
            battleEngine.setChallenger(scourge);
            battleEngine.setDefender(mecha);
            int j = 1;
            while (!battleEngine.end) {
                battleEngine.battle(j);
                j *= -1;
            }
            winCount += battleEngine.winCount;
            balancedWinCount += battleEngine.balancedWinCount;
            averagePowerDifference += (int)battleEngine.averagePowerDifference;
        }
        System.out.println("Scourge vs Mecha");
        System.out.printf("WIN COUNT : %d | BALANCED WIN COUNT : %d | DIFFERENCE IN POWER : %f\n\n", winCount, balancedWinCount, averagePowerDifference/winCount);
        winCount = 0;
        balancedWinCount = 0;
        averagePowerDifference = 0;

        for (int i = 0; i < 10; i++) {
            BattleEngine battleEngine = new BattleEngine();
            battleEngine.setChallenger(mecha);
            battleEngine.setDefender(ninpo);
            int j = 1;
            while (!battleEngine.end) {
                battleEngine.battle(j);
                j *= -1;
            }
            winCount += battleEngine.winCount;
            balancedWinCount += battleEngine.balancedWinCount;
            averagePowerDifference += (int)battleEngine.averagePowerDifference;
        }
        System.out.println("Mecha vs Ninpo");
        System.out.printf("WIN COUNT : %d | BALANCED WIN COUNT : %d | DIFFERENCE IN POWER : %f\n\n", winCount, balancedWinCount, averagePowerDifference/winCount);
        winCount = 0;
        balancedWinCount = 0;
        averagePowerDifference = 0;

        for (int i = 0; i < 10; i++) {
            BattleEngine battleEngine = new BattleEngine();
            battleEngine.setChallenger(ninpo);
            battleEngine.setDefender(scourge);
            int j = 1;
            while (!battleEngine.end) {
                battleEngine.battle(j);
                j *= -1;
            }
            winCount += battleEngine.winCount;
            balancedWinCount += battleEngine.balancedWinCount;
            averagePowerDifference += (int)battleEngine.averagePowerDifference;
        }
        System.out.println("Ninpo vs Scourge");
        System.out.printf("WIN COUNT : %d | BALANCED WIN COUNT : %d | DIFFERENCE IN POWER : %f\n\n", winCount, balancedWinCount, averagePowerDifference/winCount);
        winCount = 0;
        balancedWinCount = 0;
        averagePowerDifference = 0;

        for (int i = 0; i < 10; i++) {
            BattleEngine battleEngine = new BattleEngine();
            battleEngine.setChallenger(mecha);
            battleEngine.setDefender(beast);
            int j = 1;
            while (!battleEngine.end) {
                battleEngine.battle(j);
                j *= -1;
            }
            winCount += battleEngine.winCount;
            balancedWinCount += battleEngine.balancedWinCount;
            averagePowerDifference += (int)battleEngine.averagePowerDifference;
        }
        System.out.println("Mecha vs Beast");
        System.out.printf("WIN COUNT : %d | BALANCED WIN COUNT : %d | DIFFERENCE IN POWER : %f\n\n", winCount, balancedWinCount, averagePowerDifference/winCount);
        winCount = 0;
        balancedWinCount = 0;
        averagePowerDifference = 0;*/

    }
}
