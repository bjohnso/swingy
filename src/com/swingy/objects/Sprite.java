package com.swingy.objects;

import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;

import java.awt.*;

public class Sprite extends BattleObject implements Renderable {
    public Sprite(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, 32, 32);
    }
}
