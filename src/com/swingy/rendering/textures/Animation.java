package com.swingy.rendering.textures;

import javax.xml.soap.Text;
import java.awt.*;

public class Animation {

    private int count = 0;
    private int index = 0;
    int increment = 1;
    private int speed;
    private int numFrames;
    private Texture currentFrame;
    private Texture frames[];

    public Animation(int speed, Texture... frames){
        this.speed = speed;
        this.frames = frames;
        this.numFrames = frames.length;
    }

    private void nextFrame(){
        currentFrame = frames[index];
        index += increment;
        if (index >= numFrames || index <= 0) {
            increment *= -1;
            index += increment;
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
