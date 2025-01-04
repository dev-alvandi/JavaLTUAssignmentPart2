import objects.RoomProperty;
import objects.enemies.Monster;
import objects.items.Item;
import objects.items.Treasure;
import objects.items.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * * Class for navigating the dungeon. The playGame method is here which 
 * * contains loops for keeping the game running until player exits or quits.
 */

public class Dungeon {

    // instance variables
    private Room currentRoom;
    private String welcomeMessage;
    private List<String> directions = new ArrayList<>(List.of("ö", "n", "s", "v"));
    private boolean isCorrectInputEnterred;
    private boolean isLoopRunning = true;

    // constructor initializing the dungeon along with a welcome message
    public Dungeon() {
        this.welcomeMessage = "Välkommen till Dragon Treasure\n" +
                "Skriv ditt namn och tryck på [Enter] för att starta ett nytt spel...";
    }

    // method for setting up the game
    public void playGame() {
        // create and initialize DragonTreasure object
        DragonTreasure dragonTreasure = new DragonTreasure();
        // create the rooms, which are stored in an array list
        dragonTreasure.setupGame();

        System.out.println(welcomeMessage);

        // create scanner object for reading name and directions
        Scanner scanner = new Scanner(System.in);

        // initialize player with scanner input from player
        Player player = new Player(scanner.nextLine());
        currentRoom = dragonTreasure.getRooms().get(0);

        // print opening instructions
        System.out.printf("Välkommen %s till din skattjakt.%n", player.getName());
        System.out.println("Du står utanför en grotta. Det luktar svavel från öppningen");
        System.out.println("Grottöppningen är österut. Skriv \"ö\" och tryck på [Enter] för att komma in i grottan");

        // game runs until the player chooses the exit door/room
        while (isLoopRunning) {
            // Convert input to lowercase to simplify comparison logic
            String userInput = scanner.nextLine().toLowerCase();

            // At this point player has not entered the chosen room
            isCorrectInputEnterred = false;

            // User may exit the program using q or Q
            if (userInput.equals("q")) {
                isLoopRunning = false;
            }

            // Handling filling up the health point
            if (userInput.equals("d") && player.getHealthPoints() < 10) {
                player.setHealthPoints(10);
                System.out.println("Du tog upp och drack hälsodrycken. Nu har du max hälsopoängen (10)");
                player.getInventory().remove("hälsodrycken");
                currentRoom.doNarrative(player);
                printPlayerHealthPointAndInventory();
                isCorrectInputEnterred = true;
            }

            if (userInput.equals("i")) {
                System.out.println(player.printInventory());
                currentRoom.doNarrative(player);
                isCorrectInputEnterred = true;
                printPlayerHealthPointAndInventory();
            }

            if (userInput.equals("h")) {
                System.out.printf("Du har %d hälsopoäng.%n", player.getHealthPoints());
                currentRoom.doNarrative(player);
                printPlayerHealthPointAndInventory();
                isCorrectInputEnterred = true;
            }

            // end the game when player chooses the exit door
            if (currentRoom.equals(dragonTreasure.getRooms().get(3)) && userInput.equals("ö") && player.getInventory().containsKey("skatten")) {
                System.out.println(Treasure.treasureShape + "Du lämnar grottan med skatten. Grattis, du vann!");
                break;
            } else if (currentRoom.equals(dragonTreasure.getRooms().get(3)) && userInput.equals("ö")) {
                System.out.println("Du lämnar grottan med livet i behåll. Grattis, du förlorade inte!");
                break;
            }

            // output for if player chooses a door within the dungeon
            for (Door door : currentRoom.getDoors()) {

                // if player chooses an unlocked door, proceed with narrative to the next room
                if (door.getPosition().equals(userInput) && !door.isLocked()) {
                    currentRoom = door.getDestination();
                    System.out.println(currentRoom.getRoomDesc());

                    if(!currentRoom.doNarrative(player)) {
                        isLoopRunning = false;
                    }
                    printPlayerHealthPointAndInventory();
                    isCorrectInputEnterred = true;

                    // or if player chooses a locked door, then show picture and reprint current room narrative
                } else if (door.getPosition().equals(userInput) && door.isLocked() && !player.getInventory().containsKey("nyckeln")) {
                    System.out.println("Du har ingen nyckel som passar.\n" +
                            "Du kikar genom nyckelhålet och ser en skattkista full med guld.\n" +
                            Treasure.treasureShape);

                    if(!currentRoom.doNarrative(player)) {
                        isLoopRunning = false;
                    }
                    printPlayerHealthPointAndInventory();
                    isCorrectInputEnterred = true;

                    // or if a player chooses a locked door and has a key, open door
                } else if (door.getPosition().equals(userInput) && door.isLocked() && player.getInventory().containsKey("nyckeln")) {
                    currentRoom = door.getDestination();

                    if(!currentRoom.doNarrative(player)) {
                        isLoopRunning = false;
                    }
                    printPlayerHealthPointAndInventory();
                    isCorrectInputEnterred = true;
                }

            }


            for (RoomProperty roomProperty : currentRoom.getRoomProperties()) {
                if ((roomProperty instanceof Item) && userInput.equals("p")) {
                    if (roomProperty.getName().equals("hälsodrycken") && player.getHealthPoints() < 10) {
                        player.setHealthPoints(10);
                        System.out.println("Du tog upp och drack hälsodrycken. Nu har du max hälsopoängen (10)");
                        currentRoom.doNarrative(player);
                        printPlayerHealthPointAndInventory();
                    } else {
                        System.out.printf("Du tog upp %s.%n", roomProperty.getName());
                        currentRoom.doNarrative(player);
                        printPlayerHealthPointAndInventory();
                        player.addToInventory(roomProperty.getName(), (Item) roomProperty);
                    }
                    currentRoom.setRoomProperties(new ArrayList<>());
                    if (roomProperty instanceof Weapon) {
                        player.setDamage(2);
                    }
                    isCorrectInputEnterred = true;
                } else if (roomProperty instanceof Item) {
                    System.out.println(roomProperty.getDescription());
                }
            }




//            // Exception is thrown if player chooses wrong direction
            if (!isCorrectInputEnterred) {
                System.out.println("Du har skrivit fel. Försök igen!");
            }
        }
    }

    private static void printPlayerHealthPointAndInventory() {
        System.out.println("Du kan se din hälsopoäng [h]");
        System.out.println("Du kan se ditt lager [i]");
    }
}
