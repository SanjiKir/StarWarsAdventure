/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.List;
import java.util.ArrayList;

/*******************************************************************************
 * Instance třídy Inventory představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Inventory
{
    //== Datové atributy (statické i instancí)======================================

    private static final int MAX_ITEMS = 10;    // Maximální počet věcí v batohu
    private List<Vec> content;            // Seznam věcí v batohu
    private HerniPlan plan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Inventory(HerniPlan plan) {
        content = new ArrayList<Vec>();
        this.plan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     * Metoda pro vložení věci.
     * 
     * @param   item    vkládaná věc
     * @return          zpravu kterou vrati hra hrace
     */
    public String putItem(Vec item) {
        if (spaceLeft()) {          
            content.add(item);
            plan.upozorniPozorovatele();
            return  "You have picked the Item up";
        }
        return "Your inventory is full";
    }

    /**
     * Zjišťuje, zda je v batohu ještě místo.
     * 
     * @return  true   -pokud místo je.
     */
    public boolean spaceLeft() {
        if (content.size() < MAX_ITEMS) {
            return true;   
        }        
        return false;
    }

    /**
     * Zjišťuje, zda je věc v batohu.
     *  
     * @param   lookingItem    lookingItem vec
     * @return  true       pokud se věc v batohu nachází, jinak false
     */
    public boolean isInInventory(String lookingItem) {
        for (Vec i: content) {
            if (i.getNazev().equals(lookingItem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vrací seznam věcí v batohu
     * 
     * @return   list     grafický výpis věcí v batohu
     */
    public String getItems() {
        String list = "";
        for (Vec i: content) {
            if (!list.equals("")) {
                //pro větší přehlednost dáme čárku
                list += ",";
            }
            list += " " + i.getNazev();
        }
        return list;
    }

    /**
     * Metoda najde věc, na kterou chceme odkázat
     * 
     * @param name      -název věci, kterou chceme najít
     * @return lookingItem   -odkaz na nalezenou věc. Je null, pokud věc nebyla nalezena
     */
    public Vec getItem(String name) {
        Vec lookingItem = null;
        for (Vec i: content) {
            if(i.getNazev().equals(name)) {
                lookingItem = i;
                break;
            }
        }
        return lookingItem;
    }
    
    public List<Vec> getInventoryList() {
       return content;
    }
    /**
     * Odstrani veci z inventare
     * 
     * @param   name      odstraňovaná věc
     * @return  deleted     odstraněná věc. Je null, pokud věc nebyla v batohu nalezena
     */
    public Vec dropItem (String name) {
        Vec deleted = null;
        for(Vec i: content) {
            if(i.getNazev().equals(name)) {
                deleted = i;
                content.remove(i);
                plan.upozorniPozorovatele();
                break;
            }
        }
        return deleted;
    }

    /**
     * Metoda vrací maximální počet věcí, které lze přidat do batohu.
     * 
     * @return  -počet věcí
     */
    public int getMaxItems() {
        return MAX_ITEMS;
    }

    

}
