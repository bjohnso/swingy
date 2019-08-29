package com.swingy.metrics;

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
        int possible = 1;

        while ((possible * 1000) + (((possible - 1) * (possible - 1)) * 450) <= experience)
            possible++;
        level = --possible;
        if (level > 5)
            level = 5;
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
