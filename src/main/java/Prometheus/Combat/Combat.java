package Prometheus.Combat;

import Prometheus.Characters.Enemy;
import Prometheus.Characters.Equipment;
import Prometheus.Characters.Player;
import Prometheus.Items.Weapon;
import Prometheus.Systems.Dice;

import static Prometheus.Characters.Equipment.equipmentSlots;
import static Prometheus.Items.Weapon.basicAtk;
import static Prometheus.Systems.GameLogic.*;

public class Combat {


    public static void combatStart(Player player, Equipment playerEquip, Enemy enemy){

        boolean hasRun = false;
        boolean playerTurn;
        //get initiative
        if(player.getSaveRef() > enemy.getSaveRef()){
            playerTurn = true;
            System.out.println("\nYou caught the " + enemy.getName() + " unaware and get to act first!");
        } else {
            playerTurn = false;
            System.out.println("\nA " + enemy.getName() + " caught you off guard!");
        }

        while (player.getCurrentHP() > 0 && enemy.getCurrentHP() > 0){
            if(playerTurn){
                if(playerTurn(player, playerEquip, enemy)){
                    hasRun = true;
                    break;
                }
            }else{
                enemyTurn(player, enemy, playerEquip);
            }
            playerTurn = !playerTurn;
        }
        if(hasRun){
            System.out.println("You barely managed to escape the " + enemy.getName() + "...");
        }else if(player.getCurrentHP() <= 0){
            System.out.println("You have succumb to your wounds.... Game Over.");
        }else{
            System.out.println("The " + enemy.getName() + " is no more...");
        }

    }


    //combat menu
    private static void printCombatMenu(Player player){
        System.out.println("\n");
        printSeparator(22);
        System.out.println("|HP: " + player.getCurrentHP() + "/" + player.getMaxHP());
        System.out.println("|SP: " + player.getCurrentSP() + "/" + player.getMaxSP());
        printSeparator(22);
        System.out.println("| 1) Attack         |");
        System.out.println("| 2) Spells/Skills  |");
        System.out.println("| 3) Use Item       |");
        System.out.println("| 4) Defend         |");
        System.out.println("| 5) Run Away!      |");
        printSeparator(22);
        System.out.println("\n");
    }




//--------ROLL TO HIT
    private static boolean rollToHit(Player player, Weapon equipped, Enemy enemy){
        if(equipped.getType() == "Ranged" || equipped.getType() == "Agile"){
            if(Dice.d20(1)+player.getStatModifiers()[1]+player.getBaseAttackBonus() >= enemy.getAc()){
                return true;
            }else{
                return false;
            }
        }else if(equipped.getType() == "Magic"){
            if(Dice.d20(1)+player.getStatModifiers()[3]+player.getBaseAttackBonus() >= enemy.getAc()){
                return true;
            }else{
                return false;
            }
        }else{
            if(Dice.d20(1)+player.getStatModifiers()[0]+player.getBaseAttackBonus() >= enemy.getAc()){
                return true;
            }else{
                return false;
            }
        }
    }
    private static boolean rollToHitEnemy(Enemy enemy, Equipment playerEquip, Player player){
        if(Dice.d20(1) + enemy.getAtkBonus() >= playerEquip.getAcBonus()+player.getTempDef()){
            return true;
        }else{
            return false;
        }
    }




//------------------TURNS
    private static boolean playerTurn(Player player, Equipment playerEquip, Enemy enemy){
        player.setTempDef(0);
        int menuSelection = -1;
        boolean isTurn = true;
        while (isTurn) {
            printCombatMenu(player);
            menuSelection = promptForMenuSelection();
            if(menuSelection == 1){  //basic attack
                Weapon equipped = playerEquip.getEquippedWeapon(player.getDominantHand());
                playerAttack(player, equipped, enemy);
                isTurn = false;
            }else if(menuSelection == 2){ //spells & skills
                boolean inSkillMenu = true;
                while(inSkillMenu){
                    int skillMenuSelection = -1;
                    printSpellsAndSkillMenu(player);
                    skillMenuSelection = promptForMenuSelection();
                    if(skillMenuSelection==0){
                        inSkillMenu = false;
                    }else{
                        SpecialAttack chosenSkill = player.getSpellsAndSkills().get(skillMenuSelection);
                        if (chosenSkill.getCost() <= player.getCurrentSP()){
                            chosenSkill.useSpecialAttack(player, enemy, chosenSkill.getModifier());
                            isTurn = false;
                            inSkillMenu = false;
                        }else{
                            System.out.println("\nYou don't have enough SP!");
                            inSkillMenu = false;
                        }
                    }
                }
            }else if(menuSelection == 3){  //use item
                isTurn = false;
            }else if(menuSelection == 4){  //defend
                if(equipmentSlots.get("Shield").getDefPwr()>0){
                    player.setTempDef(equipmentSlots.get("Shield").getDefPwr());
                    System.out.println("You raise your shield and brace for an attack!");
                }else if(player.getStatModifiers()[1]>0 &&player.getStatModifiers()[1]>player.getStatModifiers()[2]){
                    player.setTempDef(player.getStatModifiers()[1]);
                    System.out.println("You set your feet and prepare to dodge!");
                }else if(player.getStatModifiers()[2]>0 &&player.getStatModifiers()[2]>player.getStatModifiers()[1]){
                    player.setTempDef(player.getStatModifiers()[2]);
                    System.out.println("You set your feet and brace for impact!");
                }else{
                    System.out.println("Your as ready as you're going to be....");
                }

                isTurn = false;
            }else if(menuSelection == 5){  //run away!
                if(Dice.d20(1)+ player.getSaveRef()>=Dice.d20(1)+enemy.getSaveRef()){
                    return true;
                }else{
                    System.out.println("The " + enemy.getName() + " prevented you from escaping!!!");
                }
                isTurn = false;
            }else if(menuSelection != 0){
                System.out.println("Invalid Menu Choice");
            }
        }
        return false;
    }
    private static void enemyTurn(Player player, Enemy enemy, Equipment playerEquip){
        if(rollToHitEnemy(enemy, playerEquip, player)){
            Attack attack = enemy.getRandomAttack(enemy.getAttacks());
            int dmgDealt = attack.basicAttack(enemy.getAtkBonus());
            System.out.println("\nThe " + enemy.getName() + "'s " + attack.getName() + " hit you for " + dmgDealt + "!!\n");
            player.setCurrentHP(player.getCurrentHP()- dmgDealt);
        }else{
            System.out.println("\nThe " + enemy.getName() + "'s attack missed!\n");
        }
    }



