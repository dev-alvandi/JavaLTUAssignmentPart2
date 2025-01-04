# Antaganden
### 1. Klasser 
#### Room Property
   * #### Enum används för att skilja mellan olika rum.
   * #### Rummen kan ha olika egenskaper definierade med `RoomProperty`, t.ex., `MONSTER`, `TREASURE`, `START`, och `END`.
   * #### Slutrummet (`END`) avslutar spelet när spelaren når det.
   * #### RoomProperty är basklassen till Klasserna Item och Monster.

#### Item och arv
* #### Key, Potion, Treasure, och Weapon ärvar från Item klassen

#### Main
* #### Egen main-klass används för att öka läsbarheten i koden genom att skapa en instans av Dungeon-klassen och anropa metoden playGame().

### 2. Felhantering
   * #### Ogiltiga kommandon/riktning resulterar i ett felmeddelande.
   * #### Programmet avslutas kontrollerat när spelaren skriver "q".

### 3. Funktioner
   * #### Spelaren kan när som helst kolla föremål som finns i lager (i) och befintliga hälsopoäng (h)

#### Hälsodrycken
   * #### Hälsodrycken återställer spelarens hälsopoäng till max (10) om spelaren har mindre är max hälsopoäng vid upplockning.
   * #### Om spelaren redan har full hälsa vid upplockningen, hamnar drycken i lagret.
   * #### När spelaren senare förlorar hälsopoäng kan hen när som helst använda drycken genom att skriva "d" i spelet.
