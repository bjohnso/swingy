package com.swingy.map;

import com.swingy.id.ID;
import com.swingy.rendering.textures.Sprite;

import java.awt.*;

public class Tile {

    protected float x, y;
    protected Sprite sprite;
    protected boolean solid;
    protected ID id;
    protected boolean isPlayer = false;

    public Tile(float x, float y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.solid = true;
    }

    public Tile(float x, float y, Sprite sprite, boolean isPlayer){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.solid = true;
        this.isPlayer = isPlayer;
    }

    public Tile(float x, float y, Sprite sprite, ID id){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.solid = true;
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void moveX(float x) {
        this.x += x;
    }

    public void moveY(float y) {
        this.y += y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID(){
        return this.id;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void render(Graphics graphics){
        sprite.render(graphics, x, y);
    }
}
