package Prometheus.Characters;

import Prometheus.Combat.Attack;

import java.util.List;
import java.util.Random;

public class Enemy {

    //params
    private String name;
    private int maxHP;
    private int currentHP;
    private int ac;
    private int saveFort;
    private int saveRef;
    private int saveWill;
    private int atkBonus;
    private List<Attack> attacks;
    private int[] stats = new int[6];
        //[0]=Strength
        //[1]=Dexterity
        //[2]=Constitution
        //[3]=Intelligence
        //[4]=Wisdom
        //[5]=Charisma


    //constructor
    public Enemy(String name, int maxHP, int ac, int saveFort, int saveRef, int saveWill, int atkBonus, List<Attack> attacks) {
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.saveFort = saveFort;
        this.saveRef = saveRef;
        this.saveWill = saveWill;
        this.atkBonus = atkBonus;
        this.attacks = attacks;
    }



    //G&S
    public String getName(){
        return name;
    }
    public void setName(){
        this.name = name;
    }

    public int getMaxHP() {
        return maxHP;
    }
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getAc() {
        return ac;
    }
    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getSaveFort() {
        return saveFort;
    }
    public void setSaveFort(int saveFort) {
        this.saveFort = saveFort;
    }

    public int getSaveRef() {
        return saveRef;
    }
    public void setSaveRef(int saveRef) {
        this.saveRef = saveRef;
    }

    public int getSaveWill() {
        return saveWill;
    }
    public void setSaveWill(int saveWill) {
        this.saveWill = saveWill;
    }

    public int[] getStats() {
        return stats;
    }
    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public int getAtkBonus() {
        return atkBonus;
    }
    public void setAtkBonus(int atkBonus) {
        this.atkBonus = atkBonus;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }
//    public void setAtkPwr(int atkPwr) {
//        this.atkPwr = atkPwr;
//    }


    //methods
    public Attack getRandomAttack(List<Attack> attacks){
        int min = 0;
        int max = attacks.size();
        Random random = new Random();
        return attacks.get(random.nextInt(min,max));
    }

//    public int basicEnemyAttack(Attack attack){
//        return getAtkBonus()+attack.getDamage();
//    }
}
