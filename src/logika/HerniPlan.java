package logika;

import java.util.ArrayList;
import java.util.List;
import util.ObserverZmenyProstoru;
import util.SubjektZmenyProstoru;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 *
 * Tato třída inicializuje prvky ze kterých se hra skládá: vytváří všechny
 * prostory, propojuje je vzájemně pomocí východů a pamatuje si aktuální
 * prostor, ve kterém se hráč právě nachází.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2015/2016
 */
public class HerniPlan implements SubjektZmenyProstoru{

    private Prostor aktualniProstor;
    private Planeta planet;
    private Inventory inventory;
    private int powerPoints;
    private List<ObserverZmenyProstoru> seznamPozorovatelu;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí
     * východů. Vytvari aktualni batoh. Jako výchozí aktuální prostor nastaví
     * halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        seznamPozorovatelu = new ArrayList<>();
        inventory = new Inventory(this);
    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů. Vytváří
     * jednotlivé planety a propojuje je pomocí východů. Vytvari veci a prida
     * jejich do prostoru a osob. Vytvari osoby a prida jejich do prostoru. Jako
     * výchozí aktuální prostor nastaví Compartment. Jako výchozí aktuální
     * planetu nastaví D'Qar.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor spaceStation1 = new Prostor("Compartment", "This is a sleeping compartment, there only beds and lockers around", 40.0, 250.0);
        Prostor spaceStation2 = new Prostor("Outpost", "This is an Outpost, you can travel to another planets from here", 115.0, 200.0);
        Prostor korribianLand1 = new Prostor("Tombs_enter", "You see the great tombs of the siths in front you,\n there is only one way to enter there", 100.0, 115.0);
        Prostor korribianLand2 = new Prostor("Tomb", "This is the great, long time ago forgotten Tomb of the Siths ,\n you see some old droid here. Maybe you should speak with him", 60.0, 80.0);
        Prostor correlianLand1 = new Prostor("Port", "This is the a mazingly beautiful port, which stands out on the water surface", 235.0, 115.0);
        Prostor correlianLand2 = new Prostor("Ruins", "This is an underwater ruins. Around you are only statues\n of unknown creatures, and fish", 270.0, 90.0);
        Prostor tatooineLand1 = new Prostor("Rock", "You are located on a high sandy hill , around is only a desert,\n not far away you can see the camp", 205.0, 185.0);
        Prostor tatooineLand2 = new Prostor("Camp", "This is a the camp of nomads , usually not a popular place", 260.0, 240.0);

        //zamykame urcite prostory
        korribianLand2.close(true);
        String keyToTombs = "546897";
        korribianLand2.setKey(keyToTombs);
        correlianLand2.close(true);
        String keyToOcean = "582";
        correlianLand2.setKey(keyToOcean);

        // vytvářejí se jednotlivé planety
        Planeta Korribian = new Planeta("Korribian", "A long time ago there was the Sith Academy, now there are only ruins", korribianLand1);
        Planeta Corellia = new Planeta("Corellia", "100 percent of the planet's surface is water", correlianLand1);
        Planeta Dqar = new Planeta("D'Qar", "This is a death planet, you can land only at a space station", spaceStation2);
        Planeta Tatooine = new Planeta("Tatooine", "A sand planet, no water, bad climate, try your luck here", tatooineLand1);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        spaceStation1.setVychod(spaceStation2);
        spaceStation2.setVychod(spaceStation1);
        korribianLand1.setVychod(korribianLand2);
        korribianLand2.setVychod(korribianLand1);
        correlianLand1.setVychod(correlianLand2);
        correlianLand2.setVychod(correlianLand1);
        tatooineLand1.setVychod(tatooineLand2);
        tatooineLand2.setVychod(tatooineLand1);

