package com.swingy.map;

import com.swingy.id.ID;
import com.swingy.rendering.textures.Texture;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Tile {

    HashMap<String, String> coordinates = new HashMap<>();
    protected Texture sprite;
    protected boolean solid;
    protected ID tileClass;
    protected String tileClassName;
    protected int MobileID;
    protected boolean isPlayer = false;
    protected String figherClassName;

    public Tile(float x, float y, Texture sprite){
        this.sprite = sprite;
        this.solid = true;
    }

    public Tile(float x, float y, Texture sprite, boolean isPlayer){
        this.sprite = sprite;
        this.solid = true;
        this.isPlayer = isPlayer;
    }

    public Tile(float x, float y, Texture sprite, ID tileClass){
        this.sprite = sprite;
        this.solid = true;
        this.tileClass = tileClass;
        setTileClassName();
    }

    public Tile(Texture sprite, boolean isPlayer){
        this.sprite = sprite;
        this.solid = true;
        this.isPlayer = isPlayer;
    }

    public Tile(Texture sprite, ID tileClass){
        this.sprite = sprite;
        this.solid = true;
        this.tileClass = tileClass;
        setTileClassName();
    }

    public void addCoordinate(String key, float x, float y){
        coordinates.put(key, x + "-" + y);
    }

    public void addCoordinate(String key, String coordinate){
        coordinates.put(key, coordinate);
    }

    public void removeCoordinate(float x, float y){
        coordinates.remove(x + "-" + y);
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

    public String getCoordinate(String key) {
        return coordinates.get(key);
    }

    /*public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void moveX(float x) {
        this.x += x;
    }

    public void moveY(float y) {
        this.y += y;
    }*/

    public void setTileClass(ID tileClass) {
        this.tileClass = tileClass;
    }

    public ID getTileClass(){
        return this.tileClass;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void render(Graphics graphics) {
        for (HashMap.Entry<String, String> s: coordinates.entrySet()) {
            String parts[] = s.getValue().split("-");
            sprite.render(graphics, Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
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
        return figherClassName;
    }

    public void setTileClassName(){
        switch (tileClass){
            case DINO:
                figherClassName = "DINO";
                tileClassName = "FIGHTER";
                break;
            case ROBO:
                figherClassName = "ROBO";
                tileClassName = "FIGHTER";
                break;
            case ZOMBO:
                figherClassName = "ZOMBO";
                tileClassName = "FIGHTER";
                break;
            case NINJA:
                figherClassName = "NINJA";
                tileClassName = "FIGHTER";
                break;
            case BORDER:
                figherClassName = "";
                tileClassName = "BORDER";
                break;
            case LAVA:
                figherClassName = "";
                tileClassName = "TRAP";
                break;
            case PIT:
                figherClassName = "";
                tileClassName = "TRAP";
                break;
            case GROUND:
                figherClassName = "";
                tileClassName = "GROUND";
                break;
            case MUSHROOM:
                figherClassName = "";
                tileClassName = "OBSTRUCTION";
                break;
            default:
                tileClassName = "";
                figherClassName = "";
        }
    }
}
