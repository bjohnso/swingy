package com.swingy.units;

public class FireFighterBaseStats extends FighterBaseStats {

    private double _bonusDamage = 0;

    public FireFighterBaseStats(int level){
        this.level = level;
        updateStats();
    }

    public void updateStats(){
        this.setAttackPoints((9 * this.level * 2) + (_bonusDamage * 2));
        this.setDefencePoints(5 * this.level * 1.5);
        this.setHitPoints(6 * this.level * 1.75);
        this.setCounterChance(0.9 * this.level * 1.5);
    }

    public double getBonusDamage() {
        return _bonusDamage;
    }

    public void setBonusDamage(double bonusDamage) {
        this._bonusDamage = bonusDamage;
    }
}
