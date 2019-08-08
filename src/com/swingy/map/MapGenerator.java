package com.swingy.map;

import com.swingy.entities.Player;
import com.swingy.id.ID;
import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
import com.swingy.rendering.textures.Texture;
import com.swingy.states.GameState;
import com.swingy.view.Swingy;
import com.swingy.view.Window;

import java.util.ArrayList;

import static java.lang.Math.random;

public class MapGenerator {

    private int path = 0, enemy = 0, block = 0, item = 0, lava = 0, pit = 0;
    private String map[][];

    private ArrayList<Tile> tiles;
    private static int MAP_SIZE;

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

    public MapGenerator(int level){
        tiles = new ArrayList<>();
        MAP_SIZE = (level - 1) * 5 + 10 - (level % 2);
        map = new String[MAP_SIZE][MAP_SIZE];
    }

    public ArrayList<Tile> generate(){
        float y = (Swingy.HEIGHT - (MAP_SIZE * 32)) / 2;
        float x = (Swingy.WIDTH - (MAP_SIZE * 32)) / 2;

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++) {

                if (map[i][j] != "!"){
                        if (i == 0) {
                            if (j == 0)
                                tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 1, 1)));
                            else if (j == MAP_SIZE - 1)
                                tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 3, 1)));
                            else
                                tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 2, 1)));
                        } else if (i == MAP_SIZE - 1) {
                            if (j == 0)
                                tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 1, 3)));
                            else if (j == MAP_SIZE - 1)
                                tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 3, 3)));
                            else
                                tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 2, 3)));
                        } else {
                            if (j == 0)
                                tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 1, 2)));
                            else if (j == MAP_SIZE - 1)
                                tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 3, 2)));
                            else {
                                int padding = 32;
                                Tile tile;
                                switch (calculateEntity()) {
                                    case "LAVA":
                                        if (i + 2 < MAP_SIZE - 1 && j + 2 < MAP_SIZE - 1 && !checkNineByNine("!", j, i)) {
                                            for (int k = 0; k < 3; k++) {
                                                for (int l = 0; l < 3; l++) {
                                                    map[i + k][j + l] = "!";
                                                    tiles.add(new Tile(x + (padding * l), y + (padding * k), new Sprite(new SpriteSheet(new Texture("terrain/lava"), 32), l + 1, k + 1)));
                                                }
                                            }
                                        } else
                                            tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 2, 2)));
                                        break;
                                    case "PIT":
                                        if (i + 2 < MAP_SIZE - 1 && j + 2 < MAP_SIZE - 1 && !checkNineByNine("!", j, i)) {
                                            for (int k = 0; k < 3; k++) {
                                                for (int l = 0; l < 3; l++) {
                                                    map[i + k][j + l] = "!";
                                                    tiles.add(new Tile(x + (padding * l), y + (padding * k), new Sprite(new SpriteSheet(new Texture("terrain/pit"), 32), l + 1, k + 1)));
                                                }
                                            }
                                        } else
                                            tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 2, 2)));
                                        break;
                                    case "MUSHROOM":
                                        tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/mushroom"), 32), 1, 1)));
                                        break;
                                    case "DINO":
                                        tile = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/dino/1"), 32), 1, 1));
                                        tile.setID(ID.DINO);
                                        tiles.add(tile);
                                        break;
                                    case "ROBO":
                                        tile = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/robo/1"), 32), 1, 1));
                                        tile.setID(ID.ROBO);
                                        tiles.add(tile);
                                        break;
                                    case "ZOMBO":
                                        tile = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/zombo/1"), 32), 1, 1));
                                        tile.setID(ID.ZOMBO);
                                        tiles.add(tile);
                                        break;
                                    case "NINJA":
                                        tile = new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ninja/1"), 32), 1, 1));
                                        tile.setID(ID.NINJA);
                                        tiles.add(tile);
                                        break;
                                    default:
                                        tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain/ground"), 32), 2, 2)));
                                        break;
                                }
                            }
                        }
                }
                x += 32;
            }
            y += 32;
            x = (Swingy.WIDTH - (MAP_SIZE * 32)) / 2;
        }

        System.out.printf("Mushroom: %d | Lava: %d | Pit: %d | Enemy: %d | Ground: %d\n", item, lava, pit, enemy, path);
        return tiles;
    }

    public String calculateEntity(){
        double seed = Math.random();

        if (seed < .15 / 20.0) {
            lava++;
            return entities[0];
        }
        else if (seed < .25 / 20.0) {
            pit++;
            return entities[1];
        }
        else if (seed < .3 / 20.0){
            item++;
            return entities[2];
        }
        else if (seed < .4 / 20.0) {
            enemy++;
            return entities[3];
        }
        else if (seed < .5 / 20.0) {
            enemy++;
            return entities[4];
        }
        else if (seed < .6 / 20.0) {
            enemy++;
            return entities[5];
        }
        else if (seed < .7 / 20.0) {
            enemy++;
            return entities[6];
        }
        else {
            path++;
            return entities[7];
        }

    }

    private boolean checkNineByNine(String c, int x, int y){
        for (int i = y; i < y + 3; i++){
            for (int j = x; j < x + 3; j++){
                if (map[i][j] == c)
                    return true;
            }
        }
        return false;
    }

    public String[][] getMap() {
        return map;
    }
}
