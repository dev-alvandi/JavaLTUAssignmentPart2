package enemies;

public class Monster {

    private String name;
    private String monsterDescription;
    protected int healthPoints;
    private int damage;

    public Monster(String name, String monsterDescription) {
        this.name = name;
        this.monsterDescription = monsterDescription;
        this.damage = 1;
    }
}
