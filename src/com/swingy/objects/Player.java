package com.swingy.objects;

import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject implements Renderable {

    private BufferedImage img = null;
    private int idle = 1;

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics graphics) {

        //graphics.setColor(Color.WHITE);

        if (idle > 400)
            idle = 1;

        if (idle == 1){
            try {
                img = ImageIO.read(new File("res/Idle(" + idle + ").png"));
            } catch (IOException exc) {

            }
        }

        if (idle % 100 == 0 && idle > 99) {
            try {
                img = ImageIO.read(new File("res/Idle(" + (idle/100) + ").png"));
            } catch (IOException exc) {

            }
        }


        graphics.drawImage(img, 100, 100, 500, 500,  null);
            idle++;
        //graphics.fillRect(x, y, 150, (170 / 12 * 9 * 2));
    }
}
