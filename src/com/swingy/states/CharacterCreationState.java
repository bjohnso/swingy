package com.swingy.states;

import com.swingy.entities.Entity;
import com.swingy.entities.Player;
import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.Texture;
import com.swingy.util.Fonts;
import com.swingy.view.Swingy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.swingy.rendering.ui.Button;

public class CharacterCreationState implements State {

    private ArrayList<Entity> entities;

    private Button[] options;
    private  int currentButtonSelection;

    private Player[] characters;
    private int currentCharacterSelection = 0;
    private boolean stateResume = false;

    @Override
    public void init() {
        entities = new ArrayList<>();
        options = new Button[3];
        options[0] = new Button("Next", 50, (200 + 0 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
        options[1] = new Button("Play", 50, (200 + 3 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
        options[2] = new Button("Back", 50, (200 + 4 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);

        characters = new Player[3];
        characters[0] = new Player(new Sprite("dino/idle/1"),
                (Swingy.WIDTH / 2), 100, this, new Animation(30,
                new Texture("dino/idle/1"),
                new Texture("dino/idle/2"),
                new Texture("dino/idle/3"),
                new Texture("dino/idle/4"),
                new Texture("dino/idle/5"),
                new Texture("dino/idle/6"),
                new Texture("dino/idle/7"),
                new Texture("dino/idle/8"),
                new Texture("dino/idle/9"),
                new Texture("dino/idle/10")));

        characters[1] = new Player(new Sprite("robo/idle/1"),
                (Swingy.WIDTH / 2), 50, this, new Animation(30,
                new Texture("robo/idle/1"),
                new Texture("robo/idle/2"),
                new Texture("robo/idle/3"),
                new Texture("robo/idle/4"),
                new Texture("robo/idle/5"),
                new Texture("robo/idle/6"),
                new Texture("robo/idle/7"),
                new Texture("robo/idle/8"),
                new Texture("robo/idle/9"),
                new Texture("robo/idle/10")));

        characters[2] = new Player(new Sprite("zombo/idle/1"),
                (Swingy.WIDTH / 2), 50, this, new Animation(30,
                new Texture("zombo/idle/1"),
                new Texture("zombo/idle/2"),
                new Texture("zombo/idle/3"),
                new Texture("zombo/idle/4"),
                new Texture("zombo/idle/5"),
                new Texture("zombo/idle/6"),
                new Texture("zombo/idle/7"),
                new Texture("zombo/idle/8"),
                new Texture("zombo/idle/9"),
                new Texture("zombo/idle/10"),
                new Texture("zombo/idle/11"),
                new Texture("zombo/idle/12"),
                new Texture("zombo/idle/13"),
                new Texture("zombo/idle/14"),
                new Texture("zombo/idle/15")));

    }

    @Override
    public void enterState() {
        if (!stateResume)
            init();
    }

    @Override
    public void exitState() {
        stateResume = true;
    }

    @Override
    public String getName() {
        return "character";
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

        for (int i = 0; i < options.length; i++) {
            if (options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
                currentButtonSelection = i;
                clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
            }
        }

        if (clicked || KeyInput.wasPressed(KeyEvent.VK_ENTER))
            select(stateManager);

        //Tick only selected character
        if (entities != null)
            entities.get(currentCharacterSelection).tick();
    }

    private void select(StateManager stateManager){
        switch (currentButtonSelection){
            case 0 :
                currentCharacterSelection++;
                if (currentCharacterSelection > 2)
                    currentCharacterSelection = 0;
                break ;
            case 1 :
                stateManager.setState("map");
                break ;
            case 2 :
                stateManager.setState("menu");
                break ;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Swingy.WIDTH, Swingy.HEIGHT);
        Fonts.drawString(graphics, new Font("Arial", Font.BOLD, 72), Color.GREEN, "Choose Your Fighter", 72, false);

        for (int i = 0; i < options.length; i++){
            if (i == currentButtonSelection)
                options[i].setSelected(true);
            else
                options[i].setSelected(false);
            options[i].render(graphics);
        }

        //Render only selected character
        if (entities != null)
            entities.get(currentCharacterSelection).render(graphics);
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }
}
