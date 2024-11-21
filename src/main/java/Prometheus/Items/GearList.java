package Prometheus.Items;

import Prometheus.Systems.Dice;

public class GearList {


//weapons
    //1-handed
    Weapon flangedMace = new Weapon("Flanged Mace", 1 ,6, 1, "1-handed Mace");
    Weapon longsword = new Weapon("Longsword", 1, 6, 1, "1-handed Blade");

    //agile
    Weapon dagger = new Weapon("Dagger",1, 4,1,"Agile");

    //staff
    Weapon woodenStaff = new Weapon("Wooden Staff", 1,6,1,"Staff");

//armor
    //cloth
    Armor pointedHat = new Armor("Pointed Hat",1,0,"Cloth");
    Armor wizardRobes = new Armor("Wizard Robe",1,0,"Cloth");

    //light
    Armor chainShirt = new Armor("Chain Shirt", 1, 4, "Light Armor");
    Armor leatherArmor = new Armor("Leather Armor", 1, 2, "Light Armor");

    //medium
    Armor scaleMail = new Armor("Scale Mail", 1,4,"Medium Armor");


//shields
    //light
    Armor woodenShield = new Armor("Wooden Shield",1,2,"Light Shield");

    //heavy
    Armor steelShield = new Armor("Steel Shield", 1,2,"Heavy Shield");


//consumables


//key items
    KeyItems holySymbol = new KeyItems("Holy Symbol", 1);
    KeyItems thievesTools = new KeyItems("Thieves Tools", 1);
    KeyItems spellbook = new KeyItems("Spellbook", 1);


}
