package Prometheus.Systems;


import Prometheus.Characters.*;
import Prometheus.Characters.Character;
import Prometheus.Combat.Attack;
import Prometheus.Story.ActOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import static Prometheus.Characters.Ancestry.*;
import static Prometheus.Combat.CombatTutorial.tutorialStart;

public class GameLogic {

    static Scanner scanner = new Scanner(System.in);
    public static boolean isRunning;


    //begin game
    public static void startGame() {
        isRunning = true;
        //print title screen
        clearConsole();

        howTo();
        anythingToContinue();

//        clearConsole();
//        printSeparator(40);
//        printSeparator(30);
//        System.out.println("***Project: PROMETHEUS***");                //removed for testing speed
//        System.out.println("     **a test rpg**");
//        System.out.println("        *by Syd*");
//        printSeparator(30);
//        printSeparator(40);
//        anythingToContinue();

        //roll luck stat and say ominous shit
        clearConsole();
        final int FIRST_LUCK = Dice.d20(1);
        printSeparator(35);
        printSeparator(30);
        if (FIRST_LUCK == 20) {
            System.out.println("You who art chosen by Luck... proceed boldy.");
        } else if (FIRST_LUCK >= 15) {
            System.out.println("Oh, ye who walks with Luck... proceed mirthfully.");
        } else if (FIRST_LUCK >= 10) {
            System.out.println("You have chosen this path... may it be in your favor.");
        } else if (FIRST_LUCK >= 2) {
            System.out.println("Ye who Luck overlooked.... proceed with caution.");
        } else {
            System.out.println("Ye who luck hath forsaken... proceed to your end.");
        }
        printSeparator(30);
        printSeparator(35);
        anythingToContinue();

        //print intro
//        ActOne.printIntro();                   //removed for testing speed

        //create player character
        Player player = createCharacter();
        Inventory backpack = player.setupInventory();
        Equipment playerEquip = new Equipment();
        Equipment.equipmentInit();


        int[] ancestryArray = setAncestryArray(player);
        starterStats(player.getStats(), setAncestryArray(player));
        player.setStatModifiers(player.getStats());

        printSeparator(40);
        System.out.println("|Stat Name|...|Rank|...|Ancestry Modifier|");
        printSeparator(40);
        System.out.println("Strength:      " + player.getStats()[0] + "       [" + ancestryArray[0] + "]");
        System.out.println("Dexterity:     " + player.getStats()[1] + "       [" + ancestryArray[1] + "]");
        System.out.println("Constitution:  " + player.getStats()[2] + "       [" + ancestryArray[2] + "]");
        System.out.println("Intelligence:  " + player.getStats()[3] + "       [" + ancestryArray[3] + "]");
        System.out.println("Wisdom:        " + player.getStats()[4] + "       [" + ancestryArray[4] + "]");
        System.out.println("Charisma:      " + player.getStats()[5] + "       [" + ancestryArray[5] + "]");
        printSeparator(40);

        System.out.println("\nGiven these stats, what class are you?");
        String choice = checkStringChoice("(Cleric/Fighter/Rogue/Wizard)", classes);
        if(choice.trim().equalsIgnoreCase("Cleric")){
            player.setCharacterClass("Cleric");
            player.setCleric(player.getStats());
        }else if(choice.trim().equalsIgnoreCase("Fighter")){
            player.setCharacterClass("Fighter");
            player.setFighter(player.getStats());
        }else if(choice.trim().equalsIgnoreCase("Rogue")){
            player.setCharacterClass("Rogue");
            player.setRogue(player.getStats());
        }else if(choice.trim().equalsIgnoreCase("Wizard")){
            player.setCharacterClass("Wizard");
            player.setWizard(player.getStats());
        }

        //print campfire
//        ActOne.printCampsite();              //removed for testing speed

        player.printCharacterSheet(playerEquip);
        Inventory.printInventory(backpack.getInventory());
        System.out.println("\n\n\n");

        //print dungeon intro
        ActOne.dungeonIntro();
        List<Attack> wendigoTut = new ArrayList<>();
        wendigoTut.add(new Attack("Claws",1,6,0));
        Enemy tutorialWendigo = new Enemy("Wendigo",35, 10, 2,2,2,4, wendigoTut);
        tutorialStart(player, playerEquip,tutorialWendigo);

        //print dungeonStart




    }

//------ create player character
    static String[] getStatName = {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
    static String[] ancestries = {"dwarf", "elf", "human", "orc"};
    static String[] classes = {"Cleric", "Fighter", "Rogue", "Wizard"};
    static String[] handed = {"Right", "Left"};


    public static Player createCharacter() {
        //greeting
        clearConsole();
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        printSeparator(30);

        clearConsole();
        System.out.println("What's your ancestry?");
        String ancestry = checkStringChoice("(Dwarf/Elf/Human/Orc)", ancestries);

        clearConsole();
        int age = checkInt("How old are you?");

        clearConsole();
        System.out.println("What is your dominant hand?");
        String dominantHand = checkStringChoice("Right/Left", handed);
        String otherHand;
        if(dominantHand.equalsIgnoreCase("Right")){
            otherHand = "Left";
        }else{
            otherHand = "Right";
        }

        printSeparator(30);
        System.out.println("~~set your stats~~");
        int[] stats = assignStats();

        clearConsole();
        return new Player(name, ancestry, age, stats, dominantHand, otherHand);
    }


    //generate 6 stats
    public static int[] assignStats() {
        int[] statRolls = new int[6];
        for (int i = 0; i < 6; i++) {
            statRolls[i] = Dice.roll4DropLowest();
        }

        printSeparator(30);

        //set each stat
        int[] stats = new int[6];
        boolean[] assigned = new boolean[6];

        assignStatsHelper(stats, statRolls, assigned);

        //display assigned stats for validation
        while (true) {
            System.out.println("Assigned Stats:");
            for (int i = 0; i < stats.length; i++) {
                System.out.println(getStatName[i] + ": " + stats[i]);
            }

            //ask for validation
            System.out.println("Are you satisfied with these stats? (yes/no): ");
            String validation = scanner.nextLine().trim().toLowerCase();

            if (validation.equals("yes")) {
                return stats;
            } else {
                System.out.println("Would you like to reassign the current stats or re-roll? (reassign/reroll): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (choice.equals("reassign")) {
                    assigned = new boolean[6];
                    assignStatsHelper(stats, statRolls, assigned);
                } else if (choice.equals("reroll")) {
                    return assignStats();
                }
            }
        }
    }

    private static void assignStatsHelper(int[] stats, int[] statRolls, boolean[] assigned){
        System.out.println("rolled stats for assignment");
        for (int stat : statRolls) {
            System.out.println(stat + " ");
        }
        System.out.println();
        printSeparator(30);

        for (int i = 0; i < stats.length; i++) {
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Enter value for " + getStatName[i] + ": ");
                int input = scanner.nextInt();
                scanner.nextLine();

                //check if input is already assigned
                for (int j = 0; j < statRolls.length; j++) {
                    if (input == statRolls[j] && !assigned[j]) {
                        stats[i] = input;
                        assigned[j] = true;
                        validInput = true;
                        break;
                    }
                }
                if (!validInput) {
                    System.out.println("Invalid input. Please enter one of the available rolled stats.");
                }
            }
        }
    }

