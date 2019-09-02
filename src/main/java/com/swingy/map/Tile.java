package com.swingy.map;

import com.swingy.id.ID;
import com.swingy.rendering.textures.Texture;
import com.swingy.states.GameState;

import java.awt.*;
import java.util.HashMap;
import com.swingy.rendering.ui.Window;

public class Tile {

    HashMap<String, String> coordinates = new HashMap<>();
    protected Texture sprite;
    protected boolean solid;
    protected ID tileClass;
    protected String tileClassName;
    protected int MobileID;
    protected String fighterClassName;
    protected int mapSize;

    public Tile(Texture sprite, int mapSize, ID tileClass){
        this.sprite = sprite;
        this.solid = true;
        this.tileClass = tileClass;
        this.mapSize = mapSize;
        setTileClassName();
    }

    public void addCoordinate(String key, float x, float y){
        coordinates.put(key, x + "|" + y);
    }

    public void addCoordinate(String key, String coordinate){
        coordinates.put(key, coordinate);
    }

    public void replaceCoordinate(String key, String cooridinate ){
        coordinates.replace(key, cooridinate);
    }

    public void removeCoordinate(String key){
        coordinates.remove(key);
    }

    public HashMap<String, String> getCoordinates() {
        return coordinates;
    }

    public ID getTileClass(){
        return this.tileClass;
    }


    public void render(Graphics graphics) {

        String parts[] = null;
        //max - min = length
        //center = (min + max) / 2
        float cX = (((Window.WIDTH - (mapSize * 32)) / 2) + ((mapSize * 32) + ((Window.WIDTH - (mapSize * 32)) / 2))) / 2;
        float cY = (((Window.HEIGHT- (mapSize * 32)) / 2) + ((mapSize * 32) + ((Window.HEIGHT - (mapSize * 32)) / 2))) / 2;

        float pX = cX;
        float pY = cY;

        if (GameState.playerCoordinates != null) {
            parts = GameState.playerCoordinates.split("\\|");
            pX = (float) Double.parseDouble(parts[0]);
            pY = (float) Double.parseDouble(parts[1]);
        }

        boolean hiddenMap = false;
        if (mapSize * 32 > Window.WIDTH || mapSize * 32 > Window.HEIGHT)
            hiddenMap = true;

        for (HashMap.Entry<String, String> s : coordinates.entrySet()) {
            parts = s.getValue().split("\\|");
            float x = (float) Double.parseDouble(parts[0]);
            float y = (float) Double.parseDouble(parts[1]);

            //Check if cordinate is in bounds of window and render
            if (!hiddenMap) {
                sprite.render(graphics, x, y);
            } else if (x + (cX - pX) >= 0 && x + (cX - pX) <= mapSize * 32 && y + (cY - pY) >= 0 && y + (cY - pY) <= mapSize * 32) {
                sprite.render(graphics, x + (cX - pX), y + (cY - pY));
            }
        }
    }

    public void setMobileID(int mobileID) {
        MobileID = mobileID;
    }

    public int getMobileID() {
        return MobileID;
    }

    public String getTileClassName() {
        return tileClassName;
    }

    public String getFigtherClassName() {
        return fighterClassName;
    }

    public void setTileClassName(){
        switch (tileClass){
            case DINO:
                fighterClassName = "DINO";
                tileClassName = "FIGHTER";
                break;
            case ROBO:
                fighterClassName = "ROBO";
                tileClassName = "FIGHTER";
                break;
            case ZOMBO:
                fighterClassName = "ZOMBO";
                tileClassName = "FIGHTER";
                break;
            case NINJA:
                fighterClassName = "NINJA";
                tileClassName = "FIGHTER";
                break;
            case BORDER:
                fighterClassName = "";
                tileClassName = "BORDER";
                break;
            case LAVA:
                fighterClassName = "";
                tileClassName = "TRAP";
                break;
            case PIT:
                fighterClassName = "";
                tileClassName = "TRAP";
                break;
            case GROUND:
                fighterClassName = "";
                tileClassName = "GROUND";
                break;
            case MUSHROOM:
                fighterClassName = "";
                tileClassName = "OBSTRUCTION";
                break;
            default:
                tileClassName = "";
                fighterClassName = "";
        }
    }
}
