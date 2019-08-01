package com.swingy.view;

import com.swingy.handlers.GameObjectHandler;
import com.swingy.id.ID;
import com.swingy.input.KeyInput;
import com.swingy.objects.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BattleGame extends Canvas implements Runnable {

    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH / 12 * 9;

    private String imgPath = "res/background/background(";

    private Thread gameThread;
    private boolean running;
    private GameObjectHandler gameObjectHandler;
    private BufferedImage img;

    private HUD hud;

    public BattleGame(){

        gameObjectHandler = new GameObjectHandler();

        //Listen for Keyboard Input
        this.addKeyListener(new KeyInput(gameObjectHandler));

        gameObjectHandler.addObject(new Dino(DEFAULT_WIDTH / 100 * 5, DEFAULT_HEIGHT / 100 * 40, ID.Challenger, true));
        gameObjectHandler.addObject(new HUD(DEFAULT_WIDTH / 100 * 5, DEFAULT_HEIGHT / 100 * 5, ID.HUD));
        gameObjectHandler.addObject(new Robo(DEFAULT_WIDTH / 100 * 60,  DEFAULT_HEIGHT / 100 * 40, ID.Defender, false));
        gameObjectHandler.addObject(new HUD(DEFAULT_WIDTH / 100 * 65, DEFAULT_HEIGHT / 100 * 5, ID.HUD));
        new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT, "Battle", this);

    }

    public synchronized void start(){
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

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
        gameObjectHandler.tick();
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
        gameObjectHandler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }
}
