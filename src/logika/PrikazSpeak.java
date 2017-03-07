/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.Scanner;

/*******************************************************************************
 * Instance třídy Speak představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazSpeak implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "speak";
    private HerniPlan plan;
    private Hra hra;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazSpeak(HerniPlan plan, Hra hra)
    {
        this.plan = plan;
        this.hra = hra;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /** Vraci rec v zavislosti na stavu boolean promenych v tride Person 
     * Kontrolujeme vstupni parametr, kdyz neni vraci string aby clovek zadal Personu
     * Hledame osobu v prostoru, pokud tam neni vraci ze tato osoba tam neni
     * Kontroluejeme jestli osoba je ziva, pokud neni vracim ze je mrtva
     * Kontrolujeme jestli osoba uz ma co chtele, pokud ma tak vracim string ze uz nic neche
     * Kontroluejme jestli s osobou lze mluvit, pak kontrolujeme jestli uz na ni se obraceli, pokud ano tak vracime jine moznosti odpovedet
     * Kontroluji se odpovedi hraca, da se odvpoedet dobre a nebo zabit osobu
     * Pri zabiti osoby kontroluejeme jestli neco pada pri jeji smrti, pokud ano pridavama predmet do mistnosti.
     * Pri zabiti osoby zmensujme pocet points.
     * Pri dobre odpovedi naopak zvetsujeme.
     * Take se tady kontrolue finalni hovor, kdyz  osoba je ancient_droid tak konverzace bude vypadat jinak.
     * Jeji vysledkem muze byt a nebo viteztvi a nebo smrt hrace. 
     * @param personName - jmeno persony se kterou chceme mluvit. 
     * @return              zprava, kterou vrati hra hrace. 
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "You want me to speak with a space?";
        }

        String  personName = parametry[0];
        /*
          if (parametry.length == 1) {
            // pokud chybí druhé slovo (sousední místnost), tak ....
            return "You should enter a password";
        }

        String answer = parametry[1];
        */
        
        
        Prostor aktualni = plan.getAktualniProstor();
        Inventory inventory = plan.getInventory();
        Person person = aktualni.findPerson(personName);

        if(person == null)
        {
            return "There is no characters with name " + personName + " here";
        }
        
            if (person.getAlive()){
                return "The person is dead";}

            if (person.getExchange()){
                return "I'm done with you! See ya!";
            }
            if (person.getAnswerable()){
                if (person.getJizMluvil()){
                    return "You have already talked to me, bring me what I want!" + 
                    "\n 1. Go" +
                    "\n 2. Kill him";
                }
                else{
                    if (person.getName().equals("Ancient_droid")){
                        return person.getDialogue();
                       }
                        person.setJizMluvil(true);
                    return person.getDialogue() + "\n" + "You could choose your answer:" + "\n" +
                        "1. " +  person.getLightAnswer() + "\n" +
                        "2. " + person.getDarkAnswer() + "\n" +
                        "type answer + name of the person + number of your answer";
                } 
            }
            
        else {return person.getDialogue();}
        
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}