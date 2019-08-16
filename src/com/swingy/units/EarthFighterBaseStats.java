package com.swingy.units;

public class EarthFighterBaseStats extends FighterBaseStats {

    public EarthFighterBaseStats(int level){
        this.level = level;
        updateStats();
    }

    public void updateStats(){
        this.setAttackPoints(3 * this.level * 1.5);
        this.setDefencePoints(6 * this.level * 1.75);
        this.setHitPoints(9 * this.level * 2);
        this.setCounterChance(0.3 * this.level * 1.75);
    }
}
