package com.swingy.states;

import com.swingy.rendering.entities.Entity;
import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
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

    @Override
    public void init() {
        try {
            swingyDB.resetCurrentPlayer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        options = new Button[4];
        options[0] = new Button("New Game", (200 + 0 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
        options[1] = new Button("Load Game", (200 + 1 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
        options[2] = new Button("Settings", (200 + 2 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
        options[3] = new Button("Exit", (200 + 3 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
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
                System.out.println("settings");
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

        Sprite background = new Sprite(new SpriteSheet(new Texture("background/1", false), Swingy.WIDTH, Swingy.HEIGHT), 1, 1);
        background.render(graphics, 0, 0);

        Fonts.drawString(graphics, new Font("Arial", Font.BOLD, 72), Color.GREEN, Swingy.TITLE, 80, false);

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
