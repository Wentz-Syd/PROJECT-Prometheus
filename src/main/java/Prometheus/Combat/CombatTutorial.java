package Prometheus.Combat;

import Prometheus.Characters.Enemy;
import Prometheus.Characters.Equipment;
import Prometheus.Characters.Player;
import Prometheus.Items.Weapon;
import Prometheus.Systems.Dice;

import java.sql.SQLOutput;

import static Prometheus.Characters.Equipment.equipmentSlots;
import static Prometheus.Items.Weapon.basicAtk;
import static Prometheus.Systems.GameLogic.*;

public class CombatTutorial {

    static int count =0;
    final int DEFAULT_SELECTION = 0;

    public static void tutorialStart(Player player, Equipment playerEquip, Enemy enemy){

        boolean hasRun = false;
        boolean playerTurn;
        //get initiative
        clearConsole();
        System.out.println("~~FIRST initiative is rolled to see who goes first!~~");
        anythingToContinue();
        clearConsole();
        if(player.getSaveRef() > enemy.getSaveRef()){
            playerTurn = true;
            System.out.println("\nYou caught the " + enemy.getName() + " unaware and get to act first!");
            System.out.println("\n ~~YES! You we're faster than the creature! Now let's decide how to handle it!");
            anythingToContinue();
            clearConsole();
        } else {
            playerTurn = false;
            System.out.println("\nA " + enemy.getName() + " caught you off guard!");
            System.out.println("\n ~~OH NO! The creature was faster than you! This is probably going to hurt....");
            anythingToContinue();
            clearConsole();
        }

        while (count<15){
            if(player.getCurrentHP() <= 0){
                System.out.println("Oops.... let's just fix that for you....");
                player.setCurrentHP(player.getMaxHP());
                anythingToContinue();
                clearConsole();
                System.out.println("        *HP restored**           ");
                System.out.println("There we go... get back in there!");
            }
            if(playerTurn){
                if(playerTurn(player, playerEquip, enemy)){
                    break;
                }
            }else{
                enemyTurn(player, enemy, playerEquip);
            }
            playerTurn = !playerTurn;
            count++;
        }
        System.out.println("~~ that's the end of the tutorial...                  ~~");
        System.out.println("~~ I hope you learned something.                      ~~");
        System.out.println("~~ I won't be refilling your health from here on out. ~~");
        System.out.println("~~                            Have fun!               ~~");
        player.setCurrentSP(player.getMaxSP());
        player.setCurrentHP(player.getMaxHP());
        anythingToContinue();
        clearConsole();

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
                System.out.println("~~ Alright! This will attack with your equipped weapon! ~~");
                anythingToContinue();
                clearConsole();
                Weapon equipped = playerEquip.getEquippedWeapon(player.getDominantHand());
                playerAttack(player, equipped, enemy);
                isTurn = false;
            }else if(menuSelection == 2){ //spells & skills
                boolean inSkillMenu = true;
                System.out.println("~~ Nice! Skills & Spells.... you can use these as long as you have enough SP! ~~");
                anythingToContinue();
                clearConsole();
                while(inSkillMenu){
                    int skillMenuSelection = -2;
                    printSpellsAndSkillMenu(player);
                    skillMenuSelection = promptForMenuSelection();
                    if(skillMenuSelection==0) {
                        inSkillMenu = false;
                    }else if(skillMenuSelection==-1){
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
                System.out.println("~~ this is where you will find any usable items from your backpack... ~~");
                System.out.println("~~...let's not use them in THIS fight but remember this for later!    ~~");
                anythingToContinue();
                clearConsole();

            }else if(menuSelection == 4){  //defend
                System.out.println("~~ THIS option will give you a temporary bonus to you AC until your next turn! ~~");
                System.out.println("~~ this means you will be more difficult to hit on your enemy's next attack! ~~");
                anythingToContinue();
                clearConsole();
                System.out.println("~~ The amount of a bonus is determined by: ~~");
                System.out.println("~~ a) your Shield, if you have one         ~~");
                System.out.println("~~ b) your Con or Dex, whichever is higher!~~");
                anythingToContinue();
                clearConsole();
                if(equipmentSlots.get("Shield").getDefPwr()>0){
                    player.setTempDef(equipmentSlots.get("Shield").getDefPwr());
                    System.out.println("You raise your shield and brace for an attack!");
                    System.out.println("~~ NICE! This added +" + equipmentSlots.get("Shield").getDefPwr() + " to your AC!");
                    anythingToContinue();
                    clearConsole();
                }else if(player.getStatModifiers()[1]>0 &&player.getStatModifiers()[1]>player.getStatModifiers()[2]){
                    player.setTempDef(player.getStatModifiers()[1]);
                    System.out.println("You set your feet and prepare to dodge!");
                    System.out.println("~~ NICE! This added +" + player.getStatModifiers()[1] + " from your Dexterity Modifier to your AC!");
                    anythingToContinue();
                    clearConsole();
                }else if(player.getStatModifiers()[2]>0 &&player.getStatModifiers()[2]>player.getStatModifiers()[1]){
                    player.setTempDef(player.getStatModifiers()[2]);
                    System.out.println("You set your feet and brace for impact!");
                    System.out.println("~~ NICE! This added +" + player.getStatModifiers()[2] + " from your Constitution Modifier to your AC!");
                    anythingToContinue();
                    clearConsole();
                }else{
                    System.out.println("Your as ready as you're going to be....");
                    System.out.println("~~ ouch.... you have neither a Shield NOR a positive stat modifier.... no bonus for you! ~~");
                    anythingToContinue();
                    clearConsole();
                }

                isTurn = false;
            }else if(menuSelection == 5){  //run away!
                System.out.println("The " + enemy.getName() + " prevented you from escaping!!!");
                System.out.println("~~this is the tutorial.... you can't run!~~");
                anythingToContinue();
                clearConsole();
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
        anythingToContinue();
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
