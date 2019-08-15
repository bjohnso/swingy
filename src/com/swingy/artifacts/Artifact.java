package com.swingy.artifacts;

import com.swingy.id.ID;

public class Artifact {

    protected ID id;
    protected int level;
    protected double _attackPoints = 0;
    protected double _defencePoints = 0;
    protected double _hitPoints = 0;
    protected double _counterChance = 0;

    public Artifact(ID id, int level){
        this.id = id;
        this.level = level;
    }

    protected ID getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

   public void setLevel(int level) {
        this.level = level;
    }

    public double getAttackBoost() {
        return _attackPoints;
    }

    public double getCounterBoost() {
        return _counterChance;
    }

    public double getDefenceBoost() {
        return _defencePoints;
    }

    public double getHitPointsBoost() {
        return _hitPoints;
    }

    protected void setAttackBoost(double _attackPoints) {
        this._attackPoints = _attackPoints;
    }

    protected void setCounterBoost(double _counterChance) {
        this._counterChance = _counterChance;
    }

    protected void setDefenceBoost(double _defencePoints) {
        this._defencePoints = _defencePoints;
    }

    protected void setHitPointsBoost(double _hitPoints) {
        this._hitPoints = _hitPoints;
    }

    protected void setId(ID id) {
        this.id = id;
    }
}
