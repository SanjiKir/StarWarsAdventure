/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazGive představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazGive implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "give";
    private HerniPlan plan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazGive(HerniPlan plan)
    {
        this.plan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     *  Provádí příkaz "give". Pokud v batohu věc není vypíše chybovou hlášku.
     *  Pokud osoba neexistu vypise chybovou hlasku. 
     *  Jinak věc odhodí do osoby. Lze dat jen jednu věc
     *  najednou.
     *
     *  @param  parametry   -jako  parametr se zadává jméno davane věci
     *  @return             zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "To should I give something?";
        }

        String  personName = parametry[0];
        Prostor aktualni = plan.getAktualniProstor();
        Person person = aktualni.findPerson(personName);
        
        if (person==null){
            return "There is no characters with name " + personName + " here";}
            
         if (person.getAlive()){
                return "The person is dead";}
                
        if (parametry.length == 1) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "What should I give to " + personName;
        }
        
        String givenThing = parametry[1];

      
        Inventory inventory = plan.getInventory();
        
        Vec forExchange = inventory.getItem(givenThing);

        if(person == null)
        {
            return "There is no characters with name " + personName + " here";
        }
        if (forExchange == null){
            return "I don't have this item in my inventory";
        }
        
        if (person.getExchange()){
         return "I'm done with you. See ya!";
        }
        
        String answerForExchange = person.exchange(forExchange);
        if (person.getExchange()){
        person.setDrop(forExchange);
        inventory.dropItem(givenThing);
        }
       // System.out.println(person.exchange(forExchange));
        return answerForExchange
                + "\n Come back later!";
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