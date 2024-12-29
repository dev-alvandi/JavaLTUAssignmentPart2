import objects.RoomProperty;
import objects.enemies.Monster;
import objects.items.Item;

import java.util.*;

/**
 * * Class for constructing the rooms, and for displaying
 * * the narratives corresponding to the doors.
 */

public class Room {

    // create empty hash map
    public static Map<String, String> letterToDirection = new HashMap<>();

    // insert key-value pairs into map
    static {
        letterToDirection.put("n", "norrut");
        letterToDirection.put("s", "söderut");
        letterToDirection.put("ö", "österut");
        letterToDirection.put("v", "västerut");
    }

    // instance variables
    private String roomDesc;
    private List<RoomProperty> roomProperties;
    private List<Door> doors;

    // constructor initializing with room description and room name(property)
    public Room(String roomDesc, List<RoomProperty> roomProperties) {
        this.roomDesc = roomDesc;
        this.roomProperties = roomProperties;
    }


    // method with narratives for doors
    public boolean doNarrative(Player player) {

        for (RoomProperty roomProperty : this.getRoomProperties()) {
            if (roomProperty instanceof Monster && this.doBattle(player, (Monster) roomProperty)) {
                if (roomProperty.getName().equals("drake")) {
                    player.addToInventory("skatten",
                            (Item) this.getRoomProperties()
                                    .stream()
                                    .filter(item -> item.getName().equals("skatten")).findFirst().get());
                }

                this.setRoomProperties(new ArrayList<>());
                break;
            } else if (roomProperty instanceof Monster) {
                return false;
            }

            System.out.println();
        }


        for (Door door : doors) {
            // if door is locked 
            if (door.isLocked() && !player.getInventory().containsKey("nyckeln")) {
                System.out.printf("Du ser en låst dörr i %s [%s]%n",
                        letterToDirection.get(door.getPosition()),
                        door.getPosition());
            // if the player has found the exit door
            } else if (door.isLocked() && player.getInventory().containsKey("nyckeln")) {
                System.out.printf("Du kan gå %s [%s]%n",
                        letterToDirection.get(door.getPosition()),
                        door.getPosition());
            } else if (door.getDestination().getRoomProperties().isEmpty() && player.getHealthPoints() > 0 && door.getPosition().equals("ö")) {
                System.out.println("Du ser en utgång österut [ö]");
            // otherwise other doors within the dungeon
            } else {
                System.out.printf("Du kan gå %s [%s]%n",
                        letterToDirection.get(door.getPosition()),
                        door.getPosition());
            }
        }

        return true;
    }

    // Method handling the battle between the instance of the Monster class and the player
    public boolean doBattle(Player player, Monster monster) {
        if (monster.getName().equals("drake")) {
            System.out.println(Monster.dragonShape);
            System.out.println("En arg drake dyker upp");
        }

        while (monster.getHealthPoints() > 0 && player.getHealthPoints() > 0) {
            System.out.printf("Ett %s attackerar dig och gör %d skada. %n", monster.getName(), monster.getDamage());
            System.out.printf("Du attackerar odjuret och gör %d skada. %n", player.getDamage());

            monster.setHealthPoints(monster.getHealthPoints() - player.getDamage());
            player.setHealthPoints(player.getHealthPoints() - monster.getDamage());
        }

        if (player.getHealthPoints() > 0 && player.getInventory().containsKey("hälsodrycken")) {

        }

        if (monster.getHealthPoints() <= 0) {
            // Victory!
            System.out.println("Du besegrar draken och samlar skatten.\n" +
                    "Kan du fly denna grotta med alla dina rikedomar?");
            return true;
        } else {
            System.out.println("Tyvärr har du blivit eliminerad :(. Försök igen från början!");
            return false;
        }


    }

    // method to get the room descriptions
    public String getRoomDesc() {
        return roomDesc;
    }

    // method to set the room descriptions
    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    // method to get the room properties
    public List<RoomProperty> getRoomProperties() {
        return roomProperties;
    }

    // method to set the room properties
    public void setRoomProperties(List<RoomProperty> roomProperties) {
        this.roomProperties = roomProperties;
    }

    // method to retrieve the door list
    public List<Door> getDoors() {
        return doors;
    }

    // method to set the door list
    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomDesc='" + roomDesc + '\'' +
                ", roomProperties=" + roomProperties +
                ", doors=" + doors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;
        return Objects.equals(getRoomDesc(), room.getRoomDesc()) && Objects.equals(getRoomProperties(), room.getRoomProperties()) && Objects.equals(getDoors(), room.getDoors());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getRoomDesc());
        result = 31 * result + Objects.hashCode(getRoomProperties());
        result = 31 * result + Objects.hashCode(getDoors());
        return result;
    }
}

