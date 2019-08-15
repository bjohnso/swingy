package com.swingy.artifacts;

import com.swingy.id.ID;

public class Armor extends Artifact{
    public Armor(ID id, int level) {
        super(id, level);
        this.setDefenceBoost(level * 3.3);
    }
}
