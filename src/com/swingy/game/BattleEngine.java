package com.swingy.game;

import com.swingy.heroes.Hero;

public class BattleEngine {

    private static BattleEngine battleEngine = new BattleEngine();

    private Hero _challenger;
    private Hero _defender;

    private BattleEngine(){

    }

    public static BattleEngine getBattleEngine(){
        return BattleEngine.battleEngine;
    }

    public void battle(Hero challenger, Hero defender){
        _challenger = challenger;
        _defender = defender;

        while(challenger.getHeroStats().getHitPoints() > challenger.getDamage()
                && challenger.getHeroStats().getHitPoints() > defender.getDamage()){

            challenger.attack();
            if(!defender.defend()) {
                if (defender.takeDamage(challenger.getHeroStats().getAttackPoints(),
                        defender.getHeroStats().getDefencePoints())){
                    System.out.println(defender.getName() + " is no more");
                    break ;
                }
            }
            defender.attack();
            if(!challenger.defend()) {
                if (challenger.takeDamage(defender.getHeroStats().getAttackPoints(),
                        challenger.getHeroStats().getDefencePoints())){
                    System.out.println(challenger.getName() + " is no more");
                    break ;
                }
            }
        }
    }
}
