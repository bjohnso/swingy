package com.swingy.states;

import com.swingy.battle.FighterMetrics;
import com.swingy.game.entities.Entity;
import com.swingy.game.entities.Fighter;
import com.swingy.id.ID;
import com.swingy.input.InputTimer;
import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.rendering.textures.Texture;
import com.swingy.util.AnimationHelper;
import com.swingy.util.Fonts;
import com.swingy.rendering.ui.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.swingy.rendering.ui.Button;
import org.apache.derby.impl.sql.catalog.SYSCOLUMNSRowFactory;

import static com.swingy.console.Console.console;
import static com.swingy.database.SwingyDB.swingyDB;
import static com.swingy.states.MenuState.swingy;

public class CharacterCreationState implements State {

    private StateManager stateManager;

    private ArrayList<Entity> entities;

    private Button[] options;
    private  int currentButtonSelection;

    private Fighter[] characters;
    private int currentCharacterSelection;

    private Fighter currentFighter;

    String userInput;

    private int buttonBaseHeight = Window.HEIGHT / 100 * 20;
    private int buttonIncrement = Window.HEIGHT / 100 * 10;
    private int textX = Window.WIDTH / 100 * 10;
    private int fontSize = Window.HEIGHT / 100 * 5;
    private int fontBold = Window.HEIGHT / 100 * 6;
    private int fontTitle = Window.HEIGHT / 100 * 10;
    private int imageWidth = Window.WIDTH / 100 * 20;
    private int imageHeight = Window.HEIGHT / 100 * 20;

    ExecutorService executorService;
    ExecutorService timerService;
    Future<Long> future;

    private int cooldown;

