package com.swingy.map;

import com.swingy.rendering.entities.Fighter;
import com.swingy.id.ID;
import com.swingy.rendering.textures.Texture;
import com.swingy.view.Swingy;

import java.util.ArrayList;
import java.util.HashMap;

public class TileMapGenerator {
    private String map[][];
    private Tile tileMap[][];

    private static int MAP_SIZE;

    private Fighter fighter;

    private String playerCoordinate;

    private HashMap<String, Tile> TILES;

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

    public TileMapGenerator(Fighter fighter){
        this.fighter = fighter;
        MAP_SIZE = (fighter.getFighterMetrics().getLevel().getLevel() - 1) * 5 + 10 - (fighter.getFighterMetrics().getLevel().getLevel() % 2);
        map = new String[MAP_SIZE][MAP_SIZE];
        tileMap = new Tile[MAP_SIZE][MAP_SIZE];

        float y = (Swingy.HEIGHT - (MAP_SIZE * 32)) / 2;
        float x = (Swingy.WIDTH - (MAP_SIZE * 32)) / 2;

        TILES = new HashMap<String, Tile>(){{
            //Corner Borders
            put("BORDER-CORNER-NE", new Tile(new Texture(new Texture("terrain/ground", false), 1, 1, 32), ID.BORDER));
            put("BORDER-CORNER-NW", new Tile(new Texture(new Texture("terrain/ground", false), 3, 1, 32), ID.BORDER));
            put("BORDER-CORNER-SE", new Tile(new Texture(new Texture("terrain/ground", false), 1, 3, 32), ID.BORDER));
            put("BORDER-CORNER-SW", new Tile(new Texture(new Texture("terrain/ground", false), 3, 3, 32), ID.BORDER));
            //Middle Borders
            put("BORDER-N", new Tile(new Texture(new Texture("terrain/ground", false), 2, 1, 32), ID.BORDER));
            put("BORDER-E", new Tile(new Texture(new Texture("terrain/ground", false), 1, 2, 32), ID.BORDER));
            put("BORDER-W", new Tile(new Texture(new Texture("terrain/ground", false), 3, 2, 32), ID.BORDER));
            put("BORDER-S", new Tile(new Texture(new Texture("terrain/ground", false), 2, 3, 32), ID.BORDER));
            //Ground
            put("GROUND", new Tile(new Texture(new Texture("terrain/ground", false), 2, 2, 32), ID.GROUND));
            //Mushroom
            put("MUSHROOM", new Tile(new Texture(new Texture("terrain/mushroom", false), 1, 1, 32), ID.MUSHROOM));
            //MOBS
            put("DINO", new Tile(new Texture(new Texture("terrain/dino/1", false), 1, 1, 32), ID.DINO));
            put("ROBO", new Tile(new Texture(new Texture("terrain/robo/1", false), 1, 1, 32), ID.ROBO));
            put("ZOMBO", new Tile(new Texture(new Texture("terrain/zombo/1", false), 1, 1, 32), ID.ZOMBO));
            put("NINJA", new Tile(new Texture(new Texture("terrain/ninja/1", false), 1, 1, 32), ID.NINJA));
            //Lava Corners
            put("LAVA-CORNER-NE", new Tile(new Texture(new Texture("terrain/lava", false), 1, 1, 32), ID.LAVA));
            put("LAVA-CORNER-NW", new Tile(new Texture(new Texture("terrain/lava", false), 3, 1, 32), ID.LAVA));
            put("LAVA-CORNER-SE", new Tile(new Texture(new Texture("terrain/lava", false), 1, 3, 32), ID.LAVA));
            put("LAVA-CORNER-SW", new Tile(new Texture(new Texture("terrain/lava", false), 3, 3, 32), ID.LAVA));
            //Middle Lava
            put("LAVA-E", new Tile(new Texture(new Texture("terrain/lava", false), 1, 2, 32), ID.LAVA));
            put("LAVA-W", new Tile(new Texture(new Texture("terrain/lava", false), 3, 2, 32), ID.LAVA));
            put("LAVA-N", new Tile(new Texture(new Texture("terrain/lava", false), 2, 1, 32), ID.LAVA));
            put("LAVA-S", new Tile(new Texture(new Texture("terrain/lava", false), 2, 3, 32), ID.LAVA));
            //Lava
            put("LAVA", new Tile(new Texture(new Texture("terrain/lava", false), 2, 2, 32), ID.LAVA));
            //Pit Corners
            put("PIT-CORNER-NE", new Tile(new Texture(new Texture("terrain/pit", false), 1, 1, 32), ID.PIT));
            put("PIT-CORNER-NW", new Tile(new Texture(new Texture("terrain/pit", false), 3, 1, 32), ID.PIT));
            put("PIT-CORNER-SE", new Tile(new Texture(new Texture("terrain/pit", false), 1, 3, 32), ID.PIT));
            put("PIT-CORNER-SW", new Tile(new Texture(new Texture("terrain/pit", false), 3, 3, 32), ID.PIT));
            //Middle Pit
            put("PIT-E", new Tile(new Texture(new Texture("terrain/pit", false), 1, 2, 32), ID.PIT));
            put("PIT-W", new Tile(new Texture(new Texture("terrain/pit", false), 3, 2, 32), ID.PIT));
            put("PIT-N", new Tile(new Texture(new Texture("terrain/pit", false), 2, 1, 32), ID.PIT));
            put("PIT-S", new Tile(new Texture(new Texture("terrain/pit", false), 2, 3, 32), ID.PIT));
            //Pit
            put("PIT", new Tile(new Texture(new Texture("terrain/pit", false), 2, 2, 32), ID.PIT));
        }};
    }