        // přiřazují se průchody mezi planety
        Korribian.setExit(Corellia);
        Korribian.setExit(Dqar);
        Korribian.setExit(Tatooine);
        Corellia.setExit(Korribian);
        Corellia.setExit(Dqar);
        Corellia.setExit(Tatooine);
        Dqar.setExit(Korribian);
        Dqar.setExit(Tatooine);
        Dqar.setExit(Corellia);
        Tatooine.setExit(Corellia);
        Tatooine.setExit(Dqar);
        Tatooine.setExit(Korribian);

        // vytvářejí se jednotlivé veci
          Vec laserGun = new Vec("laser", true);
        laserGun.setCestaKObrazku("/images/laserGun.jpg");
        Vec sword = new Vec("sword", true);
        sword.setCestaKObrazku("/images/sword.jpg");
        
        Vec bed = new Vec("bed", false);
        Vec stul = new Vec("table", false);
        
        Vec ancientStatue = new Vec("statue", true);
        ancientStatue.setCestaKObrazku("/images/AncientStatue.jpg");
        
        Vec chair = new Vec("chair", false);
        Vec spaceShip = new Vec("spaceship", false);
        Vec rocks = new Vec("rocks", false);
        
        Vec garbage = new Vec("garbage", true);
        garbage.setCestaKObrazku("/images/garbage.jpeg");
        Vec creatureHead = new Vec("head", true);
        creatureHead.setCestaKObrazku("/images/creaturehead.jpg");
      
        
        Vec nothing = new Vec("doesnt_exits", false);
        
        Vec lightsaber = new Vec("Lightsaber", true);
        lightsaber.setCestaKObrazku("/images/lightsaber.jpg");
        Vec paper = new Vec("paper", true);
        paper.setCestaKObrazku("/images/paper.jpg");
        
        Vec sithRemains = new Vec("Remains_of_the_sith", false);
        
        Vec hand = new Vec("hand", true);
        hand.setCestaKObrazku("/images/hand.jpg");
        // vytvářejí se jednotlivé persony, pridavame person do prostoru
        Person spaceStation1Person = new Person("Trader", "Hey there! I have some usefull knowledge for you" + "\n" + "but first you should bring me an ancient statue from Corellia, deal?", true, sword, false, false);
        spaceStation1Person.setTrade("There is a rumor that at Korribian is an Ancient tomb, maybe you will find there what you're looking for.", ancientStatue, "No, I asked you for ", "Yeah, thanks! That's what i need!", "Come back later");
        spaceStation1.addPerson(spaceStation1Person);
        spaceStation1Person.setAnswers("Yeah, i accept that", "No! Die! (this option will kill a NPC)");

        Person spaceStation2Person = new Person("Pilot", "Hello, welcome to D'Qar! You could use spaceship to fly somethere from here", false, sword, false, false);
        Person spaceStation2Person2 = new Person("Citizen", "What do u want?", false, sword, false, false);
        Person korribianPerson1 = new Person("Jaba", "What have you lost here? I would not recommend to be here. This is mysterious planet", false, sword, false, false);

        Person finalDroid = new Person("Ancient_droid", "Hello stranger, I'm the old robot.\n I was created to choose if people have enough power to gain the last lightsaber." + "\n"
                + "You need to answer my question to get the lighsaber. \nThere is no other way to get it. So, the question is who was the first Sith Lord?", true, nothing, true, false);
        finalDroid.setDrop(lightsaber);

        Person commander = new Person("Soldier", "Hello, jedi. I have a mission for you. There is a terrible creature under the water." + "\n" + "We need you to kill it. Could you help us?\n If you agree, the password to the door is 582 and don't forget to bring us his head!", true, sword, false, false);
        correlianLand1.addPerson(commander);
        commander.setAnswers("Yeah, sure i will help you", "No, i prefer to kill you!");
        commander.setTrade("Thanks! You saved our planet! By the way did u know what the first sith lord was Revan?", creatureHead, "No, I asked you for ", "Glad to see you are alive!", "Come back later");

        Person creature = new Person("Creature", "Rrrrrrrrrrrrrrrrrrrrr!", false, laserGun, true, true);
        creature.setDrop(creatureHead);

