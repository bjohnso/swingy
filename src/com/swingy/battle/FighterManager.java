package com.swingy.battle;

import com.swingy.battle.objects.BattleObject;
import com.swingy.battle.objects.HUD;
import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;
import com.swingy.rendering.entities.Fighter;
import com.swingy.states.BattleState;
import com.swingy.helpers.AnimationHelper;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class FighterManager extends BattleObject implements Renderable, PropertyChangeListener {

    private boolean screenLeft = true;
    private HUD hud;
    private ArrayList<Fighter> fighters;
    private Fighter fighter;
    private BattleState battleState;
    private AnimationHelper animationHelper;

    public FighterManager(int x, int y, ID id, boolean screenLeft, HUD hud, Fighter fighter, BattleState battleState) {
        super(x, y, id);
        this.screenLeft = screenLeft;
        this.hud = hud;
        this.battleState = battleState;
        this.fighters = battleState.getFighters();
        this.fighter = fighter;
        this.animationHelper = new AnimationHelper();
    }

    @Override
    public void tick() {
        hud.tick();
    }

    @Override
    public void render(Graphics graphics) {
        hud.render(graphics);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equalsIgnoreCase("AnimationEND")) {
            battleState.setFighters(fighters);
            battleState.setBattleEnd(true);
        }
        else if (evt.getPropertyName().equalsIgnoreCase("ChallengerHP") && this.getId() == ID.Challenger)
            this.hud.setHealth((double) evt.getNewValue());
        else if (evt.getPropertyName().equalsIgnoreCase("DefenderHP") && this.getId() == ID.Defender)
            this.hud.setHealth((double) evt.getNewValue());
        else if (evt.getPropertyName().equalsIgnoreCase("ChallengerDEATH") && this.getId() == ID.Challenger) {

            battleState.setBattleText("DEFEAT");

            Fighter tempFighter = fighter;

            tempFighter.getAnimation().removePropertyChangeListener(this);
            switch (fighter.getPlayerClass()) {
                case NINJA:
                    tempFighter.setAnimation(animationHelper.createAnimation("ninjaDeath"));
                    break;
                case DINO:
                    tempFighter.setAnimation(animationHelper.createAnimation("dinoDeath"));
                    break;
                case ROBO:
                    tempFighter.setAnimation(animationHelper.createAnimation("roboDeath"));
                    break;
                case ZOMBO:
                    tempFighter.setAnimation(animationHelper.createAnimation("zomboDeath"));
                    break;
            }
            tempFighter.getAnimation().addPropertyChangeListener(this);
            tempFighter.setAlive(false);

            fighters.remove(fighter);
            fighters.add(tempFighter);
            fighter = tempFighter;
        }
        else if (evt.getPropertyName().equalsIgnoreCase("DefenderDEATH") && this.getId() == ID.Defender) {

            battleState.setBattleText("VICTORY");

            Fighter tempFighter = fighter;

            tempFighter.getAnimation().removePropertyChangeListener(this);
            switch (fighter.getPlayerClass()) {
                case NINJA:
                    tempFighter.setAnimation(animationHelper.createAnimation("ninjaDeathRef"));
                    break;
                case DINO:
                    tempFighter.setAnimation(animationHelper.createAnimation("dinoDeathRef"));
                    break;
                case ROBO:
                    tempFighter.setAnimation(animationHelper.createAnimation("roboDeathRef"));
                    break;
                case ZOMBO:
                    tempFighter.setAnimation(animationHelper.createAnimation("zomboDeathRef"));
                    break;
            }
            tempFighter.getAnimation().addPropertyChangeListener(this);
            tempFighter.setAlive(false);

            fighters.remove(fighter);
            fighters.add(tempFighter);
            fighter = tempFighter;
        }
    }
}
