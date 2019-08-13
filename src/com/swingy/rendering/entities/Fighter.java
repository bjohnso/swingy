package com.swingy.rendering.entities;

import com.swingy.id.ID;
import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.states.State;

import java.awt.*;

public class Fighter extends Mobile{

    private ID playerClass;
    private String playerClassName;
    private int level;
    private boolean isPlayer = false;

    public Fighter(Sprite sprite, double x, double y, State state, Animation animation) {
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

    public void setPlayer(boolean player) {
        isPlayer = player;
    }
}
