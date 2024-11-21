package Prometheus.Combat;

import Prometheus.Systems.Dice;

public class Attack {

    //params
    private String name;
//    private int damage;
    private int numOfDice;
    private int atkDie;
    private int modifier;

    //constructor
    public Attack(String name, int numOfDice, int atkDie, int modifier) {
        this.name = name;
        this.numOfDice = numOfDice;
        this.atkDie = atkDie;
        this.modifier = modifier;
    }


    //G&S
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfDice() {
        return numOfDice;
    }
    public void setNumOfDice(int numOfDice) {
        this.numOfDice = numOfDice;
    }

    public int getAtkDie() {
        return atkDie;
    }
    public void setAtkDie(int atkDie) {
        this.atkDie = atkDie;
    }

    public int getModifier() {
        return modifier;
    }
    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

//......methods

    //basic attack
    public int basicAttack(int atkBonus){
        int rolls = 0;
        for(int i=0; i<numOfDice; i++){
            rolls += Dice.roll(atkDie);
        }
        return rolls+atkBonus;
    }
}
