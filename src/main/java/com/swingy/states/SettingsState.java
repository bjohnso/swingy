package com.swingy.states;

import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.game.entities.Entity;
import com.swingy.rendering.textures.Texture;
import com.swingy.rendering.ui.Button;
import com.swingy.util.Fonts;
import com.swingy.rendering.ui.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import static com.swingy.console.Console.console;
import static com.swingy.database.SwingyDB.swingyDB;
import static com.swingy.states.MenuState.swingy;

public class SettingsState implements State {

    private StateManager stateManager;

    private Button[] options;
    private  int currentButtonSelection;

    private int buttonBaseHeight = Window.HEIGHT / 100 * 20;
    private int buttonIncrement = Window.HEIGHT / 100 * 10;
    private int fontSize = Window.HEIGHT / 100 * 5;
    private int fontBold = Window.HEIGHT / 100 * 6;
    private int fontTitle = Window.HEIGHT / 100 * 10;

    @Override
    public void init() {
        currentButtonSelection = 0;
        options = new Button[2];
        options[0] = new Button("Reset Database", (buttonBaseHeight + 0 * buttonIncrement),
                new Font("Arial", Font.PLAIN, fontSize),
                new Font("Arial", Font.BOLD, fontBold),
                Color.WHITE,
                Color.YELLOW);
        options[1] = new Button("Back", (buttonBaseHeight + 1 * buttonIncrement),
                new Font("Arial", Font.PLAIN, fontSize),
                new Font("Arial", Font.BOLD, fontBold),
                Color.WHITE,
                Color.YELLOW);

        //Output console options and wait for userSelection
        console.userSelection(this);
    }

    @Override
    public State enterState(StateManager stateManager, State callingState) {
        this.stateManager = stateManager;
        init();
        stateManager.setTick(true);
        return this;
    }

    @Override
    public void exitState() {
        this.stateManager.setTick(false);
        options = null;
    }

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public void tick(StateManager stateManager) {

        String userInput = null;
        try {
            userInput = console.tick();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (userInput != null){
            if (userInput.equalsIgnoreCase("gui"))
                swingy.setGui(true);
            else{
                try {
                    int userOption = Integer.parseInt(userInput);
                    if (userOption > 0 && userOption < 3) {
                        currentButtonSelection = Integer.parseInt(userInput) - 1;
                        select(stateManager);
                    }
                    else
                        System.out.println("INVALID INPUT...");
                }catch (NumberFormatException e){
                    System.out.println("INVALID INPUT...");
                }
            }
        }


        if (KeyInput.wasPressed(KeyEvent.VK_UP) || KeyInput.wasPressed(KeyEvent.VK_W)){
            currentButtonSelection--;
            if (currentButtonSelection < 0){
                currentButtonSelection = options.length - 1;
            }
        }

        if (KeyInput.wasPressed(KeyEvent.VK_DOWN) || KeyInput.wasPressed(KeyEvent.VK_S)){
            currentButtonSelection++;
            if (currentButtonSelection > options.length - 1){
                currentButtonSelection = 0;
            }
        }

        boolean clicked = false;

        if (options != null) {
            for (int i = 0; i < options.length; i++) {
                if (options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
                    currentButtonSelection = i;
                    clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
                }
            }
        }

        if (clicked || KeyInput.wasPressed(KeyEvent.VK_ENTER))
            select(stateManager);

    }

    private void select(StateManager stateManager) {

        switch (currentButtonSelection){
            case 0:
                try {
                    swingyDB.deleteAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
        this.stateManager.setTick(false);
        stateManager.setState("menu", this);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);

        Texture background = new Texture("background/2", Window.WIDTH, Window.HEIGHT, false);
        background.render(graphics, 0, 0);

        Fonts.drawString(graphics, new Font("Arial", Font.BOLD, fontTitle), Color.GREEN, "Settings", fontTitle, false);

        if (options != null){
            for (int i = 0; i < options.length; i++) {
                if (i == currentButtonSelection)
                    options[i].setSelected(true);
                else
                    options[i].setSelected(false);
                options[i].render(graphics);
            }
        }
    }

    public void addEntity(Entity entity){
    }

}
