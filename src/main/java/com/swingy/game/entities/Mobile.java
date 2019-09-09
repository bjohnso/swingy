package com.swingy.game.entities;

import com.swingy.metrics.Coordinate;
import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Texture;
import com.swingy.states.State;

import java.awt.*;

public abstract class Mobile extends Entity {

    protected double dx, dy;
    protected Animation animation;
    protected boolean moving = false;
    private Coordinate mobileID;

    public Mobile(Texture sprite, double x, double y, State state, Animation animation) {
        super(sprite, x, y, state);
        this.animation = animation;
    }

    public Mobile(State state, Animation animation) {
        super(state);
        this.animation = animation;
    }

    @Override
    public void tick(){
        move();
        if (animation != null)
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

    public void setMobileID(Coordinate id) {
        this.mobileID = id;
    }

    public Coordinate getMobileID() {
        return mobileID;
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

    public Animation getAnimation() {
        return this.animation;
    }

    @Override
    protected void finalize() throws Throwable {
        this.animation = null;
        super.finalize();
    }
}
