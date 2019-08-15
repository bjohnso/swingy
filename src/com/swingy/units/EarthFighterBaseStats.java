package com.swingy.units;

public class EarthFighterBaseStats extends FighterBaseStats {

    public EarthFighterBaseStats(int level){
        updateStats(level);
    }

    public void updateStats(int level){
        this.setAttackPoints(3 * level * 1.5);
        this.setDefencePoints(6 * level * 1.75);
        this.setHitPoints(9 * level * 2);
        this.setCounterChance(0.3 * level * 1.75);
    }
}
