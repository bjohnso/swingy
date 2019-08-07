package com.swingy.map;

import com.swingy.rendering.textures.Sprite;

import java.awt.*;

public class Tile {

    protected float x, y;
    protected Sprite sprite;
    protected boolean solid;

    public Tile(float x, float y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.solid = true;
    }

    public void render(Graphics graphics){
        sprite.render(graphics, x, y);
    }
}
