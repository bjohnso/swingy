package com.swingy.game;

import com.swingy.rendering.ui.Window;
import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.states.*;

public class Swingy implements Runnable{

    private StateManager stateManager;
    private Window window;

    private boolean gui;

    private boolean running;


    public void setGui(boolean gui) {
        this.window.setFrameVisibile(gui);
    }

    public boolean getGui(){
        return this.gui;
    }

    private void tick(){
        if (!this.gui && window.isShowing()){
            this.gui = true;
            //Input Listeners
            KeyInput keyInput = new KeyInput();
            window.addKeyListener(keyInput);
            window.getFrame().addKeyListener(keyInput);
            MouseInput mouseInput = new MouseInput();
            window.addMouseListener(mouseInput);
            window.addMouseMotionListener(mouseInput);
        }
        stateManager.tick();
    }

    private void render(){
        if (gui)
            window.render(stateManager);
    }

    @Override
    public void run() {
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
                unprocessed--;
                tps++;
                canRender = true;
            } else {
                canRender = false;
            }

            KeyInput.update();
            MouseInput.update();

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

    public Swingy(boolean gui){
        this.gui = gui;
        stateManager = new StateManager();

        //Initialise Window
        this.window = new Window(this);

        if (gui){
            KeyInput keyInput = new KeyInput();
            window.addKeyListener(keyInput);
            window.getFrame().addKeyListener(keyInput);
            MouseInput mouseInput = new MouseInput();
            window.addMouseListener(mouseInput);
            window.addMouseMotionListener(mouseInput);
        }

        stateManager.addState(new MenuState(this));
        stateManager.addState(new CharacterCreationState());
        stateManager.addState(new CharacterSelectionState());
        stateManager.addState(new SettingsState());
        stateManager.addState(new GameState());
        stateManager.addState(new BattleState());

        stateManager.setState("menu", null);

        start();
    }
}
