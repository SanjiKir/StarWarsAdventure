/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import logika.Planeta;
import logika.Prostor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PlanetaTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PlanetaTest
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
     * Testuje, zda jsou správně nastaveny průchody mezi planety hry. Planety
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {      
        Prostor prostor1 = new Prostor ("","", 200, 50);
        Prostor prostor2 = new Prostor ("","", 200, 50);
        Planeta planeta = new Planeta("Boom1", "boom", prostor1);
        Planeta planeta1 = new Planeta("Boom2", "boom2", prostor2);
        planeta.setExit(planeta1);
        planeta1.setExit(planeta);
        assertEquals(prostor1, planeta.getStartLocation()); 
        assertEquals(prostor2, planeta1.getStartLocation()); 
        assertEquals(planeta, planeta1.getTheClosestPlanet("Boom1"));
        assertEquals(null, planeta1.getTheClosestPlanet("lol"));

    }
}
