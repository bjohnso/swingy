package com.swingy.battle.objects;

import com.swingy.id.ID;
import com.swingy.interfaces.Renderable;
import com.swingy.rendering.entities.Fighter;
import com.swingy.statics.Statics;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FighterManager extends BattleObject implements Renderable, PropertyChangeListener {

    private boolean screenLeft = true;
    private HUD hud;
    private Fighter fighter;

    public FighterManager(int x, int y, ID id, boolean screenLeft, HUD hud, Fighter fighter) {
        super(x, y, id);
        this.screenLeft = screenLeft;
        this.hud = hud;
        this.fighter = fighter;
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
        if (evt.getPropertyName().equalsIgnoreCase("ChallengerHP") && this.getId() == ID.Challenger){
            System.out.println("Challenger" + "\n" + this.hud.getId());
            this.hud.setHealth((double) evt.getNewValue());
        }
        else if (evt.getPropertyName().equalsIgnoreCase("DefenderHP") && this.getId() == ID.Defender) {
            System.out.println("Defender" + "\n" + this.hud.getId());
            this.hud.setHealth((double) evt.getNewValue());
        }
        else if (evt.getPropertyName().equalsIgnoreCase("ChallengerDEATH") && this.getId() == ID.Challenger) {
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
        }
        else if (evt.getPropertyName().equalsIgnoreCase("DefenderDEATH") && this.getId() == ID.Defender) {
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
        }
    }
}
