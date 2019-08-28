package com.swingy.util;

import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Texture;
import com.swingy.view.Swingy;

public class AnimationHelper {

    public AnimationHelper(){

    }

    private static int animationSpeedFast = 10;
    private static int animationSpeedSlow = 30;
    private static int imageWidth = Swingy.WIDTH / 100 * 30;
    private static int imageHeight = Swingy.HEIGHT / 100 * 60;

    public static Animation createAnimation(String animation){
        Animation anime = null;
        switch (animation) {
            case "ninjaTerrain":
                anime = new Animation(animationSpeedSlow, true,
                        new Texture("terrain/ninja/1", false),
                        new Texture("terrain/ninja/2", false),
                        new Texture("terrain/ninja/3", false),
                        new Texture("terrain/ninja/4", false),
                        new Texture("terrain/ninja/5", false),
                        new Texture("terrain/ninja/6", false),
                        new Texture("terrain/ninja/7", false),
                        new Texture("terrain/ninja/8", false),
                        new Texture("terrain/ninja/9", false),
                        new Texture("terrain/ninja/10", false));
                break;
            case "dinoTerrain":
                anime = new Animation(animationSpeedSlow, true,
                        new Texture("terrain/dino/1", false),
                        new Texture("terrain/dino/2", false),
                        new Texture("terrain/dino/3", false),
                        new Texture("terrain/dino/4", false),
                        new Texture("terrain/dino/5", false),
                        new Texture("terrain/dino/6", false),
                        new Texture("terrain/dino/7", false),
                        new Texture("terrain/dino/8", false));
                break;
            case "roboTerrain":
                anime = new Animation(animationSpeedSlow, true,
                    new Texture("terrain/robo/1", false),
                    new Texture("terrain/robo/2", false),
                    new Texture("terrain/robo/3", false),
                    new Texture("terrain/robo/4", false),
                    new Texture("terrain/robo/5", false),
                    new Texture("terrain/robo/6", false),
                    new Texture("terrain/robo/7", false),
                    new Texture("terrain/robo/8", false));
                break;
            case "zomboTerrain":
                anime = new Animation(animationSpeedSlow, true,
                    new Texture("terrain/zombo/1", false),
                    new Texture("terrain/zombo/2", false),
                    new Texture("terrain/zombo/3", false),
                    new Texture("terrain/zombo/4", false),
                    new Texture("terrain/zombo/5", false),
                    new Texture("terrain/zombo/6", false),
                    new Texture("terrain/zombo/7", false),
                    new Texture("terrain/zombo/8", false),
                    new Texture("terrain/zombo/9", false),
                    new Texture("terrain/zombo/10", false));
                break;
            case "ninjaLarge":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("ninja/idle/1", imageWidth, imageHeight,false),
                        new Texture("ninja/idle/2", imageWidth, imageHeight,false),
                        new Texture("ninja/idle/3", imageWidth, imageHeight,false),
                        new Texture("ninja/idle/4", imageWidth, imageHeight,false),
                        new Texture("ninja/idle/5", imageWidth, imageHeight,false),
                        new Texture("ninja/idle/6", imageWidth, imageHeight,false),
                        new Texture("ninja/idle/7", imageWidth, imageHeight,false),
                        new Texture("ninja/idle/8", imageWidth, imageHeight,false),
                        new Texture("ninja/idle/9", imageWidth, imageHeight,false),
                        new Texture("ninja/idle/10", imageWidth, imageHeight,false));
                break;
            case "dinoLarge":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("dino/idle/1", imageWidth, imageHeight,false),
                        new Texture("dino/idle/2", imageWidth, imageHeight,false),
                        new Texture("dino/idle/3", imageWidth, imageHeight,false),
                        new Texture("dino/idle/4", imageWidth, imageHeight,false),
                        new Texture("dino/idle/5", imageWidth, imageHeight,false),
                        new Texture("dino/idle/6", imageWidth, imageHeight,false),
                        new Texture("dino/idle/7", imageWidth, imageHeight,false),
                        new Texture("dino/idle/8", imageWidth, imageHeight,false),
                        new Texture("dino/idle/9", imageWidth, imageHeight,false),
                        new Texture("dino/idle/10", imageWidth, imageHeight,false));
                break;
            case "roboLarge":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("robo/idle/1", imageWidth, imageHeight,false),
                        new Texture("robo/idle/2", imageWidth, imageHeight,false),
                        new Texture("robo/idle/3", imageWidth, imageHeight,false),
                        new Texture("robo/idle/4", imageWidth, imageHeight,false),
                        new Texture("robo/idle/5", imageWidth, imageHeight,false),
                        new Texture("robo/idle/6", imageWidth, imageHeight,false),
                        new Texture("robo/idle/7", imageWidth, imageHeight,false),
                        new Texture("robo/idle/8", imageWidth, imageHeight,false),
                        new Texture("robo/idle/9", imageWidth, imageHeight,false),
                        new Texture("robo/idle/10", imageWidth, imageHeight,false));
                break;
            case "zomboLarge":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("zombo/idle/1", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/2", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/3", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/4", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/5", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/6", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/7", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/8", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/9", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/10", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/11", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/12", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/13", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/14", imageWidth, imageHeight,false),
                        new Texture("zombo/idle/15", imageWidth, imageHeight,false));
                break;
            case "ninjaLargeRef":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("ninja/idle/1", imageWidth, imageHeight, true),
                        new Texture("ninja/idle/2", imageWidth, imageHeight, true),
                        new Texture("ninja/idle/3", imageWidth, imageHeight, true),
                        new Texture("ninja/idle/4", imageWidth, imageHeight, true),
                        new Texture("ninja/idle/5", imageWidth, imageHeight, true),
                        new Texture("ninja/idle/6", imageWidth, imageHeight,true),
                        new Texture("ninja/idle/7", imageWidth, imageHeight, true),
                        new Texture("ninja/idle/8", imageWidth, imageHeight, true),
                        new Texture("ninja/idle/9", imageWidth, imageHeight,true),
                        new Texture("ninja/idle/10", imageWidth, imageHeight,true));
                break;
            case "dinoLargeRef":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("dino/idle/1", imageWidth, imageHeight, true),
                        new Texture("dino/idle/2", imageWidth, imageHeight,true),
                        new Texture("dino/idle/3", imageWidth, imageHeight,true),
                        new Texture("dino/idle/4", imageWidth, imageHeight,true),
                        new Texture("dino/idle/5", imageWidth, imageHeight,true),
                        new Texture("dino/idle/6", imageWidth, imageHeight,true),
                        new Texture("dino/idle/7", imageWidth, imageHeight,true),
                        new Texture("dino/idle/8", imageWidth, imageHeight,true),
                        new Texture("dino/idle/9", imageWidth, imageHeight,true),
                        new Texture("dino/idle/10", imageWidth, imageHeight,true));
                break;
            case "roboLargeRef":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("robo/idle/1", imageWidth, imageHeight,true),
                        new Texture("robo/idle/2", imageWidth, imageHeight,true),
                        new Texture("robo/idle/3", imageWidth, imageHeight,true),
                        new Texture("robo/idle/4", imageWidth, imageHeight,true),
                        new Texture("robo/idle/5", imageWidth, imageHeight,true),
                        new Texture("robo/idle/6", imageWidth, imageHeight,true),
                        new Texture("robo/idle/7", imageWidth, imageHeight,true),
                        new Texture("robo/idle/8", imageWidth, imageHeight,true),
                        new Texture("robo/idle/9", imageWidth, imageHeight,true),
                        new Texture("robo/idle/10", imageWidth, imageHeight, true));
                break;
            case "zomboLargeRef":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("zombo/idle/1", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/2", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/3", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/4", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/5", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/6", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/7", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/8", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/9", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/10", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/11", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/12", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/13", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/14", imageWidth, imageHeight,true),
                        new Texture("zombo/idle/15", imageWidth, imageHeight,true));
                break;
            case "ninjaDeath":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("ninja/dead/1", imageWidth, imageHeight,false),
                        new Texture("ninja/dead/2", imageWidth, imageHeight,false),
                        new Texture("ninja/dead/3", imageWidth, imageHeight,false),
                        new Texture("ninja/dead/4", imageWidth, imageHeight,false),
                        new Texture("ninja/dead/5", imageWidth, imageHeight,false),
                        new Texture("ninja/dead/6", imageWidth, imageHeight,false),
                        new Texture("ninja/dead/7", imageWidth, imageHeight,false),
                        new Texture("ninja/dead/8", imageWidth, imageHeight,false),
                        new Texture("ninja/dead/9", imageWidth, imageHeight,false),
                        new Texture("ninja/dead/10", imageWidth, imageHeight,false));
                break;
            case "dinoDeath":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("dino/dead/1", imageWidth, imageHeight,false),
                        new Texture("dino/dead/2", imageWidth, imageHeight,false),
                        new Texture("dino/dead/3", imageWidth, imageHeight,false),
                        new Texture("dino/dead/4", imageWidth, imageHeight,false),
                        new Texture("dino/dead/5", imageWidth, imageHeight,false),
                        new Texture("dino/dead/6", imageWidth, imageHeight,false),
                        new Texture("dino/dead/7", imageWidth, imageHeight,false),
                        new Texture("dino/dead/8", imageWidth, imageHeight,false));
                break;
            case "roboDeath":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("robo/dead/1", imageWidth, imageHeight,false),
                        new Texture("robo/dead/2", imageWidth, imageHeight,false),
                        new Texture("robo/dead/3", imageWidth, imageHeight,false),
                        new Texture("robo/dead/4", imageWidth, imageHeight,false),
                        new Texture("robo/dead/5", imageWidth, imageHeight,false),
                        new Texture("robo/dead/6", imageWidth, imageHeight,false),
                        new Texture("robo/dead/7", imageWidth, imageHeight,false),
                        new Texture("robo/dead/8", imageWidth, imageHeight,false),
                        new Texture("robo/dead/9", imageWidth, imageHeight,false),
                        new Texture("robo/dead/10", imageWidth, imageHeight,false));
                break;
            case "zomboDeath":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("zombo/dead/1", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/2", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/3", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/4", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/5", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/6", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/7", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/8", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/9", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/10", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/11", imageWidth, imageHeight,false),
                        new Texture("zombo/dead/12", imageWidth, imageHeight,false));
                break;
            case "ninjaDeathRef":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("ninja/dead/1", imageWidth, imageHeight,true),
                        new Texture("ninja/dead/2", imageWidth, imageHeight,true),
                        new Texture("ninja/dead/3", imageWidth, imageHeight,true),
                        new Texture("ninja/dead/4", imageWidth, imageHeight,true),
                        new Texture("ninja/dead/5", imageWidth, imageHeight,true),
                        new Texture("ninja/dead/6", imageWidth, imageHeight,true),
                        new Texture("ninja/dead/7", imageWidth, imageHeight,true),
                        new Texture("ninja/dead/8", imageWidth, imageHeight,true),
                        new Texture("ninja/dead/9", imageWidth, imageHeight,true),
                        new Texture("ninja/dead/10", imageWidth, imageHeight,true));
                break;
            case "dinoDeathRef":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("dino/dead/1", imageWidth, imageHeight,true),
                        new Texture("dino/dead/2", imageWidth, imageHeight,true),
                        new Texture("dino/dead/3", imageWidth, imageHeight,true),
                        new Texture("dino/dead/4", imageWidth, imageHeight,true),
                        new Texture("dino/dead/5", imageWidth, imageHeight,true),
                        new Texture("dino/dead/6", imageWidth, imageHeight,true),
                        new Texture("dino/dead/7", imageWidth, imageHeight,true),
                        new Texture("dino/dead/8", imageWidth, imageHeight,true));
                break;
            case "roboDeathRef":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("robo/dead/1", imageWidth, imageHeight,true),
                        new Texture("robo/dead/2", imageWidth, imageHeight,true),
                        new Texture("robo/dead/3", imageWidth, imageHeight,true),
                        new Texture("robo/dead/4", imageWidth, imageHeight,true),
                        new Texture("robo/dead/5", imageWidth, imageHeight,true),
                        new Texture("robo/dead/6", imageWidth, imageHeight,true),
                        new Texture("robo/dead/7", imageWidth, imageHeight,true),
                        new Texture("robo/dead/8", imageWidth, imageHeight,true),
                        new Texture("robo/dead/9", imageWidth, imageHeight,true),
                        new Texture("robo/dead/10", imageWidth, imageHeight,true));
                break;
            case "zomboDeathRef":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("zombo/dead/1", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/2", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/3", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/4", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/5", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/6", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/7", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/8", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/9", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/10", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/11", imageWidth, imageHeight,true),
                        new Texture("zombo/dead/12", imageWidth, imageHeight,true));
                break;
        }
        return anime;
    }

}
