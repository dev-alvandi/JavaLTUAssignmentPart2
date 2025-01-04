package objects.enemies;

import objects.RoomProperty;

/**
 * * Class for creating the monsters, including a picture of the drake, 
 * * the name of the monster, and setting and getting the monsters 
 * * health points and damage during battle.
 */

public class Monster extends RoomProperty {

    public static String dragonShape =
            "                                                  .~))>>\n"+
                    "                                                 .~)>>\n"+
                    "                                               .~))))>>>\n"+
                    "                                             .~))>>             ___\n"+
                    "                                           .~))>>)))>>      .-~))>>\n"+
                    "                                         .~)))))>>       .-~))>>)>\n"+
                    "                                       .~)))>>))))>>  .-~)>>)>\n"+
                    "                   )                 .~))>>))))>>  .-~)))))>>)>\n"+
                    "                ( )@@*)             //)>))))))  .-~))))>>)>\n"+
                    "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n"+
                    "            (( @.@).              //))))) .-~)>>)))))>>)>\n"+
                    "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n"+
                    "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n"+
                    "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n"+
                    "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n"+
                    "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n"+
                    "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n"+
                    "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n"+
                    " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n"+
                    " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n"+
                    "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n"+
                    "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n"+
                    "       (*.@*              / ((((        \\| | |  \\       .       b `.\n"+
                    "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n"+
                    "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n"+
                    "                       /   /   `(((((()    )    (((((~      `,  b  ;\n"+
                    "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n"+
                    "                 _.-~_.-~           /  /'                _.'~bb _.'\n"+
                    "               ((((~~              / /'              _.'~bb.--~\n"+
                    "                                  ((((          __.-~bb.-~\n"+
                    "                                              .'  b .~~\n"+
                    "                                              :bb ,' \n"+
                    "                                              ~~~~\n";

    private int healthPoints;
    private int damage;

    public Monster(String name, String description, int healthPoints) {
        super(name, description);
        this.healthPoints = healthPoints;
        this.damage = 1;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

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
