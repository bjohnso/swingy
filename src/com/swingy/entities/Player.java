package com.swingy.entities;

import com.swingy.input.KeyInput;
import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.states.GameState;
import com.swingy.states.State;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Mobile {
    public Player(Sprite sprite, double x, double y, State state, Animation animation) {
        super(sprite, x, y, state, animation);
    }

    @Override
    public void tick(){
        if (KeyInput.isDown(KeyEvent.VK_W))
            dy = -2;
        if (KeyInput.isDown(KeyEvent.VK_S))
            dy = 2;
        if (KeyInput.isDown(KeyEvent.VK_A))
            dx = -2;
        if (KeyInput.isDown(KeyEvent.VK_D))
            dx = 2;
        super.tick();
    }

    @Override
    public void render(Graphics graphics){
        super.render(graphics);
    }
}
