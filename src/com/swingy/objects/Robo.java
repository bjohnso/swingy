package com.swingy.objects;

import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;
import com.swingy.rendering.objects.HUD;

public class Robo extends Player implements Renderable {
    public Robo(int x, int y, ID id, boolean screenLeft, HUD hud) {
        super(x, y, id, screenLeft, hud);
        imgPath = "res/robo/";
    }
}
