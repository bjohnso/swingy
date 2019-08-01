package com.swingy.game;

import com.swingy.heroes.Hero;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BattleEngine implements Runnable{

    private static BattleEngine battleEngine = new BattleEngine();
    private PropertyChangeSupport support;

    private Hero challenger;
    private Hero defender;
    private double challengerHP;
    private double defenderHP;
    private int counter = 0;
    private boolean battleEnd = false;

    private Thread gameThread;
    private boolean running;
    private int turn = 1;

    public void setChallenger(Hero challenger){
        this.challenger = challenger;
    }

    public void setDefender(Hero defender) {
        this.defender = defender;
    }

    private BattleEngine(){
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    public static BattleEngine getBattleEngine(){
        return BattleEngine.battleEngine;
    }

    public double calcChallengerHP(){
        return challenger.getHeroStats().getHitPoints() - challenger.getDamage();
    }

    public double calcDefenderHP(){
        return defender.getHeroStats().getHitPoints() - defender.getDamage();
    }

    private void setChallengerHP(double challengerHP){
        if (challengerHP < 0)
            challengerHP = 0;
        support.firePropertyChange("ChallengerHP", this.challengerHP, challengerHP);
        this.challengerHP = challengerHP;
    }

    private void setDefenderHP(double defenderHP){
        if (defenderHP < 0)
            defenderHP = 0;
        support.firePropertyChange("DefenderHP", this.defenderHP, defenderHP);
        this.defenderHP = defenderHP;
    }

    private void battleEnd(String defeated){
        support.firePropertyChange(defeated, this.battleEnd, true);
        this.battleEnd = true;
    }

    private void attack(String attacker){
        support.firePropertyChange(attacker, null, true);
    }

    public void battle(int turn){

        if (counter == 0) {
            setChallengerHP((int)calcChallengerHP());
            setDefenderHP((int)calcDefenderHP());
        }
        counter++;

        if (challengerHP == 0) {
            battleEnd("ChallengerDEATH");
            return;
        }
        else if (defenderHP == 0){
            battleEnd("DefenderDEATH");
            return ;
        }

        if (turn < 0) {
            //Challenger's turn to Attack
            attack("ChallengerAttack");
            challenger.attack();
            if (!defender.defend(challenger, defender)) {
                defender.takeDamage(challenger.getHeroStats().getAttackPoints()
                                - (challenger.getHeroStats().getAttackPoints() / 100 * handicap(challenger, defender)),
                        defender.getHeroStats().getDefencePoints());
            } else {
                //Successful Deflection, Evasion, or Counter
                if (defender.counter(challenger.getHeroStats().getAttackPoints(),
                        defender.getHeroStats().getDefencePoints())) {
                    //Successful Counter
                    attack("DefenderAttack");
                    defender.attack();
                    challenger.takeDamage(defender.getHeroStats().getAttackPoints()
                                    - (defender.getHeroStats().getAttackPoints() / 100 * handicap(defender, challenger))
                                    + (defender.getHeroStats().getHitPoints() / 100 * 19),
                            challenger.getHeroStats().getDefencePoints());
                }
            }
        }
        else {
            //Defender's turn to Attack
            attack("DefenderAttack");
            defender.attack();
            if(!challenger.defend(defender, challenger)) {
                challenger.takeDamage(defender.getHeroStats().getAttackPoints()
                                - (defender.getHeroStats().getAttackPoints() / 100 * handicap(defender, challenger)),
                        challenger.getHeroStats().getDefencePoints());
            } else {
                //Successful Deflection, Evasion, or Counter
                if (challenger.counter(defender.getHeroStats().getAttackPoints(),
                        challenger.getHeroStats().getDefencePoints())){
                    //Successful Counter
                    attack("ChallengerAttack");
                    challenger.attack();
                    defender.takeDamage(challenger.getHeroStats().getAttackPoints()
                                    - (challenger.getHeroStats().getAttackPoints() / 100 * handicap(challenger, defender))
                                    + (challenger.getHeroStats().getHitPoints() / 100 * 19),
                            defender.getHeroStats().getDefencePoints());
                }
            }
        }
        /*System.out.println("HERO : " + challenger.getName() + "\nHP : " + challenger.getHeroStats().getHitPoints() + "\nDAMAGE : "
                + challenger.getDamage() + "\n\n");
        System.out.println("HERO : " + defender.getName() + "\nHP : " + defender.getHeroStats().getHitPoints() + "\nDAMAGE : "
                + defender.getDamage());*/
        setChallengerHP(calcChallengerHP());
        setDefenderHP(calcDefenderHP());
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

    @Override
    public void run() {
        running = true;
        while(running) {
            turn *= -1;
            battle(turn);
        }
        stop();
    }

    public synchronized void stop(){
        try {
            gameThread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
