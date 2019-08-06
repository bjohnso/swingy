package com.swingy.view;

import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
import com.swingy.rendering.textures.Texture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class Swingy extends Canvas implements Runnable{

    public static final String TITLE = "Swingy";
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 16 * 9;

    private double sX = 350, sY = 300;

    private JFrame frame;

    private boolean running;

    private Texture texture;

    private SpriteSheet sheet;
    private Sprite sprite;

    private void tick(){
        //TODO add Input Parameters
    }

    private void render(){
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null){
            createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        //Background
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        texture.render(graphics, 100, 100);
        sprite.render(graphics, 350, 300);

        //Clean graphics and display from Buffer Strategy
        graphics.dispose();
        bufferStrategy.show();
    }

    @Override
    public void run() {
        requestFocus();

        //Game Loop
        double targetTicks = 60.0;
        double nanoSecondsPT = 100000000.0 / targetTicks;
        double unprocessed = 0.0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int fps = 0;
        int tps = 0;
        boolean canRender = false;

        while (running){
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nanoSecondsPT;
            lastTime = now;

            if (unprocessed >= 1){
                tick();

                //Update Input References
                KeyInput.update();
                MouseInput.update();

                unprocessed--;
                tps++;
                canRender = true;
            } else {
                canRender = false;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (canRender){
                render();
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer){
                timer += 1000;
                System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
                fps = 0;
                tps = 0;
            }
        }

        System.exit(0);
    }

    private void start(){
        if (running)
            return;
        running = true;
        new Thread(this, "SwingyMain-Thread").start();
    }

    private void stop(){
        if (!running)
            return;
        running = false;
    }

    public Swingy(){
        texture = new Texture("test");

        sheet = new SpriteSheet(new Texture("test_sheet"), 64);
        sprite = new Sprite(sheet, 3, 1);

        //Initialise Window
        frame = new JFrame(TITLE);
        frame.add(this);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("Exiting Game");
                stop();
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();

        //Input Listeners
        addKeyListener(new KeyInput());
        MouseInput mouseInput = new MouseInput();
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

        start();
    }
}
