package com.swingy.objects;

import com.swingy.helpers.ImageTransformer;
import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject implements Renderable {

    protected String imgPath;
    protected BufferedImage img = null;
    protected boolean attack = false;

    private int idle = 10;
    private int counter = 1;
    private boolean screenLeft = true;


    public Player(int x, int y, ID id, boolean screenLeft) {
        super(x, y, id);
        this.screenLeft = screenLeft;
    }

    public void attack(){
        attack = true;
        idle = 1;
        counter = 1;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics graphics) {

        if (!attack) {
            if (idle == 1)
                counter = 1;
            else if (idle == 10)
                counter = -1;

                try {
                    if (screenLeft)
                        img = ImageIO.read(new File(imgPath + "idle/" + (idle) + ".png"));
                    else
                        img = ImageTransformer.horImage(ImageIO.read(new File(imgPath + "idle/" + (idle) + ".png")));
                } catch (IOException exc) {

                }
        }
        else {
            if (idle == 12)
                counter = -1;

                try {
                    if (screenLeft)
                        img = ImageIO.read(new File(imgPath + "attack/" + (idle) + ".png"));
                    else
                        img = ImageTransformer.horImage(ImageIO.read(new File(imgPath + "attack/" + (idle) + ".png")));
                } catch (IOException exc) {

                }
        }
        graphics.drawImage(img, x, y, 500, 500,  null);
        idle+=counter;
        if (counter == -1 && idle == 0) {
            attack = false;
            idle = 1;
        }
    }
}
