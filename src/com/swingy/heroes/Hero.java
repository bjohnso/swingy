package com.swingy.heroes;

import com.swingy.interfaces.Fighter;
import com.swingy.objects.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Hero implements Fighter {

    //Player Defined Variables
    protected String _name;
    protected LinkedHashMap<String, Affinity> _affinities = new LinkedHashMap<>();

    //System Defined Variables
    protected long _id = 0;
    protected Level _level = new Level();
    protected double _damage = 0;
    protected int _idCounter = 0;

    public Hero(){

    }

    public Hero(String name, String affinity){
        _name = name;
        if (affinity.equalsIgnoreCase("FIRE")) {
            if (!_affinities.containsKey(affinity))
                _affinities.put("FIRE", new FireAffinity(10));
        }
        else if (affinity.equalsIgnoreCase("WATER")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("WATER", new WaterAffinity(10));
        }
        else if (affinity.equalsIgnoreCase("EARTH")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("EARTH", new EarthAffinity(10));
        }
    }

    public String getName(){
        return _name;
    }

    public HashMap<String, Affinity> getAffinities(){
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

    public void setAffinities(LinkedHashMap<String, Affinity> affinities){
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
    public boolean defend(Hero attacker, Hero defender) {
        Double rand = Math.random() * 101;
        Double defenceChance = this.getHeroStats().getCounterChance();
        if (rand < defenceChance)
            return true;
        else {
            System.out.println(this._name + " : Took Damage");
            return false;
        }
    }

    @Override
    public boolean takeDamage(double enemyAttackPoints, double myDefencePoints) {
        this._damage += enemyAttackPoints / myDefencePoints;
        if (this.getHeroStats().getHitPoints() <= this._damage)
            return true;
        else
            return false;

    }

    @Override
    public boolean counter(double enemyAttackPoints, double myDefencePoints) {
        if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("WATER")){
            this._damage -= (enemyAttackPoints / myDefencePoints) + (enemyAttackPoints / myDefencePoints / 100 * 13);
            System.out.println(this._name + " : Absorbed Attack, and Regenerated");
        }
        else if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("FIRE")){
            FireAffinity fireAffinity = (FireAffinity)this._affinities.entrySet().iterator().next().getValue();
            fireAffinity.setBonusDamage(fireAffinity.getBonusDamage() + (enemyAttackPoints / myDefencePoints) + (this._damage / 100 * 25));
            this._affinities.replace("FIRE", fireAffinity);
            System.out.println(this._name + " : Evaded Attack, and Powered Up");
        }
        else if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("EARTH")){
            System.out.println(this._name + " : Blocked Attack, and Inflicted Damage");
            return true;
        }
        return false;
    }

    @Override
    public Affinity getHeroStats() {
        Affinity affinity = new Affinity();

        for (HashMap.Entry<String, Affinity> a : _affinities.entrySet()){
            if (a.getKey().equalsIgnoreCase("FIRE")){
                FireAffinity fireAffinity = (FireAffinity)a.getValue();
                affinity.setAttackPoints(affinity.getAttackPoints() + fireAffinity.getBonusDamage());
            }
            else
                affinity.setAttackPoints(affinity.getAttackPoints() + a.getValue().getAttackPoints());
            affinity.setDefencePoints(affinity.getDefencePoints() + a.getValue().getDefencePoints());
            affinity.setHitPoints(affinity.getHitPoints() + a.getValue().getHitPoints());
            affinity.setCounterChance(affinity.getCounterChance() + a.getValue().getCounterChance());
        }

        return affinity;
    }
}
