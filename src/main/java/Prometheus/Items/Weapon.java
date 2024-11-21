package Prometheus.Items;

import Prometheus.Systems.Dice;

public class Weapon extends Item{

    //params
    private int atkDie;
    private int numOfDice;
    private String type;


    //G&S
    public int getAtkDie() {
        return atkDie;
    }
    public void setAtkDie(int atkPwr) {
        this.atkDie = atkPwr;
    }

    public int getNumOfDice() {
        return numOfDice;
    }
    public void setNumOfDice(int numOfDice) {
        this.numOfDice = numOfDice;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    //constructor
    public Weapon(String name, int quantity, int atkDieType, int atkDieNumber, String type) {
        super(name, quantity);
        this.atkDie = atkDieType;
        this.numOfDice = atkDieNumber;
        this.type = type;
    }


    //methods (special traits)
    public static int basicAtk(int atkStat, int numOfDice, int atkDie){
        int rolls =0;
        for(int i=0; i<numOfDice; i++){
            rolls += Dice.roll(atkDie);
        }
        return rolls+atkStat;
    }

}
