package com.swingy.statics;

import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Texture;

public class Statics {

    private static int animationSpeedFast = 10;
    private static int animationSpeedSlow = 30;

    public static Animation ninjaTerrain = new Animation(animationSpeedSlow,
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

    public static Animation dinoTerrain = new Animation(animationSpeedSlow,
            new Texture("terrain/dino/1", false),
            new Texture("terrain/dino/2", false),
            new Texture("terrain/dino/3", false),
            new Texture("terrain/dino/4", false),
            new Texture("terrain/dino/5", false),
            new Texture("terrain/dino/6", false),
            new Texture("terrain/dino/7", false),
            new Texture("terrain/dino/8", false));

    public static Animation roboTerrain = new Animation(animationSpeedSlow,
            new Texture("terrain/robo/1", false),
            new Texture("terrain/robo/2", false),
            new Texture("terrain/robo/3", false),
            new Texture("terrain/robo/4", false),
            new Texture("terrain/robo/5", false),
            new Texture("terrain/robo/6", false),
            new Texture("terrain/robo/7", false),
            new Texture("terrain/robo/8", false));

    public static Animation zomboTerrain = new Animation(animationSpeedSlow,
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

    public static Animation ninjaLarge = new Animation(animationSpeedFast,
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

    public static Animation dinoLarge = new Animation(animationSpeedFast,
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

    public static Animation roboLarge = new Animation(animationSpeedFast,
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

    public static Animation zomboLarge = new Animation(animationSpeedFast,
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

    public static Animation ninjaLargeRef = new Animation(animationSpeedFast,
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

    public static Animation dinoLargeRef = new Animation(animationSpeedFast,
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

    public static Animation roboLargeRef = new Animation(animationSpeedFast,
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

    public static Animation zomboLargeRef = new Animation(animationSpeedFast,
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

}
