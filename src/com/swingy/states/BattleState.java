package com.swingy.states;

import com.swingy.Main;
import com.swingy.battle.FighterManager;
import com.swingy.rendering.entities.Entity;
import com.swingy.battle.BattleEngine;
import com.swingy.handlers.GameObjectHandler;
import com.swingy.heroes.Hero;
import com.swingy.id.ID;

import com.swingy.battle.objects.HUD;
import com.swingy.rendering.entities.Fighter;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
import com.swingy.rendering.textures.Texture;
import com.swingy.statics.Statics;
import com.swingy.util.Fonts;
import com.swingy.view.Swingy;

import java.awt.*;


public class BattleState extends Canvas implements State {

    private static final int DEFAULT_WIDTH = Swingy.WIDTH;
    private static final int DEFAULT_HEIGHT = Swingy.HEIGHT;

    private GameObjectHandler gameObjectHandler;

    private boolean stateResume = false;

    private HUD challengerHUD;
    private HUD defenderHUD;

    private Hero a = new Hero("Asuna", "Water");
    private Hero b = new Hero("Ragos", "Fire");

    protected Fighter player;
    protected Fighter defender;

    private BattleEngine battleEngine;

    private String battleText;

    private boolean battleEnd = false;

    private GameState gameState;

    @Override
    public void init() {

        player = GameState.player;
        defender = GameState.defender;

        switch (player.getPlayerClass()){
            case NINJA:
                player.setAnimation(Statics.ninjaLarge);
                player.setX(DEFAULT_WIDTH / 100 * 5);
                player.setY(DEFAULT_HEIGHT / 100 * 50);
                break;
            case DINO:
                player.setAnimation(Statics.dinoLarge);
                player.setX(DEFAULT_WIDTH / 100 * 5);
                player.setY(DEFAULT_HEIGHT / 100 * 60);
                break;
            case ROBO:
                player.setAnimation(Statics.roboLarge);
                player.setX(DEFAULT_WIDTH / 100 * 5);
                player.setY(DEFAULT_HEIGHT / 100 * 50);
                break;
            case ZOMBO:
                player.setAnimation(Statics.zomboLarge);
                player.setX(DEFAULT_WIDTH / 100 * 5);
                player.setY(DEFAULT_HEIGHT / 100 * 50);
                break;
        }

        switch (defender.getPlayerClass()){
            case NINJA:
                defender.setAnimation(Statics.ninjaLargeRef);
                defender.setX(DEFAULT_WIDTH / 100 * 70);
                defender.setY(DEFAULT_HEIGHT / 100 * 50);
                break;
            case DINO:
                defender.setAnimation(Statics.dinoLargeRef);
                defender.setX(DEFAULT_WIDTH / 100 * 60);
                defender.setY(DEFAULT_HEIGHT / 100 * 60);
                break;
            case ROBO:
                defender.setAnimation(Statics.roboLargeRef);
                defender.setX(DEFAULT_WIDTH / 100 * 70);
                defender.setY(DEFAULT_HEIGHT / 100 * 50);
                break;
            case ZOMBO:
                defender.setAnimation(Statics.zomboLargeRef);
                defender.setX(DEFAULT_WIDTH / 100 * 70);
                defender.setY(DEFAULT_HEIGHT / 100 * 50);
                break;
        }

        gameObjectHandler = new GameObjectHandler();

        challengerHUD = new HUD(DEFAULT_WIDTH / 100 * 5, DEFAULT_HEIGHT / 100 * 5, ID.ChallengerHUD);
        defenderHUD = new HUD(DEFAULT_WIDTH / 100 * 75, DEFAULT_HEIGHT / 100 * 5, ID.DefenderHUD);

        gameObjectHandler.addObject(new FighterManager(DEFAULT_WIDTH / 100 * 5, DEFAULT_HEIGHT / 100 * 40
                , ID.Challenger, true, challengerHUD, player, this));

        gameObjectHandler.addObject(new FighterManager(DEFAULT_WIDTH / 100 * 75,  DEFAULT_HEIGHT / 100 * 40
                , ID.Defender, false, defenderHUD, defender, this));


        //Initialising Battle Engine Listeners
        battleEngine = new BattleEngine();
        battleEngine.addPropertyChangeListener((FighterManager)gameObjectHandler.getObjects().get(0));
        battleEngine.addPropertyChangeListener((FighterManager)gameObjectHandler.getObjects().get(1));

        battleEngine.setChallenger(a);
        battleEngine.setDefender(b);

        //Initialising Animation Listeners
        player.getAnimation().addPropertyChangeListener((FighterManager)gameObjectHandler.getObjects().get(0));
        defender.getAnimation().addPropertyChangeListener((FighterManager)gameObjectHandler.getObjects().get(1));

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

    }

    @Override
    public void tick(StateManager stateManager) {
        gameObjectHandler.tick();
        player.tick();
        defender.tick();
        if (battleEnd) {
            if (battleText.equalsIgnoreCase("VICTORY")) {
                gameState.removeFighter(defender);
                stateManager.setState("map", this);
            }
            else
                stateManager.setState("menu", this);
        }
    }

    @Override
    public void render(Graphics graphics) {

        Font font = new Font("Arial", Font.BOLD, 72);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        Sprite background = new Sprite(new SpriteSheet(new Texture("background/4", false), Swingy.WIDTH, Swingy.HEIGHT), 1, 1);
        background.render(graphics, 0, 0);
        //graphics.setColor(Color.BLUE);
        //graphics.fillRoundRect(DEFAULT_WIDTH / 100 * 6, DEFAULT_HEIGHT / 100 * 10 , 80, 80, 80, 80);
        //graphics.fillRoundRect(DEFAULT_WIDTH / 100 * 14, DEFAULT_HEIGHT / 100 * 10 , 80, 80, 80, 80);

        gameObjectHandler.render(graphics);
        player.render(graphics);
        defender.render(graphics);

        Fonts.drawString(graphics, font, Color.GREEN, battleText, (Swingy.WIDTH - fontMetrics.stringWidth(battleText)) / 2, Swingy.HEIGHT / 2);

        graphics.dispose();
    }

    @Override
    public void addEntity(Entity entity) {

    }

    public void setBattleText(String text){
        this.battleText = text;
    }

    public void setBattleEnd(boolean battleEnd) {
        this.battleEnd = battleEnd;
    }
}
