package com.swingy.rendering.textures;

import javax.xml.soap.Text;
import java.awt.*;

public class Animation {

    private int count = 0;
    private int index = 0;
    int increment = 1;
    private int speed;
    private boolean loop;
    private int numFrames;
    private Texture currentFrame;
    private Texture frames[];

    public Animation(int speed, boolean loop, Texture... frames){
        this.speed = speed;
        this.frames = frames;
        this.numFrames = frames.length;
        this.loop = loop;
    }

    private void nextFrame(){
        if (index < numFrames)
            currentFrame = frames[index];
        index += increment;
        if (index >= numFrames || index <= 0) {
            if (loop) {
                increment *= -1;
                index += increment;
            }
        }
    }

    public void tick(){
        if (++count > speed){
            count = 0;
            nextFrame();
        }
    }

    public void render(Graphics graphics, double x, double y){
        if (currentFrame != null) {
            currentFrame.render(graphics, x, y);
        }
    }
}
