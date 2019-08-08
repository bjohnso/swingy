package com.swingy.states;

import com.swingy.entities.Entity;
import com.swingy.entities.Player;
import com.swingy.id.ID;
import com.swingy.map.MapGenerator;
import com.swingy.map.Tile;
import com.swingy.rendering.textures.Animation;
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
        mapGenerator = new MapGenerator(5);
        entities = new ArrayList<Entity>();
        tiles = mapGenerator.generate();
        for (Tile t: tiles){
            if (t.getID() == ID.DINO){
                new Player(new Sprite("terrain/dino/1"),
                        t.getX(), t.getY(), this, new Animation(30,
                        new Texture("terrain/dino/1"),
                        new Texture("terrain/dino/2"),
                        new Texture("terrain/dino/3"),
                        new Texture("terrain/dino/4"),
                        new Texture("terrain/dino/5"),
                        new Texture("terrain/dino/6"),
                        new Texture("terrain/dino/7"),
                        new Texture("terrain/dino/8")));
            }
            else if (t.getID() == ID.ROBO){
                new Player(new Sprite("terrain/robo/1"),
                        t.getX(), t.getY(), this, new Animation(30,
                        new Texture("terrain/robo/1"),
                        new Texture("terrain/robo/2"),
                        new Texture("terrain/robo/3"),
                        new Texture("terrain/robo/4"),
                        new Texture("terrain/robo/5"),
                        new Texture("terrain/robo/6"),
                        new Texture("terrain/robo/7"),
                        new Texture("terrain/robo/8")));
            }
            else if (t.getID() == ID.ZOMBO){
                new Player(new Sprite("terrain/zombo/1"),
                        t.getX(), t.getY(), this, new Animation(30,
                        new Texture("terrain/zombo/1"),
                        new Texture("terrain/zombo/2"),
                        new Texture("terrain/zombo/3"),
                        new Texture("terrain/zombo/4"),
                        new Texture("terrain/zombo/5"),
                        new Texture("terrain/zombo/6"),
                        new Texture("terrain/zombo/7"),
                        new Texture("terrain/zombo/8"),
                        new Texture("terrain/zombo/9"),
                        new Texture("terrain/zombo/10")));
            }
            else if (t.getID() == ID.NINJA){
                new Player(new Sprite("terrain/ninja/1"),
                        t.getX(), t.getY(), this, new Animation(30,
                        new Texture("terrain/ninja/1"),
                        new Texture("terrain/ninja/2"),
                        new Texture("terrain/ninja/3"),
                        new Texture("terrain/ninja/4"),
                        new Texture("terrain/ninja/5"),
                        new Texture("terrain/ninja/6"),
                        new Texture("terrain/ninja/7"),
                        new Texture("terrain/ninja/8"),
                        new Texture("terrain/ninja/9"),
                        new Texture("terrain/ninja/10")));
            }

        }
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

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, Swingy.WIDTH, Swingy.HEIGHT);

        for (Entity e : entities)
            e.render(graphics);

        for (Tile t : tiles) {
            if (t.getID() != ID.DINO && t.getID() != ID.ROBO && t.getID() != ID.ZOMBO && t.getID() != ID.NINJA)
                t.render(graphics);
        }
    }

    @Override
    public void addEntity(Entity entity){
        entities.add(entity);
    }
}
