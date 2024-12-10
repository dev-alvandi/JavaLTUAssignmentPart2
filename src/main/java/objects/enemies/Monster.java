package objects.enemies;

import objects.RoomProperty;

import java.util.List;
import java.util.Objects;

public class Monster extends RoomProperty {

    protected int healthPoints;
    private int damage;

    public Monster(String name, String monsterDescription) {
        super(name, monsterDescription);
        this.damage = 1;
    }

//    public static boolean containsMonster(List<RoomProperty> roomProperties) {
//        for (RoomProperty roomProperty : roomProperties) {
//            if (roomProperty instanceof Monster) {
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = (Monster) o;
        return healthPoints == monster.healthPoints && damage == monster.damage;
    }

    @Override
    public int hashCode() {
        int result = healthPoints;
        result = 31 * result + damage;
        return result;
    }
}
