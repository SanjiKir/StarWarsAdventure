/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import logika.Inventory;
import logika.Vec;
import logika.HerniPlan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída InventoryTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class InventoryTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }
    
    /**
     * Metoda testInventory
     * Testuje se vkladani veci do batohu a vyhozovani veci z batohu
     * 
     */
    @Test
    public void testInventory(){
        HerniPlan plan = new HerniPlan();
    Inventory inventory = new Inventory(plan);
    Vec vec1 = new Vec("lol", true);
    Vec vec2 = new Vec("lol1", true);
    assertEquals("You have picked the Item up", inventory.putItem(vec1));
    assertTrue(inventory.isInInventory("lol"));
    assertFalse(inventory.isInInventory("lol1"));
    assertEquals("You have picked the Item up", inventory.putItem(vec2));
    assertTrue(inventory.isInInventory("lol"));
    assertTrue(inventory.isInInventory("lol1"));
    assertEquals(" lol, lol1", inventory.getItems());
    assertEquals(null, inventory.getItem("lol2"));
    assertEquals(vec2, inventory.getItem("lol1"));
    assertEquals(vec2, inventory.dropItem("lol1"));
    assertFalse(inventory.isInInventory("lol1"));
    assertEquals(" lol", inventory.getItems());
    }
}
