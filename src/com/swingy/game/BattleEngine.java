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
                && defender.getHeroStats().getHitPoints() > defender.getDamage()){
            //Challenger's turn to Attack
            System.out.println("CHALLENGER's TURN");
            challenger.attack();
            if(!defender.defend()) {
                if (defender.takeDamage(challenger.getHeroStats().getAttackPoints(),
                        defender.getHeroStats().getDefencePoints())){
                    System.out.println(defender.getName() + " is no more");
                    break ;
                }
            }
            else {
                //Successful Deflection, Evasion, or Counter
                if (defender.counter(challenger.getHeroStats().getAttackPoints(),
                        defender.getHeroStats().getDefencePoints())){
                    //Successful Counter
                    defender.attack();
                    if (challenger.takeDamage(defender.getHeroStats().getAttackPoints()
                                    + (defender.getDamage() / 100 * 7),
                            challenger.getHeroStats().getDefencePoints())){
                        System.out.println(challenger.getName() + " is no more");
                        break ;
                    }
                }
            }
            System.out.println(challenger.getName() + " HP : " + (challenger.getHeroStats().getHitPoints() - challenger.getDamage())
                    + "\nAttack Points : " + challenger.getHeroStats().getAttackPoints());
            System.out.println(defender.getName() + " HP : " + (defender.getHeroStats().getHitPoints() - defender.getDamage())
                    + "\nAttack Points : " + defender.getHeroStats().getAttackPoints());
            System.out.println("DEFENDER's TURN");
            //Defender's turn to Attack
            defender.attack();
            if(!challenger.defend()) {
                if (challenger.takeDamage(defender.getHeroStats().getAttackPoints(),
                        challenger.getHeroStats().getDefencePoints())){
                    System.out.println(challenger.getName() + " is no more");
                    break ;
                }
            }
            else {
                //Successful Deflection, Evasion, or Counter
                if (challenger.counter(defender.getHeroStats().getAttackPoints(),
                        challenger.getHeroStats().getDefencePoints())){
                    //Successful Counter
                    challenger.attack();
                    if (defender.takeDamage(challenger.getHeroStats().getAttackPoints()
                                    + (challenger.getDamage() / 100 * 7),
                            defender.getHeroStats().getDefencePoints())){
                        System.out.println(defender.getName() + " is no more");
                        break ;
                    }
                }
            }
            System.out.println(challenger.getName() + " HP : " + (challenger.getHeroStats().getHitPoints() - challenger.getDamage())
                    + "\nAttack Points : " + challenger.getHeroStats().getAttackPoints());
            System.out.println(defender.getName() + " HP : " + (defender.getHeroStats().getHitPoints() - defender.getDamage())
                    + "\nAttack Points : " + defender.getHeroStats().getAttackPoints());
        }
    }
}
