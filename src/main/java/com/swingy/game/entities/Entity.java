package com.swingy.game.entities;

import com.swingy.rendering.textures.Texture;
import com.swingy.states.State;

import java.awt.*;

public abstract class Entity {

    protected double x, y;
    protected Texture sprite;
    protected State state;

    public Entity(Texture sprite, double x, double y, State state){
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

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    public Texture getSprite() {
        return sprite;
    }

    public abstract void tick();

    public void render(Graphics graphics)
    {
        if (sprite != null)
            sprite.render(graphics, x, y);
    }
}
