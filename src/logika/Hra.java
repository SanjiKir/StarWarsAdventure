package logika;

import java.util.ArrayList;
import java.util.List;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2015/2016
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazLeti(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInventory(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazDrop(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOpen(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSpeak(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazGive(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKill(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazAnswer(herniPlan, this));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Hello and welcome to the Star Wars game!\n" +
        "Your game character is one of the last Jedi and he had just learned about it.\n" +
        "He doesn’t know how to use his power yet, and he doesn’t have a lightsaber.\n" +
        "Your main goal is to find the lightsaber.\n" +
        "This is the last lightsaber in the universe, if someone else find it, the world will be doomed.\n" +
        "\n" + "If you need any help to orienteer in the game please type “help” " + "\n"+"into the game console. Have fun and may the force be with you  ." +
        "\n" + "\n" + herniPlan.getPlanet().longDescription() + "\n" + herniPlan.getAktualniProstor().dlouhyPopis() ;

    }

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        if(herniPlan.jeVyhra())
        {
            return "You won";
        }
        return "Game Over";
    }

    /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {

        return konecHry;

    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            if(herniPlan.jeVyhra())
            {
                konecHry = true;
            }
        }
        else {
            textKVypsani="This command doesn't exist";
        }
        return textKVypsani;
    }

    /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    public List<String> getPrikazy(){
        String prikazy = platnePrikazy.vratNazvyPrikazu();
        List<String> seznamPrikazu = new ArrayList<>();
        String[] words = prikazy.split("\\s+");
       for (int i=0; i<words.length; i++){
           seznamPrikazu.add(words[i]);
       }
       return seznamPrikazu;
    }
    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }

}

