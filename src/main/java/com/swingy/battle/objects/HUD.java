package com.swingy.battle.objects;

import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;

import java.awt.*;

public class HUD extends BattleObject implements Renderable{

    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH / 12 * 9;
    private double HEALTH = 100;
    private double hitPointCapacity;
    private double damage = 0;
    private int counter = 0;

    public HUD(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public ID getId() {
        return super.getId();
    }

    @Override
    public void render(Graphics graphics) {

        int width = DEFAULT_WIDTH / 100 * 30;

        //Health bar Display
        graphics.setColor(Color.WHITE);
        graphics.drawRect(x,y, width, DEFAULT_HEIGHT / 100 * 5 );
        graphics.fillRect(x,y, width, DEFAULT_HEIGHT / 100 * 5 );

        //Current Health Stats
        graphics.setColor(Color.RED);
        graphics.fillRect(x,y, width * ((int)HEALTH) / 100, DEFAULT_HEIGHT / 100 * 5 );
    }

    public void setHealth(Double hitPoints) {

        if (counter == 0)
            hitPointCapacity = hitPoints;
        damage = hitPointCapacity - hitPoints;

        if (damage == 0)
            HEALTH = HEALTH;
        else
            HEALTH = Math.ceil(100 - ((damage / hitPointCapacity * 100)));

        counter++;
    }

}
