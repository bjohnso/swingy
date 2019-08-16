package com.swingy.states;

import com.swingy.artifacts.Armor;
import com.swingy.artifacts.Helm;
import com.swingy.artifacts.Weapon;
import com.swingy.battle.FighterMetrics;
import com.swingy.rendering.entities.Entity;
import com.swingy.rendering.entities.Fighter;
import com.swingy.id.ID;
import com.swingy.id.IDAssigner;
import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.map.MapGenerator;
import com.swingy.map.Tile;
import com.swingy.rendering.textures.Sprite;
import com.swingy.rendering.textures.SpriteSheet;
import com.swingy.rendering.textures.Texture;
import com.swingy.rendering.ui.Button;
import com.swingy.util.AnimationHelper;
import com.swingy.view.Swingy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameState implements State {

    private ArrayList<Entity> entities;
    private ArrayList<Fighter> fighters;
    private ArrayList<Tile> tiles;
    private Tile[][] tileMap = null;

    private MapGenerator mapGenerator;
    private int playerIndex;
    private Button[] options;
    private int currentSelection;

    private IDAssigner idAssigner;

    private boolean isResume = false;
    private boolean gameOver;

    protected static Fighter player;
    protected static Fighter defender;

    private StateManager stateManager = null;

    private String[] artifacts = {
            "HELM",
            "WEAPON",
            "ARMOR"
    };

    @Override
    public void init() {
        gameOver = false;
        options = null;
        player = CharacterCreationState.currentFighter;
        mapGenerator = new MapGenerator(player);
        tileMap = mapGenerator.getTileMap();
        entities = new ArrayList<Entity>();
        fighters = new ArrayList<>();
        tiles = mapGenerator.generate();

        idAssigner = new IDAssigner(1);

        for (Tile t: tiles){
            Fighter tempFighter = null;

            if (t.getTileClass() == ID.DINO){
                tempFighter = new Fighter(new Sprite("terrain/dino/1"),
                        t.getX(), t.getY(),
                        new FighterMetrics("Dino", "EARTH"),
                        this, null);
                tempFighter.setPlayerClass(ID.DINO);
                tempFighter.setPlayerClassName("dino");
            }
            else if (t.getTileClass() == ID.ROBO){
                tempFighter = new Fighter(new Sprite("terrain/robo/1"),
                        t.getX(), t.getY(),
                        new FighterMetrics("Robo", "EARTH"),
                        this, null);
                tempFighter.setPlayerClass(ID.ROBO);
                tempFighter.setPlayerClassName("robo");
            }
            else if (t.getTileClass() == ID.ZOMBO){
                tempFighter = new Fighter(new Sprite("terrain/zombo/1"),
                        t.getX(), t.getY(),
                        new FighterMetrics("Zombo", "WATER"),
                        this, null);
                tempFighter.setPlayerClass(ID.ZOMBO);
                tempFighter.setPlayerClassName("zombo");
            }
            else if (t.getTileClass() == ID.NINJA){
                tempFighter = new Fighter(new Sprite("terrain/ninja/1"),
                        t.getX(), t.getY(),
                        new FighterMetrics("Ninja", "FIRE"),
                        this, null);
                tempFighter.setPlayerClass(ID.NINJA);
                tempFighter.setPlayerClassName("ninja");
            }

            if (tempFighter != null) {
                tempFighter.setID(idAssigner.next());
                t.setMobileID(tempFighter.getID());
                fighters.add(tempFighter);
                if (t.isPlayer()){
                    playerIndex = tiles.indexOf(t);
                    player = tempFighter;
                    player.setPlayer(true);
                }
            }
        }
    }

    public void enemyMove(){

        for (Fighter p: fighters) {
            if (p != player && p.isAlive()) {
                ArrayList<String> directions = new ArrayList<>();
                int originX = (Swingy.WIDTH - (tileMap.length * 32)) / 2;
                int originY = (Swingy.HEIGHT - (tileMap.length * 32)) / 2;
                int indexX = (int) ((p.getX() - originX));
                int indexY = (int) ((p.getY() - originY));

                if (indexX > 0)
                    indexX /= 32;
                if (indexY > 0)
                    indexY /= 32;

                if (indexX + 1 < tileMap.length && indexX + 1 > -1 && indexY < tileMap.length && indexY > -1) {
                    if (tileMap[indexY][indexX + 1].getTileClass() == ID.GROUND)
                        directions.add("right");

                }
                if (indexX - 1 < tileMap.length && indexX - 1 > -1 && indexY < tileMap.length && indexY > -1) {
                    if (tileMap[indexY][indexX - 1].getTileClass() == ID.GROUND)
                        directions.add("left");
                }
                if (indexX < tileMap.length && indexX > -1 && indexY + 1 < tileMap.length && indexY + 1 > -1) {
                    if (tileMap[indexY + 1][indexX].getTileClass() == ID.GROUND)
                        directions.add("down");
                }
                if (indexX < tileMap.length && indexX > -1 && indexY - 1 < tileMap.length && indexY - 1 > -1) {
                    if (tileMap[indexY - 1][indexX].getTileClass() == ID.GROUND)
                        directions.add("up");
                }

                double seed = Math.random();
                double i = 0;

                for (String d : directions) {
                    double probability = (++i/directions.size());
                    if (seed < probability) {
                        Tile tempTile;
                        if (d == "left") {
                            tempTile = tileMap[indexY][indexX - 1];
                            tileMap[indexY][indexX - 1] = tileMap[indexY][indexX];
                            tileMap[indexY][indexX] = tempTile;

                            tileMap[indexY][indexX - 1].moveX(-32);
                            tileMap[indexY][indexX].moveX(32);

                            p.moveX(-32);
                        }  else if (d == "up") {
                            tempTile = tileMap[indexY - 1][indexX];
                            tileMap[indexY - 1][indexX] = tileMap[indexY][indexX];
                            tileMap[indexY][indexX] = tempTile;

                            tileMap[indexY - 1][indexX].moveY(-32);
                            tileMap[indexY][indexX].moveY(32);

                            p.moveY(-32);
                        }
                        else if (d == "right") {
                            tempTile = tileMap[indexY][indexX + 1];
                            tileMap[indexY][indexX + 1] = tileMap[indexY][indexX];
                            tileMap[indexY][indexX] = tempTile;

                            tileMap[indexY][indexX + 1].moveX(32);
                            tileMap[indexY][indexX].moveX(-32);

                            p.moveX(32);
                        }
                        else {
                            tempTile = tileMap[indexY + 1][indexX];
                            tileMap[indexY + 1][indexX] = tileMap[indexY][indexX];
                            tileMap[indexY][indexX] = tempTile;

                            tileMap[indexY + 1][indexX].moveY(32);
                            tileMap[indexY][indexX].moveY(-32);

                            p.moveY(32);
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public State enterState(State callingState) {
        if (!isResume)
            init();
        return this;
    }

    @Override
    public void exitState() {
        if (!gameOver)
            isResume = true;
        else if (gameOver){
            isResume = false;
            entities.clear();
            fighters.clear();
            tiles.clear();
        }
    }

    @Override
    public String getName() {
        return "map";
    }

    public String calculateArtifact(){
        double seed = Math.random();

        if (seed < 1 / 3.0)
            return artifacts[0];
        else if (seed < 2 / 3)
            return artifacts[1];
        else if (seed < 3 / 3)
            return artifacts[2];
        return null;
    }

    @Override
    public void tick(StateManager stateManager) {
        this.stateManager = stateManager;
        if (KeyInput.wasPressed(KeyEvent.VK_UP) || KeyInput.wasPressed(KeyEvent.VK_W)){
            if (options == null) {
                for (int i = 0; i < tileMap.length; i++) {
                    for (int j = 0; j < tileMap.length; j++) {
                        if (tileMap[i][j].isPlayer()) {
                            if (i - 1 > -1) {
                                if (tileMap[i - 1][j].getTileClass() == ID.GROUND) {
                                    Tile tempTile = tileMap[i - 1][j];
                                    tileMap[i - 1][j] = tileMap[i][j];
                                    tileMap[i][j] = tempTile;

                                    tileMap[i - 1][j].moveY(-32);
                                    tileMap[i][j].moveY(32);

                                    player.moveY(-32);
                                    j = tileMap.length;
                                    i = tileMap.length;

                                    moveLogic();
                                }
                            }
                        }
                    }
                }
            }
            else {
                currentSelection++;
                if (currentSelection > options.length - 1){
                    currentSelection = 0;
                }
            }
        }

        if (KeyInput.wasPressed(KeyEvent.VK_DOWN) || KeyInput.wasPressed(KeyEvent.VK_S)){
            if (options == null) {
                for (int i = 0; i < tileMap.length; i++) {
                    for (int j = 0; j < tileMap.length; j++) {
                        if (tileMap[i][j].isPlayer()) {
                            if (i + 1 < tileMap.length) {
                                if (tileMap[i + 1][j].getTileClass() == ID.GROUND) {
                                    Tile tempTile = tileMap[i + 1][j];
                                    tileMap[i + 1][j] = tileMap[i][j];
                                    tileMap[i][j] = tempTile;

                                    tileMap[i + 1][j].moveY(32);
                                    tileMap[i][j].moveY(-32);

                                    player.moveY(32);
                                    j = tileMap.length;
                                    i = tileMap.length;

                                    moveLogic();
                                }
                            }
                        }
                    }
                }
            }
            else {
                currentSelection--;
                if (currentSelection < 0){
                    currentSelection = options.length - 1;
                }
            }
        }

        if (KeyInput.wasPressed(KeyEvent.VK_LEFT) || KeyInput.wasPressed(KeyEvent.VK_A)){
            if (options == null) {
                for (int i = 0; i < tileMap.length; i++) {
                    for (int j = 0; j < tileMap.length; j++) {
                        if (tileMap[i][j].isPlayer()) {
                            if (j - 1 > -1) {
                                if (tileMap[i][j - 1].getTileClass() == ID.GROUND) {
                                    Tile tempTile = tileMap[i][j - 1];
                                    tileMap[i][j - 1] = tileMap[i][j];
                                    tileMap[i][j] = tempTile;

                                    tileMap[i][j - 1].moveX(-32);
                                    tileMap[i][j].moveX(32);

                                    player.moveX(-32);
                                    j = tileMap.length;
                                    i = tileMap.length;

                                    moveLogic();
                                }
                            }
                        }
                    }
                }
            }
            else {
                currentSelection--;
                if (currentSelection < 0){
                    currentSelection = options.length - 1;
                }
            }
        }

        if (KeyInput.wasPressed(KeyEvent.VK_RIGHT) || KeyInput.wasPressed(KeyEvent.VK_D)){
            if(options == null) {
                for (int i = 0; i < tileMap.length; i++) {
                    for (int j = 0; j < tileMap.length; j++) {
                        if (tileMap[i][j] != null) {
                            if (tileMap[i][j].isPlayer()) {
                                if (j + 1 < tileMap.length) {
                                    if (tileMap[i][j + 1].getTileClass() == ID.GROUND) {
                                        Tile tempTile = tileMap[i][j + 1];
                                        tileMap[i][j + 1] = tileMap[i][j];
                                        tileMap[i][j] = tempTile;

                                        tileMap[i][j + 1].moveX(32);
                                        tileMap[i][j].moveX(-32);

                                        player.moveX(32);
                                        j = tileMap.length;
                                        i = tileMap.length;

                                        moveLogic();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else {
                currentSelection++;
                if (currentSelection > options.length - 1){
                    currentSelection = 0;
                }
            }
        }

        boolean clicked = false;

        if (options != null) {
            for (int i = 0; i < options.length; i++) {
                if (options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
                    currentSelection = i;
                    clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
                }
            }
        }

        if (clicked || KeyInput.wasPressed(KeyEvent.VK_ENTER))
            select(stateManager);

        for (Entity e : entities)
            e.tick();
    }

    private void select(StateManager stateManager){
        switch (currentSelection){
            case 0 :
                options = null;
                stateManager.setState("battle", this);
                break ;
            case 1 :
                options = null;
                if (!flee());
                    stateManager.setState("battle", this);
                break ;
        }
    }

    private void moveLogic(){
        if (item()){
            String temp = calculateArtifact();
            System.out.println("NEW ARTIFACT!!! : " + temp);
            addArtifact(temp);
        }
        collision();
        enemyMove();
        collision();
    }

    @Override
    public void render(Graphics graphics) {

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, Swingy.WIDTH, Swingy.HEIGHT);

        Sprite background = new Sprite(new SpriteSheet(new Texture("background/3", false), Swingy.WIDTH, Swingy.HEIGHT), 1, 1);
        background.render(graphics, 0, 0);

        for (Entity e : entities)
            e.render(graphics);

        for (int i = 0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap.length; j++){
                if (tileMap[i][j] != null)
                    tileMap[i][j].render(graphics);
            }
        }

        if (options != null) {
            for (int i = 0; i < options.length; i++) {
                if (i == currentSelection)
                    options[i].setSelected(true);
                else
                    options[i].setSelected(false);
                options[i].render(graphics);
            }
        }
    }

    @Override
    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public boolean flee(){
        int random = 0 + (int)(Math.random() * ((3 - 0) + 1));
        boolean possible[] = {false, true, false, false};

        if (possible[random] == true)
            defender = null;

        return possible[random];
    }

    public boolean escape(){
        int random = 0 + (int)(Math.random() * ((3 - 0) + 1));
        boolean possible[] = {true, true, false, false};

        return possible[random];
    }

    public boolean item(){
        int random = 0 + (int)(Math.random() * ((3 - 0) + 1));
        boolean possible[] = {false, true, false, false};

        return possible[random];
    }

    public void setDefender(int id){
        for (Fighter f : fighters) {
            if (f.getID() == id)
                defender = f;
        }
    }

    public boolean collision(){
        for (int i =  0; i < tileMap.length; i++){
            for (int j = 0; j < tileMap.length; j++) {
                if (tileMap[i][j] != null) {
                    if (tileMap[i][j].isPlayer()) {
                        if (j + 1 < tileMap.length) {
                            if (tileMap[i][j + 1].getTileClass() == ID.NINJA
                                    || tileMap[i][j + 1].getTileClass() == ID.DINO
                                    || tileMap[i][j + 1].getTileClass() == ID.ROBO
                                    || tileMap[i][j + 1].getTileClass() == ID.ZOMBO) {

                                setDefender(tileMap[i][j + 1].getMobileID());

                                options = new Button[2];
                                options[0] = new Button("Fight", (500 + 0 * 80),
                                        new Font("Arial", Font.PLAIN, 32),
                                        new Font("Arial", Font.BOLD, 48),
                                        Color.WHITE,
                                        Color.YELLOW);
                                options[1] = new Button("Flee", (500 + 1 * 80),
                                        new Font("Arial", Font.PLAIN, 32),
                                        new Font("Arial", Font.BOLD, 48),
                                        Color.WHITE,
                                        Color.YELLOW);

                                return true;
                            } else if (tileMap[i][j + 1].getTileClass() == ID.LAVA
                                    || tileMap[i][j + 1].getTileClass() == ID.PIT) {
                                if (!item()) {
                                    player.setAlive(false);
                                    gameOver = true;
                                    stateManager.setState("menu", this);
                                }
                                else
                                    addArtifact(calculateArtifact());
                            }
                            else if (tileMap[i][j + 1].getTileClass() == ID.BORDER) {
                                gameOver = true;
                                stateManager.setState("menu", this);
                            }
                        }
                        if (j - 1 > -1) {
                            if (tileMap[i][j - 1].getTileClass() == ID.NINJA
                                    || tileMap[i][j - 1].getTileClass() == ID.DINO
                                    || tileMap[i][j - 1].getTileClass() == ID.ROBO
                                    || tileMap[i][j - 1].getTileClass() == ID.ZOMBO) {

                                setDefender(tileMap[i][j - 1].getMobileID());

                                options = new Button[2];
                                options[0] = new Button("Fight", (500 + 0 * 80),
                                        new Font("Arial", Font.PLAIN, 32),
                                        new Font("Arial", Font.BOLD, 48),
                                        Color.WHITE,
                                        Color.YELLOW);
                                options[1] = new Button("Flee", (500 + 1 * 80),
                                        new Font("Arial", Font.PLAIN, 32),
                                        new Font("Arial", Font.BOLD, 48),
                                        Color.WHITE,
                                        Color.YELLOW);

                                return true;
                            } else if (tileMap[i][j - 1].getTileClass() == ID.LAVA
                                    || tileMap[i][j - 1].getTileClass() == ID.PIT) {
                                if (!item()) {
                                    player.setAlive(false);
                                    gameOver = true;
                                    stateManager.setState("menu", this);
                                }
                                else
                                    addArtifact(calculateArtifact());
                            }
                            else if (tileMap[i][j - 1].getTileClass() == ID.BORDER) {
                                gameOver = true;
                                stateManager.setState("menu", this);
                                System.out.println("WENT OFF THE DEEP END!!!");
                            }
                        }
                        if (i + 1 < tileMap.length) {
                            if (tileMap[i + 1][j].getTileClass() == ID.NINJA
                                    || tileMap[i + 1][j].getTileClass() == ID.DINO
                                    || tileMap[i + 1][j].getTileClass() == ID.ROBO
                                    || tileMap[i + 1][j].getTileClass() == ID.ZOMBO) {

                                setDefender(tileMap[i + 1][j].getMobileID());

                                options = new Button[2];
                                options[0] = new Button("Fight", (500 + 0 * 80),
                                        new Font("Arial", Font.PLAIN, 32),
                                        new Font("Arial", Font.BOLD, 48),
                                        Color.WHITE,
                                        Color.YELLOW);
                                options[1] = new Button("Flee", (500 + 1 * 80),
                                        new Font("Arial", Font.PLAIN, 32),
                                        new Font("Arial", Font.BOLD, 48),
                                        Color.WHITE,
                                        Color.YELLOW);
                                return true;
                            } else if (tileMap[i + 1][j].getTileClass() == ID.LAVA
                                    || tileMap[i + 1][j].getTileClass() == ID.PIT) {
                                if (!item()) {
                                    player.setAlive(false);
                                    gameOver = true;
                                    stateManager.setState("menu", this);
                                }
                                else
                                    addArtifact(calculateArtifact());
                            }
                            else if (tileMap[i + 1][j].getTileClass() == ID.BORDER) {
                                gameOver = true;
                                stateManager.setState("menu", this);
                            }
                        }
                        if (i - 1 > -1) {
                            if (tileMap[i - 1][j].getTileClass() == ID.NINJA
                                    || tileMap[i - 1][j].getTileClass() == ID.DINO
                                    || tileMap[i - 1][j].getTileClass() == ID.ROBO
                                    || tileMap[i - 1][j].getTileClass() == ID.ZOMBO) {

                                setDefender(tileMap[i - 1][j].getMobileID());

                                options = new Button[2];
                                options[0] = new Button("Fight", (500 + 0 * 80),
                                        new Font("Arial", Font.PLAIN, 32),
                                        new Font("Arial", Font.BOLD, 48),
                                        Color.WHITE,
                                        Color.YELLOW);
                                options[1] = new Button("Flee", (500 + 1 * 80),
                                        new Font("Arial", Font.PLAIN, 32),
                                        new Font("Arial", Font.BOLD, 48),
                                        Color.WHITE,
                                        Color.YELLOW);
                                return true;
                            } else if (tileMap[i - 1][j].getTileClass() == ID.LAVA
                                    || tileMap[i - 1][j].getTileClass() == ID.PIT) {
                                if (!item()) {
                                    player.setAlive(false);
                                    gameOver = true;
                                    stateManager.setState("menu", this);
                                }
                                else
                                    addArtifact(calculateArtifact());
                            }
                            else if (tileMap[i - 1][j].getTileClass() == ID.BORDER) {
                                gameOver = true;
                                stateManager.setState("menu", this);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void addArtifact(String artifact){
        switch (artifact){
            case "HELM":
                player.getFighterMetrics().addArtifact(new Helm("helm_small", ID.HELM, player.getFighterMetrics().getLevel().getLevel()));
                break;
            case "ARMOR":
                player.getFighterMetrics().addArtifact(new Armor("armor_small", ID.ARMOR, player.getFighterMetrics().getLevel().getLevel()));
                break;
            case "WEAPON":
                player.getFighterMetrics().addArtifact(new Weapon("weapon_small", ID.WEAPON, player.getFighterMetrics().getLevel().getLevel()));
                break;
        }
    }

    protected void removeFighter(Fighter fighter){

        System.out.printf("FIGHTERS: %d | ENTITIES %d", fighters.size(), entities.size());

        for (int i = 0; i < tileMap.length ;i++){
            for (int j = 0; j < tileMap.length; j++){
                if (tileMap[i][j].getMobileID() == fighter.getID()) {
                    tiles.remove(tileMap[i][j]);

                    tileMap[i][j] = new Tile(tileMap[i][j].getX(), tileMap[i][j].getY(), new Sprite(new SpriteSheet(new Texture("terrain/ground", false), 32), 2, 2), ID.GROUND);
                    tiles.add(tileMap[i][j]);

                    fighters.remove(fighter);
                    entities.remove(fighter);

                    i = tileMap.length;
                    j = tileMap.length;
                }
            }
        }
        System.out.printf("FIGHTERS: %d | ENTITIES %d", fighters.size(), entities.size());
    }
}