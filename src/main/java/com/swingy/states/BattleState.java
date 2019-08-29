package com.swingy.states;

import com.swingy.Main;
import com.swingy.artifacts.Artifact;
import com.swingy.battle.FighterManager;
import com.swingy.rendering.entities.Entity;
import com.swingy.battle.BattleEngine;
import com.swingy.handlers.GameObjectHandler;
import com.swingy.id.ID;

import com.swingy.battle.objects.HUD;
import com.swingy.rendering.entities.Fighter;
import com.swingy.rendering.textures.Texture;
import com.swingy.util.AnimationHelper;
import com.swingy.util.Fonts;
import com.swingy.view.Swingy;

import java.awt.*;
import java.util.ArrayList;


public class BattleState extends Canvas implements State {

    private GameObjectHandler gameObjectHandler;

    private BattleEngine battleEngine;

    private String battleText;

    private boolean battleEnd;

    private GameState gameState;

    private ArrayList<Entity> entities;
    private ArrayList<Fighter> fighters;

    private int buttonBaseHeight = Swingy.HEIGHT / 100 * 20;
    private int buttonIncrement = Swingy.HEIGHT / 100 * 10;
    private int textX = Swingy.WIDTH / 100 * 10;
    private int fontSmall = Swingy.HEIGHT / 100 * 3;
    private int fontSize = Swingy.HEIGHT / 100 * 5;
    private int fontBold = Swingy.HEIGHT / 100 * 6;
    private int fontTitle = Swingy.HEIGHT / 100 * 10;
    private int imageWidth = Swingy.WIDTH / 100 * 20;
    private int imageHeight = Swingy.HEIGHT / 100 * 20;


    @Override
    public void init() {

        battleEnd = false;
        entities = new ArrayList<>();
        fighters = new ArrayList<>();

        Fighter tempFighter = null;
        switch (GameState.player.getPlayerClass()){
            case NINJA:
                tempFighter = new Fighter(new Texture("ninja/idle/1", imageWidth, imageHeight, false),
                        Swingy.WIDTH / 100 * 5,
                        Swingy.HEIGHT / 100 * 50,
                        GameState.player.getFighterMetrics(),
                        this, AnimationHelper.createAnimation("ninjaLarge"));
                tempFighter.setPlayerClass(ID.NINJA);
                tempFighter.setPlayerClassName("ninja");
                break;
            case DINO:
                tempFighter = new Fighter(new Texture("dino/idle/1", imageWidth, imageHeight,false),
                        Swingy.WIDTH / 100 * 5,
                        Swingy.HEIGHT / 100 * 50,
                        GameState.player.getFighterMetrics(),
                        this, AnimationHelper.createAnimation("dinoLarge"));
                tempFighter.setPlayerClass(ID.DINO);
                tempFighter.setPlayerClassName("dino");
                break;
            case ROBO:
                tempFighter = new Fighter(new Texture("robo/idle/1", imageWidth, imageHeight,false),
                        Swingy.WIDTH / 100 * 5,
                        Swingy.HEIGHT / 100 * 50,
                        GameState.player.getFighterMetrics(),
                        this, AnimationHelper.createAnimation("roboLarge"));
                tempFighter.setPlayerClass(ID.ROBO);
                tempFighter.setPlayerClassName("robo");
                break;
            case ZOMBO:
                tempFighter = new Fighter(new Texture("zombo/idle/1", imageWidth, imageHeight,false),
                        Swingy.WIDTH / 100 * 5,
                        Swingy.HEIGHT / 100 * 50,
                        GameState.player.getFighterMetrics(),
                        this, AnimationHelper.createAnimation("zomboLarge"));
                tempFighter.setPlayerClass(ID.ZOMBO);
                tempFighter.setPlayerClassName("zombo");
                break;
        }
        //Preserve Object Info
        tempFighter.setPlayer(true);
        tempFighter.setMobileID(GameState.player.getMobileID());
        fighters.add(tempFighter);

        switch (GameState.defender.getPlayerClass()){
            case NINJA:
                tempFighter = new Fighter(new Texture("ninja/idle/1", imageWidth, imageHeight,true),
                        Swingy.WIDTH / 100 * 70,
                        Swingy.HEIGHT / 100 * 50,
                        GameState.defender.getFighterMetrics(),
                        this, AnimationHelper.createAnimation("ninjaLargeRef"));
                tempFighter.setPlayerClass(ID.NINJA);
                tempFighter.setPlayerClassName("ninja");
                break;
            case DINO:
                tempFighter = new Fighter(new Texture("dino/idle/1", imageWidth, imageHeight,true),
                        Swingy.WIDTH / 100 * 50,
                        Swingy.HEIGHT / 100 * 50,
                        GameState.defender.getFighterMetrics(),
                        this, AnimationHelper.createAnimation("dinoLargeRef"));
                tempFighter.setPlayerClass(ID.DINO);
                tempFighter.setPlayerClassName("dino");
                break;
            case ROBO:
                tempFighter = new Fighter(new Texture("robo/idle/1", imageWidth, imageHeight,true),
                        Swingy.WIDTH / 100 * 70,
                        Swingy.HEIGHT / 100 * 50,
                        GameState.defender.getFighterMetrics(),
                        this, AnimationHelper.createAnimation("roboLargeRef"));
                tempFighter.setPlayerClass(ID.ROBO);
                tempFighter.setPlayerClassName("robo");
                break;
            case ZOMBO:
                tempFighter = new Fighter(new Texture("zombo/idle/1", imageWidth, imageHeight,true),
                        Swingy.WIDTH / 100 * 70,
                        Swingy.HEIGHT / 100 * 50,
                        GameState.defender.getFighterMetrics(),
                        this, AnimationHelper.createAnimation("zomboLargeRef"));
                tempFighter.setPlayerClass(ID.ZOMBO);
                tempFighter.setPlayerClassName("zombo");
                break;
        }
        //Preserve Object Info
        tempFighter.setMobileID(GameState.defender.getMobileID());
        fighters.add(tempFighter);

        gameObjectHandler = new GameObjectHandler();

        HUD challengerHUD = new HUD(Swingy.WIDTH / 100 * 5, Swingy.HEIGHT / 100 * 5, ID.ChallengerHUD);
        HUD defenderHUD = new HUD(Swingy.WIDTH / 100 * 75, Swingy.HEIGHT / 100 * 5, ID.DefenderHUD);

        //Initialising GameObject Handler And FighterManager
        for (Fighter f : fighters){
            if (f.isPlayer()) {
                gameObjectHandler.addObject(new FighterManager(Swingy.WIDTH / 100 * 5, Swingy.HEIGHT / 100 * 40
                        , ID.Challenger, true, challengerHUD, f, this));
            }
            else{
                gameObjectHandler.addObject(new FighterManager(Swingy.WIDTH / 100 * 75,  Swingy.HEIGHT / 100 * 40
                        , ID.Defender, false, defenderHUD, (Fighter) f, this));
            }
        }

        //Initialising Battle Engine Listeners
        battleEngine = new BattleEngine();

        for (int i = 0; i < gameObjectHandler.getObjects().size(); i++)
            battleEngine.addPropertyChangeListener((FighterManager)gameObjectHandler.getObjects().get(i));

        //Initialising Animation Listeners
        for (int i = 0; i < fighters.size(); i++) {
            if (fighters.get(i).isPlayer())
                battleEngine.setChallenger(fighters.get(i).getFighterMetrics());
            else
                battleEngine.setDefender(fighters.get(i).getFighterMetrics());
            fighters.get(i).getAnimation().addPropertyChangeListener((FighterManager) gameObjectHandler.getObjects().get(i));
        }

        battleText = "FIGHT";

        Main.pool.runTask(battleEngine);
    }

