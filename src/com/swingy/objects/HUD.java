package com.swingy.objects;

import com.swingy.handlers.GameObjectHandler;
import com.swingy.id.ID;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HUD extends GameObject implements PropertyChangeListener {

    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH / 12 * 9;
    private static double HEALTH = 100;
    private static double hitPointCapacity;
    private static double damage = 0;
    private static int counter = 0;
    private String name;

    public HUD(int x, int y, ID id, String name) {
        super(x, y, id);
        this.name = name;
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (counter == 0)
            hitPointCapacity = (double)evt.getNewValue();
        damage = hitPointCapacity - (double)evt.getNewValue();

            if (name.equalsIgnoreCase(evt.getPropertyName())){
                this.HEALTH = Math.ceil(100 - ((damage / hitPointCapacity * 100)));
                System.out.println(HEALTH + "\n" + evt.getPropertyName() + "\n\n");
            }
        counter++;
    }
}
