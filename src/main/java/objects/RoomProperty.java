package objects;

import objects.enemies.Monster;

import java.util.List;

public class RoomProperty {

    private String name;
    private String description;

    public RoomProperty(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static <T extends RoomProperty> boolean containsSpecificSubClassOfRoomProperty(
            List<? extends RoomProperty> roomProperties,
            Class<T> roomPropertyClass) {
        for (RoomProperty roomProperty : roomProperties) {
            if (roomPropertyClass.isInstance(roomProperty)) {
                return true;
            }
        }
        return false;
    }
}
