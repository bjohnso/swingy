package com.swingy.map;

import com.swingy.id.ID;
import com.swingy.rendering.textures.Texture;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Tile {

    ArrayList<String> coordinates = new ArrayList<>();;
    protected Texture sprite;
    protected boolean solid;
    protected ID tileClass;
    protected String tileClassName;
    protected int MobileID;
    protected boolean isPlayer = false;

    public Tile(float x, float y, Texture sprite){
        addCoordinate(x, y);
        this.sprite = sprite;
        this.solid = true;
    }

    public Tile(float x, float y, Texture sprite, boolean isPlayer){
        addCoordinate(x, y);
        this.sprite = sprite;
        this.solid = true;
        this.isPlayer = isPlayer;
    }

    public Tile(float x, float y, Texture sprite, ID tileClass){
        addCoordinate(x, y);
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

    public void addCoordinate(float x, float y){
        coordinates.add(x + "-" + y);
    }

    public void removeTile(float x, float y){
        coordinates.remove(x + "-" + y);
    }

    public ArrayList<String> getCoordinates() {
        return coordinates;
    }

    public String getCoordinate(int i) {
        return coordinates.get(i);
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
        for (String s : coordinates) {
            String parts[] = s.split("-");
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

    public void setTileClassName(){
        switch (tileClass){
            case DINO:
                tileClassName = "DINO";
                break;
            case ROBO:
                tileClassName = "ROBO";
                break;
            case ZOMBO:
                tileClassName = "ZOMBO";
                break;
            case NINJA:
                tileClassName = "NINJA";
                break;
            default:
                tileClassName = "";
        }
    }
}
