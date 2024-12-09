package enemies;

public class Dragon extends Monster{
    public Dragon(String name, String monsterDescription) {
        super(name, monsterDescription);
        this.healthPoints = 18;
    }

    @Override
    public String toString() {
        return "Dragon{" +
                "healthPoints=" + healthPoints +
                '}';
    }
}
