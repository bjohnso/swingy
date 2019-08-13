package com.swingy.states;

import com.swingy.rendering.entities.Entity;
import com.swingy.rendering.entities.Fighter;
import com.swingy.id.ID;
import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.rendering.textures.Animation;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
import com.swingy.rendering.textures.Texture;
import com.swingy.statics.Statics;
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

    private Fighter[] characters;
    private int currentCharacterSelection = 0;
    private boolean stateResume = false;

    protected static Fighter currentFighter;

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

        characters = new Fighter[4];

        characters[0] = new Fighter(new Sprite("ninja/idle/1"),
                (Swingy.WIDTH / 2), 100, this, Statics.ninjaLarge);

        characters[1] = new Fighter(new Sprite("dino/idle/1"),
                (Swingy.WIDTH / 2), 100, this, Statics.dinoLarge);

        characters[2] = new Fighter(new Sprite("robo/idle/1"),
                (Swingy.WIDTH / 2), 50, this, Statics.roboLarge);

        characters[3] = new Fighter(new Sprite("zombo/idle/1"),
                (Swingy.WIDTH / 2), 50, this, Statics.zomboLarge);

        characters[0].setPlayerClass(ID.NINJA);
        characters[1].setPlayerClass(ID.DINO);
        characters[2].setPlayerClass(ID.ROBO);
        characters[3].setPlayerClass(ID.ZOMBO);

        characters[0].setPlayerClassName("ninja");
        characters[1].setPlayerClassName("dino");
        characters[2].setPlayerClassName("robo");
        characters[3].setPlayerClassName("zombo");

        characters[0].setLevel(5);
        characters[1].setLevel(5);
        characters[2].setLevel(5);
        characters[3].setLevel(5);

    }

    @Override
    public State enterState() {
        if (!stateResume)
            init();
        return this;
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
                if (currentCharacterSelection >= characters.length)
                    currentCharacterSelection = 0;
                break ;
            case 1 :
                currentFighter = characters[currentCharacterSelection];
                GameState gameState = (GameState) stateManager.setState("map");
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

        Sprite background = new Sprite(new SpriteSheet(new Texture("background/2", false), Swingy.WIDTH, Swingy.HEIGHT), 1, 1);
        background.render(graphics, 0, 0);

        Fonts.drawString(graphics, new Font("Arial", Font.BOLD, 72), Color.GREEN, "Choose Your FighterManager", 72, false);

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
