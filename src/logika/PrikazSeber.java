/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "take";
    private HerniPlan plan;
    private Inventory inventory;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazSeber(HerniPlan plan)
    {
        this.plan = plan;
        this.inventory = plan.getInventory();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
     /**
     * Metoda zpracovává sebrání věci - nesebere ji, pokud tam není, nebo 
     * je-li batoh plný
     * 
     * @param   parametry   sbíraná věc
     * @return              zápis o tom, zda byla věc sebrána
     */  
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "What should I pick up?";
        }

        String nazevSbiraneho = parametry[0];

      
        Prostor aktualni = plan.getAktualniProstor();
        Vec sbirana = aktualni.odeberVec(nazevSbiraneho);
        
        if(sbirana == null)
        {
            return  "There is no " + nazevSbiraneho + " here";
        }
        else
        {
            if(sbirana.jePrenositelna())
            {
               return inventory.putItem(sbirana);
                
            }
            else
            {
                aktualni.vlozVec(sbirana);
                return "It's too heavy for you";
            }
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
