package com.swingy.units;

public class WaterFighterBaseStats extends FighterBaseStats {
    public WaterFighterBaseStats(int level){
        this.level = level;
        updateStats();
    }

    public void updateStats(){
        this.setAttackPoints(4 * this.level * 1.75);
        this.setDefencePoints(8 * this.level * 2);
        this.setHitPoints(4 * this.level * 1.5);
        this.setCounterChance(0.8 * this.level * 2);
    }
}
