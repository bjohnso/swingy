package com.swingy.rendering.textures;

import com.swingy.util.TextureManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Texture {

    private final static Map<String, TextureManager> textureMap = new HashMap<>();
    private TextureManager manager;
    private String fileName;

    public Texture(String fileName) {
        this.fileName = fileName;
        TextureManager oldTexture = textureMap.get(fileName);
        if (oldTexture != null) {
            manager = oldTexture;
            manager.addReference();
        } else {
            try {
                System.out.println("loading texture : " + fileName);
                manager = new TextureManager(ImageIO.read(new File("resources/textures/" + fileName + ".png")));
                textureMap.put(fileName, manager);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (manager.removeReference() && !fileName.isEmpty()) {
            textureMap.remove(fileName);
            System.out.println("removing texture from memory : " + fileName);
        }
        super.finalize();
    }

    public void render (Graphics graphics, double x, double y){
        graphics.drawImage(manager.getImage(), (int)x, (int)y, null);
    }

    public BufferedImage getImage(){
        return this.manager.getImage();
    }
}
