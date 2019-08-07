package com.swingy.map;

import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
import com.swingy.rendering.textures.Texture;
import com.swingy.view.Swingy;

import java.util.ArrayList;

import static java.lang.Math.random;

public class MapGenerator {

    private int path = 0, enemy = 0, block = 0, item = 0;
    private String map[][];

    private ArrayList<Tile> tiles;

    private String[] entities = {
            "$",
            "X",
            "!",
            "@"
    };

    public MapGenerator(){
        tiles = new ArrayList<>();
        map = new String[9][16];
    }

    public ArrayList<Tile> generate(){
        float y = Swingy.HEIGHT - 88;
        float x = 10;
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                //map[i][j] = calculateEntity();
                //System.out.print(map[i][j]);
                switch(calculateEntity()){
                    case "$":
                        tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain_test"), 64), 1, 1)));
                        break;
                    case "X":
                        tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain_test"), 64), 2, 1)));
                        break;
                    case "!":
                        tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain_test"), 64), 3, 1)));
                        break;
                    case "@":
                        tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain_test"), 64), 4, 1)));
                        break;
                }
                x += 66;
            }
            y -= 64;
            x = 10;
            //System.out.print("\n");
        }

        System.out.printf("Item: %d | Block: %d | Enemy: %d | Path: %d\n", item, block, enemy, path);
        return tiles;
    }

    public String calculateEntity(){
        double seed = Math.random();

        if (seed < 1.0 / 20.0) {
            item++;
            return entities[0];
        }
        else if (seed < 4.0 / 20.0) {
            block++;
            return entities[1];
        }
        else if (seed < 8.0 / 20.0) {
            enemy++;
            return entities[2];
        }
        else {
            path++;
            return entities[3];
        }
    }

    public String[][] getMap() {
        return map;
    }
}
