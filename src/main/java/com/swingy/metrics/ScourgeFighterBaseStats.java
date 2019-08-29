package com.swingy.metrics;

import com.swingy.util.NumberHelper;

public class ScourgeFighterBaseStats extends FighterBaseStats {
    public ScourgeFighterBaseStats(int level){
        this.level = level;
        updateStats();
    }

    public void updateStats(){
        this.setAttackPoints(NumberHelper.round((5 * this.level * 2), 2));
        this.setDefencePoints(NumberHelper.round((5 * this.level * 2), 2));
        this.setHitPoints(NumberHelper.round((5 * this.level * 2), 2));
        this.setCounterChance(NumberHelper.round((11.0 * this.level * 2), 2));
    }
}
