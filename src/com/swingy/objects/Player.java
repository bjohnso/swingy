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
    protected boolean dead = false;

    private int frame = 10;
    private int counter = 1;
    private boolean screenLeft = true;


    public Player(int x, int y, ID id, boolean screenLeft) {
        super(x, y, id);
        this.screenLeft = screenLeft;
    }

    public void attack(){
        attack = true;
        frame = 1;
        counter = 1;
    }

    public void dead(){
        dead = true;
        frame = 1;
        counter = 1;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics graphics) {

        if (dead){
            if (frame == 1)
                counter = 1;
            else if (frame == 10)
                frame = 10;

            try {
                if (screenLeft)
                    img = ImageIO.read(new File(imgPath + "dead/" + (frame) + ".png"));
                else
                    img = ImageTransformer.horImage(ImageIO.read(new File(imgPath + "dead/" + (frame) + ".png")));
            } catch (IOException exc) {

            }
        }

        else if (!attack) {
            if (frame == 1)
                counter = 1;
            else if (frame == 10)
                counter = -1;

                try {
                    if (screenLeft)
                        img = ImageIO.read(new File(imgPath + "idle/" + (frame) + ".png"));
                    else
                        img = ImageTransformer.horImage(ImageIO.read(new File(imgPath + "idle/" + (frame) + ".png")));
                } catch (IOException exc) {

                }
        }
        else if (attack){
            if (frame == 12)
                counter = -1;

                try {
                    if (screenLeft)
                        img = ImageIO.read(new File(imgPath + "attack/" + (frame) + ".png"));
                    else
                        img = ImageTransformer.horImage(ImageIO.read(new File(imgPath + "attack/" + (frame) + ".png")));
                } catch (IOException exc) {

                }
        }
        graphics.drawImage(img, x, y, 500, 500,  null);
        frame+=counter;
        if (counter == -1 && frame == 0) {
            attack = false;
            frame = 1;
        }
    }
}