    @Override
    public void init() {
        userInput = null;
        currentCharacterSelection = 0;

        entities = new ArrayList<>();
        options = new Button[3];
        options[0] = new Button("Next", textX, (buttonBaseHeight + 0 * buttonIncrement),
                new Font("Arial", Font.PLAIN, fontSize),
                new Font("Arial", Font.BOLD, fontBold),
                Color.WHITE,
                Color.YELLOW);
        options[1] = new Button("Play", textX, (buttonBaseHeight + 3 * buttonIncrement),
                new Font("Arial", Font.PLAIN, fontSize),
                new Font("Arial", Font.BOLD, fontBold),
                Color.WHITE,
                Color.YELLOW);
        options[2] = new Button("Back", textX, (buttonBaseHeight + 4 * buttonIncrement),
                new Font("Arial", Font.PLAIN, fontSize),
                new Font("Arial", Font.BOLD, fontBold),
                Color.WHITE,
                Color.YELLOW);

        characters = new Fighter[4];

        characters[0] = new Fighter(new Texture("ninja/idle/1", imageWidth, imageHeight, false),
                (Window.WIDTH / 2), (Window.HEIGHT / 10),
                new FighterMetrics("NINJA"),this, AnimationHelper.createAnimation("ninjaLarge"));

        characters[1] = new Fighter(new Texture("dino/idle/1", imageWidth, imageHeight,false),
                (Window.WIDTH / 2), (Window.HEIGHT / 10),
                new FighterMetrics("DINO"),this, AnimationHelper.createAnimation("dinoLarge"));

        characters[2] = new Fighter(new Texture("robo/idle/1", imageWidth, imageHeight,false),
                (Window.WIDTH / 2), (Window.HEIGHT / 10), new FighterMetrics("ROBO"),
                this, AnimationHelper.createAnimation("roboLarge"));

        characters[3] = new Fighter(new Texture("zombo/idle/1", imageWidth, imageHeight,false),
                (Window.WIDTH / 2), (Window.HEIGHT / 10), new FighterMetrics("ZOMBO"),
                this, AnimationHelper.createAnimation("zomboLarge"));

        characters[0].setPlayerClass(ID.NINJA);
        characters[1].setPlayerClass(ID.DINO);
        characters[2].setPlayerClass(ID.ROBO);
        characters[3].setPlayerClass(ID.ZOMBO);

        characters[0].setPlayerClassName("ninja");
        characters[1].setPlayerClassName("dino");
        characters[2].setPlayerClassName("robo");
        characters[3].setPlayerClassName("zombo");

        characters[0].getFighterMetrics().setName("Ninja");
        characters[1].getFighterMetrics().setName("Dino");
        characters[2].getFighterMetrics().setName("Robo");
        characters[3].getFighterMetrics().setName("Zombo");

        characters[0].getFighterMetrics().getLevel().setExperience(1000);
        characters[1].getFighterMetrics().getLevel().setExperience(1000);
        characters[2].getFighterMetrics().getLevel().setExperience(1000);
        characters[3].getFighterMetrics().getLevel().setExperience(1000);

        //Output console options and wait for userSelection
        console.userSelection(this);

        System.out.println("\n" + characters[currentCharacterSelection].getFighterMetrics().toString());
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
        if (entities != null)
            entities.clear();
        currentFighter = null;
        characters = null;
        entities = null;
        try {
            swingyDB.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "character-new";
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
            if (userInput.equalsIgnoreCase("gui")) {
                swingy.setGui(true);
                stateManager.setTick(false);
                stateManager.setState("character-new", this);
            }
            else{
                try {
                    int userOption = Integer.parseInt(userInput);
                    if (userOption > 0 && userOption < 4) {
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

        cooldown--;

        if (cooldown <= 0) {
            if (KeyInput.wasPressed(KeyEvent.VK_UP) || KeyInput.wasPressed(KeyEvent.VK_W)) {
                cooldown = 10;
                System.out.println("UP KEY PRESSED!!!");
                currentButtonSelection--;
                if (currentButtonSelection < 0) {
                    currentButtonSelection = options.length - 1;
                }
            }

            else if (KeyInput.wasPressed(KeyEvent.VK_DOWN) || KeyInput.wasPressed(KeyEvent.VK_S)) {
                cooldown = 10;
                System.out.println("DOWN KEY PRESSED!!!");
                currentButtonSelection++;
                if (currentButtonSelection > options.length - 1) {
                    currentButtonSelection = 0;
                }
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

            if (clicked || KeyInput.wasPressed(KeyEvent.VK_ENTER))
                select(stateManager);
        }

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
                System.out.println("\n" + characters[currentCharacterSelection].getFighterMetrics().toString());
                break;
            case 1:
                this.stateManager.setTick(false);
                userInput = characters[currentCharacterSelection].getPlayerClassName();
                characters[currentCharacterSelection].getFighterMetrics().setName(userInput);
                currentFighter = characters[currentCharacterSelection];

                executorService = Executors.newSingleThreadExecutor();
                swingyDB.setFighter(currentFighter);
                swingyDB.setAction("INSERT");
                future = executorService.submit(swingyDB);

                while (true){
                    if (future.isDone()) {
                        try {
                            long playerId = future.get();
                            if (playerId < 0) {
                                System.out.println("Character Creation Failed, Invalid ID");
                                stateManager.setState("menu", this);
                            }
                            else {
                                stateManager.setState("map", this);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break ;
            case 2 :
                this.stateManager.setTick(false);
                stateManager.setState("menu", this);
                break ;
        }
    }

    @Override
    public void render(Graphics graphics) {

        Texture background = new Texture("background/2", Window.WIDTH, Window.HEIGHT, false);
        background.render(graphics, 0, 0);

        Fonts.drawString(graphics, new Font("Arial", Font.BOLD, fontTitle), Color.GREEN, "Create New Fighter", fontTitle, false);

        if (options != null) {
            if (options.length > 0) {
                for (int i = 0; i < options.length; i++) {
                    if (i == currentButtonSelection)
                        options[i].setSelected(true);
                    else
                        options[i].setSelected(false);
                    options[i].render(graphics);
                }
            }
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
