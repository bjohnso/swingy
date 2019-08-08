package com.swingy.map;

import com.swingy.id.ID;
import com.swingy.rendering.textures.Sprite;

import java.awt.*;

public class Tile {

    protected float x, y;
    protected Sprite sprite;
    protected boolean solid;
    protected ID id;

    public Tile(float x, float y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.solid = true;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID(){
        return this.id;
    }

    public void render(Graphics graphics){
        sprite.render(graphics, x, y);
    }
}
