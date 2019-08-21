package com.swingy.units;

import com.swingy.util.NumberHelper;

public class BeastFighterBaseStats extends FighterBaseStats {

    public BeastFighterBaseStats(int level){
        this.level = level;
        updateStats();
    }

    public void updateStats(){
        this.setAttackPoints(NumberHelper.round((6 * this.level * 1.75), 2));
        this.setDefencePoints(NumberHelper.round((6 * this.level * 2),2));
        this.setHitPoints(NumberHelper.round((9 * this.level * 2), 2));
        this.setCounterChance(NumberHelper.round((0.6 * this.level * 1.75), 2));
    }
}
