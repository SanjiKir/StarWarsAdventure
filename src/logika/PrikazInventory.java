/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazInventory představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazInventory implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================

    private static final String NAME = "inventory";
    private HerniPlan herniPlan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     *  
     *   @param herniPlan herní plán, ve kterém se bude ve hře "chodit" 
     */
    public PrikazInventory(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    
    /**
     *  Provádí příkaz "inventory". Vypíše všechny věci, které jsou v batohu.
     *
     *  @return     zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (herniPlan.getInventory().getItems().isEmpty()) {
            // pokud je batoh prázdný
            return "It's empty";
        }
        else {
            // pokud je v batohu jedna a více věcí.
            return "This is list of things in your inventory:" + herniPlan.getInventory().getItems() + ".";
        }
    }

    /**
     *  Metoda vrací název příkazu
     *  
     *  @ return název příkazu
     */
    @Override
    public String getNazev() {
        return NAME;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