        Person stranger = new Person("Stranger", "Hello, you look like a strong man. Could help me to solve the problem?" + "\n" + "There is camp not far away from here, some sand mans atack it 3 hours ago.\n Could you kill them for me", true, sword, true, false);
        stranger.setDrop(paper);
        tatooineLand1.addPerson(stranger);
        stranger.setAnswers("Of course i will help you!", "Maybe I will kill them, but u die first today!");

        stranger.setTrade("You saved the camp? Thanks dude! I know the information that will be useful for you!\n A password is 546897" + "\n" + "You will understand, when u need it", hand, "No, I asked you for ", "Hey, you made it!", "Come back later");
        Person sandMan1 = new Person("Sand_man", "RrrrRRrRRrrr", false, sword, false, true);
        Person sandMan2 = new Person("Sand_man2", "RrrrRRrRRrrr", false, sword, true, true);
        sandMan2.setDrop(hand);
        Person sandMan3 = new Person("Sand_man3", "RrrrRRrRRrrr", false, laserGun, false, true);

        tatooineLand2.addPerson(sandMan1);
        tatooineLand2.addPerson(sandMan2);
        tatooineLand2.addPerson(sandMan3);

        korribianLand1.addPerson(korribianPerson1);
        correlianLand2.addPerson(creature);

        spaceStation2.addPerson(spaceStation2Person);
        spaceStation2.addPerson(spaceStation2Person2);

        korribianLand2.addPerson(finalDroid);

        // pridavaji se veci do prostoru
        spaceStation1.vlozVec(sword);
        spaceStation1.vlozVec(stul);
        spaceStation1.vlozVec(bed);
        spaceStation1.vlozVec(chair);

        spaceStation2.vlozVec(spaceShip);

        correlianLand1.vlozVec(laserGun);
        correlianLand2.vlozVec(ancientStatue);

        korribianLand1.vlozVec(rocks);
        korribianLand1.vlozVec(garbage);
        korribianLand2.vlozVec(sithRemains);

        planet = Dqar;        // the game begins here   
        aktualniProstor = spaceStation1;  // the game begins here      
    }

    /**
     * Metoda zvysuje pocet bodu hrace
     *
     * @parametr powerpoints - pocet bodu v zavislosti ne kterych hrac hraje
     * jako dobra postava nebo sparna
     */
    public void increasePoints() {
        powerPoints++;
    }

    /**
     * Metoda zmensuje pocet bodu hrace
     *
     * @parametr powerpoints - pocet bodu v zavislosti ne kterych hrac hraje
     * jako dobra postava nebo sparna
     */
    public void decreasePoints() {
        powerPoints--;
    }

    /**
     * Metoda vraci pocet bodu hrace
     *
     * @return powerpoints - pocet bodu v zavislosti ne kterych hrac hraje jako
     * dobra postava nebo sparna
     */
    public int getPowerPoints() {
        return powerPoints;
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda vrací odkaz na aktuální planetu, na ktere se hráč právě nachází.
     *
     * @return aktuální planetu
     */
    public Planeta getPlanet() {
        return planet;
    }

    /**
     * Metoda vraci aktualni batoh hrace
     *
     * @ return aktualni batoh
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi
     * prostory
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        upozorniPozorovatele();
    }

    /**
     * Metoda nastaví aktuální planetu, používá se nejčastěji při přechodu mezi
     * planetama
     *
     * @param planet nova aktuální planeta
     */
    public void setPlanet(Planeta planet) {
        this.planet = planet;
         upozorniPozorovatele();
    }

    public boolean jeVyhra() {
        return getInventory().isInInventory("Lightsaber"); // hra konci kdyz hrac dostane do batohu lightsaber
    }

    public void zaregistrujPozorovatele(ObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }

    public void odregistrujPozorovatele(ObserverZmenyProstoru pozorovatel) {
        seznamPozorovatelu.remove(pozorovatel);
    }

    public void upozorniPozorovatele() {
        for (ObserverZmenyProstoru pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
    }

}
