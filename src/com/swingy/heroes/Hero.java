package com.swingy.heroes;

import com.swingy.interfaces.Fighter;
import com.swingy.objects.*;

import java.util.ArrayList;

public class Hero implements Fighter {

    //Player Defined Variables
    protected String _name;
    protected ArrayList<Affinity> _affinities = new ArrayList<>();

    //System Defined Variables
    protected long _id = 0;
    protected Level _level = new Level();
    protected int _damage = 0;
    protected int _idCounter = 0;

    public Hero(){

    }

    public Hero(String name, String affinity){
        _name = name;
        if (affinity.equalsIgnoreCase("FIRE")) {
            if (!_affinities.contains(affinity))
                _affinities.add(new FireAffinity(1));
        }
        else if (affinity.equalsIgnoreCase("WATER")){
            if (!_affinities.contains(affinity))
                _affinities.add(new WaterAffinity(1));
        }
        else if (affinity.equalsIgnoreCase("EARTH")){
            if (!_affinities.contains(affinity))
                _affinities.add(new EarthAffinity(1));
        }
    }

    public String getName(){
        return _name;
    }

    public ArrayList<Affinity> getAffinities(){
        return _affinities;
    }

    public long getID(){
        return _id;
    }

    public Level getLevel(){
        return _level;
    }

    public void setName(String name){
        _name = name;
    }

    public void setAffinities(ArrayList<Affinity> affinities){
        _affinities = affinities;
    }

    public double getDamage(){
        return _damage;
    }

    @Override
    public boolean attack() {
        System.out.println(this._name + " : Attacked");
        return true;
    }

    @Override
    public boolean defend() {
        System.out.println(this._name + " : Defended");
        return false;
    }

    @Override
    public boolean takeDamage(double enemyAttackPoints, double myDefencePoints) {
        this._damage += enemyAttackPoints - myDefencePoints;
        if (this.getHeroStats().getHitPoints() == this._damage)
            return true;
        else
            return false;

    }

    @Override
    public Affinity getHeroStats() {
        Affinity affinity = new Affinity();

        for (Affinity a : _affinities){
            affinity.setAttackPoints(affinity.getAttackPoints() + a.getAttackPoints());
            affinity.setDefencePoints(affinity.getDefencePoints() + a.getDefencePoints());
            affinity.setHitPoints(affinity.getHitPoints() + a.getHitPoints());
        }

        return affinity;
    }
}
