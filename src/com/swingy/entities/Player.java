package com.swingy.entities;

import com.swingy.input.KeyInput;
import com.swingy.rendering.textures.Sprite;
import com.swingy.states.GameState;

import java.awt.event.KeyEvent;

public class Player extends Mobile {
    public Player(Sprite sprite, double x, double y, GameState state) {
        super(sprite, x, y, state);
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
}
