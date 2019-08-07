package com.swingy.states;

import com.swingy.entities.Entity;
import com.swingy.entities.Player;
import com.swingy.map.MapGenerator;
import com.swingy.map.Tile;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
import com.swingy.rendering.textures.Texture;
import com.swingy.view.Swingy;

import java.awt.*;
import java.util.ArrayList;

public class GameState implements State {

    private ArrayList<Entity> entities;
    private ArrayList<Tile> tiles;

    private MapGenerator mapGenerator;

    private boolean isResume = false;

    @Override
    public void init() {
        mapGenerator = new MapGenerator();
        entities = new ArrayList<Entity>();
        tiles = mapGenerator.generate();
        /*for (int i = 0; i < 17; i++) {
            tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain_test"), 64), 1, 1)));
            x += 65;
        }*/

    }

    @Override
    public void enterState() {
        if (!isResume)
            init();
    }

    @Override
    public void exitState() {
        entities.clear();
        isResume = true;
    }

    @Override
    public String getName() {
        return "map";
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

        for (Tile t : tiles)
            t.render(graphics);
    }

    @Override
    public void addEntity(Entity entity){
        entities.add(entity);
    }
}
