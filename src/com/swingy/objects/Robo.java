package com.swingy.objects;

import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;

public class Robo extends Player implements Renderable {
    public Robo(int x, int y, ID id, boolean screenLeft) {
        super(x, y, id, screenLeft);
        imgPath = "res/robo/";
    }
}
