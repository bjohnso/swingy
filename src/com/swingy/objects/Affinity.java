package com.swingy.objects;

public class Affinity {
    protected double _attackPoints = 0;
    protected double _defencePoints = 0;
    protected double _hitPoints = 0;
    protected int _heroLevel;

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
