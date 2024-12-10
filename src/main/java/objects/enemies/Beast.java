package objects.enemies;

public class Beast extends Monster{
    public Beast(String name, String monsterDescription) {
        super(name, monsterDescription);
        this.healthPoints = 8;
    }

    @Override
    public String toString() {
        return "Beast{" +
                "healthPoints=" + healthPoints +
                '}';
    }
}
