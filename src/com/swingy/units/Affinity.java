package com.swingy.units;

public class Affinity {
    protected double _attackPoints = 0;
    protected double _defencePoints = 0;
    protected double _hitPoints = 0;
    protected double _counterChance = 0;

    public Affinity(){

    }

    public double getAttackPoints(){
        return _attackPoints;
    }

    public double getDefencePoints(){
        return _defencePoints;
    }

    public double getHitPoints(){
        return _hitPoints;
    }

    public double getCounterChance(){
        return _counterChance;
    }

    public void setCounterChance(double defenceChance){
        _counterChance = defenceChance;
    }

    public void setAttackPoints(double attackPoints){
        _attackPoints = attackPoints;
    }

    public void setDefencePoints(double defencePoints){
        _defencePoints = defencePoints;
    }

    public void setHitPoints(double hitPoints){
        _hitPoints = hitPoints;
    }
}
