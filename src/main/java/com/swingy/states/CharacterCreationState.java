package com.swingy.states;

import com.sun.istack.internal.NotNull;
import com.swingy.battle.FighterMetrics;
import com.swingy.rendering.entities.Entity;
import com.swingy.rendering.entities.Fighter;
import com.swingy.id.ID;
import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
import com.swingy.rendering.textures.Texture;
import com.swingy.util.AnimationHelper;
import com.swingy.util.Fonts;
import com.swingy.view.Swingy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import com.swingy.rendering.ui.Button;

import javax.swing.*;

import static com.swingy.database.SwingyDB.swingyDB;

public class CharacterCreationState implements State {

    private ArrayList<Entity> entities;

    private Button[] options;
    private  int currentButtonSelection;

    private Fighter[] characters;
    private int currentCharacterSelection;
    private boolean stateResume;

    protected Fighter currentFighter;

    @Override
    public void init() {
        currentCharacterSelection = 0;
        stateResume = false;

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
                (Swingy.WIDTH / 2), 100,
                new FighterMetrics("NINPO"),this, AnimationHelper.createAnimation("ninjaLarge"));

        characters[1] = new Fighter(new Sprite("dino/idle/1"),
                (Swingy.WIDTH / 2), 100,
                new FighterMetrics("BEAST"),this, AnimationHelper.createAnimation("dinoLarge"));

        characters[2] = new Fighter(new Sprite("robo/idle/1"),
                (Swingy.WIDTH / 2), 50, new FighterMetrics("BEAST"),
                this, AnimationHelper.createAnimation("roboLarge"));

        characters[3] = new Fighter(new Sprite("zombo/idle/1"),
                (Swingy.WIDTH / 2), 50, new FighterMetrics("SCOURGE"),
                this, AnimationHelper.createAnimation("zomboLarge"));

        characters[0].setPlayerClass(ID.NINJA);
        characters[1].setPlayerClass(ID.DINO);
        characters[2].setPlayerClass(ID.ROBO);
        characters[3].setPlayerClass(ID.ZOMBO);

        characters[0].setPlayerClassName("ninja");
        characters[1].setPlayerClassName("dino");
        characters[2].setPlayerClassName("robo");
        characters[3].setPlayerClassName("zombo");

        characters[0].getFighterMetrics().getLevel().setExperience(1000);
        characters[1].getFighterMetrics().getLevel().setExperience(1000);
        characters[2].getFighterMetrics().getLevel().setExperience(1000);
        characters[3].getFighterMetrics().getLevel().setExperience(1000);
    }

    @Override
    public State enterState(State callingState) {
        init();
        return this;
    }

    @Override
    public void exitState() {
        entities.clear();
        entities = null;
    }

    @Override
    public String getName() {
        return "character-new";
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
        if (entities != null) {
            if (entities.size() > currentCharacterSelection)
                entities.get(currentCharacterSelection).tick();
        }
    }

    private void select(StateManager stateManager) {
        switch (currentButtonSelection) {
            case 0:
                currentCharacterSelection++;
                if (currentCharacterSelection >= characters.length)
                    currentCharacterSelection = 0;
                break;
            case 1:
                @NotNull
                String userInput = JOptionPane.showInputDialog("Character Name");
                if (userInput == null)
                    userInput = characters[currentCharacterSelection].getPlayerClassName();
                characters[currentCharacterSelection].getFighterMetrics().setName(userInput);
                currentFighter = characters[currentCharacterSelection];
                try {
                    currentFighter.getFighterMetrics().setID(swingyDB.insertPlayer(currentFighter));
                    swingyDB.setCurrentPlayer(currentFighter.getMobileID());
                }catch (SQLException e){
                    e.printStackTrace();
                }
                stateManager.setState("map", this);
                break ;
            case 2 :
                stateManager.setState("menu", this);
                break ;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Swingy.WIDTH, Swingy.HEIGHT);

        Sprite background = new Sprite(new SpriteSheet(new Texture("background/2", false), Swingy.WIDTH, Swingy.HEIGHT), 1, 1);
        background.render(graphics, 0, 0);

        Fonts.drawString(graphics, new Font("Arial", Font.BOLD, 72), Color.GREEN, "Create New Fighter", 72, false);

        for (int i = 0; i < options.length; i++){
            if (i == currentButtonSelection)
                options[i].setSelected(true);
            else
                options[i].setSelected(false);
            options[i].render(graphics);
        }

        //Render only selected character
        if (entities != null) {
            if (entities.size() > currentCharacterSelection)
                entities.get(currentCharacterSelection).render(graphics);
        }
    }

    public void addEntity(Entity entity){
        if (entities != null)
            entities.add(entity);
    }
}
