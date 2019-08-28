package com.swingy.states;

import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.rendering.entities.Entity;
import com.swingy.rendering.textures.Texture;
import com.swingy.rendering.ui.Button;
import com.swingy.util.Fonts;
import com.swingy.view.Swingy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import static com.swingy.database.SwingyDB.swingyDB;

public class SettingsState implements State {

    private Button[] options;
    private  int currentButtonSelection;

    private int buttonBaseHeight = Swingy.HEIGHT / 100 * 20;
    private int buttonIncrement = Swingy.HEIGHT / 100 * 10;
    private int fontSize = Swingy.HEIGHT / 100 * 5;
    private int fontBold = Swingy.HEIGHT / 100 * 6;
    private int fontTitle = Swingy.HEIGHT / 100 * 10;

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
    }

    @Override
    public State enterState(State callingState) {
        init();
        return this;
    }

    @Override
    public void exitState() {
        options = null;
    }

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public void tick(StateManager stateManager) {


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
        stateManager.setState("menu", this);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Swingy.WIDTH, Swingy.HEIGHT);

        Texture background = new Texture(new Texture("background/2", Swingy.WIDTH, Swingy.HEIGHT, false), 1, 1, Swingy.WIDTH, Swingy.HEIGHT);
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
