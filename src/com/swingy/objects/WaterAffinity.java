package com.swingy.objects;

public class WaterAffinity extends Affinity{
    public WaterAffinity(int level){
        this.setAttackPoints(4 * level * 1.5);
        this.setDefencePoints(8 * level * 2);
        this.setHitPoints(6 * level * 1.75);
    }

    public void updateStats(int level){
        this.setAttackPoints(4 * level * 1.5);
        this.setDefencePoints(8 * level * 2);
        this.setHitPoints(6 * level * 1.75);
    }
}
