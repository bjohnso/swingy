package com.swingy.handlers;

import com.swingy.interfaces.Renderable;
import com.swingy.objects.BattleObject;

import java.awt.*;
import java.util.LinkedList;

public class GameObjectHandler implements Renderable {

    LinkedList<BattleObject> objects= new LinkedList<>();

    @Override
    public void tick(){
        for (int i = 0; i < objects.size(); i++){
            BattleObject tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    @Override
    public void render(Graphics graphics) {
        for (int i = 0; i < objects.size(); i++){
            BattleObject tempObject = objects.get(i);

            tempObject.render(graphics);
        }
    }

    public LinkedList<BattleObject> getObjects() {
        return objects;
    }

    public void addObject(BattleObject battleObject){
        this.objects.add(battleObject);
    }

    public void removeObject(BattleObject battleObject){
        this.objects.remove(battleObject);
    }

}
