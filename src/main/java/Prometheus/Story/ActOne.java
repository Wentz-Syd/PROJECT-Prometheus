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
    public static void printCampsite(){
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

    //dungeon intro
    public static void dungeonIntro(){
        clearConsole();
        System.out.println("** you hear shouting coming from the edge of camp and decide to check it out ** ");
        anythingToContinue();
        clearConsole();
        System.out.println("** you see a group forming around a single soldier.... one of the forward team ** ");
        anythingToContinue();
        clearConsole();
        System.out.println("-What happened?! Where's the rest of your team?!?");
        System.out.println("             the captain asks the bloody soldier");
        anythingToContinue();
        clearConsole();
        System.out.println("** you're sure the soldier can't answer... **");
        anythingToContinue();
        clearConsole();
        System.out.println("** it looks like half their face is missing  but they manage to weakly point at the opening of the cave**");
        anythingToContinue();
        clearConsole();
        System.out.println("** the captain looks down...");
        System.out.println("-ALRIGHT. Secondary team. Plans have accelerated. Prepare to move in");
        anythingToContinue();
        clearConsole();
        System.out.println("                                          ....shit.");
        anythingToContinue();
        clearConsole();
        System.out.println("That's your team. Guess it's now or never.");
        anythingToContinue();
        clearConsole();

        System.out.println("** after about 10mins your group of 5 makes their way to the opening of the cave.");
        anythingToContinue();
        clearConsole();
        System.out.println("** you all step into the cave and venture in **");
        System.out.println("** in the corner is what seems to be the remains of the forward team... and some creature hunched over top of them");
        anythingToContinue();
        clearConsole();
        System.out.println("** it cranes its like neck backwards at your team.... red eyes aglow, wreathed in shaggy white fur");
        System.out.println("** before anyone can react it has crossed half the small room and swiped out with its long claws, rendering 2 of your team on the ground and helpless....");
        System.out.println("** it sets it's sights on you.... here it comes! **");
        anythingToContinue();
        clearConsole();
    }

}
