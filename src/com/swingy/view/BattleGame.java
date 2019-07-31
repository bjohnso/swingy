package com.swingy.view;

import com.swingy.handlers.GameObjectHandler;
import com.swingy.id.ID;
import com.swingy.objects.Player;
import com.swingy.objects.Sprite;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class BattleGame extends Canvas implements Runnable {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = DEFAULT_WIDTH / 12 * 9;

    private Thread gameThread;
    private boolean running;
    private GameObjectHandler gameObjectHandler;

    public BattleGame(){
        gameObjectHandler = new GameObjectHandler();
        new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT, "Battle", this);

        gameObjectHandler.addObject(new Player(150, 100, ID.Defender));
        //gameObjectHandler.addObject(new Player(500,  250, ID.Challenger));
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
                System.out.println("FPS: " + frames);
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

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        gameObjectHandler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }
}
