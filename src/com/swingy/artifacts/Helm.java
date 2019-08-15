package com.swingy.artifacts;

import com.swingy.id.ID;

public class Helm extends Artifact{

    public Helm(ID id, int level) {
        super(id, level);
        this.setHitPointsBoost(level * 3.3);
    }
}
