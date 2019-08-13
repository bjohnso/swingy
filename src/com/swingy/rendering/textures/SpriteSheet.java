package com.swingy.rendering.textures;

public class SpriteSheet {

    private Texture texture;
    private int width, height;

    public SpriteSheet(Texture texture, int size){
        this(texture, size, size);
    }

    public SpriteSheet(Texture texture, int width, int height){
        this.texture = texture;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Texture getTexture() {
        return this.texture;
    }
}
