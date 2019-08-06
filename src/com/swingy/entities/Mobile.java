package com.swingy.entities;

import com.swingy.rendering.textures.Sprite;
import com.swingy.states.GameState;

public abstract class Mobile extends Entity {

    protected double dx, dy;

    public Mobile(Sprite sprite, double x, double y, GameState state) {
        super(sprite, x, y, state);
    }

    @Override
    public void tick(){
        move();
    }

    public void move(){
        x += dx;
        y += dy;
    }
}
