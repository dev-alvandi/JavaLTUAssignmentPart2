import objects.RoomProperty;
import objects.items.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * * Class for constructing the player with player name.
 */

public class Player {

    // instance variable
    private String name;
    private int healthPoints;
    private int damage;

    private Map<String, Item> inventory = new HashMap<>();

    // constructor initializing the player with name
    public Player(String name) {
        this.name = name;
        this.healthPoints = 10;
        this.damage = 1;
    }

    // method for retrieving the player's name
    public String getName() {
        return name;
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getDamage() {
        return damage;
    }

    public void addToInventory(String newItemName, Item newItem) {
        this.inventory.put(newItemName, newItem);
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    // Can be modified in case the player possesses the Sword (2 damage unit)
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String printInventory() {
        if (!this.inventory.isEmpty()) {
            return "Du har följande artiklar i ditt lager: " + this.inventory.values().stream()
                    .map(RoomProperty::getName).collect(Collectors.joining(", "));
        }

        return "Ditt lager är tomt";
    }
}
