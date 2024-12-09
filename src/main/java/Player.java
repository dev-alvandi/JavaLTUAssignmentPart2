import items.Item;

import java.util.List;

/**
 * * Class for constructing the player with player name.
 */

public class Player {

    // instance variable
    private String name;
    private int healthPoints;
    private int damage;

    private List<Item> inventory;

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

    public List<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item newItem) {
        this.inventory.add(newItem);
    }

    // Can be modified in case the player possesses the Sword (2 damage unit)
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
