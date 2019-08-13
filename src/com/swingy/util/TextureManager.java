package com.swingy.util;

import java.awt.image.BufferedImage;

public class TextureManager extends ResourceManager {

    private BufferedImage image;
    private boolean transform;

    public TextureManager(BufferedImage image, boolean transform){
        if (transform)
            this.image = ImageTransformer.horImage(image);
        else
            this.image = image;
        this.transform = transform;
    }

    public void transform(boolean transform){
        this.transform = transform;
        if (transform)
            this.image = ImageTransformer.horImage(image);
        else
            this.image = image;
    }

    public BufferedImage getImage(){
        return image;
    }
}
