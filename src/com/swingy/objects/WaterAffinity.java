package com.swingy.objects;

public class WaterAffinity extends Affinity{
    public WaterAffinity(int level){
        updateStats(level);
    }

    public void updateStats(int level){
        this.setAttackPoints(4 * level * 1.75);
        this.setDefencePoints(8 * level * 2);
        this.setHitPoints(4 * level * 1.5);
        this.setCounterChance(0.6 * level * 2);
    }
}
