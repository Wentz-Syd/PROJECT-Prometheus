package Prometheus.Story;

import Prometheus.Characters.Inventory;
import Prometheus.Items.Weapon;

import javax.sound.midi.Soundbank;

import static Prometheus.Systems.GameLogic.*;

public class ActOne {

    //intro
    public static void printIntro(){
        clearConsole();
        printSeparator(30);
        printSeparator(40);
        printSeparator(45);
        System.out.println("*                                     *                            *           *          ");
        System.out.println("    *           *              *                   *                                   *  ");
        System.out.println("                      *                                                     *             ");
        System.out.println("          *                              ^           *                                    ");
        System.out.println("                                        / |                          *                    ");
        System.out.println("                          *            /   7                                              ");
        System.out.println("                                     _/     l_                          *                 ");
        System.out.println("       (@)          0             _/         |_    (@)              @                 0   ");
        System.out.println(" ~~~~^^^|^^^^^^^^^~^|^^^^^|^^^^^~ |            &|  ^|^^^^^^^^~^^^^^^|^^^^^^~^^^^^^^^^^|^^^");
        printSeparator(45);
        printSeparator(40);
        printSeparator(30);
        anythingToContinue();

        clearConsole();
        System.out.println("               ~I don't know why they made such a big deal of the monsters up here...~   \n\n\n");
        anythingToContinue();
        clearConsole();
        System.out.println("               ~this godsdamned cold is going to kill us before we even find the summit.~");
        anythingToContinue();
        clearConsole();
        System.out.println("** you cinch your cloak even tighter in a vain effort to block the blisteringly cold mountain wind **");
        System.out.println("** as you do so, you feel the identifier tag sewn into the lining of your cloak **");
        anythingToContinue();
        clearConsole();
        System.out.println("** you were told that the monsters up here may leave you... unrecognizable... **");
        System.out.println("** the clerics seem to think this may help them return whatever remains to next of kin **");
        anythingToContinue();
        clearConsole();
        System.out.println("** curious as to how you'll be remembered when you no longer have a face, you take a look **");
        anythingToContinue();
        clearConsole();
    }

    //campsite AFTER character initialization
    //setup starter items
    public static void printCampsite(Inventory backpack){
        clearConsole();
        System.out.println("-WE'RE HERE! EVERYONE SET UP!");
        anythingToContinue();
        clearConsole();
        System.out.println("** the captain managed to yell above the din... I guess we should get the forward base set up. **");
        anythingToContinue();
        clearConsole();
        System.out.println("** it takes the better part of an hour in the wind and cold but a basic camp is erected **\n\n\n");
        anythingToContinue();
        clearConsole();
        System.out.println("** with the tents blocking a bit of wind, one of the soldiers manages to get a fire going **");
        System.out.println("\n\n** it isn't much, but at this point it may as well be the hearth back home **");
        anythingToContinue();

        clearConsole();
        System.out.println("** you decide you should get your gear in order **");
        anythingToContinue();
        clearConsole();
        System.out.println("**rustle**\n");
        System.out.println("                    **rustle**\n");
        System.out.println("      **rustle**");
        anythingToContinue();
        clearConsole();
        System.out.println("** all of my basics are here... **");
        anythingToContinue();

        clearConsole();
    }

    //

}
