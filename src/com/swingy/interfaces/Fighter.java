package com.swingy.interfaces;

import com.swingy.battle.FighterMetrics;
import com.swingy.units.FighterBaseStats;

public interface Fighter {
    boolean attack();
    boolean defend(FighterMetrics attacker, FighterMetrics defender);
    boolean takeDamage(double enemyAttackPoints, double myDefencePoints);
    boolean counter(double enemyAttackPoints, double myDefencePoints);
    FighterBaseStats getFighterStats();
}
