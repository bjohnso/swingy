package com.swingy.states;

import com.swingy.entities.Entity;
import com.swingy.entities.Player;
import com.swingy.rendering.textures.Sprite;

import java.awt.*;
import java.util.ArrayList;

public class GameState implements State {

    private ArrayList<Entity> entities;

    @Override
    public void init() {
        entities = new ArrayList<Entity>();
        new Player(new Sprite("test"), 10, 10, this);
    }

    @Override
    public void enterState() {

    }

    @Override
    public void exitState() {
        entities.clear();
    }

    @Override
    public String getName() {
        return "level1";
    }

    @Override
    public void tick(StateManager stateManager) {
        for (Entity e : entities)
            e.tick();
    }

    @Override
    public void render(Graphics graphics) {
        for (Entity e : entities)
            e.render(graphics);
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }
}
