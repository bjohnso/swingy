package com.swingy.artifacts;

import com.swingy.id.ID;

public class Weapon extends Artifact{

    public Weapon(ID id, int level) {
        super(id, level);
        this.setAttackBoost(level * 3.3);
        this.setCounterBoost(level * 0.1);
    }
}