    public void generate(){
        float y = (Swingy.HEIGHT - (MAP_SIZE * 32)) / 2;
        float x = (Swingy.WIDTH - (MAP_SIZE * 32)) / 2;

        map[map.length / 2][map.length / 2] = "P";
        map[(map.length / 2) - 1][map.length / 2] = "p";
        map[(map.length / 2) + 1][map.length / 2] = "p";
        map[map.length / 2][(map.length / 2) - 1] = "p";
        map[map.length / 2][(map.length / 2) + 1] = "p";

        String entity;

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++) {
                entity = null;
                if (map[i][j] == "P") {
                    tileMap[i][j] = TILES.get(fighter.getPlayerClassName().toUpperCase());
                    playerCoordinate = x + "-" + y;
                }
                else if (map[i][j] == "p"){
                    tileMap[i][j] = TILES.get("GROUND");
                }
                else if (map[i][j] != "!" && map[i][j] != "p"){
                        if (i == 0) {
                            if (j == 0)
                                tileMap[i][j] = TILES.get("BORDER-CORNER-NE");
                            else if (j == MAP_SIZE - 1)
                                tileMap[i][j] = TILES.get("BORDER-CORNER-NW");
                            else
                                tileMap[i][j] = TILES.get("BORDER-N");
                        } else if (i == MAP_SIZE - 1) {
                            if (j == 0)
                                tileMap[i][j] = TILES.get("BORDER-CORNER-SE");
                            else if (j == MAP_SIZE - 1)
                                tileMap[i][j] = TILES.get("BORDER-CORNER-SW");
                            else
                                tileMap[i][j] = TILES.get("BORDER-S");
                        } else {
                            if (j == 0)
                                tileMap[i][j] = TILES.get("BORDER-E");
                            else if (j == MAP_SIZE - 1)
                                tileMap[i][j] = TILES.get("BORDER-W");
                            else {
                                entity = calculateEntity();
                                switch (entity) {
                                    case "LAVA":
                                        if (i + 2 < MAP_SIZE - 1 && j + 2 < MAP_SIZE - 1 && !checkNineByNine("!", j, i))
                                            generateNineByNine("LAVA", j, i, x, y);
                                        else
                                            tileMap[i][j] = TILES.get("GROUND");
                                        break;
                                    case "PIT":
                                        if (i + 2 < MAP_SIZE - 1 && j + 2 < MAP_SIZE - 1 && !checkNineByNine("!", j, i))
                                            generateNineByNine("PIT", j, i, x, y);
                                        else
                                            tileMap[i][j] = TILES.get("GROUND");
                                        break;
                                    case "MUSHROOM":
                                        tileMap[i][j] = TILES.get("MUSHROOM");
                                        break;
                                    case "DINO":
                                        tileMap[i][j] = TILES.get("DINO");
                                        break;
                                    case "ROBO":
                                        tileMap[i][j] = TILES.get("ROBO");
                                        break;
                                    case "ZOMBO":
                                        tileMap[i][j] = TILES.get("ZOMBO");
                                        break;
                                    case "NINJA":
                                        tileMap[i][j] = TILES.get("NINJA");
                                        break;
                                    default:
                                        tileMap[i][j] = TILES.get("GROUND");
                                }
                            }
                        }
                }
                tileMap[i][j].addCoordinate(x, y);
                x += 32;
            }
            y += 32;
            x = (Swingy.WIDTH - (MAP_SIZE * 32)) / 2;
        }
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

    private void generateNineByNine(String type, int x, int y, float padX, float padY) {
        float originX = padX;
        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                map[i][j] = "!";
                if (i == y) {
                    if (j == x)
                        tileMap[i][j] = TILES.get(type.toUpperCase() + "-CORNER-NE");
                    else if (j == MAP_SIZE - 1)
                        tileMap[i][j] = TILES.get(type.toUpperCase() + "-CORNER-NW");
                    else
                        tileMap[i][j] = TILES.get(type.toUpperCase() + "-N");
                } else if (i == y + 3 - 1) {
                    if (j == x)
                        tileMap[i][j] = TILES.get(type.toUpperCase() + "-CORNER-SE");
                    else if (j == x + 3 - 1)
                        tileMap[i][j] = TILES.get(type.toUpperCase() + "-CORNER-SW");
                    else
                        tileMap[i][j] = TILES.get(type.toUpperCase() + "-S");
                } else {
                    if (j == x)
                        tileMap[i][j] = TILES.get(type.toUpperCase() + "-E");
                    else if (j == MAP_SIZE - 1)
                        tileMap[i][j] = TILES.get(type.toUpperCase() + "-W");
                    else
                        tileMap[i][j] = TILES.get(type.toUpperCase());
                }
                tileMap[i][j].addCoordinate(padX, padY);
                padX += 32;
            }
            padY += 32;
            padX = originX;
        }
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

    public String getPlayerCoordinate() {
        return playerCoordinate;
    }
}
