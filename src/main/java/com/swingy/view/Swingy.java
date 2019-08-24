package com.swingy.view;

import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.states.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class Swingy extends Canvas implements Runnable{

    public static final String TITLE = "Swingy";
    public static final int WIDTH = 1920;
    public static final int HEIGHT = WIDTH / 16 * 9;

    private StateManager stateManager;

    private JFrame frame;

    private boolean running;

    private void tick(){
        //Input Logic
        stateManager.tick();
    }

    private void render(){
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null){
            createBufferStrategy(2);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        //Background
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        //MenuState
        stateManager.render(graphics);

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
                //MouseInput.update();

                unprocessed--;
                tps++;
                canRender = true;
            } else {
                canRender = false;
            }

            try {
                Thread.sleep(10);
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

    public void stop(){
        if (!running)
            return;
        running = false;
    }

    public Swingy(){
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
        //MouseInput mouseInput = new MouseInput();
        //addMouseListener(mouseInput);
        //addMouseMotionListener(mouseInput);

        stateManager = new StateManager();
        stateManager.addState(new MenuState(this));
        stateManager.addState(new CharacterCreationState());
        stateManager.addState(new CharacterSelectionState());
        stateManager.addState(new SettingsState());
        stateManager.addState(new GameState());
        stateManager.addState(new BattleState());

        start();
    }
}
