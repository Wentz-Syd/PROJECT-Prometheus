package Prometheus.Combat;

public class PoisonAttack extends Attack{

    //params
    private int damageOverTime;
    private int cost;
    private String saveType;
    private int saveDc;

    //constructor
    public PoisonAttack(String name, int numOfDice, int atkDie, int modifier, int damageOverTime, int cost, String saveType, int saveDc) {
        super(name, numOfDice, atkDie, modifier);
        this.damageOverTime = damageOverTime;
        this.cost = cost;
        this.saveType = saveType;
        this.saveDc = saveDc;
    }



}
