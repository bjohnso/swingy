package com.swingy.game;

import com.swingy.heroes.Hero;

public class BattleEngine {

    private static BattleEngine battleEngine = new BattleEngine();

    private Hero challenger;
    private Hero defender;

    private BattleEngine(){

    }

    public static BattleEngine getBattleEngine(){
        return BattleEngine.battleEngine;
    }

    public double getChallengerHP(){
        return challenger.getHeroStats().getHitPoints() - challenger.getDamage();
    }

    public double getDefenderHP(){
        return defender.getHeroStats().getHitPoints() - defender.getDamage();
    }

    public void battle(Hero chall, Hero def){
        challenger = chall;
        defender = def;

        while(challenger.getHeroStats().getHitPoints() > challenger.getDamage()
                && defender.getHeroStats().getHitPoints() > defender.getDamage()){
            //Challenger's turn to Attack
            System.out.println("CHALLENGER's TURN");
            challenger.attack();
            if(!defender.defend(challenger, defender)) {
                if (defender.takeDamage(challenger.getHeroStats().getAttackPoints()
                                - (challenger.getHeroStats().getAttackPoints() / 100 * handicap(challenger, defender)),
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
                                    - (defender.getHeroStats().getAttackPoints() / 100 * handicap(defender, challenger))
                                    + (defender.getHeroStats().getHitPoints() / 100 * 19),
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
            if(!challenger.defend(defender, challenger)) {
                if (challenger.takeDamage(defender.getHeroStats().getAttackPoints()
                                - (defender.getHeroStats().getAttackPoints() / 100 * handicap(defender, challenger)),
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
                                    - (challenger.getHeroStats().getAttackPoints() / 100 * handicap(challenger, defender))
                                    + (challenger.getHeroStats().getHitPoints() / 100 * 19),
                            defender.getHeroStats().getDefencePoints())){
                        System.out.println(defender.getName() + " is no more");
                        break ;
                    }
                }
            }
            System.out.println(challenger.getName() + " HP : " + (challenger.getHeroStats().getHitPoints() - challenger.getDamage())
                    + "\nAttack Points : " + challenger.getHeroStats().getAttackPoints()
                    + "\nDefence Points : " + challenger.getHeroStats().getDefencePoints());
            System.out.println(defender.getName() + " HP : " + (defender.getHeroStats().getHitPoints() - defender.getDamage())
                    + "\nAttack Points : " + defender.getHeroStats().getAttackPoints()
                    + "\nDefence Points : " + defender.getHeroStats().getDefencePoints());
        }
    }

    private Double handicap(Hero attacker, Hero defender){
        if (attacker.getAffinities().entrySet().iterator().next().getKey().equalsIgnoreCase("WATER")
                && defender.getAffinities().entrySet().iterator().next().getKey().equalsIgnoreCase("EARTH")) {
            return 5.0;
        }
        else if (attacker.getAffinities().entrySet().iterator().next().getKey().equalsIgnoreCase("FIRE")
                && defender.getAffinities().entrySet().iterator().next().getKey().equalsIgnoreCase("WATER")) {
            return .9;
        }
        else if (attacker.getAffinities().entrySet().iterator().next().getKey().equalsIgnoreCase("EARTH")
                && defender.getAffinities().entrySet().iterator().next().getKey().equalsIgnoreCase("FIRE")) {
            return 7.0;
        }
        return 0.0;
    }
}
