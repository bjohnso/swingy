package com.swingy.objects;

public class FireAffinity extends Affinity {

    public FireAffinity(int level){
        this.setAttackPoints(8 * level * 2);
        this.setDefencePoints(4 * level * 1.5);
        this.setHitPoints(6 * level * 1.75);
    }

    public void updateStats(int level){
        this.setAttackPoints(8 * level * 2);
        this.setDefencePoints(4 * level * 1.5);
        this.setHitPoints(6 * level * 1.75);
    }
}
