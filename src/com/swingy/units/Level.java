package com.swingy.units;

public class Level {

    private int level = 1;
    private double experience = 1000;

    public Level(){

    }

    public Level(double experience){
        this.experience = experience;
        setLevel();
    }

    private void setLevel() {
        double _dup = 0;

        int counter = this.level;
        while (_dup < this.experience){
            _dup = (counter * 1000) + ((counter - 1) * (counter -1)) * 450;
            if (this.experience <= _dup) {
                this.level = counter;
                break;
            }
            counter++;
        }
    }

    public double getExperience() {
        return this.experience;
    }

    public int getLevel() {
        return this.level;
    }

    public void increaseExperience(double experience){
        this.experience += experience;
        setLevel();
    }

    public void setExperience(double experience) {
        this.experience = experience;
        setLevel();
    }
}
