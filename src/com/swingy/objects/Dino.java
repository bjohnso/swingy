package com.swingy.objects;

import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;

public class Dino extends Player implements Renderable {
    public Dino(int x, int y, ID id, boolean screenLeft) {
        super(x, y, id, screenLeft);
        imgPath = "res/dino/";
    }
}
