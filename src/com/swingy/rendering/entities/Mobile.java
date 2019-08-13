package com.swingy.rendering.entities;

import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.states.State;

import java.awt.*;

public abstract class Mobile extends Entity {

    protected double dx, dy;
    protected Animation animation;
    protected boolean moving = false;
    private int id;

    public Mobile(Sprite sprite, double x, double y, State state, Animation animation) {
        super(sprite, x, y, state);
        this.animation = animation;
    }

    @Override
    public void tick(){
        move();
        animation.tick();
    }

    @Override
    public void render(Graphics graphics){
        if (animation != null) {
            animation.render(graphics, x, y);
            return;
        }
        super.render(graphics);
    }

    protected void move(){
        x += dx;
        y += dy;
        dx = 0;
        dy = 0;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void moveX(double x){
        this.dx += x;
    }

    public void moveY(double y){
        this.dy += y;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}
