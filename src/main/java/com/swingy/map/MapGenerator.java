package com.swingy.map;

import com.swingy.rendering.entities.Fighter;
import com.swingy.id.ID;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
import com.swingy.rendering.textures.Texture;
import com.swingy.view.Swingy;

import java.util.ArrayList;

public class MapGenerator {

    private String map[][];
    private Tile tileMap[][];

    private ArrayList<Tile> tiles;
    private static int MAP_SIZE;

    private Fighter fighter;

    private String[] entities = {
            "LAVA",
            "PIT",
            "MUSHROOM",
            "DINO",
            "ROBO",
            "ZOMBO",
            "NINJA",
            "GROUND"
    };

    public MapGenerator(Fighter fighter){
        this.fighter = fighter;
        tiles = new ArrayList<>();
        MAP_SIZE = (fighter.getFighterMetrics().getLevel().getLevel() - 1) * 5 + 10 - (fighter.getFighterMetrics().getLevel().getLevel() % 2);
        map = new String[MAP_SIZE][MAP_SIZE];
        tileMap = new Tile[MAP_SIZE][MAP_SIZE];
    }

    public ArrayList<Tile> generate(){
        float y = (Swingy.HEIGHT - (MAP_SIZE * 32)) / 2;
        float x = (Swingy.WIDTH - (MAP_SIZE * 32)) / 2;

        map[map.length / 2][map.length / 2] = "P";
        map[(map.length / 2) - 1][map.length / 2] = "p";
        map[(map.length / 2) + 1][map.length / 2] = "p";
        map[map.length / 2][(map.length / 2) - 1] = "p";
        map[map.length / 2][(map.length / 2) + 1] = "p";

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++) {

                if (map[i][j] == "P") {
                    tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/" + fighter.getPlayerClassName() + "/1", false), 32), 1, 1), true);
                    tileMap[i][j].setTileClass(fighter.getPlayerClass());
                    tiles.add(tileMap[i][j]);
                }
                else if (map[i][j] == "p"){
                    tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 2, 2), ID.GROUND);
                    tiles.add(tileMap[i][j]);
                }
                else if (map[i][j] != "!"){
                        if (i == 0) {
                            if (j == 0)
                                tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 1, 1), ID.BORDER);
                            else if (j == MAP_SIZE - 1)
                                tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 3, 1), ID.BORDER);
                            else
                                tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 2, 1), ID.BORDER);
                        } else if (i == MAP_SIZE - 1) {
                            if (j == 0)
                                tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 1, 3), ID.BORDER);
                            else if (j == MAP_SIZE - 1)
                                tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 3, 3), ID.BORDER);
                            else
                                tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 2, 3), ID.BORDER);
                        } else {
                            if (j == 0)
                                tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 1, 2), ID.BORDER);
                            else if (j == MAP_SIZE - 1)
                                tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 3, 2), ID.BORDER);
                            else {
                                int padding = 32;
                                Tile tile;
                                switch (calculateEntity()) {
                                    case "LAVA":
                                        if (i + 2 < MAP_SIZE - 1 && j + 2 < MAP_SIZE - 1 && !checkNineByNine("!", j, i)) {
                                            for (int k = 0; k < 3; k++) {
                                                for (int l = 0; l < 3; l++) {
                                                    map[i + k][j + l] = "!";
                                                    tileMap[i + k][j + l] = new Tile(x + (padding * l), y + (padding * k), new Sprite(new SpriteSheet(new Texture("terrain/lava", false), 32), l + 1, k + 1), ID.LAVA);
                                                    if (k > 0 || l > 0)
                                                        tiles.add(tileMap[i + k][j + l]);
                                                }
                                            }
                                        } else
                                            tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 2, 2), ID.GROUND);
                                        break;
                                    case "PIT":
                                        if (i + 2 < MAP_SIZE - 1 && j + 2 < MAP_SIZE - 1 && !checkNineByNine("!", j, i)) {
                                            for (int k = 0; k < 3; k++) {
                                                for (int l = 0; l < 3; l++) {
                                                    map[i + k][j + l] = "!";
                                                    tileMap[i + k][j + l] = new Tile(x + (padding * l), y + (padding * k), new Sprite(new SpriteSheet(new Texture("terrain/pit", false), 32), l + 1, k + 1), ID.PIT);
                                                    tiles.add(tileMap[i + k][j + l]);
                                                    if (k > 0 || l > 0)
                                                        tiles.add(tileMap[i + k][j + l]);
                                                }
                                            }
                                        } else
                                            tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 2, 2), ID.GROUND);
                                        break;
                                    case "MUSHROOM":
                                        tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/mushroom", false), 32), 1, 1));
                                        break;
                                    case "DINO":
                                        tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/dino/1", false), 32), 1, 1));
                                        tileMap[i][j].setTileClass(ID.DINO);
                                        break;
                                    case "ROBO":
                                        tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/robo/1", false), 32), 1, 1));
                                        tileMap[i][j].setTileClass(ID.ROBO);
                                        break;
                                    case "ZOMBO":
                                        tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/zombo/1", false), 32), 1, 1));
                                        tileMap[i][j].setTileClass(ID.ZOMBO);
                                        break;
                                    case "NINJA":
                                        tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ninja/1", false), 32), 1, 1));
                                        tileMap[i][j].setTileClass(ID.NINJA);
                                        break;
                                    default:
                                        tileMap[i][j] = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 2, 2), ID.GROUND);
                                        break;
                                }
                            }
                        }
                    tiles.add(tileMap[i][j]);
                }
                x += 32;
            }
            y += 32;
            x = (Swingy.WIDTH - (MAP_SIZE * 32)) / 2;
        }

        return tiles;
    }

    public String calculateEntity(){
        double seed = Math.random();

        if (seed < .15 / 20.0)
            return entities[0];
        else if (seed < .25 / 20.0)
            return entities[1];
        else if (seed < .3 / 20.0)
            return entities[2];
        else if (seed < .4 / 20.0)
            return entities[3];
        else if (seed < .5 / 20.0)
            return entities[4];
        else if (seed < .6 / 20.0)
            return entities[5];
        else if (seed < .7 / 20.0)
            return entities[6];
        else
            return entities[7];
    }

    private boolean checkNineByNine(String c, int x, int y){
        for (int i = y; i < y + 3; i++){
            for (int j = x; j < x + 3; j++){
                if (map[i][j] == c || map[i][j] == "P" || map[i][j] == "p")
                    return true;
            }
        }
        return false;
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }
}
