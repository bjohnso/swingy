package com.swingy.battle;

import com.swingy.input.KeyInput;
import com.swingy.input.MouseInput;
import com.swingy.util.NumberHelper;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BattleEngine implements Runnable{

    private PropertyChangeSupport support;

    private FighterMetrics challenger;
    private FighterMetrics defender;
    private double challengerHP;
    private double defenderHP;
    private int counter = 0;
    private boolean battleEnd = false;

    private boolean running;
    private int turn = 1;
    private int tickLag = 0;

    public boolean end = false;
    public int winCount = 0;
    public int balancedWinCount = 0;
    public double averagePowerDifference = 0;

    public void setChallenger(FighterMetrics challenger){
        this.challenger = challenger;
    }

    public void setDefender(FighterMetrics defender) {
        this.defender = defender;
    }

    public BattleEngine(){
        start();
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }


    public double calcChallengerHP(){
        return challenger.getFighterStats().getHitPoints() - challenger.getDamage();
    }

    public double calcDefenderHP(){
        return defender.getFighterStats().getHitPoints() - defender.getDamage();
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
        support.firePropertyChange(defeated, false, true);
        this.end =true;
        stop();
    }

    private void attack(String attacker){
        support.firePropertyChange(attacker, null, true);
    }

    public void battle(int turn){

        if (counter == 0) {
            challenger.setDamage(0);
            defender.setDamage(0);
            setChallengerHP((int)calcChallengerHP());
            setDefenderHP((int)calcDefenderHP());
        }
        counter++;

        if (challengerHP <= 0 && !battleEnd) {
            this.battleEnd = true;
            battleEnd("ChallengerDEATH");
            return;
        }
        else if (defenderHP <= 0 && !battleEnd){
            averagePowerDifference += NumberHelper.round(challenger.getFighterStats().getHitPoints(), 2) - NumberHelper.round(challenger.getDamage(), 2);
            winCount++;
            if (challengerHP - 10 >= defenderHP && defenderHP + 15 <= challengerHP)
                balancedWinCount++;
            this.battleEnd = true;
            challenger.gainExperience();
            battleEnd("DefenderDEATH");
            return ;
        }

        if (turn < 0) {
            //Challenger's turn to Attack
            attack("ChallengerAttack");
            challenger.attack();
            if (!defender.defend(challenger, defender)) {
                defender.takeDamage(challenger.getFighterStats().getAttackPoints()
                                - (challenger.getFighterStats().getAttackPoints() / 100 * handicap(challenger, defender)),
                        defender.getFighterStats().getDefencePoints());
            } else {
                //Successful Deflection, Evasion, or Counter
                if (defender.counter(challenger.getFighterStats().getAttackPoints(),
                        defender.getFighterStats().getDefencePoints())) {
                    //Successful Counter
                    attack("DefenderAttack");
                    defender.attack();
                    challenger.takeDamage(defender.getFighterStats().getAttackPoints()
                                    - (defender.getFighterStats().getAttackPoints() / 100 * handicap(defender, challenger))
                                    + (defender.getFighterStats().getHitPoints() / 100 * 30),
                            challenger.getFighterStats().getDefencePoints());
                }
            }
        }
        else {
            //Defender's turn to Attack
            attack("DefenderAttack");
            defender.attack();
            if(!challenger.defend(defender, challenger)) {
                challenger.takeDamage(defender.getFighterStats().getAttackPoints()
                                - (defender.getFighterStats().getAttackPoints() / 100 * handicap(defender, challenger)),
                        challenger.getFighterStats().getDefencePoints());
            } else {
                //Successful Deflection, Evasion, or Counter
                if (challenger.counter(defender.getFighterStats().getAttackPoints(),
                        challenger.getFighterStats().getDefencePoints())){
                    //Successful Counter
                    attack("ChallengerAttack");
                    challenger.attack();
                    defender.takeDamage(challenger.getFighterStats().getAttackPoints()
                                    - (challenger.getFighterStats().getAttackPoints() / 100 * handicap(challenger, defender))
                                    + (challenger.getFighterStats().getHitPoints() / 100 * 19),
                            defender.getFighterStats().getDefencePoints());
                }
            }
        }
        setChallengerHP(calcChallengerHP());
        setDefenderHP(calcDefenderHP());

        //System.out.println(challenger.toString() + "\nHP LEFT : " + (challenger.getFighterStats().getHitPoints() - challenger.getDamage()) + "\n");
        //System.out.println(defender.toString() + "\nHP LEFT : " + (defender.getFighterStats().getHitPoints() - defender.getDamage()) + "\n");
    }


    private Double handicap(FighterMetrics attacker, FighterMetrics defender){
        double toReturn = 0;
        switch (attacker.getAffinities().entrySet().iterator().next().getKey()){
            case "NINJA":
                if (defender.getAffinities().entrySet().iterator().next().getKey().equals("ROBO"))
                    toReturn = 96.0;
                else if (defender.getAffinities().entrySet().iterator().next().getKey().equals("ZOMBO"))
                    toReturn = 97.0;
                break;
            case "DINO":
                if (defender.getAffinities().entrySet().iterator().next().getKey().equals("NINJA"))
                    toReturn = 95.0;
                else if (defender.getAffinities().entrySet().iterator().next().getKey().equals("ROBO"))
                    toReturn = 23.0;
                break;
            case "ZOMBO":
                if (defender.getAffinities().entrySet().iterator().next().getKey().equals("DINO"))
                    toReturn = 30.0;
                else if (defender.getAffinities().entrySet().iterator().next().getKey().equals("NINJA"))
                    toReturn = 0;
                break;
            case "ROBO":
                if (defender.getAffinities().entrySet().iterator().next().getKey().equals("ZOMBO"))
                    toReturn = 85.0;
                else if (defender.getAffinities().entrySet().iterator().next().getKey().equals("DINO"))
                    toReturn = 20.0;
                break;
        }
        return toReturn;
    }

    @Override
    public void run() {
        //Game Loop
        double targetTicks = 60.0;
        double nanoSecondsPT = 100000000.0 / targetTicks;
        double unprocessed = 0.0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int fps = 0;
        int tps = 0;
        boolean canRender = false;

        while (running){
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nanoSecondsPT;
            lastTime = now;

            if (unprocessed >= 1){
                tick();

                //Update Input References
                KeyInput.update();
                MouseInput.update();

                unprocessed--;
                tps++;
                canRender = true;
            } else {
                canRender = false;
            }

            if (canRender){
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer){
                timer += 1000;
                fps = 0;
                tps = 0;
            }
        }
        stop();
    }

    public void tick() {
        running = true;
        if (tickLag++ > 30){
            turn*=-1;
            battle(turn);
            tickLag = 0;
        }
    }

    private void start(){
        if (running)
            return;
        running = true;
        new Thread(this, "BattleEngine-Thread").start();
    }

    public void stop(){
        if (!running)
            return;
        running = false;
    }
}
