package com.swingy.entities;

import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.states.GameState;
import com.swingy.states.State;

import javax.swing.*;
import java.awt.*;

public abstract class Mobile extends Entity {

    protected double dx, dy;
    protected Animation animation;
    protected boolean moving = false;

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

    public void move(){
        x += dx;
        y += dy;
        dx = 0;
        dy = 0;
    }
}
