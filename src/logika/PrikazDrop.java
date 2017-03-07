/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy prikazDrop představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazDrop implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================

    private static final String NAZEV = "drop";
    private HerniPlan herniPlan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  
     *   @param herniPlan herní plán, ve kterém se bude ve hře "chodit" 
     */
    public PrikazDrop(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }
    
    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     *  Provádí příkaz "drop". Pokud v batohu věc není vypíše chybovou hlášku.
     *  Jinak věc odhodí do aktuálního prostoru. Lze zahodit jen jednu věc
     *  najednou.
     *
     *  @param  parametry   -jako  parametr se zadává jméno odhazované věci
     *  @return             zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Which item you want me to drop here?";
        }

        String itemName = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Inventory inventory = herniPlan.getInventory();
        Vec deleted = inventory.getItem(itemName);

        if (deleted == null) {
            // pokud mazana věc není v batohu.
            return "I don't have this item";            
        }
        else {
            // pokud je věc smazána z batohu, přesune se do aktualního prostoru
            deleted = inventory.dropItem(itemName);
            aktualniProstor.vlozVec(deleted);
            return "I have droped here " + itemName;
        }
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