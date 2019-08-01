package com.swingy.objects;

import com.swingy.id.ID;

import java.awt.*;

public class HUD extends GameObject {

    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH / 12 * 9;
    private static int HEALTH = 100;

    public HUD(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        HEALTH--;
    }

    @Override
    public void render(Graphics graphics) {
        //Health bar Display
        graphics.setColor(Color.WHITE);
        graphics.drawRect(x,y, DEFAULT_WIDTH / 100 * 30, DEFAULT_HEIGHT / 100 * 5 );
        graphics.fillRect(x,y, DEFAULT_WIDTH / 100 * 30, DEFAULT_HEIGHT / 100 * 5 );

        //Current Health Stats
        graphics.setColor(Color.RED);
        graphics.fillRect(x,y, (DEFAULT_WIDTH / 100 * 30) / 100 * HEALTH, DEFAULT_HEIGHT / 100 * 5 );
    }
}
