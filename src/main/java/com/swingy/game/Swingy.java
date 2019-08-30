package com.swingy.game;

import com.swingy.rendering.ui.Window;
import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.states.*;

public class Swingy implements Runnable{

    private StateManager stateManager;
    private Window window;

    private boolean running;

    private void tick(){
        stateManager.tick();
    }

    private void render(){
        //window.render(stateManager);
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

                //Update Input References
                //KeyInput.update();
               // MouseInput.update();

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
                //System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
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
        stateManager = new StateManager();
        stateManager.setGui(false);

        //Initialise Window
        //this.window = new Window(this);

        stateManager.addState(new MenuState(this));
        stateManager.addState(new CharacterCreationState());
        stateManager.addState(new CharacterSelectionState());
        stateManager.addState(new SettingsState());
        stateManager.addState(new GameState());
        stateManager.addState(new BattleState());

        start();
    }
}
