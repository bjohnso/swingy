package com.swingy.units;

public class HeroStats {

    private int _attackPoints;
    private int _defencePoints;
    private int _hitPoints;

    public HeroStats(){

    }
    
    public HeroStats(int attackPoints, int defencePoints, int hitPoints){
        _attackPoints = attackPoints;
        _defencePoints = defencePoints;
        _hitPoints = hitPoints;
    }

    public int getAttackPoints(){
        return _attackPoints;
    }

    public int getDefencePoints(){
        return _defencePoints;
    }

    public int getHitPoints(){
        return _hitPoints;
    }

    public void setAttackPoints(int attackPoints){
        _attackPoints = attackPoints;
    }

    public void setDefencePoints(int defencePoints){
        _defencePoints = defencePoints;
    }

    public void setHitPoints(int hitPoints){
        _hitPoints = hitPoints;
    }
}
