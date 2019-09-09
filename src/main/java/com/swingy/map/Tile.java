package com.swingy.map;

import com.swingy.id.ID;
import com.swingy.metrics.Coordinate;
import com.swingy.rendering.textures.Texture;
import com.swingy.states.GameState;

import java.awt.*;
import java.util.HashMap;
import com.swingy.rendering.ui.Window;

public class Tile {

    HashMap<String, Coordinate> coordinates = new HashMap<>();
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

    public void addCoordinate(String key, Coordinate coordinate){
        coordinates.put(key, coordinate);
    }

    public void replaceCoordinate(String key, Coordinate cooridinate){ coordinates.replace(key, cooridinate); }

    public void removeCoordinate(String key){
        coordinates.remove(key);
    }

    public HashMap<String, Coordinate> getCoordinates() {
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
            pX = GameState.playerCoordinates.getAxisX();
            pY = GameState.playerCoordinates.getAxisY();
        }

        boolean hiddenMap = false;
        if (mapSize * 32 > Window.WIDTH || mapSize * 32 > Window.HEIGHT)
            hiddenMap = true;

        for (HashMap.Entry<String, Coordinate> s : coordinates.entrySet()) {
            float x = s.getValue().getAxisX();
            float y = s.getValue().getAxisY();

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
