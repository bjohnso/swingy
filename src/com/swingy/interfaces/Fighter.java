package com.swingy.interfaces;

import com.swingy.heroes.Hero;
import com.swingy.objects.Affinity;
import com.swingy.objects.HeroStats;

public interface Fighter {
    boolean attack();
    boolean defend(Hero attacker, Hero defender);
    boolean takeDamage(double enemyAttackPoints, double myDefencePoints);
    boolean counter(double enemyAttackPoints, double myDefencePoints);
    Affinity getHeroStats();
}
