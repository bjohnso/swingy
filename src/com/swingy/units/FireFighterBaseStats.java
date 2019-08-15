package com.swingy.units;

public class FireFighterBaseStats extends FighterBaseStats {

    private double _bonusDamage = 0;

    public FireFighterBaseStats(int level){
        updateStats(level);
    }

    public void updateStats(int level){
        this.setAttackPoints((9 * level * 2) + (_bonusDamage * 2));
        this.setDefencePoints(5 * level * 1.5);
        this.setHitPoints(6 * level * 1.75);
        this.setCounterChance(0.9 * level * 1.5);
    }

    public double getBonusDamage() {
        return _bonusDamage;
    }

    public void setBonusDamage(double bonusDamage) {
        this._bonusDamage = bonusDamage;
    }
}
