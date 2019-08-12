package com.swingy.entities;

import com.swingy.id.ID;
import com.swingy.input.KeyInput;
import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.states.GameState;
import com.swingy.states.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Player extends Mobile{

    private ID playerClass;
    private String playerClassName;
    private int level;
    private boolean isPlayer = false;

    public Player(Sprite sprite, double x, double y, State state, Animation animation) {
        super(sprite, x, y, state, animation);
    }

    @Override
    public void tick(){
        super.tick();
    }

    @Override
    public void render(Graphics graphics){
        super.render(graphics);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPlayerClass(ID playerClass) {
        this.playerClass = playerClass;
    }

    public ID getPlayerClass() {
        return playerClass;
    }

    public int getLevel() {
        return level;
    }

    public void setPlayerClassName(String playerClassName) {
        this.playerClassName = playerClassName;
    }

    public String getPlayerClassName() {
        return playerClassName;
    }

    public void moveX(double x){
        this.dx += x;
    }

    public void moveY(double y){
        this.dy += y;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
}
