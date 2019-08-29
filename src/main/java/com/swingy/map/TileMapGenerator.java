package com.swingy.map;

import com.swingy.id.IDAssigner;
import com.swingy.rendering.entities.Fighter;
import com.swingy.id.ID;
import com.swingy.rendering.textures.Texture;
import com.swingy.view.Swingy;

import java.util.ArrayList;
import java.util.HashMap;

public class TileMapGenerator {
    private String map[][];

    private static int MAP_SIZE;

    private Fighter fighter;

    private String playerCoordinate;

    private HashMap<String, Tile> TILES;

    IDAssigner nonPlayeridAssigner = new IDAssigner(1);
    public static IDAssigner groundIDAssigner = new IDAssigner(1);

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
        //MAP_SIZE = (fighter.getFighterMetrics().getLevel().getLevel() - 1) * 5 + 10 - (fighter.getFighterMetrics().getLevel().getLevel() % 2);
        MAP_SIZE = (5 - 1) * 5 + 10 - (5 % 2);
        map = new String[MAP_SIZE][MAP_SIZE];

        TILES = new HashMap<String, Tile>(){{
            //Corner Borders
            put("BORDER-CORNER-NE", new Tile(new Texture(new Texture("terrain/ground", false), 1, 1, 32), MAP_SIZE, ID.BORDER));
            put("BORDER-CORNER-NW", new Tile(new Texture(new Texture("terrain/ground", false), 3, 1, 32), MAP_SIZE, ID.BORDER));
            put("BORDER-CORNER-SE", new Tile(new Texture(new Texture("terrain/ground", false), 1, 3, 32), MAP_SIZE, ID.BORDER));
            put("BORDER-CORNER-SW", new Tile(new Texture(new Texture("terrain/ground", false), 3, 3, 32), MAP_SIZE, ID.BORDER));
            //Middle Borders
            put("BORDER-N", new Tile(new Texture(new Texture("terrain/ground", false), 2, 1, 32), MAP_SIZE, ID.BORDER));
            put("BORDER-E", new Tile(new Texture(new Texture("terrain/ground", false), 1, 2, 32), MAP_SIZE, ID.BORDER));
            put("BORDER-W", new Tile(new Texture(new Texture("terrain/ground", false), 3, 2, 32), MAP_SIZE, ID.BORDER));
            put("BORDER-S", new Tile(new Texture(new Texture("terrain/ground", false), 2, 3, 32), MAP_SIZE, ID.BORDER));
            //Ground
            put("GROUND", new Tile(new Texture(new Texture("terrain/ground", false), 2, 2, 32), MAP_SIZE, ID.GROUND));
            //Mushroom
            put("MUSHROOM", new Tile(new Texture(new Texture("terrain/mushroom", false), 1, 1, 32), MAP_SIZE, ID.MUSHROOM));
            //MOBS
            put("DINO", new Tile(new Texture(new Texture("terrain/dino/1", false), 1, 1, 32), MAP_SIZE, ID.DINO));
            put("ROBO", new Tile(new Texture(new Texture("terrain/robo/1", false), 1, 1, 32), MAP_SIZE, ID.ROBO));
            put("ZOMBO", new Tile(new Texture(new Texture("terrain/zombo/1", false), 1, 1, 32), MAP_SIZE, ID.ZOMBO));
            put("NINJA", new Tile(new Texture(new Texture("terrain/ninja/1", false), 1, 1, 32), MAP_SIZE, ID.NINJA));
            //Lava Corners
            put("LAVA-CORNER-NE", new Tile(new Texture(new Texture("terrain/lava", false), 1, 1, 32), MAP_SIZE, ID.LAVA));
            put("LAVA-CORNER-NW", new Tile(new Texture(new Texture("terrain/lava", false), 3, 1, 32), MAP_SIZE, ID.LAVA));
            put("LAVA-CORNER-SE", new Tile(new Texture(new Texture("terrain/lava", false), 1, 3, 32), MAP_SIZE, ID.LAVA));
            put("LAVA-CORNER-SW", new Tile(new Texture(new Texture("terrain/lava", false), 3, 3, 32), MAP_SIZE, ID.LAVA));
            //Middle Lava
            put("LAVA-E", new Tile(new Texture(new Texture("terrain/lava", false), 1, 2, 32), MAP_SIZE, ID.LAVA));
            put("LAVA-W", new Tile(new Texture(new Texture("terrain/lava", false), 3, 2, 32), MAP_SIZE, ID.LAVA));
            put("LAVA-N", new Tile(new Texture(new Texture("terrain/lava", false), 2, 1, 32), MAP_SIZE, ID.LAVA));
            put("LAVA-S", new Tile(new Texture(new Texture("terrain/lava", false), 2, 3, 32), MAP_SIZE, ID.LAVA));
            //Lava
            put("LAVA", new Tile(new Texture(new Texture("terrain/lava", false), 2, 2, 32), MAP_SIZE, ID.LAVA));
            //Pit Corners
            put("PIT-CORNER-NE", new Tile(new Texture(new Texture("terrain/pit", false), 1, 1, 32), MAP_SIZE, ID.PIT));
            put("PIT-CORNER-NW", new Tile(new Texture(new Texture("terrain/pit", false), 3, 1, 32), MAP_SIZE, ID.PIT));
            put("PIT-CORNER-SE", new Tile(new Texture(new Texture("terrain/pit", false), 1, 3, 32), MAP_SIZE, ID.PIT));
            put("PIT-CORNER-SW", new Tile(new Texture(new Texture("terrain/pit", false), 3, 3, 32), MAP_SIZE, ID.PIT));
            //Middle Pit
            put("PIT-E", new Tile(new Texture(new Texture("terrain/pit", false), 1, 2, 32), MAP_SIZE, ID.PIT));
            put("PIT-W", new Tile(new Texture(new Texture("terrain/pit", false), 3, 2, 32), MAP_SIZE, ID.PIT));
            put("PIT-N", new Tile(new Texture(new Texture("terrain/pit", false), 2, 1, 32), MAP_SIZE, ID.PIT));
            put("PIT-S", new Tile(new Texture(new Texture("terrain/pit", false), 2, 3, 32), MAP_SIZE, ID.PIT));
            //Pit
            put("PIT", new Tile(new Texture(new Texture("terrain/pit", false), 2, 2, 32), MAP_SIZE, ID.PIT));
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
                    TILES.get(fighter.getPlayerClassName().toUpperCase()).addCoordinate("PLAYER", x, y);
                    playerCoordinate = x + "|" + y;
                }
                else if (map[i][j] == "p")
                    TILES.get("GROUND").addCoordinate("GROUND-" + groundIDAssigner.next(), x, y);
                else if (map[i][j] != "!" && map[i][j] != "p"){
                        if (i == 0) {
                            if (j == 0)
                                TILES.get("BORDER-CORNER-NE").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                            else if (j == MAP_SIZE - 1)
                                TILES.get("BORDER-CORNER-NW").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                            else
                                TILES.get("BORDER-N").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                        } else if (i == MAP_SIZE - 1) {
                            if (j == 0)
                                TILES.get("BORDER-CORNER-SE").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                            else if (j == MAP_SIZE - 1)
                                TILES.get("BORDER-CORNER-SW").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                            else
                                TILES.get("BORDER-S").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                        } else {
                            if (j == 0)
                                TILES.get("BORDER-E").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                            else if (j == MAP_SIZE - 1)
                                TILES.get("BORDER-W").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                            else {
                                entity = calculateEntity();
                                switch (entity) {
                                    case "LAVA":
                                        if (i + 2 < MAP_SIZE - 1 && j + 2 < MAP_SIZE - 1 && !checkNineByNine("!", j, i))
                                            generateNineByNine("LAVA", j, i, x, y);
                                        else
                                            TILES.get("GROUND").addCoordinate("GROUND-" + groundIDAssigner.next(), x, y);
                                        break;
                                    case "PIT":
                                        if (i + 2 < MAP_SIZE - 1 && j + 2 < MAP_SIZE - 1 && !checkNineByNine("!", j, i))
                                            generateNineByNine("PIT", j, i, x, y);
                                        else
                                            TILES.get("GROUND").addCoordinate("GROUND-" + groundIDAssigner.next(), x, y);
                                        break;
                                    case "MUSHROOM":
                                        TILES.get("MUSHROOM").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                                        break;
                                    case "DINO":
                                        TILES.get("DINO").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                                        break;
                                    case "ROBO":
                                        TILES.get("ROBO").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                                        break;
                                    case "ZOMBO":
                                        TILES.get("ZOMBO").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                                        break;
                                    case "NINJA":
                                        TILES.get("NINJA").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), x, y);
                                        break;
                                    default:
                                        TILES.get("GROUND").addCoordinate("GROUND-" + groundIDAssigner.next(), x, y);
                                }
                            }
                        }
                }
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
                        TILES.get(type.toUpperCase() + "-CORNER-NE").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), padX, padY);
                    else if (j == x + 2)
                        TILES.get(type.toUpperCase() + "-CORNER-NW").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), padX, padY);
                    else
                        TILES.get(type.toUpperCase() + "-N").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), padX, padY);
                } else if (i == y + 2) {
                    if (j == x)
                        TILES.get(type.toUpperCase() + "-CORNER-SE").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), padX, padY);
                    else if (j == x + 2)
                        TILES.get(type.toUpperCase() + "-CORNER-SW").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), padX, padY);
                    else
                        TILES.get(type.toUpperCase() + "-S").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), padX, padY);
                } else {
                    if (j == x)
                        TILES.get(type.toUpperCase() + "-E").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), padX, padY);
                    else if (j == x + 2)
                        TILES.get(type.toUpperCase() + "-W").addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), padX, padY);
                    else
                        TILES.get(type.toUpperCase()).addCoordinate("NON-PLAYER-" + nonPlayeridAssigner.next(), padX, padY);
                }
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

    public HashMap<String, Tile> getTileMap() {
        return TILES;
    }

    public String getPlayerCoordinate() {
        return playerCoordinate;
    }
}
