package com.swingy.objects;

public class EarthAffinity extends Affinity{
    public EarthAffinity(int level){
        this.setAttackPoints(4 * level * 1.5);
        this.setDefencePoints(6 * level * 1.75);
        this.setHitPoints(8 * level * 2);
    }

    public void updateStats(int level){
        this.setAttackPoints(4 * level * 1.5);
        this.setDefencePoints(6 * level * 1.75);
        this.setHitPoints(8 * level * 2);
    }
}
