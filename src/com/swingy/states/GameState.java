package com.swingy.states;

import com.swingy.entities.Entity;
import com.swingy.entities.Player;
import com.swingy.id.ID;
import com.swingy.input.KeyInput;
import com.swingy.map.MapGenerator;
import com.swingy.map.Tile;
import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.Texture;
import com.swingy.view.Swingy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameState implements State {

    private ArrayList<Entity> entities;
    private ArrayList<Player> players;
    private ArrayList<Tile> tiles;
    private Tile[][] tileMap;

    private MapGenerator mapGenerator;
    private Player player;
    private int playerIndex;

    private boolean isResume = false;

    @Override
    public void init() {
        player = CharacterCreationState.currentPlayer;
        mapGenerator = new MapGenerator(player);
        tileMap = mapGenerator.getTileMap();
        entities = new ArrayList<Entity>();
        players = new ArrayList<>();
        tiles = mapGenerator.generate();

        for (Tile t: tiles){
            Player tempPlayer = null;

            if (t.getID() == ID.DINO){
                tempPlayer = new Player(new Sprite("terrain/dino/1"),
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
                tempPlayer = new Player(new Sprite("terrain/robo/1"),
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
                tempPlayer = new Player(new Sprite("terrain/zombo/1"),
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
                tempPlayer = new Player(new Sprite("terrain/ninja/1"),
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

            if (tempPlayer != null) {
                players.add(tempPlayer);
                if (t.isPlayer()){
                    playerIndex = tiles.indexOf(t);
                    player = tempPlayer;
                }
            }
        }
    }

    public void enemyMove(){

        for (Player p: players) {
            if (p != player) {
                ArrayList<String> directions = new ArrayList<>();
                int originX = (Swingy.WIDTH - (tileMap.length * 32)) / 2;
                int originY = (Swingy.HEIGHT - (tileMap.length * 32)) / 2;
                int indexX = (int) ((p.getX() - originX));
                int indexY = (int) ((p.getY() - originY));

                if (indexX > 0)
                    indexX /= 32;
                if (indexY > 0)
                    indexY /= 32;

                if (indexX + 1 < tileMap.length && indexX + 1 > -1 && indexY < tileMap.length && indexY > -1) {
                    System.out.println(indexX);
                    if (tileMap[indexY][indexX + 1].getID() == ID.GROUND)
                        directions.add("right");
                }
                if (indexX - 1 < tileMap.length && indexX - 1 > -1 && indexY < tileMap.length && indexY > -1) {
                    if (tileMap[indexY][indexX - 1].getID() == ID.GROUND)
                        directions.add("left");
                }
                if (indexX < tileMap.length && indexX > -1 && indexY + 1 < tileMap.length && indexY + 1 > -1) {
                    if (tileMap[indexY + 1][indexX].getID() == ID.GROUND)
                        directions.add("down");
                }
                if (indexX < tileMap.length && indexX > -1 && indexY - 1 < tileMap.length && indexY - 1 > -1) {
                    if (tileMap[indexY - 1][indexX].getID() == ID.GROUND)
                        directions.add("up");
                }

                double seed = Math.random();
                double i = 0;

                for (String d : directions) {
                    double probability = (++i/directions.size());
                    if (seed < probability) {
                        Tile tempTile;
                        if (d == "left") {
                            tempTile = tileMap[indexY][indexX - 1];
                            tileMap[indexY][indexX - 1] = tileMap[indexY][indexX];
                            tileMap[indexY][indexX] = tempTile;

                            tileMap[indexY][indexX - 1].moveX(-32);
                            tileMap[indexY][indexX].moveX(32);

                            p.moveX(-32);
                        }  else if (d == "up") {
                            tempTile = tileMap[indexY - 1][indexX];
                            tileMap[indexY - 1][indexX] = tileMap[indexY][indexX];
                            tileMap[indexY][indexX] = tempTile;

                            tileMap[indexY - 1][indexX].moveY(-32);
                            tileMap[indexY][indexX].moveY(32);

                            p.moveY(-32);
                        }
                        else if (d == "right") {
                            tempTile = tileMap[indexY][indexX + 1];
                            tileMap[indexY][indexX + 1] = tileMap[indexY][indexX];
                            tileMap[indexY][indexX] = tempTile;

                            tileMap[indexY][indexX + 1].moveX(32);
                            tileMap[indexY][indexX].moveX(-32);

                            p.moveX(32);
                        }else {
                            tempTile = tileMap[indexY + 1][indexX];
                            tileMap[indexY + 1][indexX] = tileMap[indexY][indexX];
                            tileMap[indexY][indexX] = tempTile;

                            tileMap[indexY + 1][indexX].moveY(32);
                            tileMap[indexY][indexX].moveY(-32);

                            p.moveY(32);
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public State enterState() {
        if (!isResume)
            init();
        return this;
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

        if (KeyInput.wasPressed(KeyEvent.VK_UP) || KeyInput.wasPressed(KeyEvent.VK_W)){

            for (int i =  0; i < tileMap.length; i++){
                for (int j = 0; j < tileMap.length; j++){
                    if (tileMap[i][j].isPlayer()){
                        if (i - 1 > -1){
                            if(tileMap[i - 1][j].getID() == ID.GROUND){
                                Tile tempTile = tileMap[i - 1][j];
                                tileMap[i - 1][j] = tileMap[i][j];
                                tileMap[i][j] = tempTile;

                                tileMap[i - 1][j].moveY(-32);
                                tileMap[i][j].moveY(32);

                                player.moveY(-32);
                                j = tileMap.length;
                                i = tileMap.length;
                                enemyMove();
                            }
                        }
                    }
                }
            }
        }

        if (KeyInput.wasPressed(KeyEvent.VK_DOWN) || KeyInput.wasPressed(KeyEvent.VK_S)){

            for (int i =  0; i < tileMap.length; i++){
                for (int j = 0; j < tileMap.length; j++){
                    if (tileMap[i][j].isPlayer()){
                        if (i + 1 < tileMap.length){
                            if(tileMap[i + 1][j].getID() == ID.GROUND){
                                Tile tempTile = tileMap[i + 1][j];
                                tileMap[i + 1][j] = tileMap[i][j];
                                tileMap[i][j] = tempTile;

                                tileMap[i + 1][j].moveY(32);
                                tileMap[i][j].moveY(-32);

                                player.moveY(32);
                                j = tileMap.length;
                                i = tileMap.length;
                                enemyMove();
                            }
                        }
                    }
                }
            }
        }

        if (KeyInput.wasPressed(KeyEvent.VK_LEFT) || KeyInput.wasPressed(KeyEvent.VK_A)){

            for (int i =  0; i < tileMap.length; i++){
                for (int j = 0; j < tileMap.length; j++){
                    if (tileMap[i][j].isPlayer()){
                        if (j - 1 > -1){
                            if(tileMap[i][j - 1].getID() == ID.GROUND){
                                Tile tempTile = tileMap[i][j - 1];
                                tileMap[i][j - 1] = tileMap[i][j];
                                tileMap[i][j] = tempTile;

                                tileMap[i][j - 1].moveX(-32);
                                tileMap[i][j].moveX(32);

                                player.moveX(-32);
                                j = tileMap.length;
                                i = tileMap.length;
                                enemyMove();
                            }
                        }
                    }
                }
            }
        }

        if (KeyInput.wasPressed(KeyEvent.VK_RIGHT) || KeyInput.wasPressed(KeyEvent.VK_D)){

            for (int i =  0; i < tileMap.length; i++){
                for (int j = 0; j < tileMap.length; j++){
                    if (tileMap[i][j].isPlayer()){
                        if (j + 1 < tileMap.length){
                            if(tileMap[i][j + 1].getID() == ID.GROUND){
                                Tile tempTile = tileMap[i][j + 1];
                                tileMap[i][j + 1] = tileMap[i][j];
                                tileMap[i][j] = tempTile;

                                tileMap[i][j + 1].moveX(32);
                                tileMap[i][j].moveX(-32);

                                player.moveX(32);
                                j = tileMap.length;
                                i = tileMap.length;
                                enemyMove();
                            }
                        }
                    }
                }
            }
        }

        for (Entity e : entities)
            e.tick();
    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, Swingy.WIDTH, Swingy.HEIGHT);

        for (Entity e : entities)
            e.render(graphics);

        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap.length; j++){
                tileMap[i][j].render(graphics);
            }
        }
    }

    @Override
    public void addEntity(Entity entity){
        entities.add(entity);
    }
}
