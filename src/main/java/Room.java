import objects.RoomProperty;
import objects.enemies.Monster;
import objects.items.Item;
import objects.items.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public void doNarrative(List<Item> playerInventory) {
        for (Door door : doors) {
            // if door is locked 
            if (door.isLocked() &&
                    !RoomProperty.containsSpecificSubClassOfRoomProperty(playerInventory, Key.class)) {
                System.out.printf("Du ser en låst dörr i %s [%s]%n",
                        letterToDirection.get(door.getPosition()),
                        door.getPosition());
            // if the player has found the exit door
            } else if (door.getDestination().getRoomProperties() != null &&
                    Monster.containsSpecificSubClassOfRoomProperty(door.getDestination().getRoomProperties(), Monster.class) &&
                    door.getPosition().equals("ö")) {
                System.out.println("Du ser en utgång österut [ö]");
            // otherwise other doors within the dungeon
            } else {
                System.out.printf("Du kan gå %s [%s]%n",
                        letterToDirection.get(door.getPosition()),
                        door.getPosition());
            }
        }
    }



    // Method handling the battle between the instance of the Monster class and the player
    public void doBattle() {

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
}

