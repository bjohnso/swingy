package com.swingy.rendering.textures;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Animation{

    private PropertyChangeSupport support;

    private int count = 0;
    private int index = 0;
    private int tickLag = 0;
    int increment = 1;
    private int speed;
    private boolean loop;
    private int numFrames;
    private Texture currentFrame;
    private Texture frames[];
    private boolean animationEnd = false;

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    private void animationEnd(){
        this.animationEnd = true;
        support.firePropertyChange("AnimationEND", false, this.animationEnd);
    }

    public Animation(int speed, boolean loop, Texture... frames){
        this.speed = speed;
        this.frames = frames;
        this.numFrames = frames.length;
        this.loop = loop;
        this.support = new PropertyChangeSupport(this);
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
            else if(!animationEnd) {
                if (tickLag++ > 10)
                    animationEnd();
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
