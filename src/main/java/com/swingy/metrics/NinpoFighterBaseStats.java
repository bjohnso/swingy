package com.swingy.metrics;

import com.swingy.util.NumberHelper;

public class NinpoFighterBaseStats extends FighterBaseStats {

    private double _bonusDamage = 0;

    public NinpoFighterBaseStats(int level){
        this.level = level;
        updateStats();
    }

    public void updateStats(){
        this.setAttackPoints(NumberHelper.round((12 * this.level * 2), 2));
        this.setDefencePoints(NumberHelper.round((6 * this.level * 1.5), 2));
        this.setHitPoints(NumberHelper.round((6 * this.level * 1.5), 2));
        this.setCounterChance(NumberHelper.round((24.0 * this.level * 1.5), 2));
    }

    public double getBonusDamage() {
        return _bonusDamage;
    }

    public void setBonusDamage(double bonusDamage) {
        this._bonusDamage = bonusDamage;
    }
}
