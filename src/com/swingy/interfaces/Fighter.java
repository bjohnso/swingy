package com.swingy.interfaces;

import com.swingy.heroes.Hero;
import com.swingy.units.Affinity;

public interface Fighter {
    boolean attack();
    boolean defend(Hero attacker, Hero defender);
    boolean takeDamage(double enemyAttackPoints, double myDefencePoints);
    boolean counter(double enemyAttackPoints, double myDefencePoints);
    Affinity getHeroStats();
}
