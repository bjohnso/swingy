package com.swingy.battle;

import com.swingy.artifacts.Artifact;
import com.swingy.interfaces.Fighter;
import com.swingy.units.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FighterMetrics implements Fighter {

    //FighterManager Defined Variables
    protected String _name;
    protected LinkedHashMap<String, FighterBaseStats> _affinities = new LinkedHashMap<>();
    protected ArrayList<Artifact> artifacts;

    //System Defined Variables
    protected long _id = 0;
    protected Level _level = new Level();
    protected double _damage = 0;
    protected int _idCounter = 0;

    public FighterMetrics(String name, String affinity){
        _name = name;
        if (affinity.equalsIgnoreCase("FIRE")) {
            if (!_affinities.containsKey(affinity))
                _affinities.put("FIRE", new FireFighterBaseStats(10));
        }
        else if (affinity.equalsIgnoreCase("WATER")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("WATER", new WaterFighterBaseStats(10));
        }
        else if (affinity.equalsIgnoreCase("EARTH")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("EARTH", new EarthFighterBaseStats(10));
        }
        artifacts = new ArrayList<>();
    }

    public void setDamage(double _damage) {
        this._damage = _damage;
    }

    public String getName(){
        return _name;
    }

    public HashMap<String, FighterBaseStats> getAffinities(){
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

    public void setAffinities(LinkedHashMap<String, FighterBaseStats> affinities){
        _affinities = affinities;
    }

    public ArrayList<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(ArrayList<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public void addArtifact(Artifact artifact){
        this.artifacts.add(artifact);
    }

    public void removeArtifact(Artifact artifact){
        this.artifacts.remove(artifact);
    }

    public double getDamage(){ return _damage; }

    @Override
    public boolean attack() {
        return true;
    }

    @Override
    public boolean defend(FighterMetrics attacker, FighterMetrics defender) {
        Double rand = Math.random() * 101;
        Double defenceChance = this.getFighterStats().getCounterChance();
        if (rand < defenceChance)
            return true;
        else
            return false;
    }

    @Override
    public boolean takeDamage(double enemyAttackPoints, double myDefencePoints) {
        this._damage += enemyAttackPoints / myDefencePoints;
        if (this.getFighterStats().getHitPoints() <= this._damage)
            return true;
        else
            return false;

    }

    @Override
    public boolean counter(double enemyAttackPoints, double myDefencePoints) {
        if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("WATER")){
            this._damage -= (enemyAttackPoints / myDefencePoints) + (enemyAttackPoints / myDefencePoints / 100 * 13);
        }
        else if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("FIRE")){
            FireFighterBaseStats fireAffinity = (FireFighterBaseStats)this._affinities.entrySet().iterator().next().getValue();
            fireAffinity.setBonusDamage(fireAffinity.getBonusDamage() + (enemyAttackPoints / myDefencePoints) + (this._damage / 100 * 25));
            this._affinities.replace("FIRE", fireAffinity);
        }
        else if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("EARTH"))
            return true;
        return false;
    }

    @Override
    public FighterBaseStats getFighterStats() {
        FighterBaseStats fighterBaseStats = new FighterBaseStats();
        //GetBaseStats
        for (HashMap.Entry<String, FighterBaseStats> a : _affinities.entrySet()){
            if (a.getKey().equalsIgnoreCase("FIRE")){
                FireFighterBaseStats fireAffinity = (FireFighterBaseStats)a.getValue();
                fighterBaseStats.setAttackPoints(fighterBaseStats.getAttackPoints() + fireAffinity.getBonusDamage());
            }
            else
                fighterBaseStats.setAttackPoints((int)(fighterBaseStats.getAttackPoints() + a.getValue().getAttackPoints()));
            fighterBaseStats.setDefencePoints((int)(fighterBaseStats.getDefencePoints() + a.getValue().getDefencePoints()));
            fighterBaseStats.setHitPoints((int)(fighterBaseStats.getHitPoints() + a.getValue().getHitPoints()));
            fighterBaseStats.setCounterChance((int)(fighterBaseStats.getCounterChance() + a.getValue().getCounterChance()));
        }
        //Add Additional stats from artifacts
        for (Artifact a : artifacts){
            fighterBaseStats.setAttackPoints((int)(fighterBaseStats.getAttackPoints() + a.getAttackBoost()));
            fighterBaseStats.setDefencePoints((int)(fighterBaseStats.getDefencePoints() + a.getDefenceBoost()));
            fighterBaseStats.setHitPoints((int)(fighterBaseStats.getHitPoints() + a.getHitPointsBoost()));
            fighterBaseStats.setCounterChance((int)(fighterBaseStats.getCounterChance() + a.getCounterBoost()));
        }

        return fighterBaseStats;
    }

    public String toString() {
        String toReturn = "Fighter : " + this._name
                + "\nLevel : " + this._level.getLevel()
                + "\nHP : " + this.getFighterStats().getHitPoints()
                +"\nAP : " + this.getFighterStats().getAttackPoints()
                + "\nDP : " + this.getFighterStats().getDefencePoints()
                +"\nCC : " + this.getFighterStats().getCounterChance() + "%";
        return toReturn;
    }

    public ArrayList<String> toStringArray() {
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add(this._name);
        toReturn.add("Level : " + this._level.getLevel());
        toReturn.add("HP : " + this.getFighterStats().getHitPoints());
        toReturn.add("AP : " + this.getFighterStats().getAttackPoints());
        toReturn.add("DP : " + this.getFighterStats().getDefencePoints());
        toReturn.add("CC : " + this.getFighterStats().getCounterChance() + "%");
        return toReturn;
    }
}
