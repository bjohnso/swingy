package com.swingy.states;

import com.swingy.rendering.entities.Entity;
import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.rendering.textures.Texture;
import com.swingy.util.Fonts;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import com.swingy.rendering.ui.Button;
import com.swingy.view.Swingy;

import static com.swingy.database.SwingyDB.swingyDB;

public class MenuState implements State {

    private Swingy swingy;

    private Button[] options;
    private int currentSelection;
    private int buttonBaseHeight = Swingy.HEIGHT / 100 * 20;
    private int buttonIncrement = Swingy.HEIGHT / 100 * 10;
    private int fontSize = Swingy.HEIGHT / 100 * 5;
    private int fontBold = Swingy.HEIGHT / 100 * 6;
    private int fontTitle = Swingy.HEIGHT / 100 * 10;

    @Override
    public void init() {
        try {
            swingyDB.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        currentSelection = 0;
        try {
            swingyDB.resetCurrentPlayer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        options = new Button[4];
        options[0] = new Button("New Game", (buttonBaseHeight + 0 * buttonIncrement),
                new Font("Arial", Font.PLAIN, fontSize),
                new Font("Arial", Font.BOLD, fontBold),
                Color.WHITE,
                Color.YELLOW);
        options[1] = new Button("Load Game", (buttonBaseHeight + 1 * buttonIncrement),
                new Font("Arial", Font.PLAIN, fontSize),
                new Font("Arial", Font.BOLD, fontBold),
                Color.WHITE,
                Color.YELLOW);
        options[2] = new Button("Settings", (buttonBaseHeight + 2 * buttonIncrement),
                new Font("Arial", Font.PLAIN, fontSize),
                new Font("Arial", Font.BOLD, fontBold),
                Color.WHITE,
                Color.YELLOW);
        options[3] = new Button("Exit", (buttonBaseHeight + 3 * buttonIncrement),
                new Font("Arial", Font.PLAIN, fontSize),
                new Font("Arial", Font.BOLD, fontBold),
                Color.WHITE,
                Color.YELLOW);
    }

    public void dropDB(){
        try {
            swingyDB.dropDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public State enterState(State callingState) {
        init();
        return this;
    }

    public MenuState(Swingy swingy){
        this.swingy = swingy;
    }

    @Override
    public void tick(StateManager stateManager) {
        if (KeyInput.wasPressed(KeyEvent.VK_UP) || KeyInput.wasPressed(KeyEvent.VK_W)){
            currentSelection--;
            if (currentSelection < 0){
                currentSelection = options.length - 1;
            }
        }

        if (KeyInput.wasPressed(KeyEvent.VK_DOWN) || KeyInput.wasPressed(KeyEvent.VK_S)){
            currentSelection++;
            if (currentSelection > options.length - 1){
                currentSelection = 0;
            }
        }

        boolean clicked = false;

        for (int i = 0; i < options.length; i++) {
            if (options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
                currentSelection = i;
                clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
            }
        }

        if (clicked || KeyInput.wasPressed(KeyEvent.VK_ENTER))
            select(stateManager);
    }

    private void select(StateManager stateManager){
        switch (currentSelection){
            case 0 :
                stateManager.setState("character-new", this);
                break ;
            case 1 :
                stateManager.setState("character-load", this);
                break ;
            case 2 :
                stateManager.setState("settings", this);
                break ;
            case 3 :
                System.out.println("exit");
                swingy.stop();
                break ;
        }
    }

    @Override
    public void exitState() {
    }

    @Override
    public String getName() {
        return "menu";
    }

    public void render (Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Swingy.WIDTH, Swingy.HEIGHT);

        Texture background = new Texture("background/1", Swingy.WIDTH, Swingy.HEIGHT, false);
        background.render(graphics, 0, 0);

        Fonts.drawString(graphics, new Font("Arial", Font.BOLD, fontTitle), Color.GREEN, Swingy.TITLE, fontTitle, false);

        for (int i = 0; i < options.length; i++){
            if (i == currentSelection)
                options[i].setSelected(true);
            else
                options[i].setSelected(false);
            options[i].render(graphics);
        }
    }

    @Override
    public void addEntity(Entity entity) {

    }
}
