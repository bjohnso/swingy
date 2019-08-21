package com.swingy.rendering.entities;

import com.swingy.battle.FighterMetrics;
import com.swingy.id.ID;
import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.states.State;

import java.awt.*;

public class Fighter extends Mobile{

    private ID playerClass;
    private String playerClassName;
    private boolean isPlayer;
    private boolean alive;
    private FighterMetrics fighterMetrics;

    public Fighter(Sprite sprite, double x, double y, FighterMetrics fighterMetrics, State state, Animation animation) {
        super(sprite, x, y, state, animation);
        alive = true;
        isPlayer = false;
        this.fighterMetrics = fighterMetrics;
        if (fighterMetrics.getName() == "")
            this.fighterMetrics.setName(this.playerClassName);
    }

    public Fighter(FighterMetrics fighterMetrics, State state, Animation animation){
        super(state, animation);
        alive = true;
        isPlayer = false;
        this.fighterMetrics = fighterMetrics;
        if (fighterMetrics.getName() == "")
            this.fighterMetrics.setName(this.playerClassName);
    }

    @Override
    public void tick(){
        super.tick();
    }

    @Override
    public void render(Graphics graphics){
        super.render(graphics);
    }

    public void setPlayerClass(ID playerClass) {
        this.playerClass = playerClass;
    }

    public ID getPlayerClass() {
        return playerClass;
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

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public FighterMetrics getFighterMetrics() { return fighterMetrics; }

    public void setFighterMetrics(FighterMetrics fighterMetrics) { this.fighterMetrics = fighterMetrics; }
}