    //set ancestry array
    public static int[] setAncestryArray(Character player){

        if (Objects.equals(player.getAncestry(), "dwarf")) {
            return dwarfStats;

        } else if (Objects.equals(player.getAncestry(), "elf")) {
            return elfStats;

        } else if (Objects.equals(player.getAncestry(), "human")) {
            return humanStats;

        } else {
            return orcStats;
        }

    }

    //change stats based on ancestry
    public static void starterStats(int[] characterStats, int[] ancestry){
        int i = 0;
        for(int stat : ancestry){
            characterStats[i]+=stat;
            i++;
        }
    }



    //class and derived stats methods

//------ end of character creation-------


//---- system processes

    //print how-to file
    public static void howTo(){
        printSeparator(30);
        printSeparator(40);
        System.out.println("Time stops when you see 'press 'enter' to continue...' simply hit enter to continue. ");
        System.out.println("When presented with a choice (option1/option2) type the word and hit enter.");
        printSeparator(40);
        printSeparator(30);
    }

    //clear the console
    public static void clearConsole(){
        for(int i=0; i<100; i++){
            System.out.println();
        }
    }

    //print a separator of length n
    public static void printSeparator(int n){
        for(int i=0; i<n; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    //print a heading
    public static void printHeading(String title){
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    //get integer input given a prompt and variable number of choices
    public static int readInt(String prompt, int userChoices){
        int input;
        do{
            System.out.println(prompt);
            try{
                input = Integer.parseInt(scanner.next());
            }catch (Exception e){
                input = -1;
                System.out.println("Please enter an integer!");
            }
        }while(input <1 || input > userChoices);
        return input;
    }

    //check if an input is an integer
    public static int checkInt(String prompt){
        int input;
        while (true){
            System.out.println(prompt);
            try{
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please input a valid integer.");
            }
        }
        return input;
    }

    //check if a valid option is chosen from an array of strings
    public static String checkStringChoice(String prompt, String[] validOptions){
        String input;
        while (true){
            System.out.println(prompt);
            input = scanner.nextLine().trim().toLowerCase();
            if(isValidOption(input, validOptions)){
                break;
            }else{
                System.out.println("Invalid input. Please choose from the following options: ");
            }
        }
        return input;
    }
    private static boolean isValidOption(String input, String[] validOptions){
        for(String option : validOptions){
            if (option.equalsIgnoreCase(input)){
                return true;
            }
        }
        return false;
    }

    //menu item selection
    public static int promptForMenuSelection() {
        int menuSelection;
        System.out.print("Please choose an option: ");
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    //wait for input
    public static void anythingToContinue(){
        System.out.println("\nPress 'enter' to continue...");
        scanner.nextLine();
    }

    //round down half
    public static int roundDown(int n){
        double num = n;
        return (int)Math.floor(num/2);
    }


}
