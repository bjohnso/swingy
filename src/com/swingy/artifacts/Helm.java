package com.swingy.artifacts;

import com.swingy.id.ID;

public class Helm extends Artifact{

    public Helm(String type, ID id, int level) {
        super(type, id, level);
        this.setHitPointsBoost(level * 3.3);
    }
}
