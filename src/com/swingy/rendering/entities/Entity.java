package com.swingy.rendering.entities;

import com.swingy.rendering.textures.Sprite;
import com.swingy.states.State;

import java.awt.*;

public abstract class Entity {

    protected double x, y;
    protected Sprite sprite;
    protected State state;

    public Entity(Sprite sprite, double x, double y, State state){
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.state = state;
        this.state.addEntity(this);
    }

    public Entity(State state){
        this.sprite = null;
        this.state = state;
    }

    public abstract void tick();

    public void render(Graphics graphics)
    {
        if (sprite != null)
            sprite.render(graphics, x, y);
    }
}
