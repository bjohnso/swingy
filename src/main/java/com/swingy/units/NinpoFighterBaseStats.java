package com.swingy.units;

import com.swingy.util.NumberHelper;

public class NinpoFighterBaseStats extends FighterBaseStats {

    private double _bonusDamage = 0;

    public NinpoFighterBaseStats(int level){
        this.level = level;
        updateStats();
    }

    public void updateStats(){
        this.setAttackPoints(NumberHelper.round((9 * this.level * 1.25), 2));
        this.setDefencePoints(NumberHelper.round((5 * this.level * 1.25), 2));
        this.setHitPoints(NumberHelper.round((5 * this.level * 1.25), 2));
        this.setCounterChance(NumberHelper.round((8.0 * this.level * 1.25), 2));
    }

    public double getBonusDamage() {
        return _bonusDamage;
    }

    public void setBonusDamage(double bonusDamage) {
        this._bonusDamage = bonusDamage;
    }
}
