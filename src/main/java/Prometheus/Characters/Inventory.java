package Prometheus.Characters;

import Prometheus.Items.Item;

import java.util.*;

import static Prometheus.Systems.GameLogic.printSeparator;

public class Inventory {

    //params
    private Map<Integer, Item> inventory;
    private Set<Integer> availableSlots;


    //G&S
    public Map<Integer, Item> getInventory() {
        return inventory;
    }

    public Set<Integer> getAvailableSlots() {
        return availableSlots;
    }


    //constructor
    public Inventory(Map<Integer, Item> inventory, Set<Integer> availableSlots) {
        this.inventory = inventory;
        this.availableSlots = availableSlots;
    }


    //methods
    //available slots
    public static Set<Integer> setPackSize(int maxSlots) {
        Set<Integer> availableSlots = new HashSet<>();
        for (int i = 1; i <= maxSlots; i++) {
            availableSlots.add(i);
        }
        return availableSlots;
    }

    //add &| increment
    public static void addItem(Item item, Set<Integer> availableSlots, Map<Integer, Item> inventory) {
        if (!availableSlots.isEmpty()) {
            // Reuse an available slot
            int slot = availableSlots.iterator().next();
            availableSlots.remove(slot);
            inventory.put(slot, item);
        } else {
            System.out.println("Your inventory is full!");
        }
    }

    //remove & decrement
    public static void removeItem(int slot, Set<Integer> availableSlots, Map<Integer, Item> inventory) {
        Item removedItem = inventory.remove(slot);
        if (removedItem != null) {
            availableSlots.add(slot);
        }
    }

    //print inventory
    public static void printInventory(Map<Integer, Item> inventory){
        printSeparator(40);
        System.out.println("|Slot|..|Item Name|......|Quantity|");
        inventory.forEach((i, item) -> System.out.printf("%d%s %-20s %s%s\n", i, ") ",item.getName()," Qty: ",item.getQuantity()));
        printSeparator(40);
    }


}
