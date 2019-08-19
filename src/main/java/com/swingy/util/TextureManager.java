package com.swingy.util;

import java.awt.image.BufferedImage;

public class TextureManager extends ResourceManager {

    private BufferedImage image;
    private boolean transform = false;

    public TextureManager(BufferedImage image, boolean transform){
        if (transform && this.transform != transform)
            this.image = ImageTransformer.horImage(image);
        else
            this.image = image;
        this.transform = transform;
    }

    public void transform(boolean transform){
        if (this.transform != transform)
            this.image = ImageTransformer.horImage(image);
        this.transform = transform;
    }

    public BufferedImage getImage(){
        return image;
    }
}
