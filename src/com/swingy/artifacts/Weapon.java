package com.swingy.artifacts;

import com.swingy.id.ID;

public class Weapon extends Artifact{

    public Weapon(String type, ID id, int level) {
        super(type, id, level);
        this.setAttackBoost(level * 3.3);
        this.setCounterBoost(level * 0.1);
    }
}
