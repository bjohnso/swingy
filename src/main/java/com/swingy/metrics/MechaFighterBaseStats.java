package com.swingy.metrics;

import com.swingy.util.NumberHelper;

public class MechaFighterBaseStats extends FighterBaseStats{

    public MechaFighterBaseStats(int level){
        this.level = level;
        updateStats();
    }

    public void updateStats(){
        this.setAttackPoints(NumberHelper.round((8 * this.level * 2), 2));
        this.setDefencePoints(NumberHelper.round((8 * this.level * 2), 2));
        this.setHitPoints(NumberHelper.round((6 * this.level * 2), 2));
        this.setCounterChance(NumberHelper.round((12 * this.level * 1.5), 2));
    }

}
