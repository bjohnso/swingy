package com.swingy.handlers;

import com.swingy.interfaces.Renderable;
import com.swingy.objects.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class GameObjectHandler implements Renderable {

    LinkedList<GameObject> objects= new LinkedList<>();

    @Override
    public void tick(){
        for (int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    @Override
    public void render(Graphics graphics) {
        for (int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);

            tempObject.render(graphics);
        }
    }

    public void addObject(GameObject gameObject){
        this.objects.add(gameObject);
    }

    public void removeObject(GameObject gameObject){
        this.objects.remove(gameObject);
    }

}
