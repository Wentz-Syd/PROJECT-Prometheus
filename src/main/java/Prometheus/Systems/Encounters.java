package Prometheus.Systems;

import Prometheus.Characters.Enemy;
import Prometheus.Combat.Attack;
import Prometheus.Combat.PoisonAttack;
import Prometheus.Combat.SpecialAttack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Encounters {

    private static Map<Integer, Enemy> enemyEncounters = new LinkedHashMap<>();

    public static Map<Integer, Enemy> getEnemyEncounters() {
        return enemyEncounters;
    }

    public static void setupEnemyEncounters(){
        List<Attack> skeletonAttacks = new ArrayList<>();
        skeletonAttacks.add(new Attack("Claw", 1, 4,1));
        enemyEncounters.put(1, new Enemy("Skeleton", Dice.d12(1),15,0,1,2, 1,skeletonAttacks));

        List<Attack> skeletonAttacksArmed = new ArrayList<>();
        skeletonAttacksArmed.add(new Attack("Claw",1,4,1));
        skeletonAttacksArmed.add(new Attack("Sword", 1,6,1));
        enemyEncounters.put(2,new Enemy("Armed Skeleton", Dice.d12(1),15,0,1,2, 1, skeletonAttacksArmed));

        List<Attack> troglodyteAttacks = new ArrayList<>();
        troglodyteAttacks.add(new Attack("Club", 1,6,0));
        troglodyteAttacks.add(new Attack("Claw", 1,4,0));
        enemyEncounters.put(3, new Enemy("Troglodyte",Dice.d8(2)+4,15,5,-1,0, 1,troglodyteAttacks));

        List<Attack> vargouilleAttacks = new ArrayList<>();
        vargouilleAttacks.add(new Attack("Bite",1,4,0));
        vargouilleAttacks.add(new PoisonAttack("Bite", 1,4,0,Dice.d4(1)-1,3,"Fortitude",12));
        enemyEncounters.put(4, new Enemy("Vargouille", Dice.d8(1)+1,12,3,3,3,3,vargouilleAttacks));

        List<Attack> batSwarmAttacks = new ArrayList<>();
        batSwarmAttacks.add(new Attack("Swarm",1,6,0));
        enemyEncounters.put(5, new Enemy("Bat Swarm", Dice.d8(3),16,3,7,3,0, batSwarmAttacks));

        skeletonAttacks.add(new Attack("Claw",1,4,1));
        enemyEncounters.put(6, new Enemy("Skeleton",Dice.d12(1),15,0,1,2, 1,skeletonAttacks));
    }

    public static Enemy chooseEnemy(Map<Integer, Enemy> enemies){
        int choice = Dice.d6(1);
        return enemies.get(choice);
    }



}
