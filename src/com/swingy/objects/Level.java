package com.swingy.objects;

public class Level {

    private int _level = 1;
    private double _experience = 1000;

    public Level(){

    }

    public Level(double experience){
        _experience = experience;
    }

    public void gainExperience(double experience){
        _experience += experience;
        double _dup = 0;

        int counter = _level;
        while (_dup < _experience){
            _dup = (counter * 1000) + ((counter - 1) * (counter -1)) * 450;
            if (_experience <= _dup) {
                _level = counter;
                break;
            }
            counter++;
        }
    }

    public double getExperience() {
        return _experience;
    }

    public int getLevel() {
        return _level;
    }

    public void setExperience(double experience) {
        _experience = experience;
    }
}
