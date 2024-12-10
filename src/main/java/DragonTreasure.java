/**
 * * Class for setting up the rooms and doors.
 */

import objects.RoomProperty;
import objects.enemies.Beast;
import objects.enemies.Dragon;
import objects.items.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DragonTreasure {

    // instance variable - empty list for room descriptions
    private List<Room> rooms = new ArrayList<>();

    // method for retrieving the rooms
    public List<Room> getRooms() {
        return rooms;
    }

    public void setupGame() {

        // instantiating Items
        RoomProperty key = new Key("nyckel", "");
        RoomProperty potion = new Potion("hälsodryck", "Du ser en hälsodryck på golvet, du kan plocka upp den [p]");
        RoomProperty treasure = new Treasure("skatt", "");
        RoomProperty weapon = new Weapon("svärd", "Du ser ett svärd på golvet, du kan ta upp det [p]");
        // instantiating Monsters
        RoomProperty beast = new Beast("odjur", "");
        RoomProperty dragon = new Dragon("drake", "");

        // setting up room descriptions
        Room beginningRoom = new Room("Du börjar spelet härifrån", null);
        Room lightRoom = new Room("När du går in i grottan kollapsar ingången bakom dig.\nRummet är upplyst av några ljus som sitter på ett bord framför dig.", null);
        Room swordRoom = new Room("Du ser en död kropp på golvet.", List.of(weapon));
        Room monsterRoom = new Room("Du ser en brinnande fackla i rummets ena hörn och känner en motbjudande stank.", List.of(beast));
        Room potionRoom = new Room("Du kommer in i ett fuktigt rum med vatten sipprandes längs den västra väggen.", List.of(potion));
        Room keyRoom = new Room("Du kommer in i ett rymligt bergrum med en ljusstrimma sipprandes genom en spricka i den östra väggen.", List.of(key));
        Room treasureRoom = new Room("Du kikar genom nyckelhålet och ser en skattkista full med guld.", List.of(treasure, dragon));
        Room exitRoom = new Room("Du lämnar grottan med livet i behåll. Grattis, du förlorade inte!", null);

        // Assembling Doors on the Rooms
        beginningRoom.setDoors(List.of(new Door("ö", false, lightRoom)));
        lightRoom.setDoors(List.of(new Door("n", false, swordRoom), new Door("s", false, keyRoom)));
        swordRoom.setDoors(List.of(new Door("ö", false, monsterRoom), new Door("s", false, lightRoom)));
        monsterRoom.setDoors(List.of(new Door("v", false, swordRoom), new Door("s", false, potionRoom), new Door("ö", false, exitRoom)));
        potionRoom.setDoors(List.of(new Door("n", false, monsterRoom), new Door("v", false, keyRoom), new Door("ö", true, treasureRoom)));
        keyRoom.setDoors(List.of(new Door("n", false, lightRoom), new Door("ö", false, potionRoom)));
        treasureRoom.setDoors(List.of(new Door("v", false, potionRoom)));

        // add room descriptions to ArrayList
        rooms.addAll(Arrays.asList(
                beginningRoom,
                lightRoom,
                swordRoom,
                monsterRoom,
                potionRoom,
                keyRoom,
                treasureRoom,
                exitRoom
        ));
    }
}
