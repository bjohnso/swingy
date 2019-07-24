package com.swingy.heroes;

import java.util.ArrayList;

public class Hero {

    //Player Defined Variables
    protected String _name;
    protected ArrayList<String> _affinities = new ArrayList<>();
    //System Defined Variables
    protected long _id = 0;
    protected int _level = 0;
    protected int _experience = 0;
    protected int idCounter = 0;

    public Hero(){

    }

    public Hero(String name, String affinity){
        _name = name;
        if (!_affinities.contains(affinity))
            _affinities.add(affinity);
    }

    public String getName(){
        return _name;
    }

    public ArrayList<String> getAffinities(){
        return _affinities;
    }

    public long getID(){
        return _id;
    }

    public int getExperience(){
        return _experience;
    }

    public int getLevel(){
        return _level;
    }

    public void setName(String name){
        _name = name;
    }

    public void setAffinities(ArrayList<String> affinities){
        _affinities = affinities;
    }

}
