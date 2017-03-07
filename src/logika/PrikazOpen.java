/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazOpen představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazOpen implements IPrikaz {
    private static final String NAZEV = "open";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, je potřeba zjistit, zda jsem v místnosti
     *                    vedle místnosti, která se má odemknout
     */    
    public PrikazOpen(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "open". Zjišťuji, zda z aktuální místnosti je
     *  východ do zadané místnosti. Pokud místnost existuje a je zamčená,
     *  tak se zjistí, zda v batohu je potřebný klíč. Pokud ano, odemknu
     *  místnost.
     *
     *@param parametry - jako  parametr obsahuje jméno místnosti,
     *                         do které se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední místnost), tak ....
            return "What should I open?";
        }

        String mistnost = parametry[0];
        Prostor sousedniMistnost = plan.getAktualniProstor().vratSousedniProstor(mistnost);
        if (sousedniMistnost == null) {
            return "There is no doors to "+mistnost+" !";
        }

        if (parametry.length == 1) {
            // pokud chybí druhé slovo (sousední místnost), tak ....
            return "You should enter a password";
        }

        String key = parametry[1];
        // hledám zadanou místnost mezi východy

        
        if (sousedniMistnost.ifClosed()) {
            if (key.equals(sousedniMistnost.getKey())) {
                sousedniMistnost.close(false);
                return "You have opened the doors to "
                + mistnost + ". Go ahead";
            }
            else {
                return "If u want to open "+mistnost+" you need to know the password";
            }
        }
        else {
            return "The location "+mistnost+" has already been opened";
        }

    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
