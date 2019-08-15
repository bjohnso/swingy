package com.swingy.statics;

import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Texture;

public class Statics {

    private static int animationSpeedFast = 10;
    private static int animationSpeedSlow = 30;

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
                        new Texture("ninja/idle/1", false),
                        new Texture("ninja/idle/2", false),
                        new Texture("ninja/idle/3", false),
                        new Texture("ninja/idle/4", false),
                        new Texture("ninja/idle/5", false),
                        new Texture("ninja/idle/6", false),
                        new Texture("ninja/idle/7", false),
                        new Texture("ninja/idle/8", false),
                        new Texture("ninja/idle/9", false),
                        new Texture("ninja/idle/10", false));
                break;
            case "dinoLarge":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("dino/idle/1",false),
                        new Texture("dino/idle/2",false),
                        new Texture("dino/idle/3",false),
                        new Texture("dino/idle/4",false),
                        new Texture("dino/idle/5",false),
                        new Texture("dino/idle/6",false),
                        new Texture("dino/idle/7",false),
                        new Texture("dino/idle/8",false),
                        new Texture("dino/idle/9",false),
                        new Texture("dino/idle/10",false));
                break;
            case "roboLarge":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("robo/idle/1",false),
                        new Texture("robo/idle/2",false),
                        new Texture("robo/idle/3",false),
                        new Texture("robo/idle/4",false),
                        new Texture("robo/idle/5",false),
                        new Texture("robo/idle/6",false),
                        new Texture("robo/idle/7",false),
                        new Texture("robo/idle/8",false),
                        new Texture("robo/idle/9",false),
                        new Texture("robo/idle/10",false));
                break;
            case "zomboLarge":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("zombo/idle/1",false),
                        new Texture("zombo/idle/2",false),
                        new Texture("zombo/idle/3",false),
                        new Texture("zombo/idle/4",false),
                        new Texture("zombo/idle/5",false),
                        new Texture("zombo/idle/6",false),
                        new Texture("zombo/idle/7",false),
                        new Texture("zombo/idle/8",false),
                        new Texture("zombo/idle/9",false),
                        new Texture("zombo/idle/10",false),
                        new Texture("zombo/idle/11",false),
                        new Texture("zombo/idle/12",false),
                        new Texture("zombo/idle/13",false),
                        new Texture("zombo/idle/14",false),
                        new Texture("zombo/idle/15",false));
                break;
            case "ninjaLargeRef":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("ninja/idle/1", true),
                        new Texture("ninja/idle/2", true),
                        new Texture("ninja/idle/3", true),
                        new Texture("ninja/idle/4", true),
                        new Texture("ninja/idle/5", true),
                        new Texture("ninja/idle/6", true),
                        new Texture("ninja/idle/7", true),
                        new Texture("ninja/idle/8", true),
                        new Texture("ninja/idle/9", true),
                        new Texture("ninja/idle/10", true));
                break;
            case "dinoLargeRef":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("dino/idle/1", true),
                        new Texture("dino/idle/2", true),
                        new Texture("dino/idle/3", true),
                        new Texture("dino/idle/4", true),
                        new Texture("dino/idle/5", true),
                        new Texture("dino/idle/6", true),
                        new Texture("dino/idle/7", true),
                        new Texture("dino/idle/8", true),
                        new Texture("dino/idle/9", true),
                        new Texture("dino/idle/10", true));
                break;
            case "roboLargeRef":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("robo/idle/1", true),
                        new Texture("robo/idle/2", true),
                        new Texture("robo/idle/3", true),
                        new Texture("robo/idle/4", true),
                        new Texture("robo/idle/5", true),
                        new Texture("robo/idle/6", true),
                        new Texture("robo/idle/7", true),
                        new Texture("robo/idle/8", true),
                        new Texture("robo/idle/9", true),
                        new Texture("robo/idle/10", true));
                break;
            case "zomboLargeRef":
                anime = new Animation(animationSpeedFast, true,
                        new Texture("zombo/idle/1", true),
                        new Texture("zombo/idle/2", true),
                        new Texture("zombo/idle/3", true),
                        new Texture("zombo/idle/4", true),
                        new Texture("zombo/idle/5", true),
                        new Texture("zombo/idle/6", true),
                        new Texture("zombo/idle/7", true),
                        new Texture("zombo/idle/8", true),
                        new Texture("zombo/idle/9", true),
                        new Texture("zombo/idle/10", true),
                        new Texture("zombo/idle/11", true),
                        new Texture("zombo/idle/12", true),
                        new Texture("zombo/idle/13", true),
                        new Texture("zombo/idle/14", true),
                        new Texture("zombo/idle/15", true));
                break;
            case "ninjaDeath":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("ninja/dead/1", false),
                        new Texture("ninja/dead/2", false),
                        new Texture("ninja/dead/3", false),
                        new Texture("ninja/dead/4", false),
                        new Texture("ninja/dead/5", false),
                        new Texture("ninja/dead/6", false),
                        new Texture("ninja/dead/7", false),
                        new Texture("ninja/dead/8", false),
                        new Texture("ninja/dead/9", false),
                        new Texture("ninja/dead/10", false));
                break;
            case "dinoDeath":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("dino/dead/1", false),
                        new Texture("dino/dead/2", false),
                        new Texture("dino/dead/3", false),
                        new Texture("dino/dead/4", false),
                        new Texture("dino/dead/5", false),
                        new Texture("dino/dead/6", false),
                        new Texture("dino/dead/7", false),
                        new Texture("dino/dead/8", false));
                break;
            case "roboDeath":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("robo/dead/1", false),
                        new Texture("robo/dead/2", false),
                        new Texture("robo/dead/3", false),
                        new Texture("robo/dead/4", false),
                        new Texture("robo/dead/5", false),
                        new Texture("robo/dead/6", false),
                        new Texture("robo/dead/7", false),
                        new Texture("robo/dead/8", false),
                        new Texture("robo/dead/9", false),
                        new Texture("robo/dead/10", false));
                break;
            case "zomboDeath":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("zombo/dead/1", false),
                        new Texture("zombo/dead/2", false),
                        new Texture("zombo/dead/3", false),
                        new Texture("zombo/dead/4", false),
                        new Texture("zombo/dead/5", false),
                        new Texture("zombo/dead/6", false),
                        new Texture("zombo/dead/7", false),
                        new Texture("zombo/dead/8", false),
                        new Texture("zombo/dead/9", false),
                        new Texture("zombo/dead/10", false),
                        new Texture("zombo/dead/11", false),
                        new Texture("zombo/dead/12", false));
                break;
            case "ninjaDeathRef":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("ninja/dead/1", true),
                        new Texture("ninja/dead/2", true),
                        new Texture("ninja/dead/3", true),
                        new Texture("ninja/dead/4", true),
                        new Texture("ninja/dead/5", true),
                        new Texture("ninja/dead/6", true),
                        new Texture("ninja/dead/7", true),
                        new Texture("ninja/dead/8", true),
                        new Texture("ninja/dead/9", true),
                        new Texture("ninja/dead/10", true));
                break;
            case "dinoDeathRef":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("dino/dead/1", true),
                        new Texture("dino/dead/2", true),
                        new Texture("dino/dead/3", true),
                        new Texture("dino/dead/4", true),
                        new Texture("dino/dead/5", true),
                        new Texture("dino/dead/6", true),
                        new Texture("dino/dead/7", true),
                        new Texture("dino/dead/8", true));
                break;
            case "roboDeathRef":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("robo/dead/1", true),
                        new Texture("robo/dead/2", true),
                        new Texture("robo/dead/3", true),
                        new Texture("robo/dead/4", true),
                        new Texture("robo/dead/5", true),
                        new Texture("robo/dead/6", true),
                        new Texture("robo/dead/7", true),
                        new Texture("robo/dead/8", true),
                        new Texture("robo/dead/9", true),
                        new Texture("robo/dead/10", true));
                break;
            case "zomboDeathRef":
                anime = new Animation(animationSpeedFast, false,
                        new Texture("zombo/dead/1", true),
                        new Texture("zombo/dead/2", true),
                        new Texture("zombo/dead/3", true),
                        new Texture("zombo/dead/4", true),
                        new Texture("zombo/dead/5", true),
                        new Texture("zombo/dead/6", true),
                        new Texture("zombo/dead/7", true),
                        new Texture("zombo/dead/8", true),
                        new Texture("zombo/dead/9", true),
                        new Texture("zombo/dead/10", true),
                        new Texture("zombo/dead/11", true),
                        new Texture("zombo/dead/12", true));
                break;
        }
        return anime;
    }

}