    //player attack method
    private static void playerAttack(Player player, Weapon equipped, Enemy enemy){
        if(rollToHit(player,equipped,enemy)){
            if(equipped.getType() == "Ranged" || equipped.getType() == "Agile"){
                int dmgDealt = basicAtk(player.getStatModifiers()[1],equipped.getNumOfDice(),equipped.getAtkDie());
                System.out.println("\nYou hit the " + enemy.getName() + " with your " + equipped.getName() + " for " + dmgDealt + "!\n");
                enemy.setCurrentHP(enemy.getCurrentHP()-dmgDealt);
            }else if(equipped.getType() == "Magic"){
                int dmgDealt = basicAtk(player.getStatModifiers()[3],equipped.getNumOfDice(),equipped.getAtkDie());
                System.out.println("\nYou hit the " + enemy.getName() + " with your " + equipped.getName()  + " for " + dmgDealt + "!\n");
                enemy.setCurrentHP(enemy.getCurrentHP()-dmgDealt);
            }else{
                int dmgDealt = basicAtk(player.getStatModifiers()[0],equipped.getNumOfDice(),equipped.getAtkDie());
                System.out.println("\nYou hit the " + enemy.getName() + " with your " + equipped.getName()  + " for " + dmgDealt + "!\n");
                enemy.setCurrentHP(enemy.getCurrentHP()- dmgDealt);
            }
        }else{
            System.out.println("\nYour attack missed!!\n");
        }
    }


    private static void printSpellsAndSkillMenu(Player player){
        printSeparator(22);
        for(int i=1; i<player.getSpellsAndSkills().size()+1; i++){
            System.out.println(i + ") " + player.getSpellsAndSkills().get(i).getName());
        }
        System.out.println("0) Exit");
        printSeparator(22);
    }


}
