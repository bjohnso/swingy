package com.swingy;

import com.swingy.game.BattleEngine;
import com.swingy.heroes.Hero;

public class Main {

    public static void main(String[] args) {

        Hero a = new Hero("Asuna", "Water");
        Hero b = new Hero("Ragos", "Fire");
        Hero c = new Hero("Titan", "Earth");
        /*System.out.println(a.getName() + " : \n" + a.getHeroStats().getAttackPoints() + "\n" +
        a.getHeroStats().getDefencePoints() + "\n" +
        a.getHeroStats().getHitPoints() + "\n" +
        a.getDamage());

        System.out.println(b.getName() + " : \n" + b.getHeroStats().getAttackPoints() + "\n" +
                b.getHeroStats().getDefencePoints() + "\n" +
                b.getHeroStats().getHitPoints() + "\n" +
                b.getDamage());*/
        BattleEngine battleEngine = BattleEngine.getBattleEngine();
        battleEngine.battle(a,c);
    }
}
