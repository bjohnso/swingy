package com.swingy.rendering.textures;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    private BufferedImage image;

    public Sprite(SpriteSheet spriteSheet, int x, int y){
        this.image = spriteSheet.getTexture().getImage().getSubimage((x * spriteSheet.getWidth()) - spriteSheet.getWidth(),
                (y * spriteSheet.getHeight()) - spriteSheet.getHeight(),
                spriteSheet.getWidth(), spriteSheet.getHeight());
    }

    public void render(Graphics graphics, double x, double y){
        graphics.drawImage(image, (int)x, (int)y, null);
    }
}