    @Override
    public String getName() {
        return "battle";
    }

    @Override
    public State enterState(State callingState) {
        init();
        gameState = (GameState)callingState;
        return this;
    }

    @Override
    public void exitState() {
        fighters.clear();
        entities.clear();
        gameObjectHandler.clear();
    }

    @Override
    public void tick(StateManager stateManager) {
        gameObjectHandler.tick();

        for(Entity e: entities)
            e.tick();

        for(Fighter f: fighters)
            f.tick();

        if (battleEnd) {
            for (Fighter f : fighters) {
                if (!f.isAlive())
                    gameState.removeFighter(f);
            }
            if (battleText.equalsIgnoreCase("VICTORY"))
                stateManager.setState("map", this);
            else {
                gameState.gameOver();
                stateManager.setState("menu", this);
            }
        }
    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Swingy.WIDTH, Swingy.HEIGHT);

        Texture background = new Texture("background/4", Swingy.WIDTH, Swingy.HEIGHT, false);
        background.render(graphics, 0, 0);

        Font font = new Font("Arial", Font.PLAIN, fontSmall);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);

        //Draw Fighter Stats and Artifacts to Screen

        for (int i = 0; i < fighters.size(); i++) {
            //Fighter Stats
            int j = 0;
            for (String s : fighters.get(i).getFighterMetrics().toStringArray()) {
                Fonts.drawString(graphics, font, Color.GREEN, s, (i * (Swingy.WIDTH / 6 * 5)), (buttonBaseHeight + (j * buttonIncrement)));
                j++;
            }
            //Artifacts
            j = 0;
            for (Artifact a : fighters.get(i).getFighterMetrics().getArtifacts()){
                Texture artifact;
                if (a.getId() == ID.WEAPON)
                    artifact = new Texture("battle/"+ a.getType(), Swingy.WIDTH / 100 * (5/4), Swingy.WIDTH / 100 * 5, false);
                else
                    artifact = new Texture("battle/"+ a.getType(), Swingy.WIDTH / 100 * 5, Swingy.WIDTH / 100 * 5, false);
                artifact.render(graphics, (0) + (j * buttonIncrement), Swingy.HEIGHT / 100 * 95);
                j++;
            }
        }

        gameObjectHandler.render(graphics);
        for(Entity e: entities)
            e.render(graphics);

        font = new Font("Arial", Font.BOLD, fontTitle);
        fontMetrics = graphics.getFontMetrics(font);
        Fonts.drawString(graphics, font, Color.GREEN, battleText, (Swingy.WIDTH - fontMetrics.stringWidth(battleText)) / 2, Swingy.HEIGHT / 2);

        graphics.dispose();
    }

    @Override
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void setBattleText(String text){
        this.battleText = text;
    }

    public void setBattleEnd(boolean battleEnd) {
        this.battleEnd = battleEnd;
    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(ArrayList<Fighter> fighters) {
        this.fighters = fighters;
    }
}
