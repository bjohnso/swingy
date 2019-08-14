package com.swingy.battle;

import com.swingy.battle.objects.BattleObject;
import com.swingy.battle.objects.HUD;
import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;
import com.swingy.rendering.entities.Fighter;
import com.swingy.states.BattleState;
import com.swingy.statics.Statics;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FighterManager extends BattleObject implements Renderable, PropertyChangeListener {

    private boolean screenLeft = true;
    private HUD hud;
    private Fighter fighter;

    private BattleState battleState;

    public FighterManager(int x, int y, ID id, boolean screenLeft, HUD hud, Fighter fighter, BattleState battleState) {
        super(x, y, id);
        this.screenLeft = screenLeft;
        this.hud = hud;
        this.fighter = fighter;
        this.battleState = battleState;
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

        if (evt.getPropertyName().equalsIgnoreCase("AnimationEND"))
            battleState.setBattleEnd(true);
        else if (evt.getPropertyName().equalsIgnoreCase("ChallengerHP") && this.getId() == ID.Challenger)
            this.hud.setHealth((double) evt.getNewValue());
        else if (evt.getPropertyName().equalsIgnoreCase("DefenderHP") && this.getId() == ID.Defender)
            this.hud.setHealth((double) evt.getNewValue());
        else if (evt.getPropertyName().equalsIgnoreCase("ChallengerDEATH") && this.getId() == ID.Challenger) {
            battleState.setBattleText("DEFEAT");
            fighter.getAnimation().removePropertyChangeListener(this);
            switch (fighter.getPlayerClass()) {
                case NINJA:
                    fighter.setAnimation(Statics.ninjaDeath);
                    break;
                case DINO:
                    fighter.setAnimation(Statics.dinoDeath);
                    break;
                case ROBO:
                    fighter.setAnimation(Statics.roboDeath);
                    break;
                case ZOMBO:
                    fighter.setAnimation(Statics.zomboDeath);
                    break;
            }
            fighter.getAnimation().addPropertyChangeListener(this);
            fighter.setAlive(false);
        }
        else if (evt.getPropertyName().equalsIgnoreCase("DefenderDEATH") && this.getId() == ID.Defender) {
            battleState.setBattleText("VICTORY");
            fighter.getAnimation().removePropertyChangeListener(this);
            switch (fighter.getPlayerClass()) {
                case NINJA:
                    fighter.setAnimation(Statics.ninjaDeathRef);
                    break;
                case DINO:
                    fighter.setAnimation(Statics.dinoDeathRef);
                    break;
                case ROBO:
                    fighter.setAnimation(Statics.roboDeathRef);
                    break;
                case ZOMBO:
                    fighter.setAnimation(Statics.zomboDeathRef);
                    break;
            }
            fighter.getAnimation().addPropertyChangeListener(this);
            fighter.setAlive(false);
        }
    }
}
