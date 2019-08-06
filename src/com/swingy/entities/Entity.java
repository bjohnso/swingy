package com.swingy.entities;

import com.swingy.rendering.textures.Sprite;
import com.swingy.states.GameState;

import java.awt.*;

public abstract class Entity {

    protected double x, y;
    protected Sprite sprite;
    protected GameState state;

    public Entity(Sprite sprite, double x, double y, GameState state){
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.state = state;
        state.addEntity(this);
    }

    public abstract void tick();

    public void render(Graphics graphics){
        sprite.render(graphics, x, y);
    }
}
