package com.swingy.battle;

import com.swingy.artifacts.Artifact;
import com.swingy.interfaces.Fighter;
import com.swingy.metrics.*;
import com.swingy.util.NumberHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FighterMetrics implements Fighter {

    //FighterManager Defined Variables
    protected String _name;
    protected LinkedHashMap<String, FighterBaseStats> _affinities = new LinkedHashMap<String, FighterBaseStats>();
    protected ArrayList<Artifact> artifacts;

    //System Defined Variables
    protected long _id = 0;
    protected Level _level = new Level();
    protected double _damage = 0;

    protected String easterEgg = "NO EGGS HERE";

    public FighterMetrics(String affinity){
        if (affinity.equalsIgnoreCase("NINJA")) {
            if (!_affinities.containsKey(affinity))
                _affinities.put("NINJA", new NinpoFighterBaseStats(1));
        }
        else if (affinity.equalsIgnoreCase("ZOMBO")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("ZOMBO", new ScourgeFighterBaseStats(1));
        }
        else if (affinity.equalsIgnoreCase("DINO")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("DINO", new BeastFighterBaseStats(1));
        }
        else if (affinity.equalsIgnoreCase("ROBO")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("ROBO", new MechaFighterBaseStats(1));
        }
        artifacts = new ArrayList<>();
    }

    public FighterMetrics(String name, String affinity){
        this._name = name;
        if (affinity.equalsIgnoreCase("NINJA")) {
            if (!_affinities.containsKey(affinity))
                _affinities.put("NINJA", new NinpoFighterBaseStats(1));
        }
        else if (affinity.equalsIgnoreCase("ZOMBO")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("ZOMBO", new ScourgeFighterBaseStats(1));
        }
        else if (affinity.equalsIgnoreCase("DINO")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("DINO", new BeastFighterBaseStats(1));
        }
        else if (affinity.equalsIgnoreCase("ROBO")){
            if (!_affinities.containsKey(affinity))
                _affinities.put("ROBO", new MechaFighterBaseStats(1));
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

    public void setID(long _id) {
        this._id = _id;
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

    public void setEasterEgg(String easterEgg) {
        this.easterEgg = easterEgg;
    }

    public String getEasterEgg() {
        return easterEgg;
    }

    public void gainExperience(){
        this._level.increaseExperience(250);
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
        this._damage += NumberHelper.round(enemyAttackPoints / myDefencePoints, 2);
        if (this._damage < 0)
            this._damage = 0;
        if (this.getFighterStats().getHitPoints() <= this._damage)
            return true;
        else
            return false;

    }

    public void updateAffinities(){
        for (HashMap.Entry<String, FighterBaseStats> fbs : _affinities.entrySet()){
            fbs.getValue().setLevel(this._level.getLevel());
        }
    }

    @Override
    public boolean counter(double enemyAttackPoints, double myDefencePoints) {
        updateAffinities();
        if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("ZOMBO")){
            this._damage -= NumberHelper.round((enemyAttackPoints / myDefencePoints), 2) + NumberHelper.round((enemyAttackPoints / myDefencePoints / 100 * 13), 2);
            if (this._damage < 0)
                this._damage = 0;
        }
        else if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("NINJA")){
            NinpoFighterBaseStats fireAffinity = (NinpoFighterBaseStats)this._affinities.entrySet().iterator().next().getValue();
            fireAffinity.setBonusDamage(fireAffinity.getBonusDamage() + NumberHelper.round((enemyAttackPoints / myDefencePoints), 2) + NumberHelper.round(((this._damage) * 2), 2));
            this._affinities.replace("NINJA", fireAffinity);
        }
        else if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("DINO"))
            return true;
        else if (this._affinities.entrySet().iterator().next().getKey().equalsIgnoreCase("ROBO"))
            return true;
        return false;
    }

    @Override
    public FighterBaseStats getFighterStats() {
        updateAffinities();
        FighterBaseStats fighterBaseStats = new FighterBaseStats();
        //GetBaseStats
        for (HashMap.Entry<String, FighterBaseStats> a : _affinities.entrySet()){
            if (a.getKey().equalsIgnoreCase("NINJA")){
                NinpoFighterBaseStats fireAffinity = (NinpoFighterBaseStats)a.getValue();
                fighterBaseStats.setAttackPoints(NumberHelper.round(a.getValue().getAttackPoints() + fireAffinity.getBonusDamage(), 0));
            }
            else
                fighterBaseStats.setAttackPoints(a.getValue().getAttackPoints());
            fighterBaseStats.setDefencePoints(a.getValue().getDefencePoints());
            fighterBaseStats.setHitPoints(a.getValue().getHitPoints());
            fighterBaseStats.setCounterChance(a.getValue().getCounterChance());
            break;
        }
        //Add Additional stats from artifacts
        for (Artifact a : artifacts){
            fighterBaseStats.setAttackPoints((fighterBaseStats.getAttackPoints() + a.getAttackBoost()));
            fighterBaseStats.setDefencePoints((fighterBaseStats.getDefencePoints() + a.getDefenceBoost()));
            fighterBaseStats.setHitPoints((fighterBaseStats.getHitPoints() + a.getHitPointsBoost()));
            fighterBaseStats.setCounterChance((fighterBaseStats.getCounterChance() + a.getCounterBoost()));
        }
        return fighterBaseStats;
    }

    public String toString() {
        updateAffinities();
        String toReturn = "Fighter : " + this._name
                + "\nLevel : " + this._level.getLevel()
                + "\nExperience : " + this._level.getExperience()
                + "\nHP : " + this.getFighterStats().getHitPoints()
                +"\nAP : " + this.getFighterStats().getAttackPoints()
                + "\nDP : " + this.getFighterStats().getDefencePoints()
                +"\nCC : " + this.getFighterStats().getCounterChance() + "%";
        return toReturn;
    }

    public ArrayList<String> toStringArray() {
        updateAffinities();
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add(this._name);
        toReturn.add("Level : " + this._level.getLevel());
        toReturn.add("Experience : " + this._level.getExperience());
        toReturn.add("HP : " + this.getFighterStats().getHitPoints());
        toReturn.add("AP : " + this.getFighterStats().getAttackPoints());
        toReturn.add("DP : " + this.getFighterStats().getDefencePoints());
        toReturn.add("CC : " + this.getFighterStats().getCounterChance() + "%");
        return toReturn;
    }
}
