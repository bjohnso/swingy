package com.swingy.states;

import com.swingy.entities.Entity;
import com.swingy.game.BattleEngine;
import com.swingy.handlers.GameObjectHandler;
import com.swingy.heroes.Hero;
import com.swingy.id.ID;
import com.swingy.objects.*;
import com.swingy.states.State;
import com.swingy.states.StateManager;
import com.swingy.view.Swingy;
import com.swingy.view.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class BattleState extends Canvas implements State {

    private static final int DEFAULT_WIDTH = Swingy.WIDTH;
    private static final int DEFAULT_HEIGHT = Swingy.HEIGHT;

    private String imgPath = "res/background/";

    private GameObjectHandler gameObjectHandler;
    private BufferedImage img;

    private boolean stateResume = false;

    private HUD challengerHUD;
    private HUD defenderHUD;

    private Hero a = new Hero("Asuna", "Water");
    private Hero b = new Hero("Ragos", "Fire");

    private BattleEngine battleEngine;

    @Override
    public void init() {
        gameObjectHandler = new GameObjectHandler();

        challengerHUD = new HUD(DEFAULT_WIDTH / 100 * 5, DEFAULT_HEIGHT / 100 * 5, ID.ChallengerHUD);
        defenderHUD = new HUD(DEFAULT_WIDTH / 100 * 65, DEFAULT_HEIGHT / 100 * 5, ID.DefenderHUD);

        gameObjectHandler.addObject(new Dino(DEFAULT_WIDTH / 100 * 5, DEFAULT_HEIGHT / 100 * 40
                , ID.Challenger, true, challengerHUD));

        gameObjectHandler.addObject(new Robo(DEFAULT_WIDTH / 100 * 60,  DEFAULT_HEIGHT / 100 * 40
                , ID.Defender, false, defenderHUD));


        //Initialising Battle Engine
        battleEngine = BattleEngine.getBattleEngine();
        battleEngine.addPropertyChangeListener((Player)gameObjectHandler.getObjects().get(0));
        battleEngine.addPropertyChangeListener((Player)gameObjectHandler.getObjects().get(1));

        battleEngine.setChallenger(a);
        battleEngine.setDefender(b);
    }

    @Override
    public String getName() {
        return "battle";
    }

    @Override
    public State enterState() {
        if (!stateResume)
            init();
        return this;
    }

    @Override
    public void exitState() {
        stateResume = true;
    }

    @Override
    public void tick(StateManager stateManager) {
        gameObjectHandler.tick();
    }

    @Override
    public void render(Graphics graphics) {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null){
            this.createBufferStrategy(3);
            return ;
        }

        graphics = bufferStrategy.getDrawGraphics();

        try {
            img = ImageIO.read(new File(imgPath + (4) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        graphics.drawImage(img, 0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT,  null);

        graphics.setColor(Color.BLUE);
        graphics.fillRoundRect(DEFAULT_WIDTH / 100 * 6, DEFAULT_HEIGHT / 100 * 10 , 80, 80, 80, 80);
        graphics.fillRoundRect(DEFAULT_WIDTH / 100 * 13, DEFAULT_HEIGHT / 100 * 10 , 80, 80, 80, 80);

        gameObjectHandler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    @Override
    public void addEntity(Entity entity) {

    }
}
