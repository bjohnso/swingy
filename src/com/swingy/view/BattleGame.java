package com.swingy.view;

import com.swingy.game.BattleEngine;
import com.swingy.handlers.GameObjectHandler;
import com.swingy.heroes.Hero;
import com.swingy.id.ID;
import com.swingy.input.KeyInputDeprecated;
import com.swingy.objects.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class BattleGame extends Canvas implements Runnable{

    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH / 12 * 9;

    private String imgPath = "res/background/background(";

    private Thread gameThread;
    private boolean running;
    private GameObjectHandler gameObjectHandler;
    private BufferedImage img;

    private HUD challengerHUD;
    private HUD defenderHUD;

    private Hero a = new Hero("Asuna", "Water");
    private Hero b = new Hero("Ragos", "Fire");
    private Hero c = new Hero("Titan", "Earth");

    private int turn = 1;

    private BattleEngine battleEngine;

    public BattleGame(){

        gameObjectHandler = new GameObjectHandler();

        //Listen for Keyboard Input
        this.addKeyListener(new KeyInputDeprecated(gameObjectHandler));

        challengerHUD = new HUD(DEFAULT_WIDTH / 100 * 5, DEFAULT_HEIGHT / 100 * 5, ID.ChallengerHUD);
        defenderHUD = new HUD(DEFAULT_WIDTH / 100 * 65, DEFAULT_HEIGHT / 100 * 5, ID.DefenderHUD);

        gameObjectHandler.addObject(new Dino(DEFAULT_WIDTH / 100 * 5, DEFAULT_HEIGHT / 100 * 40
                , ID.Challenger, true, challengerHUD));

        gameObjectHandler.addObject(new Robo(DEFAULT_WIDTH / 100 * 60,  DEFAULT_HEIGHT / 100 * 40
                , ID.Defender, false, defenderHUD));


        new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT, "Battle", this);

        //Initialising Battle Engine
        battleEngine = BattleEngine.getBattleEngine();
        battleEngine.addPropertyChangeListener((Player)gameObjectHandler.getObjects().get(0));
        battleEngine.addPropertyChangeListener((Player)gameObjectHandler.getObjects().get(1));

        battleEngine.setChallenger(b);
        battleEngine.setDefender(c);
    }

    /*public synchronized void start(){
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }*/

    public synchronized void stop(){
        try {
            gameThread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;
        this.requestFocus();
        //Game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running){
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void tick(){
        turn *= -1;
        gameObjectHandler.tick();
        /*battleEngine.battle(turn);*/
    }


    public void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null){
            this.createBufferStrategy(3);
            return ;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        try {
            img = ImageIO.read(new File(imgPath + (4) + ").png"));
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

}
