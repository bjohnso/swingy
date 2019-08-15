package com.swingy.artifacts;

import com.swingy.id.ID;

public class Armor extends Artifact{
    public Armor(String type, ID id, int level) {
        super(type, id, level);
        this.setDefenceBoost(level * 3.3);
    }
}
