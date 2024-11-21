package Prometheus.Combat;

import Prometheus.Characters.Enemy;
import Prometheus.Characters.Player;

public class SpecialAttack extends Attack{

    //params
    private int cost;
    private String target;


    //G&S
    public int getCost() {
        return cost;
    }
    public String getTarget() {
        return target;
    }

    //constructor
    public SpecialAttack(String name, int numOfDice, int atkDie, int modifier, int cost, String target) {
        super(name,numOfDice,atkDie,modifier);
        this.cost = cost;
        this.target = target;
    }

//....methods
    public void useSpecialAttack(Player player, Enemy enemy, int modifier){
        if(target == "Heal") {
            int startingHp = player.getCurrentHP();
            player.setCurrentHP(player.getCurrentHP() + basicAttack(modifier));
            if (player.getCurrentHP() > player.getMaxHP()) {
                player.setCurrentHP(player.getMaxHP());
            }
            player.setCurrentSP(player.getCurrentHP()-getCost());
            System.out.println("\nYou healed yourself for " + (player.getCurrentHP()-startingHp) + "!" );
        }else{
            int dmgDealt = basicAttack(modifier);
            player.setCurrentSP(player.getCurrentSP()-getCost());
            System.out.println("\nYou hit the " + enemy.getName() + " with your " + getName() + " for " + dmgDealt + "!\n");
            enemy.setCurrentHP(enemy.getCurrentHP()-dmgDealt);
        }
    }
}
