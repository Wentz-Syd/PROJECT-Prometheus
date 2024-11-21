package Prometheus.Characters;

import Prometheus.Items.Armor;
import Prometheus.Items.Item;
import Prometheus.Items.Weapon;

import java.util.LinkedHashMap;
import java.util.Map;

public class Equipment {

    //params
    int AcBonus;
    public static Map<String, Armor> equipmentSlots = new LinkedHashMap<>();
    static Weapon emptyHand = new Weapon("Fist",1, 4,1, "Unarmed");

    static Map<String, Weapon> equippedWeapons = new LinkedHashMap<>();
    static Armor empty = new Armor("-empty-",1,0,"placeholder");

    //G&S
    public int getAcBonus() {
        AcBonus = calcAC(equipmentSlots);
        return AcBonus;
    }

    public static Map<String, Armor> getEquipmentSlots() {
        return equipmentSlots;
    }
    public static Map<String, Weapon> getEquippedWeapons() {
        return equippedWeapons;
    }

    public static Weapon getEquippedWeapon(String hand){
        return equippedWeapons.get(hand);
    }

    //    public void setAcBonus(int acBonus) {
//        AcBonus = acBonus;
//    }




    //calculate ac bonus
    public static int calcAC(Map<String, Armor> equipmentSlots){
        final int[] acBonus = {0};
        equipmentSlots.forEach((i, Armor) -> {
            acBonus[0] += equipmentSlots.get(i).getDefPwr();
        });
        return acBonus[0];
    }


    //Initial empty equipment state
    public static void equipmentInit(){
        equipmentSlots.put("Head", empty);
        equipmentSlots.put("Neck", empty);
        equipmentSlots.put("Body", empty);
        equipmentSlots.put("Belt", empty);
        equipmentSlots.put("Legs", empty);
        equipmentSlots.put("Feet", empty);
        equipmentSlots.put("Hands", empty);
        equipmentSlots.put("Shield", empty);
        equipmentSlots.put("Ring", empty);
    }
    public static void weaponsInit(){
        equippedWeapons.put("Right", emptyHand );
        equippedWeapons.put("Left", emptyHand);
    }

    //equip an item
    public static void equipArmor(String bodySlot, Armor armorName){
        equipmentSlots.put(bodySlot, armorName);
    }
    public static void equipWeapon(String hand, Weapon weaponName){
        equippedWeapons.put(hand,weaponName);
    }



}
