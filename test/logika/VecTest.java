/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import logika.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída VecTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class VecTest
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
     * Metoda testVec
     *Testuji se veci, jejich prenositelnost
     */
    @Test
    
    public void testVec(){
    Vec vec1 = new Vec("lol", true);
    Vec vec2 = new Vec("lol2", false);
    assertEquals("lol", vec1.getNazev());
    assertEquals("lol2", vec2.getNazev());
    assertTrue(vec1.jePrenositelna());
    assertFalse(vec2.jePrenositelna());
    vec2.setPrenositelnost(true);
    assertTrue(vec2.jePrenositelna());
    }
}
